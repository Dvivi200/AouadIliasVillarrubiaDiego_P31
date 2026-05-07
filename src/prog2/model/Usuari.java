package prog2.model;

import java.io.Serializable;

/**
 * @author Diego Villarrubia
 * La classe usuari conté tota la informació
 * important per definir un usuari de la
 * nostra biblioteca. Aquesta classe guarda
 * l'email, el nom i l'adreça de l'usuari,
 * el nombre de prestecs normals i llargs
 * que poden fer i el tipus d'usuari que depen d'ell
 * el màxim de prestecs que es poden fer
 */
public abstract class Usuari implements InUsuari, Serializable {

    private String email;
    private String nom;
    private String adreca;
    private int numPrestecsNormals;
    private int numPrestecsLlargs;
    protected String tipusUsuari;

    /**
     * Costructor d'usuari.
     * Inicialitza els prestecs normals i llargs a 0
     */
    public Usuari(String email, String nom, String adreca) {
        this.email = email;
        this.nom = nom;
        this.adreca = adreca;
        this.numPrestecsNormals = 0;
        this.numPrestecsLlargs = 0;
    }

    /**
     * Getters i setters de la classe
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    @Override
    public String getAdreca() {
        return adreca;
    }

    @Override
    public String tipusUsuari() {
        return tipusUsuari;
    }

    @Override
    public void setNumPrestecsNormals(int numPrestecsNormals) {
        this.numPrestecsNormals = numPrestecsNormals;
    }

    @Override
    public int getNumPrestecsNormals() {
        return numPrestecsNormals;
    }

    @Override
    public void setNumPrestecsLlargs(int numPrestecsLlargs) {
        this.numPrestecsLlargs = numPrestecsLlargs;
    }

    @Override
    public int getNumPrestecsLlargs() {
        return numPrestecsLlargs;
    }

    /**
     * Mètodes abstractes per definir el maxim de
     * prestecs normals i llargs que un usuari
     * pot fer depenent del tipus
     * @return int
     */
    @Override
    public abstract int getMaxPrestecsNormals();

    @Override
    public abstract int getMaxPrestecsLlargs();

    /**
     * Mètode toString que retorna la informació
     * de l'objecte sense modificar-ho
     * @return String
     */
    @Override
    public String toString() {
        return "Tipus=" +  tipusUsuari + ", Email=" + email + ", Nom=" + nom
                + ", Adreca=" + adreca + ", Num. prestecs normals=" + numPrestecsNormals
                + ", Num. prestecs llargs=" + numPrestecsLlargs;
    }
}
