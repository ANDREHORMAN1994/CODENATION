package classesAbstratas;

import java.io.Console;

import static javafx.scene.input.KeyCode.M;

public class ExibirInformações {

    public static void main(String[] args) {
        Programador objProgramador = new Programador();
        Designer objDesigner = new Designer();

        objProgramador.Salario = 1300.00;
        System.out.println("Calculando Reajuste Programador");
        objProgramador.Reajustar();
        System.out.println("Novo salário: " + objProgramador.Salario + "\n");

        objDesigner.Salario = 1100.00;
        System.out.println("Calculando Reajuste Designer");
        objDesigner.Reajustar();
        System.out.println("Novo salário: " + objDesigner.Salario + "\n");
    }

}
