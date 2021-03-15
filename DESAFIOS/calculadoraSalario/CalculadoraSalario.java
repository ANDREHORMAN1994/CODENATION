package calculadoraSalario;
//Use o Math.round apenas no final do método para arredondar o valor final.
//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-

public class CalculadoraSalario {

    public static long calcularSalarioLiquido(double salarioBase) {
        double inss = calcularInss(salarioBase);
        double irrf = calcularIrrf(salarioBase - inss);
        double liquido = salarioBase - inss - irrf;
        if (liquido < 1039.00) return 0;
        return Math.round(liquido);
    }

    private static double calcularInss(double salarioBase) {
        double inss = 0;

        if (salarioBase <= 1500.00) {
            inss =  ((salarioBase * 8) / 100);
        } else if (salarioBase > 1500.00 && salarioBase <= 4000.00) {
            inss = ((salarioBase * 9) / 100);
        } else if (salarioBase > 4000.00) {
            inss = ((salarioBase * 11) / 100);
        }
        return inss;
    }

    private static double calcularIrrf(double salarioBase) {
        double irrf = 0;

        if (salarioBase > 3000.00 && salarioBase <= 6000.00) {
            irrf = ((salarioBase * 7.5) / 100);
        } else if (salarioBase > 6000.00) {
            irrf = ((salarioBase * 15) / 100);
        }
        return irrf;
    }

    public static void main(String[] args) {
        System.out.println(calcularInss(1038.99));
        System.out.println(calcularIrrf(1038.99));
        System.out.println(calcularSalarioLiquido(6000));
    }

}

/*
INSS
Faixa salarial	Percentual de desconto
Até R$ 1.500,00	8%
De R$ 1.500,01 até R$ 4.000,00	9%
Acima de R$ 4.000,00	11%

IRRF
Faixa salarial	Percentual de desconto
Até R$ 3.000,00	ISENTO
De R$ 3.000,01 até R$ 6.000,00	7.5%
Acima de R$ 6.000,00	15%
 */