package org.JavaCar;

public abstract class Vehicle {
    String Matricula;
    String Marca;
    String Model;
    double PreuBase;
    Motor Motor;
    int Rodes;
    String EtiquetaAmbiental;
    
    public Vehicle(String Matr, String Marca, String Model, double preu, Motor Motor,int Rodes, String etiqueta){
        this.Matricula = Matr;
        this.Marca = Marca;
        this.Model = Model;
        this.PreuBase = preu;
        this.Motor = Motor;
        this.Rodes = Rodes;
        this.EtiquetaAmbiental = etiqueta;

    }
}
