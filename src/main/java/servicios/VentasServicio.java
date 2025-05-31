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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import modelos.BusesModelo;
import modelos.ConductoresModelo;
import modelos.RutasModelo;
import modelos.VentasModelo;

/**
 *
 * @author Esmeralda
 */
public class VentasServicio {

    private final String filePath;
    private final String filePathBuses;

    public VentasServicio() {
        this.filePath = System.getProperty("user.dir") + File.separator + "ventas.txt";
        this.filePathBuses = System.getProperty("user.dir") + File.separator + "buses.txt";
    }

    Scanner scanner = new Scanner(System.in);

    public void venderBoleto() {
        RutasServicio rutaServicio = new RutasServicio();
        List<RutasModelo> ListaRutas = rutaServicio.verRutas();
        RutasModelo rutaSeleccionada = null;

        System.out.println("Ingrese Codigo de ruta de viaje:");
        String codigoRuta = scanner.nextLine();

        for (RutasModelo ruta : ListaRutas) {
            if (codigoRuta.equals(ruta.getCodigo())) {
                rutaSeleccionada = ruta;
                break;
            }
        }
        if (rutaSeleccionada != null) {
            System.out.println("Ruta seleccionada: " + rutaSeleccionada.getDestino());
        } else {
            System.out.println("Codigo de ruta invalido");
        }

        String horarioSeleccionado = seleccionHorarios(rutaSeleccionada);

        BusesModelo busSeleccionado = seleccionarBus(rutaSeleccionada, horarioSeleccionado);
        Integer boletosDisponibles = validarDisponibiliad(horarioSeleccionado, rutaSeleccionada.getCodigo(), busSeleccionado.getPlaca()!=null?busSeleccionado.getPlaca():"0");

        System.out.println("Boletos Disponibles: " + boletosDisponibles);
        if (boletosDisponibles > 0) {
            VentasModelo venta = new VentasModelo();
            System.out.println("-------DATOS DEL BOLETO------");
            venta.setBus(busSeleccionado.getPlaca());
            venta.setRuta(rutaSeleccionada.getCodigo());
            venta.setHora(horarioSeleccionado);
            Date fechaHoy = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaHoyFormateada = formato.format(fechaHoy);
            venta.setFecha(fechaHoyFormateada);

            System.out.println("Ingrese Nombre del Pasajero: ");
            venta.setNombrePasajero(scanner.nextLine());
            System.out.println("Ingrese DPI del Pasajero: ");
            venta.setDpiPasajero(scanner.nextLine());
            System.out.println("Ingrese genero del Pasajero: ");
            venta.setGeneroPasajero(scanner.nextLine());

            String json = new Gson().toJson(venta);

            try (FileWriter writer = new FileWriter(filePath, true)) {
                writer.write(json + "\n");
                System.out.println("Boleto registrado correctamente. \n");
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El bus seleccionado no cuenta con boletos disponibles");
        }

    }

    public BusesModelo seleccionarBus(RutasModelo rutaSeleccionada, String horarioSeleccionado) {
        BusesServicio busesServicio = new BusesServicio();
        List<BusesModelo> ListaBuses = busesServicio.retornarBuses();
        List<BusesModelo> ListaBusesRuta = new ArrayList<>();
        BusesModelo busSeleccionado = null;

        for (BusesModelo bus : ListaBuses) {
            if (rutaSeleccionada.getCodigo().equals(bus.getRuta())) {
                ListaBusesRuta.add(bus);
            }
        }
        System.out.println("----BUSES DISPONIBLES----");
        for (BusesModelo busRuta : ListaBusesRuta) {
            Integer boletoDisponible = validarDisponibiliad(horarioSeleccionado, rutaSeleccionada.getCodigo(), busRuta.getPlaca());
            System.out.println("Placa/Codigo: " + busRuta.getPlaca() + "- Boletos disponibles: " + boletoDisponible);
        }

        System.out.println("Ingrese la Placa/codigo del bus a seleccionar:");
        String placaIngresada = scanner.nextLine();
        for (BusesModelo busRuta : ListaBusesRuta) {
            if (placaIngresada.equals(busRuta.getPlaca())) {
                busSeleccionado = busRuta;
            }
        }
        if (busSeleccionado != null) {
            System.out.println("Bus seleccionado: " + busSeleccionado.getPlaca());
        } else {
            System.out.println("Codigo de bus invalido");
        }

        return busSeleccionado;
    }

    public Integer validarDisponibiliad(String horarioSeleccionado, String codigoRutaSeleccionada, String bus) {
        List<VentasModelo> listaVentas = new ArrayList<>();
        Integer disponibles = 0;
        Date fechaHoy = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaHoyFormateada = formato.format(fechaHoy);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaVentas.add(new Gson().fromJson(linea, VentasModelo.class));
            }
            Integer totalVendidos = 0;
            for (VentasModelo venta : listaVentas) {
                if (venta.getHora().equals(horarioSeleccionado)
                        && venta.getRuta().equals(codigoRutaSeleccionada)
                        && venta.getFecha().equals(fechaHoyFormateada)
                        && venta.getHora().equals(horarioSeleccionado)
                        && venta.getBus().equals(bus)) {

                    totalVendidos++;

                }
            }
            Integer totalCapacidad = calcularTotalCapacidadBusRuta(bus);
            disponibles = totalCapacidad - totalVendidos;

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }

        return disponibles;
    }

    public Integer calcularTotalCapacidadBusRuta(String placaBus) {

        List<BusesModelo> listaBuses = new ArrayList<>();
        Integer totalCapacidad = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathBuses))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaBuses.add(new Gson().fromJson(linea, BusesModelo.class));
            }
            for (BusesModelo bus : listaBuses) {
                if (bus != null && bus.getRuta() != null && bus.getPlaca().equals(placaBus)) {
                    totalCapacidad = Integer.parseInt(bus.getCapacidad());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }

        return totalCapacidad;
    }

    public String seleccionHorarios(RutasModelo rutaSeleccionada) {
        Integer horaInicio = 0;
        Integer horaFin = 0;
        try {
            horaInicio = Integer.parseInt(rutaSeleccionada.getHoraInicio());
            horaFin = Integer.parseInt(rutaSeleccionada.getHoraFin());// Esto generará una excepción
        } catch (NumberFormatException e) {
            System.out.println("Error: la hora de inicio no es un valor valido.");
        }
        List<Integer> horarios = new ArrayList<>();
        Integer indice = 0;
        for (int hora = horaInicio; hora <= horaFin;) {
            System.out.println("opcion " + indice + ": " + hora + ":00 hrs");
            indice++;
            hora = hora + Integer.parseInt(rutaSeleccionada.getFrecuencia());
        }
        System.out.println("Seleccione un horario:");
        String horarioSeleccionado = scanner.nextLine();
        return horarioSeleccionado;
    }

}
