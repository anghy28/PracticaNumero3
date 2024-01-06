/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author marian
 */
public class Vendedor extends Persona{
    private Integer idV;
    
    private double sueldo;
    private Auto auto;

    public Vendedor() {
    }

    public Vendedor(Integer idV, double sueldo, Auto auto) {
        this.idV = idV;
        this.sueldo = sueldo;
        this.auto = auto;
    }

    public Vendedor(Integer idV, double sueldo, Auto auto, Integer idP, String ruc, String nombre, String apellido, String direccion, String numeeroTelefono) {
        super(idP, ruc, nombre, apellido, direccion, numeeroTelefono);
        this.idV = idV;
        this.sueldo = sueldo;
        this.auto = auto;
    }



    /**
     * @return the sueldo
     */
    public double getSueldo() {
        return sueldo;
    }

    /**
     * @param sueldo the sueldo to set
     */
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * @return the auto
     */
    public Auto getAuto() {
        return auto;
    }

    /**
     * @param auto the auto to set
     */
    public void setAuto(Auto auto) {
        this.auto = auto;
    }

     
}
