package org.JavaCar;

import java.io.*;
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

    public boolean Login(){
        File registration = new File(auth);
        try (BufferedReader br = new BufferedReader(new FileReader(registration))) {
            if(registration.exists()){
                boolean userfound = false;
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
                        break;
                    }

                }
                br.close();
                return userfound;
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
        return false;
    }
}