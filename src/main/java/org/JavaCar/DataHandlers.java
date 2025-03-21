package org.JavaCar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataHandlers {
    String projectURL = "src/main/java/org/JavaCar/carStock.txt";
    String auth = "src/main/java/org/JavaCar/auth.txt";
    Scanner input = new Scanner(System.in);

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

    public List<Vehicle> loadStock() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(projectURL))) {
            String line;
            String type = "", matricula = "", marca = "", model = "";
            double preuBase = 0;
            String tipusMotor = "";
            int potenciaMotor = 0;
            String rodaMarca = "";
            double rodaDiametre = 0;
            int capacitatCarga = 0, nombrePlaces = 0, cilindrada = 0;
            List<Roda> rodes = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(": ");
                if (line.isEmpty()) { // Nueva entrada de vehículo
                    Motor motor = new Motor(tipusMotor, potenciaMotor);
                    Vehicle vehicle = null;

                    switch (type) {
                        case "Furgoneta":
                            vehicle = new Furgoneta(matricula, marca, model, preuBase, capacitatCarga, motor, rodes.toArray(new Roda[0]));
                            break;
                        case "Cotxe":
                            vehicle = new Cotxe(matricula, marca, model, preuBase, nombrePlaces, motor, rodes.toArray(new Roda[0]));
                            break;
                        case "Moto":
                            vehicle = new Moto(matricula, marca, model, preuBase, cilindrada, motor, rodes.toArray(new Roda[0]));
                            break;
                    }

                    if (vehicle != null) {
                        vehicles.add(vehicle);
                    }

                    // Reset variables
                    matricula = ""; marca = ""; model = ""; preuBase = 0;
                    tipusMotor = ""; potenciaMotor = 0; rodes.clear();
                    capacitatCarga = 0; nombrePlaces = 0; cilindrada = 0;
                }

                switch (parts[0].trim()) {
                    case "Vehicle":
                        type = parts[1].trim();
                        break;
                    case "Matricula":
                        matricula = parts[1].trim();
                        break;
                    case "Marca":
                        marca = parts[1].trim();
                        break;
                    case "Model":
                        model = parts[1].trim();
                        break;
                    case "PreuBase":
                        preuBase = Double.parseDouble(parts[1].trim());
                        break;
                    case "Tipus(Motor)":
                        tipusMotor = parts[1].trim();
                        break;
                    case "Potencia(Motor)":
                        potenciaMotor = Integer.parseInt(parts[1].trim());
                        break;
                    case "Roda(marca)":
                        rodaMarca = parts[1].trim();
                        break;
                    case "Roda(Diametre)":
                        rodaDiametre = Double.parseDouble(parts[1].trim());
                        rodes.add(new Roda(rodaMarca, rodaDiametre));
                        break;
                    case "Capacitat MAX":
                        capacitatCarga = Integer.parseInt(parts[1].trim());
                        break;
                    case "Nº Plaçes":
                        nombrePlaces = Integer.parseInt(parts[1].trim());
                        break;
                    case "Cilindrada":
                        cilindrada = Integer.parseInt(parts[1].trim());
                        break;
                }


            }
        } catch (IOException e) {
            System.out.println("Error llegint el fitxer: " + e.getMessage());
        }

        return vehicles;
    }


    public void RegisterUser(){
        System.out.println("Register Triggered");
        File registration = new File(auth);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(registration, true))) {
            if (!registration.exists()){
                System.out.println("Already a users file");
            }else if (registration.exists()){
                boolean registrationloop = true;
                while (registrationloop){
                    System.out.println("Enter a User Name");
                    String username = input.nextLine();
                    System.out.println("Enter "+username+"'s password");
                    String passwd1 = input.nextLine();
                    System.out.println("Repeat the password");
                    String passwd2 = input.nextLine();
                    if (passwd1.equals(passwd2)){
                        System.out.println("Creating user "+ username);
                        //writer.newLine();
                        writer.write(username+"|"+passwd1.hashCode());
                        writer.newLine();
                        registrationloop=false;
                    }else{
                        System.out.println("User or password are wrong");
                    }
                }
            }
            writer.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e){
            System.out.println(e.toString());
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public Object[] Login(){
        File registration = new File(auth);
        try (BufferedReader br = new BufferedReader(new FileReader(registration))) {
            if(registration.exists()){
                boolean userfound = false;
                String userLogged="";
                System.out.println("Enter your UserName");
                String username = input.nextLine();
                System.out.println("Enter your Password");
                String passwd = input.nextLine();
                passwd = String.valueOf(passwd.hashCode());
                String Line;
                while ((Line = br.readLine()) != null){
                    String[] line = Line.split("\\|");
                    String aux = line[1];
                    if (line[0].equals(username) && (aux.equals(passwd))){
                        userfound = true;
                        userLogged=username;
                        break;
                    }

                }
                br.close();
                return new Object[]{userfound,userLogged};
            }else{
                registration.createNewFile();
                Login();
            }

        }catch(FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e){
            System.out.println(e.toString());
        } catch(Exception e){
            System.out.println(e.toString());
        }
        return new Object[]{false,""};
    }
}