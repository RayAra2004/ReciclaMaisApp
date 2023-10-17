package carlos.dara.kaua.raynan.reciclamais.entities;

import java.math.BigInteger;
import java.util.Date;

public class Usuario {
    String nome;
    Date dataNascimento;
    String CPF;
    String telefone;

    public Usuario(String nome, Date dataNascimento, String CPF, String telefone) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.CPF = CPF;
        this.telefone = telefone;
    }
}
