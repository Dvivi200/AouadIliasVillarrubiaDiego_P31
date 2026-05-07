/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import prog2.vista.BiblioException;

/**
 * @author Diego Villarrubia
 * La classe llista es una estructura de dades
 * que guardarà tant com els exemplars, usuaris i
 * prestecs en llistes diferents segons
 * el que es indiqui en el <T> i segons aixó farà les
 * operacions de diferent forma.
 * @param <T>
 */
public class Llista<T> implements InLlista<T>, Serializable {
   protected ArrayList<T> llista;

    /**
     * Constructor de llista
     */
   public Llista() {
       llista = new ArrayList<>();
    }

    /**
     * Mètode per retornar
     * la mida de la llista
     * @return int
     */
    public int getSize() {
       return llista.size();
    }

    /**
     * Mètode per afegir un element a la llista
     * @param t
     * @throws BiblioException
     */
    public void afegir(T t) throws BiblioException {
       llista.add(t);
    }

    /**
     * Mètode per eliminar un element a la
     * llista segons <T>
     * @param t
     */
    public void esborrar(T t) {
       llista.remove(t);
    }

    /**
     * Mètode per obtenir un element
     * concret de la llista
     * @param position
     * @return
     */
    public T getAt(int position) {
       return llista.get(position);
    }

    /**
     * Mètode per esborrar tota la llista
     */
    public void clear() {
       llista.clear();
    }

    /**
     * Mètode per comprobar que
     * la llista esta buida
     * @return boolean
     */
    public boolean isEmpty() {
       return llista.isEmpty();
    }

    /**
     * Getter de llista
     * @return ArratList<T>
     */
    public ArrayList<T> getArrayList() {
       return llista;
    }
}
