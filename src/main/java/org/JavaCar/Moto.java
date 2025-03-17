package org.JavaCar;

public class Moto extends Vehicle implements Llogable {
    private int cilindrada;
    public Moto(String Matr, String Marca, String Model, double preu, int cilin, Motor Motor,Roda[] Rodes){
        super(Matr,Marca,Model,preu,Motor,Rodes);
        this.cilindrada = cilin;
    }
    public int getCilindrada(){
        return cilindrada;
    }
    @Override
    public double calcularPreu(int dies){
        if(cilindrada >= 500){
            return (getPreuBase()+5)*dies;
        }else{
            return getPreuBase()*dies;
        }
    }
}
