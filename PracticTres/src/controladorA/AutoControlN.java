/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorA;

import Modelo.Auto;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;

/**
 *
 * @author marian
 */
public class AutoControlN {
      private Auto auto = new Auto();
    private DynamicList<Auto> autos;

    public AutoControlN(Auto auto) {
        this.auto = auto;
    }

    public AutoControlN() {
        this.autos = new DynamicList<>();
        
    }
    

    //Metodo que permite guardar
    public Boolean guardar() {
        
        try {
            getAuto().setIdA(getAutos().getLength());
            getAutos().add(getAuto());
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

        for (Integer i = 0; i <= this.autos.getLength(); i++) {
            
            if (this.getAutos().getInfo(i) == null) {
                bandera = i;
                break;
            }
        }
        return bandera;
    }

    public void imprimir() throws EmptyException {
        for (int i = 0; i < this.getAutos().getLength(); i++) {
            System.out.println(getAutos().getInfo(i));
        }
    }

    /**
     * @return the persona
     */
    public Auto getAuto() {
        if (auto == null) {
            auto = new Auto();
        }
        return auto;
    }

    /**
     * @param persona the persona to set
     */
    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public DynamicList<Auto> getAutos() {
        return autos;
    }

    public void setAutos(DynamicList<Auto> autos) {
        this.autos = autos;
    }

    @Override
    public String toString() {
        return "MARCA: " + getAuto().getMarca()+ " MODDELO: " + getAuto().getModelo()+ " COLOR: " + getAuto().getColor()+ " AÑO: " + getAuto().getAnio()
                + " PRECIO: " + getAuto().getPrecio();
                
    }


}
