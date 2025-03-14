package org.JavaCar;

public class Furgoneta extends Vehicle{
    double capacitatCarga;
    public Furgoneta(String Matr, String Marca, String Model, double preu, double Carga, Motor Motor,Roda[] Rodes){
        super(Matr,Marca,Model,preu,Motor,Rodes);
        this.capacitatCarga = Carga;
    }
}
