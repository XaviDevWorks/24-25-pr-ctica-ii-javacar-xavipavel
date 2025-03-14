package org.JavaCar;

public class Moto extends Vehicle{
    int cilindrada;
    public Moto(String Matr, String Marca, String Model, double preu, int cilin, Motor Motor,Roda[] Rodes){
        super(Matr,Marca,Model,preu,Motor,Rodes);
        this.cilindrada = cilin;
    }
}
