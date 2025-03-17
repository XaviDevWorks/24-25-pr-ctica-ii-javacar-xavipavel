package org.JavaCar;

public class Motor {
    private String tipus;
    private int potencia;
    public Motor(String tipus, int potencia){
        this.tipus = tipus;
        this.potencia = potencia;
    }
    public String getTipus(){
        return tipus;
    }
    public int getPotencia(){
        return potencia;
    }
}
