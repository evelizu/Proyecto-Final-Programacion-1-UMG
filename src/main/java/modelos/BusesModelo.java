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
public class BusesModelo {
    private String placa;
    private String color;
    private String marca;
    private String estado;
    private String ruta;
    private String capacidad;

    public BusesModelo() {
    }

    public BusesModelo(String placa, String color, String marca, String estado, String ruta, String capacidad) {
        this.placa = placa;
        this.color = color;
        this.marca = marca;
        this.estado = estado;
        this.ruta = ruta;
        this.capacidad = capacidad;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    
}
