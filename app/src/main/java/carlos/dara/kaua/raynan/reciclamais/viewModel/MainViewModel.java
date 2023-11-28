package carlos.dara.kaua.raynan.reciclamais.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;

public class MainViewModel extends AndroidViewModel {
    int navigationOpSelected = R.id.homeOP;
    String currentPhotoPath = "";

    public MainViewModel(@NonNull Application application){ super(application);}

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
