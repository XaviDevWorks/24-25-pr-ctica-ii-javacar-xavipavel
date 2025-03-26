package org.JavaCar;

public class Motor {
    private String tipus;
    private int potencia;
    private int normativaEuro;

    public Motor(String tipus, int potencia) {
        this.tipus = tipus;
        this.potencia = potencia;
        this.normativaEuro = calcularNormativaEuro(potencia);
    }

    public String getTipus() {
        return tipus;
    }

    public int getPotencia() {
        return potencia;
    }

    public boolean cumpleNormativaEuro(int normativa) {
        return this.normativaEuro >= normativa;
    }

    private int calcularNormativaEuro(int potencia) {
        if (potencia >= 2014) return 6;
        if (potencia >= 2006) return 4;
        if (potencia >= 2000) return 3;
        return 0;
    }
}
