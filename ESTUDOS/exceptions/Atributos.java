package exceptions;

import java.util.ArrayList;
import java.util.List;

public class Atributos {

    String nome;
    int idade;
    public List<String> gostos;

    public Atributos(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.gostos = new ArrayList<>();
    }

    public void inserirGostos(String gosto) throws ValidationException {
        if (analisarGosto(gosto)) {
            gostos.add(gosto);
        } else {
            throw new ValidationException(" MESSAGEM DE ERRO: GOSTO " + gosto + " INVÁLIDO");
        }

    }

    public boolean analisarGosto(String gosto) {
        return gosto != null && !gosto.isEmpty() && gosto.length() < 10;
    }
}
