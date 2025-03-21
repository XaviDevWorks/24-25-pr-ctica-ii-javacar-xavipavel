package org.JavaCar;

public abstract class Vehicle implements Llogable {
    protected String matricula;
    protected String marca;
    protected String model;
    protected double PreuBase;
    protected Motor Motor;
    protected Roda[] Rodes;
    protected String EtiquetaAmbiental;
    protected boolean disponibilidad = true;
    
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

    public String printVehicle(){
        String form="Matricula: "+ getMatricula()+"\n" +
                                   "Marca: "+getMarca()+"\n" +
                                   "Model: "+getModel()+"\n" +
                                   "PreuBase: "+getPreuBase()+"\n" +
                                   "Tipus(Motor): "+Motor.getTipus()+"\n" +
                                   "Potencia(Motor): "+Motor.getPotencia()+"\n";
        for (Roda i : Rodes){
            form+="Roda(marca): "+i.getMarca()+"\nRoda(Diametre): "+i.getDiametre()+"\n";
        }
        System.out.println(form);
        return form;
    }
}
