/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelos.AsignacionConductorBus;

/**
 *
 * @author Esmeralda
 */
public class AsignacionConductorBusServicio {

    private final String filePath;

    public AsignacionConductorBusServicio() {
        this.filePath = System.getProperty("user.dir") + File.separator + "asignacionconductorbus.txt";
    }

    public void crearAsignacion(String conductor, String bus) {
        AsignacionConductorBus asignacion = new AsignacionConductorBus();

        asignacion.setBus(bus);
        asignacion.setConductor(conductor);
        Date fechaHoy = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaHoyFormateada = formato.format(fechaHoy);
        asignacion.setFecha(fechaHoyFormateada);

        String json = new Gson().toJson(asignacion);

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(json + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
