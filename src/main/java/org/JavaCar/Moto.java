package org.JavaCar;

public class Moto extends Vehicle{
    int cilindrada;
    public Moto(String Matr, String Marca, String Model, double preu, Motor Motor,int Rodes, String etiqueta, int places, int cilin){
        super(Matr,Marca,Model,preu,Motor,Rodes,etiqueta,places);
        this.cilindrada = cilin;
    }
}
