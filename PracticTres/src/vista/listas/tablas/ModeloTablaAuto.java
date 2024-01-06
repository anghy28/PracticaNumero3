/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.listas.tablas;

import Modelo.Auto;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author marian
 */
public class ModeloTablaAuto  extends AbstractTableModel{
     private DynamicList<Auto> autos;

    @Override
    public int getRowCount() {
        return autos.getLength();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Auto a = autos.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (a != null) ? a.getMarca(): " ";
                case 1:
                    return (a != null) ? a.getModelo(): "";
                case 2:
                    return (a != null) ? a.getColor(): "";
                case 3:
                    return (a != null) ? a.getAnio(): "";
                case 4:
                    return (a != null) ? a.getPrecio(): "";
                default:
                    return null;
            }
        } catch (EmptyException ex) {
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "MARCA";
            case 1:
                return "MODELO";
            case 2:
                return "COLOR";
            case 3:
                return "AÑO";
            case 4:
                return "PRECIO";
            default:
                return null;
        }
    }

    public DynamicList<Auto> getAutos() {
        return autos;
    }

    public void setAutos(DynamicList<Auto> autos) {
        this.autos = autos;
    }

}
