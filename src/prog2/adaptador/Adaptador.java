package prog2.adaptador;

import prog2.model.*;
import prog2.vista.BiblioException;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Diego Villarrubia
 * Classe encarregada de connectar el package
 * model amb el de vista.
 * Aixó li permet a vista accedir a model sense modificar
 * les seves dades directament.
 */
public class Adaptador implements Serializable {

    private Dades dades;

    /**
     * Constructor d'adaptador.
     */
    public Adaptador() { dades = new Dades(); }

    /**
     * Mètode per afegir un exemplar amb el mètode
     * {@link Dades#afegirExemplar(String, String, String, boolean)}
     * @param id
     * @param titol
     * @param autor
     * @param admetPrestecLlarg
     * @throws BiblioException
     */
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        dades.afegirExemplar(id, titol, autor, admetPrestecLlarg);
    }

    /**
     * Mètode per accedir a la informació
     * dels exemplars mitjançant els mètodes
     * {@link Dades#recuperaExemplars()}
     * {@link Exemplar#toString()}
     * @return ArrayList<String>
     */
    public ArrayList<String> recuperaExemplars() {
        ArrayList<String> llista = new ArrayList<>();
        for(Exemplar ex : dades.recuperaExemplars()){
            llista.add(ex.toString());
        }
        return llista;
    }

    /**
     * Mètode per afegir un usuari amb el mètode
     * {@link Dades#afegirUsuari(String, String, String, boolean)}
     * @param email
     * @param nom
     * @param adreca
     * @param esEstudiant
     * @throws BiblioException
     */
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }

    /**
     * Mètode per accedir a la informació
     * dels usuaris mitjançant els mètodes
     * {@link Dades#recuperaUsuaris()}
     * {@link Usuari#toString()}
     * @return ArrayList<String>
     */
    public ArrayList<String> recuperaUsuaris() {
        ArrayList<String> llista = new ArrayList<>();
        for(Usuari us : dades.recuperaUsuaris()){
            llista.add(us.toString());
        }
        return llista;
    }

    /**
     * Mètode per afegir un prestec amb el mètode
     * {@link Dades#afegirPrestec(int, int, boolean)}
     * @param exemplarPos
     * @param usuariPos
     * @param esLlarg
     * @throws BiblioException
     */
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    /**
     * Mètode per retornar un prèstec mitjançat el mètode
     * {@link Dades#retornarPrestec(int)}
     * @param position
     * @throws BiblioException
     */
    public void retornarPrestec(int position) throws BiblioException { dades.retornarPrestec(position); }

    /**
     * Mètode per accedir a la informació
     * dels prestecs mitjançant els mètodes
     * {@link Dades#recuperaPrestecs()}
     * {@link Prestec#toString()}
     * @return ArrayList<String>
     */
    public ArrayList<String> recuperaPrestecs() {
        ArrayList<String> llista = new ArrayList<>();
        for(Prestec pr : dades.recuperaPrestecs()){
            llista.add(pr.toString());
        }
        return llista;
    }

    /**
     * Mètode per accedir a la informació
     * dels prestecs no retornats mitjançant els mètodes
     * {@link Dades#recuperaPrestecsNoRetornats()}
     * {@link Prestec#toString()}
     * @return ArrayList<String>
     */
    public ArrayList<String> recuperaPrestecsNoRetornats() {
        ArrayList<String> llista = new ArrayList<>();
        for(Prestec pr : dades.recuperaPrestecsNoRetornats()){
            llista.add(pr.toString());
        }
        return llista;
    }

    /**
     * Mètode per guardar les dades de la
     * biblioteca en un fitxer. Llença excepció en
     * cas de no poder guardar-ho
     * @param camiDesti
     * @throws BiblioException
     */
    public void guardaDades(String camiDesti) throws BiblioException {
        File fitxer = new File(camiDesti);
        try(FileOutputStream fos = new FileOutputStream(fitxer);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new BiblioException("Error en guardar les dades al fitxer: " + e.getMessage());
        }
    }

    /**
     * Mètode per carregar les dades d'un
     * fitxer a la biblioteca.
     * Llença excepció en cas de no poder carregar-ho
     * @param camiOrigen
     * @throws BiblioException
     */
    public void carregaDades(String camiOrigen) throws BiblioException {
        File fitxer = new File(camiOrigen);
        try (FileInputStream fin = new FileInputStream(fitxer);
             ObjectInputStream ois = new ObjectInputStream(fin)) {
            Adaptador ad = (Adaptador) ois.readObject();
            this.dades = ad.dades;
        } catch (IOException | ClassNotFoundException e) {
            throw new BiblioException("Error en carregar les dades del fitxer: " + e.getMessage());
        }
    }

}
