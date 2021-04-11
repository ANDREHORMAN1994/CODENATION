package javaReflection.interfaces;

import java.math.BigDecimal;

public interface Calculavel {

    BigDecimal somar(Object obj) throws AssertionError;

    BigDecimal subtrair(Object obj) throws AssertionError;

    BigDecimal totalizar(Object obj) throws AssertionError;

}
