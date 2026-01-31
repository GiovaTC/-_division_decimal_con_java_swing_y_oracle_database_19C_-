package model;

import java.math.BigDecimal;

public class Division {

    private BigDecimal a;
    private BigDecimal b;
    private BigDecimal resultado;

    public Division(BigDecimal a, BigDecimal b, BigDecimal resultado) {
        this.a = a;
        this.b = b;
        this.resultado = resultado;
    }

    public BigDecimal getA() {
        return a;
    }

    public BigDecimal getB() {
        return b;
    }

    public BigDecimal getResultado() {
        return resultado;
    }
}
