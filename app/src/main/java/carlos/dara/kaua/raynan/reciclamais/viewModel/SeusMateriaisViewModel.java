package carlos.dara.kaua.raynan.reciclamais.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import carlos.dara.kaua.raynan.reciclamais.entities.Material;
import carlos.dara.kaua.raynan.reciclamais.repository.UserRepository;
import kotlinx.coroutines.CoroutineScope;

public class SeusMateriaisViewModel extends AndroidViewModel {

    LiveData<PagingData<Material>> materialLD;
    private UserRepository userRepository;  // Certifique-se de inicializar isso no construtor

    public SeusMateriaisViewModel(@NonNull Application application){
        super(application);

        userRepository = new UserRepository(getApplication());

        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, Material> pager = new Pager(new PagingConfig(10), () -> new MaterialPagingSource(userRepository));
        materialLD = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }


    public LiveData<PagingData<Material>> getMateriaisLD(){
        return  materialLD;
    }

    public LiveData<Boolean> marcarColetado(Integer id) {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {

            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                // Criamos uma instância de ProductsRepository. É dentro dessa classe que estão os
                // métodos que se comunicam com o servidor web.
                UserRepository userRepository = new UserRepository(getApplication());

                // O método login envia os dados de autenticação ao servidor. Ele retorna
                // um booleano indicando true caso o login tenha sido feito com sucesso e false
                // em caso contrário
                boolean b = userRepository.marcarColetado(id);

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.postValue(b);
            }
        });

        return result;
    }
}
