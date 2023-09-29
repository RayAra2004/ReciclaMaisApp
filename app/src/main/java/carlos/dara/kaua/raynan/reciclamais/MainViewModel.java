package carlos.dara.kaua.raynan.reciclamais;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    ArrayList<PontoColeta> pontos = new ArrayList<>();
    public ArrayList<PontoColeta> getPontos() {
        PontoColeta p1 = new PontoColeta();
        pontos.add(p1);
        return pontos;
    }




}
