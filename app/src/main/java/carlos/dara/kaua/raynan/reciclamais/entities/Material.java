package carlos.dara.kaua.raynan.reciclamais.entities;

import java.util.ArrayList;

public class Material {
     int image;
     String nome;
     Double quilos;
     String descricaoMaterial;
     ArrayList<TipoMaterial> tipoMaterial;

     String imageURL;
    public Material(int image, String nome, Double quilos, String descricaoMaterial, ArrayList<TipoMaterial> tipoMaterial) {
        this.image = image;
        this.nome = nome;
        this.quilos = quilos;
        this.descricaoMaterial = descricaoMaterial;
        this.tipoMaterial = tipoMaterial;
    }

    public Material(String image, String nome, Double quilos, String descricaoMaterial, ArrayList<TipoMaterial> tipoMaterial) {
        this.imageURL = image;
        this.nome = nome;
        this.quilos = quilos;
        this.descricaoMaterial = descricaoMaterial;
        this.tipoMaterial = tipoMaterial;
    }


}
