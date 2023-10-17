package carlos.dara.kaua.raynan.reciclamais.entities;

public class Comentario {
    Usuario usuario;
    Integer nota;
    String descricao;

    public Comentario(Usuario usuario, Integer nota, String descricao) {
        this.usuario = usuario;
        this.nota = nota;
        this.descricao = descricao;
    }
}
