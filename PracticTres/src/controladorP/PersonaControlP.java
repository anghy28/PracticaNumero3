/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorP;

import ControladorUtiles.Utiles;
import Modelo.Persona;
import controlador.DAO.DaoImplements;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import java.lang.reflect.Field;
import java.util.Comparator;

/**
 *
 * @author santi
 */
public class PersonaControlP extends DaoImplements<Persona> {

    private DynamicList<Persona> personas;
    private Persona persona;

    public PersonaControlP() {
        super(Persona.class);
    }

    public DynamicList<Persona> getPersonas() {
        personas = all();
        return personas;
    }

    public void setPersonas(DynamicList<Persona> personas) {
        this.personas = personas;
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Boolean persist() {
        persona.setIdP(all().getLength() + 1);
        return persist(persona);
    }

    public Boolean guardar() {

        if (persona != null) {
            personas.add(persona);
            persona = null;
            return true;
        }
        return guardar();
    }

    public DynamicList<Persona> ordenar(DynamicList<Persona> lista, Integer tipo, String field) throws EmptyException, Exception {
        Field attribute = Utiles.getField(Persona.class, field);
        Integer n = lista.getLength();
        Persona[] personas = lista.toArray();
        if (attribute != null) {
            for (int i = 0; i < n; i++) {
                int k = i;
                Persona t = personas[i];
                for (int j = i + 1; j < n; j++) {
//                    if (personas[j].getApellidos().compareTo(t.getApellidos()) < 0) {
                    if (personas[j].comparar(t, field, tipo)) {
                        t = personas[j];
                        k = j;
                    }
                }
                personas[k] = personas[i];
                personas[i] = t;
            }
        } else {
            throw new Exception("NO EXITE BUSQUEDA");
        }
        return lista.toList(personas);

    }

    public static DynamicList<Persona> ShellSort(DynamicList<Persona> lista, Integer tipo, String field) {
        int n = lista.getLength();
        Persona[] personas = lista.toArray();

        for (int intervalo = n / 2; intervalo > 0; intervalo /= 2) {
            for (int i = intervalo; i < n; i++) {
                Persona ayuda = personas[i];
                int j;
                for (j = i; j >= intervalo && ayuda.comparar(personas[j - intervalo], field, tipo); j -= intervalo) {
                    personas[j] = personas[j - intervalo];
                }
                personas[j] = ayuda;
            }
        }
        return lista.toList(personas);

    }

    public int compare(Persona p1, Persona p2) {
        return 0; // Valor predeterminado si la comparación no se realiza correctamente
    }

    public Comparator<Persona> getComparator(String field, Integer tipo) {
        switch (field.toLowerCase()) {
            case "apellidos":
                return Comparator.comparing(Persona::getApellido, (tipo == 0) ? Comparator.naturalOrder() : Comparator.reverseOrder());
            case "nombres":
                return Comparator.comparing(Persona::getNombre, (tipo == 0) ? Comparator.naturalOrder() : Comparator.reverseOrder());
            case "ruc":
                return Comparator.comparing(Persona::getRuc, (tipo == 0) ? Comparator.naturalOrder() : Comparator.reverseOrder());
            
            default:
                return null; 
        }
    }


  //  comparar
    public static DynamicList<Persona> MetodoQuickSort(DynamicList<Persona> listaPersonas, Integer tipo, String Campo) throws EmptyException, NullPointerException {
        if (listaPersonas == null || listaPersonas.getLength()<= 1) {
            return listaPersonas;
        }
        Recursuvoquicksort(listaPersonas, 0, listaPersonas.getLength()- 1, tipo, Campo);
        return listaPersonas;
    }

    private static void Recursuvoquicksort(DynamicList<Persona> listaPersonas, int inicio, int fin, Integer tipo, String Campo) throws EmptyException, NullPointerException {
        if (inicio < fin) {
            int indiceParticion = Dividir(listaPersonas, inicio, fin, tipo, Campo);
            Recursuvoquicksort(listaPersonas, inicio, indiceParticion - 1, tipo, Campo);
            Recursuvoquicksort(listaPersonas, indiceParticion + 1, fin, tipo, Campo);
        }
    }
      private static void cambio(DynamicList<Persona> listaPersonas, int i, int j) throws EmptyException, NullPointerException {
        Persona ayuda = listaPersonas.getInfo(i);
        listaPersonas.ModificarInfo(listaPersonas.getInfo(j), i);
        listaPersonas.ModificarInfo(ayuda, j);
    }

    private static int Dividir(DynamicList<Persona> listaPersonas, int inicio, int fin, Integer tipo, String Campo) throws EmptyException, NullPointerException {
        Persona pivote = listaPersonas.getInfo(fin);
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (pivote.comparar(listaPersonas.getInfo(j), Campo, tipo)) {
                i++;
                cambio(listaPersonas, i, j);
            }
        }
        cambio(listaPersonas, i + 1, fin);
        return i + 1;
    }
    public static void main(String[] args) {
          PersonaControlP controlp = new PersonaControlP();

    try {
        DynamicList<Persona> listaPasajeros = controlp.getPersonas();
        long tiempoInicio = System.nanoTime();
        DynamicList<Persona> listaOrdenada = controlp.MetodoQuickSort(listaPasajeros, 0, "nombre");
        // Mostrar la lista ordenada
        for (int i = 0; i < listaOrdenada.getLength(); i++) {
            Persona Persona = listaOrdenada.getInfo(i);
            System.out.println("Nombre: " + Persona.getNombre() + ", apellido: " + Persona.getApellido());
        }

        
    } catch (EmptyException e) {
        e.printStackTrace(); // Manejo de excepciones, reemplaza por la lógica adecuada
    } catch (Exception ex) {
        ex.printStackTrace(); // Manejo de excepciones, reemplaza por la lógica adecuada
    }
    
}
    }
    

