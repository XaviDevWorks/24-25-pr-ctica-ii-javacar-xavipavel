package org.JavaCar;

public class Roda {
    private String Marca;
    private double diametre;

    public Roda(String Marca, double diametre){
        this.Marca = Marca;
        this.diametre = diametre;
    }

    public String getMarca(){
        return Marca;
    }
    public double getDiametre(){
        return diametre;
    }
}
