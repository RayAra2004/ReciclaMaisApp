package carlos.dara.kaua.raynan.reciclamais.viewModel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;

public class MainViewModel extends ViewModel {
    ArrayList<PontoColeta> pontos = new ArrayList<>();
    public ArrayList<PontoColeta> getPontos() {
        //PontoColeta p1 = new PontoColeta();
        //pontos.add(p1);
        return pontos;
    }




}