package org.JavaCar;

import java.util.List;

public class GestorLloguers {
    public static double calcularIngressosTotals(List<Vehicle> vehicles, int dies) {
        double total = 0;
        for (Vehicle v : vehicles) {
            if (v instanceof Cotxe) {
                total+=v.getPreuBase()*dies;
            }else if (v instanceof Moto){
                if(((Moto) v).getCilindrada() >= 500){
                    total+=(v.getPreuBase()+5)*dies;
                }else{
                    total+=v.getPreuBase()*dies;
                }
            }else if (v instanceof Furgoneta){
                if(((Furgoneta) v).getCapacitatCarga() >= 1000){
                    total+=(v.getPreuBase()+10)*dies;
                }else{
                    total+=v.getPreuBase()*dies;
                }
            }
        }
        return total;
    }
}
