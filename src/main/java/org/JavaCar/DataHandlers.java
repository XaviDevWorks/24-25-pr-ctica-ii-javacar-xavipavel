package org.JavaCar;

import java.io.*;
import java.util.List;

public class DataHandlers {
    String projectURL = "src/main/java/org/JavaCar/carStock.txt";

    public void saveStock(List<Vehicle> vehicle) {
        File cochesStock = new File(projectURL);
        if (!cochesStock.exists()) {
            try {
                cochesStock.createNewFile();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
            for (Vehicle i : vehicle) {
                System.out.println("Processing vehicle of type: " + i.getClass().getName());
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(cochesStock, true))) {
                    if(i instanceof Furgoneta){
                        Furgoneta furgoneta = (Furgoneta) i;
                        writer.write("Vehicle: Furgoneta");
                        writer.newLine();
                        writer.write(i.printVehicle());
                        writer.write("Capacitat MAX:"+furgoneta.getCapacitatCarga());
                        writer.newLine();
                    }else if (i instanceof Cotxe){
                        Cotxe cotxe = (Cotxe) i;
                        writer.write("Vehicle: Cotxe");
                        writer.newLine();
                        writer.write(i.printVehicle());
                        writer.write("Nº Plaçes:"+cotxe.getNombrePlaces());
                        writer.newLine();
                    }else if(i instanceof Moto){
                        Moto moto = (Moto) i;
                        writer.write("Vehicle: Moto");
                        writer.newLine();
                        writer.write(i.printVehicle());
                        writer.write("Cilindrada:"+moto.getCilindrada());
                        writer.newLine();
                    }
                    writer.newLine();
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
                }
            }
        }
        else {
            System.out.println("Already a stock registry");
        }

    }

    public void loadStock() {
        File cochesStock = new File(projectURL);
        try (BufferedReader br = new BufferedReader(new FileReader(projectURL))) {
            while(br.readLine() != null){

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}