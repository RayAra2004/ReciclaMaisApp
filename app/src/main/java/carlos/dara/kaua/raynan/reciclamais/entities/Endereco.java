package carlos.dara.kaua.raynan.reciclamais.entities;

import java.math.BigInteger;

public class Endereco {
    BigInteger cep;
    String tp_logradouro;
    String logradouro;
    Integer numero;
    String estado;
    String cidade;
    String bairro;

    Double lat, lon;

    public Endereco(BigInteger cep, String tp_logradouro, String logradouro, int numero, String estado, String cidade, String bairro, Double lat, Double lon) {
        this.cep = cep;
        this.tp_logradouro = tp_logradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.lat = lat;
        this.lon = lon;
    }
}
