/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Date;

/**
 *
 * @author Esmeralda
 */
public class RutasModelo {
    
    private String codigo;
    private String destino;
    private String horaInicio;
    private String horaFin;
    private String frecuencia;
    private String estado;

    public RutasModelo() {
    }

    public RutasModelo(String codigo, String destino, String horaInicio, String horaFin, String frecuencia, String estado) {
        this.codigo = codigo;
        this.destino = destino;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.frecuencia = frecuencia;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
 
    
}
