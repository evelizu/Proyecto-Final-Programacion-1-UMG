/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelos.BusesModelo;
import modelos.RutasModelo;

/**
 *
 * @author Esmeralda
 */
public class BusesServicio {

    private final String filePath;
    private final String filePathRutas;

    public BusesServicio() {
        this.filePath = System.getProperty("user.dir") + File.separator + "buses.txt";
        this.filePathRutas = System.getProperty("user.dir") + File.separator + "rutas.txt";
    }

    Scanner scanner = new Scanner(System.in);

    public void crearBus() {
        BusesModelo bus = new BusesModelo();

        System.out.println("\n ----------- NUEVO BUS ----------: \n");
        System.out.println("Ingrese placa/codigo de bus:");
        bus.setPlaca(scanner.nextLine());
        System.out.println("Ingrese color del bus:");
        bus.setColor(scanner.nextLine());
        System.out.println("Ingrese marca del bus:");
        bus.setMarca(scanner.nextLine());
        System.out.println("Ingrese capacidad del bus:");
        bus.setCapacidad(scanner.nextLine());
        bus.setEstado("Activo");

        String json = new Gson().toJson(bus);

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(json + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

    }

    public List<BusesModelo>  verBuses() {
        List<BusesModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, BusesModelo.class));
            }
            for (BusesModelo objeto : listaObjetos) {
                System.out.println("Placa/Codigo: " + objeto.getPlaca() + " - Marca: " + objeto.getMarca() + " - Color: " + objeto.getColor() + " - Ruta: " + objeto.getRuta()+ " - Estado: " + objeto.getEstado()+ " - Capacidad: " + objeto.getCapacidad());
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
        
        return listaObjetos;
    }
    
    
    public List<BusesModelo>  retornarBuses() {
        List<BusesModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, BusesModelo.class));
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
        
        return listaObjetos;
    }

    public void borrarDoc() {
        try (FileWriter clean = new FileWriter(filePath, false)) {
            clean.write("");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void darBaja() {

        List<BusesModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, BusesModelo.class));
            }

            borrarDoc();

            System.out.println("Ingrese Placa/codigo para dar de baja:");
            String codigo = scanner.nextLine();
            String json;

            for (BusesModelo objeto : listaObjetos) {
                if (codigo.equals(objeto.getPlaca())) {
                    System.out.println("Placa/Codigo: " + objeto.getPlaca() + " - Marca: " + objeto.getMarca() + " - Color: " + objeto.getColor() + " - Estado: " + objeto.getEstado());
                    System.out.println("\n Desea dar de baja el bus seleccionado Si (s) / No (n)");
                    String opcion = scanner.nextLine();
                    if (opcion.equals("s")) {
                        objeto.setEstado("Inactivo");
                        System.out.println("Bus Inactivado");
                    }
                }
                json = new Gson().toJson(objeto);
                try (FileWriter write = new FileWriter(filePath, true)) {
                    write.write(json + "\n");
                } catch (IOException e) {
                    System.out.println("Error al escribir en el archivo: " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }

    }
    
    public void modificar() {

        List<BusesModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, BusesModelo.class));
            }

            borrarDoc();

            System.out.println("Ingrese Placa/codigo a modificar:");
            String codigo = scanner.nextLine();
            String json;
            System.out.println("------LISTA DE BUSES------");
            for (BusesModelo objeto : listaObjetos) {
                if (codigo.equals(objeto.getPlaca())) {
                    System.out.println("Color Actual: " + objeto.getColor() + "¿Desea Modificarlo? SI (s) / NO (n)");
                    if(scanner.nextLine().equals("s")){
                        System.out.println("Nuevo color: ");
                        objeto.setColor(scanner.nextLine());
                    }
                     System.out.println("Marca Actual: " + objeto.getMarca() + "¿Desea Modificarla? SI (s) / NO (n)");
                    if(scanner.nextLine().equals("s")){
                        System.out.println("Nueva Marca: ");
                        objeto.setMarca(scanner.nextLine());
                    } 
                    
                     System.out.println("Capacidad Actual: " + objeto.getMarca() + "¿Desea Modificarla? SI (s) / NO (n)");
                    if(scanner.nextLine().equals("s")){
                        System.out.println("Nueva Capacidad: ");
                        objeto.setCapacidad(scanner.nextLine());
                    }
                }
                json = new Gson().toJson(objeto);
                try (FileWriter write = new FileWriter(filePath, true)) {
                    write.write(json + "\n");
                } catch (IOException e) {
                    System.out.println("Error al escribir en el archivo: " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }

    }
    
    public void asignarRuta() {

        List<BusesModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, BusesModelo.class));
            }

            borrarDoc();

            System.out.println("Ingrese Placa/codigo del bus:");
            String codigo = scanner.nextLine();
            String json;

            for (BusesModelo objeto : listaObjetos) {
                if (codigo.equals(objeto.getPlaca())) {
                    System.out.println("Ruta Actual: " + objeto.getRuta()+"\n");
                        System.out.println("Ingrese el codigo de la nueva ruta: ");
                        objeto.setRuta(scanner.nextLine());
                        System.out.println("Ruta asignada correctamente");
                }
                json = new Gson().toJson(objeto);
                try (FileWriter write = new FileWriter(filePath, true)) {
                    write.write(json + "\n");
                } catch (IOException e) {
                    System.out.println("Error al escribir en el archivo: " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }

    }
}
