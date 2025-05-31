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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import modelos.AsignacionConductorBus;
import modelos.BusesModelo;
import modelos.ConductoresModelo;
import modelos.RutasModelo;
import modelos.VentasModelo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Esmeralda
 */
public class ReportesServicio {

    private final String filePathAsignacion;
    private final String filePathVentas;

    public ReportesServicio() {
        this.filePathAsignacion = System.getProperty("user.dir") + File.separator + "asignacionconductorbus.txt";
        this.filePathVentas = System.getProperty("user.dir") + File.separator + "ventas.txt";
    }

    Scanner scanner = new Scanner(System.in);

    public void conductores() {

        List<AsignacionConductorBus> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathAsignacion))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, AsignacionConductorBus.class));
            }
            System.out.println("Ingrese DPI del conductor: ");
            String dpi = scanner.nextLine();
            System.out.println("Ingrese fecha Inicio: ");
            String fechaInicioTexto = scanner.nextLine();
            System.out.println("Ingrese fecha Fin");
            String fechaFinTexto = scanner.nextLine();

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaInicio = new Date();
            Date fechaFin = new Date();
            try {
                fechaInicio = formato.parse(fechaInicioTexto);
                fechaFin = formato.parse(fechaFinTexto);
            } catch (Exception e) {
                System.out.println("Error al convertir la fecha: " + e.getMessage());
            }

            System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "OFF");
            Workbook workbook = new XSSFWorkbook();
            Sheet hoja = workbook.createSheet("Datos");
            Row fila = hoja.createRow(0); // Fila 0
            Cell celda = fila.createCell(0); // Celda A1
            celda.setCellValue("DPI COnductor");
            celda = fila.createCell(1); // Celda A2
            celda.setCellValue("Placa Bus");
            celda = fila.createCell(2); // Celda A3
            celda.setCellValue("FechaAsignacion");

            System.out.println("---LISTA DE ASIGNACIONES -----");
            Integer indice = 1;
            for (AsignacionConductorBus objeto : listaObjetos) {
                Date fechaasignacion = new Date();
                try {
                    fechaasignacion = formato.parse(objeto.getFecha());
                } catch (Exception e) {
                    System.out.println("Error al convertir la fecha: " + e.getMessage());
                }

                if (objeto.getConductor().equals(dpi) && fechaasignacion.compareTo(fechaInicio) >= 0 && fechaasignacion.compareTo(fechaFin) <= 0) {
                    System.out.println("DPI Conductor: " + objeto.getConductor() + " - Placa Bus: " + objeto.getBus() + " -Fecha Asignacion: " + objeto.getFecha());
                    fila = hoja.createRow(indice); // Fila 0
                    celda = fila.createCell(0); // Celda 1
                    celda.setCellValue(objeto.getConductor());
                    celda = fila.createCell(1); // Celda A2
                    celda.setCellValue(objeto.getBus());
                    celda = fila.createCell(2); // Celda A1
                    celda.setCellValue(objeto.getFecha());

                    indice++;
                }

            }
            System.out.println("Desea generar un excel con el reporte? SI (s) NO (n)");
            String excel = scanner.nextLine();
            if (excel.equals("s")) {
                FileOutputStream fileOut = new FileOutputStream("Reporte Conductores.xlsx");
                workbook.write(fileOut);
                System.out.println("Archivo excel generado exitosamente");
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    public void ventasRangoBus() {

        List<VentasModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathVentas))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, VentasModelo.class));
            }
            System.out.println("Ingrese fecha Inicio: ");
            String fechaInicioTexto = scanner.nextLine();
            System.out.println("Ingrese fecha Fin");
            String fechaFinTexto = scanner.nextLine();

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaInicio = new Date();
            Date fechaFin = new Date();
            try {
                fechaInicio = formato.parse(fechaInicioTexto);
                fechaFin = formato.parse(fechaFinTexto);
            } catch (Exception e) {
                System.out.println("Error al convertir la fecha: " + e.getMessage());
            }

            BusesServicio busesServicio = new BusesServicio();
            List<BusesModelo> ListaBuses = busesServicio.retornarBuses();

            System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "OFF");
            Workbook workbook = new XSSFWorkbook();
            Sheet hoja = workbook.createSheet("Datos");
            Row fila = hoja.createRow(0); // Fila 0
            Cell celda = fila.createCell(0); // Celda A1
            celda.setCellValue("Bus");
            celda = fila.createCell(1); // Celda A1
            celda.setCellValue("Boletos Vendidos");
            System.out.println("----TOTAL VENDIDO POR BUS DEL " + fechaInicioTexto + " AL " + fechaFinTexto + "---");
            Integer indice = 1;
            for (BusesModelo bus : ListaBuses) {
                Integer totalVendido = 0;
                for (VentasModelo venta : listaObjetos) {
                    Date fechaVenta = new Date();
                    try {
                        fechaVenta = formato.parse(venta.getFecha());
                    } catch (Exception e) {
                        System.out.println("Error al convertir la fecha: " + e.getMessage());
                    }

                    if (venta.getBus().equals(bus.getPlaca()) && fechaVenta.compareTo(fechaInicio) >= 0 && fechaVenta.compareTo(fechaFin) <= 0) {
                        totalVendido = totalVendido + 1;
                    }

                }

                System.out.println("Bus: " + bus.getPlaca() + " - Boletos vendidos: " + totalVendido);
                fila = hoja.createRow(indice); // Fila 0
                celda = fila.createCell(0); // Celda A1
                celda.setCellValue(bus.getPlaca());
                celda = fila.createCell(1); // Celda A1
                celda.setCellValue(totalVendido);

                indice++;
            }
            System.out.println("Desea generar un excel con el reporte? SI (s) NO (n)");
            String excel = scanner.nextLine();
            if (excel.equals("s")) {
                FileOutputStream fileOut = new FileOutputStream("Ventas Rango Fecha Bus.xlsx");
                workbook.write(fileOut);
                System.out.println("Archivo excel generado exitosamente");
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    public void ventasRangoRuta() {

        List<VentasModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathVentas))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, VentasModelo.class));
            }
            System.out.println("Ingrese fecha Inicio: ");
            String fechaInicioTexto = scanner.nextLine();
            System.out.println("Ingrese fecha Fin");
            String fechaFinTexto = scanner.nextLine();

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaInicio = new Date();
            Date fechaFin = new Date();
            try {
                fechaInicio = formato.parse(fechaInicioTexto);
                fechaFin = formato.parse(fechaFinTexto);
            } catch (Exception e) {
                System.out.println("Error al convertir la fecha: " + e.getMessage());
            }

            RutasServicio rutasServicio = new RutasServicio();
            List<RutasModelo> ListaRutas = rutasServicio.retornarRutas();
            System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "OFF");
            Workbook workbook = new XSSFWorkbook();
            Sheet hoja = workbook.createSheet("Datos");
            Row fila = hoja.createRow(0); // Fila 0
            Cell celda = fila.createCell(0); // Celda A1
            celda.setCellValue("Codigo Ruta");
            celda = fila.createCell(1); // Celda A1
            celda.setCellValue("Destino");
            celda = fila.createCell(2); // Celda A1
            celda.setCellValue("Boletos vendidos");
            Integer indice = 1;
            System.out.println("----TOTAL VENDIDO POR RUTA---");
            for (RutasModelo ruta : ListaRutas) {
                Integer totalVendido = 0;

                for (VentasModelo venta : listaObjetos) {
                    Date fechaVenta = new Date();
                    try {
                        fechaVenta = formato.parse(venta.getFecha());
                    } catch (Exception e) {
                        System.out.println("Error al convertir la fecha: " + e.getMessage());
                    }

                    if (venta.getRuta().equals(ruta.getCodigo())
                            && fechaVenta.compareTo(fechaInicio) >= 0
                            && fechaVenta.compareTo(fechaFin) <= 0) {

                        totalVendido = totalVendido + 1;
                    }

                }

                System.out.println("Codigo Ruta: " + ruta.getCodigo() + " - Destino: " + ruta.getDestino() + " - Boletos vendidos: " + totalVendido);

                fila = hoja.createRow(indice); // Fila 0
                celda = fila.createCell(0); // Celda A1
                celda.setCellValue(ruta.getCodigo());
                celda = fila.createCell(1); // Celda A1
                celda.setCellValue(ruta.getDestino());
                celda = fila.createCell(2); // Celda A1
                celda.setCellValue(totalVendido);
                indice++;
            }
            System.out.println("Desea generar un excel con el reporte? SI (s) NO (n)");
            String excel = scanner.nextLine();
            if (excel.equals("s")) {
                FileOutputStream fileOut = new FileOutputStream("Ventas Rango Fecha Ruta.xlsx");
                workbook.write(fileOut);
                System.out.println("Archivo excel generado exitosamente");
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    public void ventasHoyBus() {

        List<VentasModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathVentas))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, VentasModelo.class));
            }

            Date fechaHoy = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaHoyFormateada = formato.format(fechaHoy);

            BusesServicio busesServicio = new BusesServicio();
            List<BusesModelo> ListaBuses = busesServicio.retornarBuses();
            System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "OFF");
            Workbook workbook = new XSSFWorkbook();
            Sheet hoja = workbook.createSheet("Datos");
            Row fila = hoja.createRow(0); // Fila 0
            Cell celda = fila.createCell(0); // Celda A1
            celda.setCellValue("Bus");
            celda = fila.createCell(1); // Celda A1
            celda.setCellValue("Boletos vendidos");
            System.out.println("----TOTAL VENDIDO POR BUS---");
            Integer indice = 1;
            for (BusesModelo bus : ListaBuses) {
                Integer totalVendido = 0;
                for (VentasModelo venta : listaObjetos) {

                    if (venta.getBus().equals(bus.getPlaca()) && venta.getFecha().equals(fechaHoyFormateada)) {
                        totalVendido = totalVendido + 1;
                    }

                }

                System.out.println("Bus: " + bus.getPlaca() + " - Boletos vendidos: " + totalVendido);

                fila = hoja.createRow(indice); // Fila 0
                celda = fila.createCell(0); // Celda A1
                celda.setCellValue(bus.getPlaca());
                celda = fila.createCell(1); // Celda A1
                celda.setCellValue(totalVendido);
            }
            System.out.println("Desea generar un excel con el reporte? SI (s) NO (n)");
            String excel = scanner.nextLine();
            if (excel.equals("s")) {
                FileOutputStream fileOut = new FileOutputStream("Ventas Hoy Bus.xlsx");
                workbook.write(fileOut);
                System.out.println("Archivo excel generado exitosamente");
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    public void ventasHoyRuta() {

        List<VentasModelo> listaObjetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathVentas))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaObjetos.add(new Gson().fromJson(linea, VentasModelo.class));
            }

            Date fechaHoy = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaHoyFormateada = formato.format(fechaHoy);

            RutasServicio rutasServicio = new RutasServicio();
            List<RutasModelo> ListaRutas = rutasServicio.retornarRutas();
            System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "OFF");
            Workbook workbook = new XSSFWorkbook();
            Sheet hoja = workbook.createSheet("Datos");
            Row fila = hoja.createRow(0); // Fila 0
            Cell celda = fila.createCell(0); // Celda A1
            celda.setCellValue("Codigo Ruta");
            celda = fila.createCell(1); // Celda A1
            celda.setCellValue("Destino");
            celda = fila.createCell(2); // Celda A1
            celda.setCellValue("Boletos vendidos");
            Integer indice = 1;
            System.out.println("----TOTAL VENDIDO POR RUTA---");
            for (RutasModelo ruta : ListaRutas) {
                Integer totalVendido = 0;
                for (VentasModelo venta : listaObjetos) {

                    if (venta.getRuta().equals(ruta.getCodigo()) && venta.getFecha().equals(fechaHoyFormateada)) {
                        totalVendido = totalVendido + 1;
                    }

                }

                System.out.println("Codigo Ruta: " + ruta.getCodigo() + " - Destino: " + ruta.getDestino() + " - Boletos vendidos: " + totalVendido);

                fila = hoja.createRow(indice); // Fila 0
                celda = fila.createCell(0); // Celda A1
                celda.setCellValue(ruta.getCodigo());
                celda = fila.createCell(1); // Celda A1
                celda.setCellValue(ruta.getDestino());
                celda = fila.createCell(2); // Celda A1
                celda.setCellValue(totalVendido);
                indice++;
            }
            System.out.println("Desea generar un excel con el reporte? SI (s) NO (n)");
            String excel = scanner.nextLine();
            if (excel.equals("s")) {
                FileOutputStream fileOut = new FileOutputStream("Ventas Hoy Ruta.xlsx");
                workbook.write(fileOut);
                System.out.println("Archivo excel generado exitosamente");
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Registre primero algún registro.");
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

}
