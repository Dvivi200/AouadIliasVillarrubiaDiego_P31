package prog2.model;

import java.io.Serializable;

/**
 * @author Diego Villarrubia
 * La classe exemplar conté tota la informació
 * important per definir un exemplar d'una biblioteca.
 * Entre aquestes característiques estan el id, l'autor
 * i el titol del exemplar, si admet prestec a llarg termini
 * i si esta disponible.
 */
public class Exemplar implements InExemplar, Serializable {

    private String id;
    private String autor;
    private String titol;
    private boolean admetPrestecLlarg;
    private boolean disponible;

    /**
     * Constructor d'exemplar.
     * Inicialitza disponible sempre en true.
     */
    public Exemplar(String id, String titol, String autor, boolean admetPrestecLlarg) {
        this.id = id;
        this.autor = autor;
        this.titol = titol;
        this.admetPrestecLlarg = admetPrestecLlarg;
        this.disponible = true;
    }

    /**
     * Getters i setters de la classe
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setTitol(String titol) {
        this.titol = titol;
    }

    @Override
    public String getTitol() {
        return titol;
    }

    @Override
    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    @Override
    public void setAdmetPrestecLlarg(boolean admetPrestecLlarg) {
        this.admetPrestecLlarg = admetPrestecLlarg;
    }

    @Override
    public boolean getAdmetPrestecLlarg() {
        return admetPrestecLlarg;
    }

    @Override
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Mètode toString que retorna la informació
     * de l'objecte sense modificar-ho
     * @return String
     */
    @Override
    public String toString() {
        return "Id=" + id + ", Titol=" + titol +
                ", Autor=" + autor + ", Admet Prestec Llarg=" + admetPrestecLlarg +
                ", Disponible=" + disponible;
    }
}
