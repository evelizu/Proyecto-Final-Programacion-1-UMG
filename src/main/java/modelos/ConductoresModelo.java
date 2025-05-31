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
public class ConductoresModelo {
    private String dpi;
    private String nombre;
    private String fechaNacimiento;
    private String tipoLicencia;
    private String Bus;

    public ConductoresModelo() {
    }

    public ConductoresModelo(String dpi, String nombre, String fechaNacimiento, String tipoLicencia, String Bus) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoLicencia = tipoLicencia;
        this.Bus = Bus;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public String getBus() {
        return Bus;
    }

    public void setBus(String Bus) {
        this.Bus = Bus;
    }

        
}
