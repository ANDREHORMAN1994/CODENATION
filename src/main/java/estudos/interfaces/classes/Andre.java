package estudos.interfaces.classes;

import estudos.interfaces.Pessoa;

public class Andre implements Pessoa {

    String nome;
    Integer idade;
    String sonho;

    public Andre(String nome, Integer idade, String sonho) {
        this.nome = nome;
        this.idade = idade;
        this.sonho = sonho;
    }

    @Override
    public void setName(String nome) {
        this.nome = nome;
    }

    @Override
    public void setAge(Integer idade) {
        this.idade = idade;
    }

    @Override
    public void setDream(String sonho) {
        this.sonho = sonho;
    }

    @Override
    public String getName() {
        return this.nome;
    }

    @Override
    public Integer getAge() {
        return this.idade;
    }

    @Override
    public String getDream() {
        return this.sonho;
    }
}
