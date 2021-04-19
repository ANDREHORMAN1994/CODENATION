package estudos.interfaces.classes;

import estudos.interfaces.Pessoa;

public class Exibir {

    public void exibirInfo(Pessoa pessoa) {
        System.out.println("Seu nome é: " + pessoa.getName());
        System.out.println("Sua idade é: " + pessoa.getAge());
        System.out.println("Seu sonho é: " + pessoa.getDream());
    }

    public static void main(String[] args) {
        Exibir exibir = new Exibir();
        Andre andre = new Andre("Andre Horman", 26, "Ser Feliz");
        Rayane rayane = new Rayane("Rayane Mayara", 32, "Ser Feliz");

        exibir.exibirInfo(andre);
        exibir.exibirInfo(rayane);
    }

}
