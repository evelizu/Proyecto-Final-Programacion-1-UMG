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
import modelos.ConductoresModelo;

/**
 *
 * @author Esmeralda
 */
public class ConductoresServicio {
    
    private final String filePath;

    public ConductoresServicio() {
        this.filePath = System.getProperty("user.dir") + File.separator + "conductores.txt";
    }

    Scanner scanner = new Scanner(System.in);

    public void crearConductor() {
        ConductoresModelo conductor = new ConductoresModelo();

        System.out.println("\n ----------- NUEVO CONDUCTOR ----------: \n");
        System.out.println("Ingrese DPI:");
        conductor.setDpi(scanner.nextLine());
        System.out.println("Ingrese nombre del conductor:");
        conductor.setNombre(scanner.nextLine());
        System.out.println("Ingrese fecha de nacimiento:");
        conductor.setFechaNacimiento(scanner.nextLine());
        System.out.println("Ingrese tipo de licencia:");
        conductor.setTipoLicencia(scanner.nextLine());

        String json = new Gson().toJson(conductor);

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(json + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

    }

    public void verConductores() {
        List<ConductoresModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, ConductoresModelo.class));
            }
            System.out.println("---LISTA DE CONDUCTORES-----");
            for (ConductoresModelo objeto : listaObjetos) {
                System.out.println("DPI: " + objeto.getDpi()+ " - Nombre: " + objeto.getNombre()+ " - Fecha Nacimiento: " + objeto.getFechaNacimiento()+ " - Tipo Licencia: " + objeto.getTipoLicencia()+ " - Bus: " + objeto.getBus());
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    public void borrarDoc() {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    
    public void modificarConductor() {

        List<ConductoresModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, ConductoresModelo.class));
            }

            borrarDoc();

            System.out.println("Ingrese DPI del conductor:");
            String dpi = scanner.nextLine();
            String json;

            for (ConductoresModelo objeto : listaObjetos) {
                if (dpi.equals(objeto.getDpi())) {
                    System.out.println("Nombre: " + objeto.getNombre()+ " ¿Desea Modificarlo? SI (s) / NO (n)");
                    if(scanner.nextLine().equals("s")){
                        System.out.println("Nuevo nombre: ");
                        objeto.setNombre(scanner.nextLine());
                    }
                     System.out.println("Fecha de nacimiento: " + objeto.getFechaNacimiento()+ " ¿Desea Modificarla? SI (s) / NO (n)");
                    if(scanner.nextLine().equals("s")){
                        System.out.println("Nueva fecha de nacimiento: ");
                        objeto.setFechaNacimiento(scanner.nextLine());
                    }  
                    System.out.println("Tipo de licencia: " + objeto.getTipoLicencia()+ " ¿Desea Modificarla? SI (s) / NO (n)");
                    if(scanner.nextLine().equals("s")){
                        System.out.println("Nuevo tipo de licencia: ");
                        objeto.setTipoLicencia(scanner.nextLine());
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
    
    public void EliminarConductor() {

        List<ConductoresModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, ConductoresModelo.class));
            }

            borrarDoc();

            System.out.println("Ingrese DPI del conductor:");
            String dpi = scanner.nextLine();
            String json;

            for (ConductoresModelo objeto : listaObjetos) {
                if (!dpi.equals(objeto.getDpi())) {
                    json = new Gson().toJson(objeto);
                try (FileWriter write = new FileWriter(filePath, true)) {
                    write.write(json + "\n");
                } catch (IOException e) {
                    System.out.println("Error al escribir en el archivo: " + e.getMessage());
                }
                }
                
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }

    }
    
    public void asignarBus() {
        AsignacionConductorBusServicio asignacionServicio = new AsignacionConductorBusServicio();
        List<ConductoresModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, ConductoresModelo.class));
            }

            borrarDoc();

            System.out.println("Ingrese DPI del conductor: ");
            String codigo = scanner.nextLine();
            String json;

            for (ConductoresModelo objeto : listaObjetos) {
                if (codigo.equals(objeto.getDpi())) {
                    System.out.println("Bus actual: " + objeto.getBus()+"\n");
                        System.out.println("Ingrese el codigo del bus a asignar: ");
                        objeto.setBus(scanner.nextLine());
                        System.out.println("Bus asignado correctamente");
                        asignacionServicio.crearAsignacion(objeto.getDpi(), objeto.getBus());
                        
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
