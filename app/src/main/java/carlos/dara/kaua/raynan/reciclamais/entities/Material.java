package carlos.dara.kaua.raynan.reciclamais.entities;

import java.util.ArrayList;

public class Material {
     int image;
     String nome;
     Integer quilos;
     String descricaoMaterial;
     ArrayList<TipoMaterial> tipoMaterial;
    public Material(int image, String nome, Integer quilos, String descricaoMaterial, ArrayList<TipoMaterial> tipoMaterial) {
        this.image = image;
        this.nome = nome;
        this.quilos = quilos;
        this.descricaoMaterial = descricaoMaterial;
        this.tipoMaterial = tipoMaterial;
    }


}
