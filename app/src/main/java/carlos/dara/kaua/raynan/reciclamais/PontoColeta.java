package carlos.dara.kaua.raynan.reciclamais;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PontoColeta {
    String nome;
    Double lat, lon;
    Int imagem;
    ArrayList<String> materiasReciclados = new ArrayList<>();

    public PontoColeta(String nome, Double lat, Double lon, Int imagem, ArrayList<String> materiasReciclados) {
        this.nome = nome;
        this.lat = lat;
        this.lon = lon;
        this.imagem = imagem;
        this.materiasReciclados = materiasReciclados;
    }
}
