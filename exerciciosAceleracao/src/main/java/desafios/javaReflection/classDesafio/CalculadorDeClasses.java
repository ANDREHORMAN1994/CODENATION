package desafios.javaReflection.classDesafio;

import desafios.javaReflection.annotations.Somar;
import desafios.javaReflection.annotations.Subtrair;
import desafios.javaReflection.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    public BigDecimal somarAtributos(Object objeto, Class anotacao) {
        BigDecimal result = BigDecimal.ZERO;
        try {
            if (objeto.equals(null)) throw new IllegalAccessException();
            Field[] listaAtributos = objeto.getClass().getDeclaredFields();
            for (Field field : listaAtributos) {
                if (field.getType().equals(BigDecimal.class) && field.isAnnotationPresent(anotacao)) {
                    field.setAccessible(true);
//                    System.out.println("Nome do Atributo: " + field.getName());
//                    System.out.println("Tipo do Atributo: " + field.getType().getTypeName());
//                    System.out.println("Valor do Atributo: " + (BigDecimal) field.get(objeto));
                    result = result.add((BigDecimal) field.get(objeto));
                }
            }
//            System.out.println(result);
            return result;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public BigDecimal somar(Object obj) {
        return somarAtributos(obj, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object obj) {
        return somarAtributos(obj, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object obj) {
        return somar(obj).subtract(subtrair(obj));
    }

    public static void main(String[] args) {
        CalculadorDeClasses calcular = new CalculadorDeClasses();
        Teste teste = new Teste(new BigDecimal(30), new BigDecimal(5), new BigDecimal(20), new BigDecimal(15));

        System.out.println(calcular.somar(teste));
        System.out.println(calcular.subtrair(teste));
        System.out.println(calcular.totalizar(teste));
    }

}
