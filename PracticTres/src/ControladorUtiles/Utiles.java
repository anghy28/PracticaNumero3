/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorUtiles;

import Modelo.Persona;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import java.lang.reflect.Field;

/**
 *
 * @author marian
 */
public class Utiles {
      //Codigo para validar la cedula
    public static boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
// Coeficientes de validación cédula
// El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedulaCorrecta;
    }
     public static Field getField(Class clazz, String atribute) {
        Field field = null;
        for (Field f : clazz.getSuperclass().getDeclaredFields()) {
            if (f.getName().equalsIgnoreCase(atribute)) {
                field = f;
                break;
            }
        }
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getName().equalsIgnoreCase(atribute)) {
                field = f;
                break;
            }
        }
        return field;
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
       private static boolean comparar(Persona persona1, Persona persona2, String field, Integer tipo) {
    if (persona1 == null || persona2 == null) {
        // Handle null values, you can modify this part based on your logic
        return false;
    }

    // Assuming 'field' is a valid field in the Persona class for comparison
    switch (field) {
        case "nombre":
            // Compare personas based on the 'nombre' field
            // You may need to implement the comparison logic for the 'nombre' field
            return tipo == 0 ? persona1.getNombre().compareTo(persona2.getNombre()) > 0 :
                               persona1.getNombre().compareTo(persona2.getNombre()) < 0;
        
        default:
            throw new IllegalArgumentException("Invalid field: " + field);
    }
}
    
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
}
