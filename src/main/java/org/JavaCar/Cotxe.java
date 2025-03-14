package org.JavaCar;

public class Cotxe extends Vehicle {
    int nombrePlaces;
    public Cotxe(String Matr, String Marca, String Model, double preu, Motor Motor,int Rodes, String etiqueta, int places){
        super(Matr,Marca,Model,preu,Motor,Rodes,etiqueta);
        this.nombrePlaces = places;
    }
}
