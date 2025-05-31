/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import servicios.AsignacionConductorBusServicio;
import servicios.BusesServicio;
import servicios.ConductoresServicio;
import servicios.ReportesServicio;
import servicios.RutasServicio;
import servicios.VentasServicio;

/**
 *
 * @author Esmeralda
 */
public class Main {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Gestion de Autobuses");
            System.out.println("2. Gestion de Rutas");
            System.out.println("3. Gestion de Conductores");
            System.out.println("4. Gestion de Boletos");
            System.out.println("5. Gestion de Reportes");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    menuBuses();
                    break;
                case "2":
                    menuRutas();
                    break;
                case "3":
                    menuConductores();
                    break;
                case "4":
                    menuVentas();
                    break;
                case "5":
                    menuReportes();
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        }
    }

    public static void menuRutas() {
        RutasServicio ruta = new RutasServicio();
        Scanner scanner = new Scanner(System.in);
        Boolean subMenu = true;
        while (subMenu) {
            System.out.println("\n\n====MENU RUTAS======");
            System.out.println("1. Nueva Ruta");
            System.out.println("2. Ver Rutas");
            System.out.println("3. Definir horarios y frecuencia");
            System.out.println("0. Menu Principal");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    ruta.crearRuta();
                    break;
                case "2":
                    ruta.verRutas();
                    break;
                case "3":
                    ruta.ingresarHorario();
                    break;
                case "0":
                    subMenu = false;
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        }
    }

    public static void menuBuses() {
        BusesServicio buses = new BusesServicio();
        Scanner scanner = new Scanner(System.in);
        Boolean subMenu = true;
        while (subMenu) {
            System.out.println("\n\n====MENU BUSES======");
            System.out.println("1. Nuevo bus");
            System.out.println("2. Ver buses activos");
            System.out.println("3. Dar de baja bus");
            System.out.println("4. Modificar Bus");
            System.out.println("5. Asignar bus a ruta");
            System.out.println("0. Menu Principal");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    buses.crearBus();
                    break;
                case "2":
                    buses.verBuses();
                    break;
                case "3":
                    buses.darBaja();
                    break;
                case "4":
                    buses.modificar();
                    break;
                case "5":
                    buses.asignarRuta();
                    break;
                case "0":
                    subMenu = false;
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        }
    }
    
    public static void menuConductores() {
        ConductoresServicio conductores = new ConductoresServicio();
        Scanner scanner = new Scanner(System.in);
        Boolean subMenu = true;
        while (subMenu) {
            System.out.println("\n\n====MENU CONDUCTORES======");
            System.out.println("1. Nuevo conductor");
            System.out.println("2. Ver conductores");
            System.out.println("3. Modificar conductor");
            System.out.println("4. Eliminar conductor");
            System.out.println("5. Asignar bus");
            System.out.println("0. Menu Principal");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    conductores.crearConductor();
                    break;
                case "2":
                    conductores.verConductores();
                    break;
                case "3":
                    conductores.modificarConductor();
                    break;
                case "4":
                    conductores.EliminarConductor();
                    break;
                case "5":
                    conductores.asignarBus();
                    break;
                case "0":
                    subMenu = false;
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        }
    }
    
    public static void menuVentas() {
        VentasServicio ventas = new VentasServicio();
        Scanner scanner = new Scanner(System.in);
        Boolean subMenu = true;
        while (subMenu) {
            System.out.println("\n\n====MENU VENTAS======");
            System.out.println("1. Vender Boleto");
            System.out.println("0. Menu Principal");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    ventas.venderBoleto();
                    break;
                case "0":
                    subMenu = false;
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        }
    }
    
    public static void menuReportes() {
        ReportesServicio reportes = new ReportesServicio();
        Scanner scanner = new Scanner(System.in);
        Boolean subMenu = true;
        while (subMenu) {
            System.out.println("\n\n====MENU VENTAS======");
            System.out.println("1. Reportes de asignacion de conductores");
            System.out.println("2. Reportes de ventas (Hoy)");
            System.out.println("3. Reportes de ventas (Rando de fecha)");
            System.out.println("0. Menu Principal");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    reportes.conductores();
                    break;
                case "2":
                    menuReportesVentasDia();
                    break;
                    
                case "3":
                    menuReportesVentasRango();
                    break;
                case "0":
                    subMenu = false;
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        }
    }
    
    public static void menuReportesVentasDia() {
        ReportesServicio reportes = new ReportesServicio();
        Scanner scanner = new Scanner(System.in);
        Boolean subMenu2 = true;
        while (subMenu2) {
            System.out.println("\n\n====MENU DE VENTAS POR DIA======");
            System.out.println("1. Ventas de bus (hoy)");
            System.out.println("2. Ventas de Ruta (hoy)");
            System.out.println("0. Menu Principal");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    reportes.ventasHoyBus();
                    break;
                case "2":
                    reportes.ventasHoyRuta();
                    break;
                case "0":
                    subMenu2 = false;
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        }
    }
    
    public static void menuReportesVentasRango() {
        ReportesServicio reportes = new ReportesServicio();
        Scanner scanner = new Scanner(System.in);
        Boolean subMenu2 = true;
        while (subMenu2) {
            System.out.println("\n\n====MENU DE VENTAS POR DIA======");
            System.out.println("1. Ventas de bus (Rango de fecha)");
            System.out.println("2. Ventas de Ruta (Rango de fecha)");
            System.out.println("0. Menu Principal");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    reportes.ventasRangoBus();
                    break;
                case "2":
                    reportes.ventasRangoRuta();
                    break;
                case "0":
                    subMenu2 = false;
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        }
    }
}
