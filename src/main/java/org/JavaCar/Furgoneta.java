package org.JavaCar;

public class Furgoneta extends Vehicle implements Llogable {
    private int capacitatCarga;
    public Furgoneta(String Matr, String Marca, String Model, double preu, int Carga, Motor Motor,Roda[] Rodes){
        super(Matr,Marca,Model,preu,Motor,Rodes);
        this.capacitatCarga = Carga;
    }
    public int getCapacitatCarga(){
        return capacitatCarga;
    }
    @Override
    public double calcularPreu(int dies){
        if(capacitatCarga >= 1000){
            return (getPreuBase()+10)*dies;
        }else{
            return getPreuBase()*dies;
        }
    }
}
