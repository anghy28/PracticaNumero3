/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author marian
 */
public class Auto {
    private Integer idA;
    private String marca;
    private String modelo;
    private String color;
    private Date anio;
    private double precio;
    private Vendedor vendedor;

    public Auto() {
    }

    public Auto(Integer idA, String marca, String modelo, String color, Date anio, double precio, Vendedor vendedor) {
        this.idA = idA;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.precio = precio;
        this.vendedor = vendedor;
    }


 

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the anio

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the vendedor
     */
    public Vendedor getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * @return the idA
     */
    public Integer getIdA() {
        return idA;
    }

    /**
     * @param idA the idA to set
     */
    public void setIdA(Integer idA) {
        this.idA = idA;
    }

    /**
     * @return the anio
     */
    public Date getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Date anio) {
        this.anio = anio;
    }
    
    
    
}
