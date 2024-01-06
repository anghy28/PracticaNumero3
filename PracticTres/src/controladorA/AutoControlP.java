/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorA;

import Modelo.Auto;

import controlador.DAO.DaoImplements;
import controlador.TDA.listas.DynamicList;

/**
 *
 * @author marian
 */
public class AutoControlP extends DaoImplements<Auto>{
      private DynamicList<Auto> autos;
    private Auto auto;

    public AutoControlP() {
        super(Auto.class);
    }

    public DynamicList<Auto> getAutos() {
        autos = all();
        return autos;
    }

    public void setAutos(DynamicList<Auto> autos) {
        this.autos = autos;
    }

    public Auto getAuto() {
        if (auto == null) {
            auto = new Auto();
        }
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
    
    public Boolean persist(){
        auto.setIdA(all().getLength() + 1);
        return persist(auto);
    }
      public Boolean guardar() {
        if (auto != null) {
            autos.add(auto);
            auto = null;
            return true;
        }
        return false;
    }
}
