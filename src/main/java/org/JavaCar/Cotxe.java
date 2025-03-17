package org.JavaCar;

public class Cotxe extends Vehicle implements Llogable{
    int nombrePlaces;
    public Cotxe(String Matr, String Marca, String Model, double preu, int places, Motor Motor,Roda[] Rodes){
        super(Matr,Marca,Model,preu,Motor,Rodes);
        this.nombrePlaces = places;
    }
    @Override
    public double calcularPreu(int dies){
        return getPreuBase()*dies;
    }
}
