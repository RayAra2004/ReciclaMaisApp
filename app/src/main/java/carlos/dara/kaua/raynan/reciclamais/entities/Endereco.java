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

    public Endereco(BigInteger cep, String tp_logradouro, String logradouro, int numero, String estado, String cidade, String bairro) {
        this.cep = cep;
        this.tp_logradouro = tp_logradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
    }

    public BigInteger getCep() {
        return cep;
    }

    public String getTp_logradouro() {
        return tp_logradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }
}
