package carlos.dara.kaua.raynan.reciclamais.entities;

import java.math.BigInteger;
import java.util.ArrayList;

public class PontoColeta {
    public String nome;
    int imagem;
    public String URLimagem;
    public Double nota;
    public BigInteger telefone;
    ArrayList<TipoMaterial> materiasReciclados;
    Endereco endereco;
    ArrayList<Comentario> comentarios;
    public String materiaisRecicladosBD;
    public Double distancia;
    public int id;

    public PontoColeta(String nome, int imagem, Double nota, BigInteger telefone, ArrayList<TipoMaterial> materiasReciclados, Endereco endereco, ArrayList<Comentario> comentarios) {
        this.nome = nome;
        this.imagem = imagem;
        this.nota = nota;
        this.telefone = telefone;
        this.materiasReciclados = materiasReciclados;
        this.endereco = endereco;
        this.comentarios = comentarios;
    }

    public PontoColeta(int id, String nome, String imagem, Double distancia, String materiasRecicladosBD) {
        this.id = id;
        this.nome = nome;
        this.URLimagem = imagem;
        this.distancia = distancia;
        this.materiaisRecicladosBD = materiasRecicladosBD;
    }

    public PontoColeta(String URLimagem, ArrayList<TipoMaterial> materiasReciclados, Double distancia, int id) {
        this.URLimagem = URLimagem;
        this.materiasReciclados = materiasReciclados;
        this.distancia = distancia;
        this.id = id;
    }

    public PontoColeta(Double nota, String URLimagem, String nome, Endereco endereco, BigInteger telefone, ArrayList<Comentario> comentarios){
        this.nota = nota;
        this.URLimagem = URLimagem;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.comentarios = comentarios;
    }
}
