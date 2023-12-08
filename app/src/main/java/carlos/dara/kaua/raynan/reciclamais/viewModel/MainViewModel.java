package carlos.dara.kaua.raynan.reciclamais.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.entities.Endereco;
import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;
import carlos.dara.kaua.raynan.reciclamais.entities.TipoMaterial;
import carlos.dara.kaua.raynan.reciclamais.repository.PontoColetaRepository;
import kotlinx.coroutines.CoroutineScope;

public class MainViewModel extends AndroidViewModel {
    int navigationOpSelected = R.id.homeOP;
    String currentPhotoPath = "";
    LiveData<PagingData<PontoColeta>> pontoColetaLD;

    Double lat, lon;

    public MainViewModel(@NonNull Application application){
        super(application);
    }
    public MainViewModel(@NonNull Application application, double lat, double lon){
        super(application);

        this.lat = lat;
        this.lon = lon;

        PontoColetaRepository pontoColetaRepository = new PontoColetaRepository(getApplication());
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, PontoColeta> pager = new Pager(new PagingConfig(10), () -> new PontoColetaPagingSource(pontoColetaRepository, lat, lon));
        pontoColetaLD = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }

    public LiveData<PagingData<PontoColeta>> getPontosColetaLd() {
        return pontoColetaLD;
    }

    /*ArrayList<PontoColeta> pontos = new ArrayList<>();
    PontoColeta pontoColeta;
    public ArrayList<PontoColeta> getPontos() {
        //PontoColeta p1 = new PontoColeta();
        //pontos.add(p1);
        return pontos;
    }

    public void setPontos(ArrayList<PontoColeta> pontos) {
        this.pontos = pontos;
    }*/

    /* ADICIONAR PONTO VIEW MODEL*/

    public LiveData<Boolean> addPontoColeta(String nome, BigInteger cep, String tipoLogradouro, String logradouro, int numero, String estado, String cidade, String bairro, ArrayList<String> materiaisSelecionados, String imgLocation, String telefone) {

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
                PontoColetaRepository pontoColetaRepository = new PontoColetaRepository(getApplication());
                Endereco endereco = new Endereco(cep, tipoLogradouro, logradouro, numero, estado, cidade, bairro);
                // O método addProduct envia os dados de um novo produto ao servidor. Ele retorna
                // um booleano indicando true caso o produto tenha sido cadastrado e false
                // em caso contrário
                boolean b = pontoColetaRepository.addPontoColeta(nome, materiaisSelecionados, endereco, imgLocation, telefone);

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.postValue(b);
            }
        });

        return result;
    }
    public String getCurrentPhotoPath() {
        return currentPhotoPath;
    }

    public void setCurrentPhotoPath(String currentPhotoPath) {
        this.currentPhotoPath = currentPhotoPath;
    }

    /* NAVEGAÇÃO ENTRE TELAS */
    public int getNavigationOpSelected(){
        return navigationOpSelected;
    }

    public void setNavigationOpSelected(int navigationopSelected) {
        this.navigationOpSelected = navigationopSelected;
    }
}
