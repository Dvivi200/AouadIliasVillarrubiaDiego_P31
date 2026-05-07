package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Diego Villarrubia
 * La classe Prestec conté tota la informació
 * important per definir l'operació de prestec en
 * la nostra biblioteca. Aquesta classe guarda
 * l'exemplar a agafar, l'usuari que realitza el prestec,
 * la data de creació i de límit per retornar el prestec
 * i si aquest s'ha retornat o no.
 */
public abstract class Prestec implements InPrestec, Serializable {
    protected Exemplar exemplar;
    protected Usuari usuari;
    private Date dataCreacio;
    private Date dataLimitRetorn;
    private boolean retornat;

    /**
     * Constructor de prestec pels tests.
     * Inicialitza una data de creació qualsevol i
     * retornat sempre en false.
     */
    public Prestec(Exemplar exemplar, Usuari usuari, Date dataCreacio){
        this.exemplar = exemplar;
        this.usuari = usuari;
        this.dataCreacio = dataCreacio;
        this.dataLimitRetorn = new Date(this.dataCreacio.getTime() + duradaPrestec());
        this.retornat = false;
    }

    /**
     * Constructor de prestec.
     * Inicialitza retornat sempre en false.
     */
    public Prestec(Exemplar exemplar, Usuari usuari){
        this.exemplar = exemplar;
        this.usuari = usuari;
        this.dataCreacio = new Date();
        this.dataLimitRetorn = new Date(dataCreacio.getTime() + duradaPrestec());
        this.retornat = false;
    }

    /**
     * Getters i setters de la classe
     */
    @Override
    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    @Override
    public Exemplar getExemplar() {
        return exemplar;
    }

    @Override
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    @Override
    public Usuari getUsuari() {
        return usuari;
    }

    @Override
    public void setDataCreacio(Date data) {
        this.dataCreacio = data;
    }

    @Override
    public Date getDataCreacio() {
        return dataCreacio;
    }

    @Override
    public void setDataLimitRetorn(Date data) {
        this.dataLimitRetorn = data;
    }

    @Override
    public Date getDataLimitRetorn() {
        return dataLimitRetorn;
    }

    /**
     * Metode abstracte per determinar el
     * tipus de prestec a realitzar segons la classe
     * i per tant la durada d'aquest prestec.
     * @return String
     */
    @Override
    public abstract String tipusPrestec();

    @Override
    public void setRetornat(boolean retornat) {
        this.retornat = retornat;
    }

    @Override
    public boolean getRetornat() {
        return retornat;
    }

    /**
     * Mètode abstracte per retornar un prestec
     * i modificar el numero de prestecs del usuari
     * segons el tipus de prestec.
     * @return void
     */
    @Override
    public abstract void retorna();

    /**
     * Mètode abstracte per determinar la durada
     * del prestec en milisegons segons el tipus
     * @return long
     */
    @Override
    public abstract long duradaPrestec();

    /**
     * Mètode per determinar si hi ha un prestec
     * endarrerit segons la data límit i l'actual
     * @return boolean
     */
    @Override
    public boolean prestecEndarrerit() {
        if(getRetornat()) return false;
        Date dataActual = new Date();
        return dataActual.after(dataLimitRetorn);
    }

    /**
     * Mètode toString que retorna la informació
     * de l'objecte sense modificar-ho
     * @return String
     */
    public String toString() {
        return "Tipus=" + tipusPrestec() + ", Exemplar=" + exemplar.getTitol() + ", Usuari="
                + usuari.getNom() + ", Data de creacio=" + dataCreacio + ", Data límit retorn="
                + dataLimitRetorn + ", Retornat=" + retornat;
    }
}
