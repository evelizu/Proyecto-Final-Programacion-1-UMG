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
public class AsignacionConductorBus {
    private String conductor;
    private String bus;
    private String fecha;

    public AsignacionConductorBus() {
    }

    public AsignacionConductorBus(String conductor, String bus, String fecha) {
        this.conductor = conductor;
        this.bus = bus;
        this.fecha = fecha;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
