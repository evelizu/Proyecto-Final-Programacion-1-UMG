/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Esmeralda
 */
public class VentasModelo {
    private String bus;
    private String ruta;
    private String fecha;
    private String hora;
    private String nombrePasajero;
    private String dpiPasajero;
    private String generoPasajero;
    private Integer totalVendido;

    public VentasModelo() {
    }

    public VentasModelo(String bus, String ruta, String fecha, String hora, String nombrePasajero, String dpiPasajero, String generoPasajero, Integer totalVendido) {
        this.bus = bus;
        this.ruta = ruta;
        this.fecha = fecha;
        this.hora = hora;
        this.nombrePasajero = nombrePasajero;
        this.dpiPasajero = dpiPasajero;
        this.generoPasajero = generoPasajero;
        this.totalVendido = totalVendido;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    public String getDpiPasajero() {
        return dpiPasajero;
    }

    public void setDpiPasajero(String dpiPasajero) {
        this.dpiPasajero = dpiPasajero;
    }

    public String getGeneroPasajero() {
        return generoPasajero;
    }

    public void setGeneroPasajero(String generoPasajero) {
        this.generoPasajero = generoPasajero;
    }

    public Integer getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Integer totalVendido) {
        this.totalVendido = totalVendido;
    }

    
}
