package estudos.exceptions;

public class Pessoa {

    private static Atributos atributos = new Atributos("André", 26);

    public static void main(String[] args) {
        try {
            atributos.inserirGostos("jogar");
            atributos.inserirGostos("assistir");
            atributos.inserirGostos("estudar");
            atributos.inserirGostos("CONQUISTAR O MUNDO TODO");

            System.out.println(atributos.nome);
            System.out.println(atributos.idade + " anos");
            System.out.print("Meus gostos são: ");
            for ( String gosto : atributos.gostos ) {
                if ( atributos.gostos.get(atributos.gostos.size() - 1) == gosto ) System.out.print(gosto + ";");
                else System.out.print(gosto + ", ");
            }
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
