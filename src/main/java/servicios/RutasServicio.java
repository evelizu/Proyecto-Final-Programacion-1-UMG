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
public class RutasServicio {

    private final String filePath;

    public RutasServicio() {
        this.filePath = System.getProperty("user.dir") + File.separator + "rutas.txt";
    }

    Scanner scanner = new Scanner(System.in);

    public void crearRuta() {
        RutasModelo ruta = new RutasModelo();

        System.out.println("\n ----------- RUTA NUEVA ----------: \n");
        System.out.println("Ingrese codigo de ruta:");
        ruta.setCodigo(scanner.nextLine());
                
        System.out.println("Ingrese destino de la ruta:");
        ruta.setDestino(scanner.nextLine());
        
        ruta.setEstado("Activo");
        
       String json = new Gson().toJson(ruta);

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write (json +"\n");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
        
    }
     public List<RutasModelo> verRutas() {
        List<RutasModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("----LISTA DE RUTAS-----");
            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, RutasModelo.class));
            }
            for (RutasModelo objeto : listaObjetos) {
                System.out.println("Codigo: " + objeto.getCodigo() + " - Destino: " + objeto.getDestino()+ " - Horario: " + objeto.getHoraInicio() + "-" +objeto.getHoraFin() + " - Frecuencia: " + objeto.getFrecuencia()+ " - Estado: " + objeto.getEstado());
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
        return listaObjetos;
    }
     
      public List<RutasModelo> retornarRutas() {
        List<RutasModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, RutasModelo.class));
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
    
   public void ingresarHorario() {

        List<RutasModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, RutasModelo.class));
            }

            borrarDoc();

            System.out.println("Ingrese codigo de ruta:");
            String codigo = scanner.nextLine();
            
            String json;

            for (RutasModelo objeto : listaObjetos) {
                if (codigo.equals(objeto.getCodigo())) {
                    System.out.println("Horario Actual: " + objeto.getHoraInicio()+ "-"+ objeto.getHoraFin() + "¿Desea Modificarlo? SI (s) / NO (n)");
                   
                    
                    if(scanner.nextLine().equals("s")){
                        
                        System.out.println("Nueva hora inicio (valores entre 1-24):");
                        objeto.setHoraInicio(scanner.nextLine());
                        
                        System.out.println("Nueva hora final  (valores entre 1-24):");
                        objeto.setHoraFin(scanner.nextLine());
                        
                    }
                     System.out.println("Frecuencia Actual:" + objeto.getFrecuencia()+ "¿Desea Modificarla? SI (s) / NO (n)");
                    if(scanner.nextLine().equals("s")){
                        
                        System.out.println("Nueva Frecuencia (valores entre 1-24):");
                        objeto.setFrecuencia(scanner.nextLine());
                        
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
}
