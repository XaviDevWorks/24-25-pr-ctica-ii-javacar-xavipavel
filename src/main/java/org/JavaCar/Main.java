package org.JavaCar;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Furgoneta furgoneta = new Furgoneta("3344MNO", "Mercedes", "Vito", 50, 900, new Motor("Gasolina", 120), new Roda[]{new Roda("Michelin", 17), new Roda("Michelin", 17)});
        Furgoneta furgoneta2 = new Furgoneta("5566PQR", "Iveco", "Daily", 55, 1200, new Motor("Gasolina", 120), new Roda[]{new Roda("Michelin", 17), new Roda("Michelin", 17)});
        Cotxe cotxe1 = new Cotxe("1234ABC", "Toyota", "Corolla", 30, 5, new Motor("Gasolina", 120), new Roda[]{new Roda("Michelin", 17), new Roda("Michelin", 17), new Roda("Michelin", 17), new Roda("Michelin", 17)});
        Moto moti1 = new Moto("5678DEF", "Yamaha", "R6", 25, 600, new Motor("Gasolina", 80), new Roda[]{new Roda("Pirelli", 16), new Roda("Pirelli", 16)});

        List<Vehicle> aux = new ArrayList<>();
        aux.add(furgoneta2);
        aux.add(furgoneta);
        aux.add(cotxe1);
        aux.add(moti1);
        List<Vehicle> sorted = GestorLloguers.filtrarPerPreu(aux,50);
        for(Vehicle i : sorted){
            i.printVehicle();
            System.out.println("");
        }
    }
}