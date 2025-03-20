package org.JavaCar;

public class Cotxe extends Vehicle implements Llogable{
    private int nombrePlaces;
    public Cotxe(String Matr, String Marca, String Model, double preu, int places, Motor Motor,Roda[] Rodes){
        super(Matr,Marca,Model,preu,Motor,Rodes);
        this.nombrePlaces = places;
    }
    public int getNombrePlaces(){
        return nombrePlaces;
    }
    @Override
    public double calcularPreu(int dies){
        return getPreuBase()*dies;
    }
    public String getTipus() {
        return "Cotxe";
    }
    public String printCotxe(){
        String form="Tipus(Vehicle): Cotxe"+
                "Matricula: "+ getMatricula()+"\n" +
                "Marca: "+getMarca()+"\n" +
                "Model: "+getModel()+"\n" +
                "Plaçes: "+nombrePlaces+"\n"+
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
