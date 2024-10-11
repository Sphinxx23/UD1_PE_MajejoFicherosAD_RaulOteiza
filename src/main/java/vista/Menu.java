/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.Controlador;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import modelo.Jugador;

/**
 *
 * @author RaulOteiza
 */
public class Menu implements IVista<Jugador> {

    private Controlador controlador;
    private Jugador a;

    //Ruta que tienes que cambiar dependiendo de donde quieres crear la carpeta, 
    //el nombre de la carpeta a crear se pedira antes de la creacion de archivos dentro de ella
    private static final String RUTA = "G:\\2DAM\\AD\\pruebaProyecto\\";

    public Menu() {
        a = new Jugador();
    }

    /**
     * Devuelve el controlador
     * return el controlador asignado como atributo
     */
    public Controlador getControlador() {
        return controlador;
    }

    /**
     * Seattea el controlador
     * param el controlador que queremos settear
     */
    @Override
    public void setControlador(Controlador c) {
        this.controlador = c;
    }

    /**
     * Devuelve el jugador
     * return el jugador asignado como atributo
     */
    @Override
    public Jugador getObject() {
        return a;
    }

    /**
     *Seattea el jugador
     * param el jugador que queremos settear     
     */
    @Override
    public void setObject(Jugador jugador) {
        this.a = a;
    }

    /**
     * Realiza alta o mofificacion de jugador, sirve para ambas
     * return devuelve el jugador a dar de alta o modificar
     */
    private Jugador altaJug() {
        Scanner sc = new Scanner(System.in);
        a = new Jugador();

        boolean b = false;
        int recInt = 0;
        String tamano;

        do {
            System.out.println("ID:");
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;

            }

        } while (b);
        a.setUser_id(recInt);
        b = true;

        do {
            System.out.println("Nick:  (Maximo 8 caracteres)");
            tamano = sc.nextLine();

        } while (tamano.length() > 8);
        a.setNick_name(tamano);

        do {
            System.out.println("Experiencia:");
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;

            }

        } while (b);
        a.setExperience(recInt);
        b = true;

        do {
            System.out.println("Nivel de vida:");
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;

            }

        } while (b);
        a.setLife_level(recInt);
        b = true;

        do {
            System.out.println("Cantidad de monedas");
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;

            }

        } while (b);
        a.setCoins(recInt);

        return a;
    }

    /**
     * Devuelve el jugador que quieres dar de baja o consultar por ID
     * return jugador deseado 
     */
    public Jugador bajaJug() {
        Scanner sc = new Scanner(System.in);
        a = new Jugador();
        boolean b = false;
        int recInt = 0;

        do {
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;
                System.out.println("ID:");
            }

        } while (b);
        a.setUser_id(recInt);

        return a;
    }

    /**
     * Se utiliza para enviar un mensaje en vez de usar un System.out.println();
     * param se le pasa un String con el mensaje a sacar por terminal
     */
    @Override
    public void mensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Realiza un borrado recursivo de un directorio
     * param se le pasa un file con la direccion del directorio a borrar
     */
    public static void borrarArchivos(File carpeta) {

        File[] archivos = carpeta.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {

                    borrarArchivos(archivo);
                }

                archivo.delete();
            }
        }

        carpeta.delete();
    }

    /**
     * Metodo que pide el nombre de la nueva carpeta a crerar y crea la ruta completa para
     *   borrar la carpeta y crearla si ya existe o solo crearla si esta no existe
     * return devuelve un String con la ruta Final 
     */
    @Override
    public String showRut() {
        String config;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre de la carpeta donde se van a crear tus archivos: ");
        config = sc.nextLine();

        String rutaFin = RUTA + config;
        System.out.println("La Ruta final sera:" + rutaFin);

        Path dirPath = Paths.get(rutaFin);

        File f = new File(rutaFin);

        if (Files.exists(dirPath) && f.isDirectory()) {

            borrarArchivos(f);
        }

        f.mkdir();

        return rutaFin;
    }

    /**
     * Metodo para elegir el tipo de archivo a crear en la nueva carpeta
     * return devulve el numero necesario asociado al tipo de archivo
     */
    @Override
    public int showPri() {
        Scanner sc = new Scanner(System.in);
        boolean b = false;
        int recInt = 0;
        controlador.reiniciarLista();

        System.out.println("ELIGE FORMATO DE ARCHIVO:\n 1- Fichero Secuencial de Texto\n 2- Fichero Secuencial Binario\n"
                + " 3- Fichero de objetos Binario\n 4- Fichero de acceso aleatorio Binario\n 5- Fichero de texto XML");

        do {
            System.out.println("Introduce un numero válido:");
            do {
                try {
                    recInt = Integer.parseInt(sc.nextLine());
                    b = false;

                } catch (Exception e) {
                    b = true;
                    System.out.println("Caracter no valido:");
                }

            } while (b);

            b = true;

        } while (recInt <= 0 || recInt > 5);

        return recInt;
    }

    /**
     * Menu que muestra las diferentes acciones posibles y se manda la seleccion al controlador
     * param numero de la configuracion anterior para saber el tipo de archivo y String con la ruta de la carpeta donde crear los archivos
     */
    @Override
    public void show(int num, String rut) {
        Scanner sc = new Scanner(System.in);
        int recogerNum = 0;
        boolean b = false;

        do {
            a = new Jugador();
            System.out.println("\n\nMenu Principal\n Elige que accion quieres realizar:\n 1- ALTA\n 2- BAJA\n "
                    + "3- Modificar\n 4- CONSULTA ID\n 5- LISTAR\n 6- CONFIGURACION \n 7- SALIR");

            do {
                try {
                    recogerNum = Integer.parseInt(sc.nextLine());
                    b = false;

                } catch (Exception e) {
                    b = true;
                    System.out.println("Caracter no valido intente de nuevo");
                }

            } while (b);

            switch (recogerNum) {
                case 1:
                    System.out.println("Introduce los datos del jugador a dar de alta:");
                    a = altaJug();

                    break;
                case 2:
                    System.out.println("Introduce el ID del jugador a dar de baja");
                    a = bajaJug();

                    break;
                case 3:
                    System.out.println("Introduce los datos del jugador a Modificar:");
                    a = altaJug();
                    break;
                case 4:
                    System.out.println("Introduce el ID del jugador a Mostrar");
                    a = bajaJug();
                    break;
                case 5:
                    break;
                case 6:
                    num = showPri();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Numero no valido, tiene que estar comprendido entre el 1-7");
            }

            if (recogerNum > 0 && recogerNum < 6) {
                controlador.operacion(recogerNum);
            }

            controlador.eleccion(num, rut);

        } while (recogerNum != 7);
        System.out.println("Fin de Programa. ");

    }
}
