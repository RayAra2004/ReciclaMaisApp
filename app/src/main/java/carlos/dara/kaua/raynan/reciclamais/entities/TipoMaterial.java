package carlos.dara.kaua.raynan.reciclamais.entities;

public class TipoMaterial {
    int codigo;
    public String nome;
    public TipoMaterial(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public TipoMaterial(String nome){
        this.nome = nome;
    }


}
