package carlos.dara.kaua.raynan.reciclamais.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import carlos.dara.kaua.raynan.reciclamais.entities.Comentario;
import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;
import carlos.dara.kaua.raynan.reciclamais.repository.PontoColetaRepository;
import kotlinx.coroutines.CoroutineScope;

public class DescricaoPontoViewModel extends AndroidViewModel {

    int idPontoColeta;
    LiveData<PagingData<Comentario>> comentarioLD;

    public DescricaoPontoViewModel(@NonNull Application application){
        super(application);
    }
    public DescricaoPontoViewModel(@NonNull Application application, int idPontoColeta){
        super(application);

        PontoColetaRepository pontoColetaRepository = new PontoColetaRepository(getApplication());
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, Comentario> pager = new Pager(new PagingConfig(10), () -> new ComentarioPagingSource(pontoColetaRepository, idPontoColeta));
        comentarioLD = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }

    public LiveData<PagingData<Comentario>> getComentariosLD(){
        return  comentarioLD;
    }

    public LiveData<PontoColeta> getPontoColetaDetailsLD(String pid) {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<PontoColeta> pontoColetaDetailLD = new MutableLiveData<>();

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
                PontoColetaRepository pontoColetaRepository = new PontoColetaRepository(getApplication());

                // O método loadProductDetail obtem os dados detalhados de um produto junto ao servidor.
                // Ele retorna um objeto do tipo Product, que contém os dados detalhados do produto.
                PontoColeta p = pontoColetaRepository.loadPontoColetaDetail(pid);

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                pontoColetaDetailLD.postValue(p);
            }
        });

        return pontoColetaDetailLD;
    }
}
