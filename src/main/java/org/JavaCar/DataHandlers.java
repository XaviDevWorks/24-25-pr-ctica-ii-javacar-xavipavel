package org.JavaCar;

import javax.swing.colorchooser.ColorChooserComponentFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * La clase {@code DataHandlers} se encarga de gestionar la persistencia de datos de vehículos
 * y la autenticación de usuarios. Proporciona métodos para guardar y cargar el stock de vehículos,
 * así como para registrar y autenticar usuarios.
 *
 * <p>Adicionalmente, ofrece métodos estáticos para crear instancias de {@code Furgoneta}, {@code Cotxe} y {@code Moto}
 * mediante la entrada de datos por consola.</p>
 */
public class DataHandlers {
    /** Ruta del archivo donde se almacena el stock de vehículos. */
    String projectURL = "src/main/java/org/JavaCar/carStock.txt";

    /** Ruta del archivo de autenticación de usuarios. */
    String auth = "src/main/java/org/JavaCar/auth.txt";

    /** Objeto Scanner para leer la entrada por consola. */
    static Scanner input = new Scanner(System.in);

    /**
     * Guarda en un archivo el listado de vehículos pasados como parámetro.
     * Si el archivo no existe, se crea. En caso de que ya exista, se elimina y se vuelve a crear.
     *
     * <p>Para cada vehículo, se escribe en el archivo su tipo, sus atributos principales y los atributos
     * específicos de cada subclase (Furgoneta, Cotxe, Moto).</p>
     *
     * @param vehicle Lista de objetos {@code Vehicle} a guardar.
     */
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
                        writer.write("Capacitat MAX:" + furgoneta.getCapacitatCarga());
                        writer.newLine();
                    } else if (i instanceof Cotxe){
                        Cotxe cotxe = (Cotxe) i;
                        writer.write("Vehicle: Cotxe");
                        writer.newLine();
                        writer.write(i.printVehicle());
                        writer.write("Nº Plaçes:" + cotxe.getNombrePlaces());
                        writer.newLine();
                    } else if(i instanceof Moto){
                        Moto moto = (Moto) i;
                        writer.write("Vehicle: Moto");
                        writer.newLine();
                        writer.write(i.printVehicle());
                        writer.write("Cilindrada:" + moto.getCilindrada());
                        writer.newLine();
                    }
                    writer.newLine();
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
                }
            }
        } else {
            cochesStock.delete();
            saveStock(vehicle);
        }
    }

    /**
     * Carga el stock de vehículos almacenado en el archivo y los devuelve como una lista.
     *
     * <p>El método lee el archivo línea a línea, parsea la información de cada vehículo
     * y reconstruye los objetos correspondientes según el tipo (Furgoneta, Cotxe o Moto).</p>
     *
     * @return Lista de objetos {@code Vehicle} reconstruidos a partir del archivo.
     */
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

                    // Reiniciar variables para el siguiente vehículo
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

    /**
     * Registra un nuevo usuario solicitando el nombre de usuario y contraseña por consola.
     *
     * <p>El método escribe el nombre de usuario y el hash de la contraseña en el archivo de autenticación.
     * Si el archivo ya existe, se agregan los nuevos registros; en caso contrario, se muestra un mensaje informativo.</p>
     */
    public void RegisterUser(){
        System.out.println("Register Triggered");
        File registration = new File(auth);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(registration, true))) {
            if (!registration.exists()){
                System.out.println("Already a users file");
            } else if (registration.exists()){
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
                        writer.write(username + "|" + passwd1.hashCode());
                        writer.newLine();
                        registrationloop = false;
                    } else {
                        System.out.println("User or password are wrong");
                    }
                }
            }
            writer.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Realiza el proceso de autenticación de usuario.
     *
     * <p>Solicita el nombre de usuario y la contraseña por consola y verifica las credenciales comparándolas
     * con las almacenadas en el archivo de autenticación. Si el usuario se encuentra, retorna un arreglo
     * con {@code true} y el nombre del usuario; de lo contrario, retorna {@code false} y una cadena vacía.</p>
     *
     * @return Un arreglo de {@code Object} donde el primer elemento es un {@code Boolean} que indica si el usuario fue encontrado,
     *         y el segundo es un {@code String} con el nombre del usuario autenticado.
     */
    public Object[] Login(){
        File registration = new File(auth);
        try (BufferedReader br = new BufferedReader(new FileReader(registration))) {
            if(registration.exists()){
                boolean userfound = false;
                String userLogged = "";
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
                        userLogged = username;
                        break;
                    }
                }
                br.close();
                return new Object[]{userfound, userLogged};
            } else {
                registration.createNewFile();
                Login();
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return new Object[]{false, ""};
    }

    /**
     * Crea y retorna una nueva instancia de {@code Furgoneta} solicitando al usuario los datos por consola.
     *
     * <p>Se solicitan datos generales del vehículo, así como los atributos específicos de una furgoneta,
     * incluyendo la información de cada rueda.</p>
     *
     * @return Una instancia de {@code Furgoneta} con los datos proporcionados por el usuario.
     */
    public static Furgoneta crearFurgoneta() {
        System.out.print("Introduce la matrícula: ");
        String matr = input.nextLine();
        input.reset();
        System.out.print("Introduce la marca: ");
        String marca = input.nextLine();
        input.reset();
        System.out.print("Introduce el modelo: ");
        String model = input.nextLine();
        input.reset();
        System.out.print("Introduce el precio base: ");
        double preu = input.nextDouble();
        input.reset();
        System.out.print("Introduce la capacidad de carga: ");
        int carga = input.nextInt();
        input.nextLine(); // Consumir nueva línea
        input.reset();
        System.out.print("Introduce el tipo de motor: ");
        String tipusMotor = input.nextLine();
        input.reset();
        System.out.print("Introduce la potencia del motor: ");
        int potenciaMotor = input.nextInt();
        input.nextLine(); // Consumir nueva línea
        Motor motor = new Motor(tipusMotor, potenciaMotor);

        Roda[] rodes = new Roda[4];
        for (int i = 0; i < 4; i++) {
            System.out.println("Introduce los datos de la rueda " + (i + 1) + ":");
            System.out.print("Marca: ");
            String marcaRoda = input.nextLine();
            input.reset();
            System.out.print("Diámetro: ");
            double diametreRoda = input.nextDouble();
            input.nextLine(); // Consumir nueva línea
            rodes[i] = new Roda(marcaRoda, diametreRoda);
        }

        return new Furgoneta(matr, marca, model, preu, carga, motor, rodes);
    }

    /**
     * Crea y retorna una nueva instancia de {@code Cotxe} solicitando al usuario los datos por consola.
     *
     * <p>Se solicitan datos generales del vehículo, así como los atributos específicos de un coche,
     * incluyendo la información de cada rueda.</p>
     *
     * @return Una instancia de {@code Cotxe} con los datos proporcionados por el usuario.
     */
    public static Cotxe crearCotxe() {
        System.out.print("Introduce la matrícula: ");
        String matr = input.nextLine();

        System.out.print("Introduce la marca: ");
        String marca = input.nextLine();

        System.out.print("Introduce el modelo: ");
        String model = input.nextLine();

        System.out.print("Introduce el precio base: ");
        double preu = input.nextDouble();

        System.out.print("Introduce el número de plazas: ");
        int places = input.nextInt();
        input.nextLine(); // Consumir nueva línea

        System.out.print("Introduce el tipo de motor: ");
        String tipusMotor = input.nextLine();

        System.out.print("Introduce la potencia del motor: ");
        int potenciaMotor = input.nextInt();
        input.nextLine(); // Consumir nueva línea

        Motor motor = new Motor(tipusMotor, potenciaMotor);

        Roda[] rodes = new Roda[4];
        for (int i = 0; i < 4; i++) {
            System.out.println("Introduce los datos de la rueda " + (i + 1) + ":");
            System.out.print("Marca: ");
            String marcaRoda = input.nextLine();
            System.out.print("Diámetro: ");
            double diametreRoda = input.nextDouble();
            input.nextLine(); // Consumir nueva línea
            rodes[i] = new Roda(marcaRoda, diametreRoda);
        }

        return new Cotxe(matr, marca, model, preu, places, motor, rodes);
    }

    /**
     * Crea y retorna una nueva instancia de {@code Moto} solicitando al usuario los datos por consola.
     *
     * <p>Se solicitan datos generales del vehículo, así como los atributos específicos de una moto,
     * incluyendo la información de cada rueda.</p>
     *
     * @return Una instancia de {@code Moto} con los datos proporcionados por el usuario.
     */
    public static Moto crearMoto() {
        Scanner input = new Scanner(System.in);

        System.out.print("Introduce la matrícula: ");
        String matr = input.nextLine();

        System.out.print("Introduce la marca: ");
        String marca = input.nextLine();

        System.out.print("Introduce el modelo: ");
        String model = input.nextLine();

        System.out.print("Introduce el precio base: ");
        double preu = input.nextDouble();

        System.out.print("Introduce la cilindrada: ");
        int cilindrada = input.nextInt();
        input.nextLine(); // Consumir nueva línea

        System.out.print("Introduce el tipo de motor: ");
        String tipusMotor = input.nextLine();

        System.out.print("Introduce la potencia del motor: ");
        int potenciaMotor = input.nextInt();
        input.nextLine(); // Consumir nueva línea

        Motor motor = new Motor(tipusMotor, potenciaMotor);

        Roda[] rodes = new Roda[2];
        for (int i = 0; i < 2; i++) {
            System.out.println("Introduce los datos de la rueda " + (i + 1) + ":");
            System.out.print("Marca: ");
            String marcaRoda = input.nextLine();
            System.out.print("Diámetro: ");
            double diametreRoda = input.nextDouble();
            input.nextLine(); // Consumir nueva línea
            rodes[i] = new Roda(marcaRoda, diametreRoda);
        }

        return new Moto(matr, marca, model, preu, cilindrada, motor, rodes);
    }

    public static void llistarInventari(List<Vehicle> inventari){
        for(Vehicle v : inventari){
            if (v instanceof Furgoneta){
                Furgoneta furgo = (Furgoneta)v;
                v.printVehicle();
                System.out.println(furgo.getCapacitatCarga());
            }else if (v instanceof Cotxe){
                Cotxe cotxe = (Cotxe) v;
                v.printVehicle();
                System.out.println(cotxe.getNombrePlaces());
            }else if (v instanceof Moto){
                Moto moto = (Moto) v;
                v.printVehicle();
                System.out.println(moto.getCilindrada());
            }
        }
    }

    public static Vehicle searchVehicle(List<Vehicle> inventory,String matr){
        for (Vehicle v : inventory){
            if (v.getMatricula().equals(matr)){
                return v;
            }
        }
        return null;
    }

    public void compra(int option,Vehicle vehicle){
        Furgoneta auxFurgo = null;
        Moto auxMoto = null;
        Cotxe auxCotxe = null;
        int dies=0;
        if (vehicle instanceof Furgoneta){
            auxFurgo = (Furgoneta) vehicle;
        }else if(vehicle instanceof Moto){
            auxMoto = (Moto)vehicle;
        }else if(vehicle instanceof Cotxe){
            auxCotxe = (Cotxe)vehicle;
        }

        if (option == 1){ //comprar
            System.out.println("ESPECIFICACIONS DEL VEHICLE\n\n\n");
            vehicle.printVehicle();
            if (auxFurgo == null){
                System.out.println("Capacitat MAX: "+auxFurgo.getCapacitatCarga());
            }else if (auxMoto != null){
                System.out.println("Cilindrada: "+auxMoto.getCilindrada());
            }else if (auxCotxe != null){
                System.out.println("Nº Plaçes: "+auxCotxe.getNombrePlaces());
            }
            System.out.println("\n\n\n QUANTITAT A PAGAR:"+ vehicle.getPreuBase());
        }else { // llogar
            try{
                System.out.println("Per cuants dies vols llogar el vehicle?");
                dies = input.nextInt();
            }catch (InputMismatchException e){
                System.out.println("No es un numero");
            }
            System.out.println("ESPECIFICACIONS DEL VEHICLE\n\n\n");
            vehicle.printVehicle();
            if (auxFurgo != null){
                System.out.println("Capacitat MAX: "+auxFurgo.getCapacitatCarga());
                System.out.println("\n\n\n QUANTITAT A PAGAR:"+ auxFurgo.calcularPreu(dies));
            }else if (auxMoto != null){
                System.out.println("Cilindrada: "+auxMoto.getCilindrada());
                System.out.println("\n\n\n QUANTITAT A PAGAR:"+ auxMoto.calcularPreu(dies));
            }else{
                System.out.println("Nº Plaçes: "+auxCotxe.getNombrePlaces());
                System.out.println("\n\n\n QUANTITAT A PAGAR:"+ auxCotxe.calcularPreu(dies));
            }



        }
    }
}
