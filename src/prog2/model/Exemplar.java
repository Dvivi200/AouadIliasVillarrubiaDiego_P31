package prog2.model;

import prog2.vista.IniciadorBiblioUB;

import java.io.Serializable;

public class Exemplar implements InExemplar, Serializable {

    private String id;
    private String autor;
    private String titol;
    private boolean admetPrestecLlarg;
    private boolean disponible;

    public Exemplar(String id, String autor, String titol, boolean admetPrestecLlarg) {
        this.id = id;
        this.autor = autor;
        this.titol = titol;
        this.admetPrestecLlarg = admetPrestecLlarg;
        this.disponible = true;
    }

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

    @Override
    public String toString() {
        return "Id=" + id + ", Titol=" + titol + ", Autor=" + autor + ", Admet Prestec Llarg=" + admetPrestecLlarg + ", Disponible=" + disponible;
    }
}
