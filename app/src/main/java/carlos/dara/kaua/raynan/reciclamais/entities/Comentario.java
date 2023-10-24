package carlos.dara.kaua.raynan.reciclamais.entities;

public class Comentario {
    Usuario usuario;
    String nome;
    Integer nota;
    String descricao;

    int id;

    public Comentario(Usuario usuario, Integer nota, String descricao) {
        this.usuario = usuario;
        this.nota = nota;
        this.descricao = descricao;
    }

    public Comentario(int id, String nome, Integer nota, String descricao){
        this.id = id;
        this.nome = nome;
        this.nota = nota;
        this.descricao = descricao;
    }
}
