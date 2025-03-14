package org.JavaCar;

public abstract class Vehicle {
    String Matricula;
    String Marca;
    String Model;
    double PreuBase;
    Motor Motor;
    Roda[] Rodes;
    String EtiquetaAmbiental;
    
    public Vehicle(String Matr, String Marca, String Model, double preu,Motor Motor,Roda[] Rodes){
        this.Matricula = Matr;
        this.Marca = Marca;
        this.Model = Model;
        this.PreuBase = preu;
        this.Motor = Motor;
        this.Rodes = Rodes;
        //this.EtiquetaAmbiental = etiqueta;

    }

    public String getMatricula(){
        return Matricula;
    }
    public String getMarca(){
        return Marca;
    }
    public String getModel(){
        return Model;
    }
    public int getPreuBase(){
        return (int) PreuBase;
    }
    public Motor getMotor(){
        return Motor;
    }
    public Roda[] getRodes(){
        return Rodes;
    }
    public double calcularPreu(int dies){
        return this.PreuBase*dies;
    }
}
