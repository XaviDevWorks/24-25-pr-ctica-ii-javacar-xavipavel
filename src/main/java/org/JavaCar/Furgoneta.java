package org.JavaCar;

public class Furgoneta extends Vehicle{
    double capacitatCarga;
    public Furgoneta(String Matr, String Marca, String Model, double preu, Motor Motor,int Rodes, String etiqueta, double Carga){
        super(Matr,Marca,Model,preu,Motor,Rodes,etiqueta);
        this.capacitatCarga = Carga;
    }
}
