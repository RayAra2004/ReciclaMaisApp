package carlos.dara.kaua.raynan.reciclamais.entities;

import java.math.BigInteger;
import java.util.ArrayList;

public class PontoColeta {
    String nome;
    int imagem;

    Double nota;
    BigInteger telefone;
    ArrayList<TipoMaterial> materiasReciclados;
    Endereco endereco;
    ArrayList<Comentario> comentarios;

    public PontoColeta(String nome, int imagem, Double nota, BigInteger telefone, ArrayList<TipoMaterial> materiasReciclados, Endereco endereco, ArrayList<Comentario> comentarios) {
        this.nome = nome;
        this.imagem = imagem;
        this.nota = nota;
        this.telefone = telefone;
        this.materiasReciclados = materiasReciclados;
        this.endereco = endereco;
        this.comentarios = comentarios;
    }
}
