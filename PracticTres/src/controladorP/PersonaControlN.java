/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorP;

import Modelo.Persona;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;



/**
 *
 * @author santi
 */
public class PersonaControlN {

    private Persona persona = new Persona();
    private DynamicList<Persona> personas;

    public PersonaControlN(Persona persona) {
        this.persona = persona;
    }

    public PersonaControlN() {
        this.personas = new DynamicList<>();
        
    }
    

    //Metodo que permite guardar
    public Boolean guardar() {
        
        try {
            getPersona().setIdP(getPersonas().getLength());
            getPersonas().add(getPersona());
            return true;
        } catch (Exception e) {
            return false;
        }
        
//        Integer pos = posVerificar();
//        if (pos > 0) {
//            persona.setId(pos + 1);
//            personas.add(persona, posVerificar());
//            return true;
//        } else {
//            return false;
//        }
    }

    public Integer posVerificar() throws EmptyException {
        
        Integer bandera = 0;

        for (Integer i = 0; i <= this.personas.getLength(); i++) {
            
            if (this.getPersonas().getInfo(i) == null) {
                bandera = i;
                break;
            }
        }
        return bandera;
    }

    public void imprimir() throws EmptyException {
        for (int i = 0; i < this.getPersonas().getLength(); i++) {
            System.out.println(getPersonas().getInfo(i));
        }
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public DynamicList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(DynamicList<Persona> personas) {
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "DNI: " + getPersona().getRuc()+ " Nombres: " + getPersona().getNombre()+ " Apellidos: " + getPersona().getApellido()+ " Dirrecion: " + getPersona().getDireccion()
                + " Telefono: " + getPersona().getNumeeroTelefono();
                
    }
}
