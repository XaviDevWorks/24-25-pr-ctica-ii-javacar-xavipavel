package org.JavaCar;

public class Roda {
    private String marca;
    private double diametre;

    public Roda(String Marca, double diametre){
        this.marca = Marca;
        this.diametre = diametre;
    }

    public String getMarca(){
        return marca;
    }
    public double getDiametre(){
        return diametre;
    }
}
