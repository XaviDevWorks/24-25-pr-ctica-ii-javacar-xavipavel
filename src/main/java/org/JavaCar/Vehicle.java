package org.JavaCar;

public abstract class Vehicle implements Llogable {
    String matricula;
    String marca;
    String model;
    double PreuBase;
    Motor Motor;
    Roda[] Rodes;
    String EtiquetaAmbiental;
    
    public Vehicle(String Matr, String Marca, String Model, double preu,Motor Motor,Roda[] Rodes){
        this.matricula = Matr;
        this.marca = Marca;
        this.model = Model;
        this.PreuBase = preu;
        this.Motor = Motor;
        this.Rodes = Rodes;
        //this.EtiquetaAmbiental = etiqueta;

    }

    public String getMatricula(){
        return matricula;
    }
    public String getMarca(){
        return marca;
    }
    public String getModel(){
        return model;
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
