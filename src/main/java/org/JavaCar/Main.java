package org.JavaCar;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static DataHandlers dh = new DataHandlers();
    public static String loggedUser="";
    public static  List<Vehicle> inventory = new ArrayList<>();
    public static void main(String[] args) {
        /*
        DataHandlers dth = new DataHandlers();
        Furgoneta furgoneta = new Furgoneta("3344MNO", "Mercedes", "Vito", 50, 900, new Motor(String.valueOf(tipusVehicle.GASOLINA), 120), new Roda[]{new Roda("Michelin", 17), new Roda("Michelin", 17)});
        Furgoneta furgoneta2 = new Furgoneta("5566PQR", "Iveco", "Daily", 55, 1200, new Motor(String.valueOf(tipusVehicle.GASOLINA), 120), new Roda[]{new Roda("Michelin", 17), new Roda("Michelin", 17)});
        Cotxe cotxe1 = new Cotxe("1234ABC", "Toyota", "Corolla", 30, 5, new Motor(String.valueOf(tipusVehicle.GASOLINA), 120), new Roda[]{new Roda("Michelin", 17), new Roda("Michelin", 17), new Roda("Michelin", 17), new Roda("Michelin", 17)});
        Moto moti1 = new Moto("5678DEF", "Yamaha", "R6", 25, 600, new Motor(String.valueOf(tipusVehicle.GASOLINA), 80), new Roda[]{new Roda("Pirelli", 16), new Roda("Pirelli", 16)});

        List<Vehicle> aux = new ArrayList<>();
        aux.add(furgoneta2);
        aux.add(furgoneta);
        aux.add(cotxe1);
        aux.add(moti1);
        List<Vehicle> sorted = GestorLloguers.filtrarPerPreu(aux,50);
        // Debug: Verify object types
        for (Vehicle vehicle : sorted) {
            System.out.println("Vehicle type: " + vehicle.getClass().getName());
        }
        dth.saveStock(aux);

         */

        List<Vehicle> si = dh.loadStock();
        for(Vehicle v : si){
            inventory.add(v);
        }
        Menu();
    }

    public static void Menu(){
        boolean program = true;
        while(program){
            try{
                System.out.println("Benvingut a JavaCar\n" +
                                           "1-Registrar-se\n" +
                                           "2-Log-in\n" +
                                           "3-Sortir");
                int option = input.nextInt();
                input.reset();
                switch (option){
                    case 1:
                        dh.RegisterUser();
                        Menu();
                        break;
                    case 2:
                        Object[] res = dh.Login();

                        if ((boolean)res[0]){
                            loggedUser=(String)res[1];
                            System.out.println("succesfull Login");
                            System.out.println("Wellecome user "+ (String) res[1]);
                            if(loggedUser.equals("admin")){
                                ADM_logged_Menu();
                            }else{
                                User_logged_Menu();
                            }
                        }else{
                            Menu();
                        }
                        break;
                    case 3:
                        program = false;
                        break;
                    default:
                        System.out.println("Opció no valida");
                }
            }catch (InputMismatchException e){
                System.out.println(e.toString());
            }
        }


    }

    public static void User_logged_Menu(){
        boolean program = true;
        String matr;
        while (program){
            try{
                System.out.println("1-Comprar Vehicle\n" +
                                           "2-Llogar Vehicle\n" +
                                           "3-Sortir");
                int option = input.nextInt();
                input.nextLine();
                switch (option){
                    case 1:
                        dh.llistarInventari(inventory);
                        System.out.println("INTRODUEIX LA MATRICULA DEL VEHICLE:");
                        matr = input.nextLine();
                        Vehicle vehicle = dh.searchVehicle(inventory,matr);
                        dh.compra(1,vehicle);
                        break;
                    case 2:
                        dh.llistarInventari(inventory);
                        System.out.println("INTRODUEIX LA MATRICULA DEL VEHICLE:");
                        matr = input.nextLine();
                        input.reset();
                        Vehicle si = dh.searchVehicle(inventory,matr);
                        dh.compra(2,si);
                        break;
                    case 3:
                        Menu();
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }catch (InputMismatchException e){
                System.out.println("wrong data entered");
            }


        }


    }

    public static void ADM_logged_Menu(){
        boolean program = true;
        while (program){
            try{
                System.out.println("1-Registrar Vehicle\n" +
                                           "2-Revisio de ventes\n" +
                                           "3-Sortir");
                input.reset();
                int option = input.nextInt();
                switch (option){
                    case 1:
                        ADM_vehicle_registre_submenu();
                        break;
                    case 2:
                        break;
                    case 3:
                        Menu();
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }catch (InputMismatchException e){
                System.out.println("wrong data entered");
            }


        }
    }

    public static void ADM_vehicle_registre_submenu(){
        boolean program = true;
        while (program){
            try{
                System.out.println("1-Registrar Furgoneta\n" +
                                           "2-Registrar Cotxe\n" +
                                           "3-Registrar Moto\n" +
                                           "4-Sortir");
                input.reset();
                int option = input.nextInt();
                switch (option){
                    case 1:
                        inventory.add(dh.crearFurgoneta());
                        dh.saveStock(inventory);
                        break;
                    case 2:
                        inventory.add(dh.crearCotxe());
                        dh.saveStock(inventory);
                        break;
                    case 3:
                        inventory.add(dh.crearMoto());
                        dh.saveStock(inventory);
                        break;
                    case 4:

                        ADM_logged_Menu();
                    default:
                        System.out.println("Invalid option");
                }
            }catch (InputMismatchException e){
                System.out.println("wrong data entered");
            }


        }
    }
}