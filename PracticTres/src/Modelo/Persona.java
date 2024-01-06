/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author marian
 */
public class Persona {
    private Integer idP;
    private String ruc;
    private String nombre;
    private String apellido;
    private String direccion;
    private String numeeroTelefono;
    

    public Persona() {
    }

    public Persona(Integer idP, String ruc, String nombre, String apellido, String direccion, String numeeroTelefono) {
        this.idP = idP;
        this.ruc = ruc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.numeeroTelefono = numeeroTelefono;
    }


    /**
     * @return the ruc
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the numeeroTelefono
     */
    public String getNumeeroTelefono() {
        return numeeroTelefono;
    }

    /**
     * @param numeeroTelefono the numeeroTelefono to set
     */
    public void setNumeeroTelefono(String numeeroTelefono) {
        this.numeeroTelefono = numeeroTelefono;
    }

    /**
     * @return the idP
     */
    public Integer getIdP() {
        return idP;
    }

    /**
     * @param idP the idP to set
     */
    public void setIdP(Integer idP) {
        this.idP = idP;
    }
     public int compararPersona(Persona otraPersona, String campo) {
          if (!isValidField(campo)) {
        throw new IllegalArgumentException("Campo no válido: " + campo);
    }
        switch (campo.toLowerCase()) {
            
            case "ruc":
                return ruc.compareTo(otraPersona.getRuc());
            case "nombre":
                return nombre.compareTo(otraPersona.getNombre());
            case "genero":
                return apellido.compareTo(otraPersona.getApellido());
            default:
                throw new IllegalArgumentException("Campo no válido");
        }
    }
    
      public Boolean comparar(Persona p, String campo, Integer tipo) {
        switch (tipo) {
            case 0:
                return compararPersona(p, campo) < 0;
            case 1:
                return compararPersona(p, campo) > 0;
            default:
                throw new IllegalArgumentException("NO VALIDOO");
        }
    }
      private boolean isValidField(String campo) {
    // Only allow specific fields
    return campo.equals("nombre") || campo.equals("apellidp");
}
}
