package carlos.dara.kaua.raynan.reciclamais.entities;

import java.util.ArrayList;

public class Material {
     int image;
     public Integer id;
     String nome;
     public String estado;
     Double quilos;
     String descricaoMaterial;
     ArrayList<TipoMaterial> tipoMaterial;
     public String imageURL;

    public Material(int image, String nome, Double quilos, String descricaoMaterial, ArrayList<TipoMaterial> tipoMaterial) {
        this.image = image;
        this.nome = nome;
        this.quilos = quilos;
        this.descricaoMaterial = descricaoMaterial;
        this.tipoMaterial = tipoMaterial;
    }

    public Material(Integer id, String image, String estado) {
        this.id = id;
        this.imageURL = image;
        this.estado = estado;
    }


}
