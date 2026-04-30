package prog2.adaptador;

import prog2.model.*;
import prog2.vista.BiblioException;

import java.io.*;
import java.util.ArrayList;

public class Adaptador implements Serializable {

    private Dades dades;

    public Adaptador() { dades = new Dades(); }

    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        dades.afegirExemplar(id, titol, autor, admetPrestecLlarg);
    }

    public ArrayList<String> recuperaExemplars() {
        ArrayList<String> llista = new ArrayList<>();
        for(Exemplar ex : dades.recuperaExemplars()){
            llista.add(ex.toString());
        }
        return llista;
    }

    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }

    public ArrayList<String> recuperaUsuaris() {
        ArrayList<String> llista = new ArrayList<>();
        for(Usuari us : dades.recuperaUsuaris()){
            llista.add(us.toString());
        }
        return llista;
    }

    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    public void retornarPrestec(int position) throws BiblioException { dades.retornarPrestec(position); }

    public ArrayList<String> recuperaPrestecs() {
        ArrayList<String> llista = new ArrayList<>();
        for(Prestec pr : dades.recuperaPrestecs()){
            llista.add(pr.toString());
        }
        return llista;
    }

    public ArrayList<String> recuperaPrestecsNoRetornats() {
        ArrayList<String> llista = new ArrayList<>();
        for(Prestec pr : dades.recuperaPrestecsNoRetornats()){
            llista.add(pr.toString());
        }
        return llista;
    }


    public void guardaDades(String camiDesti) throws BiblioException {
        File fitxer = new File(camiDesti);
        try(FileOutputStream fos = new FileOutputStream(fitxer);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new BiblioException("Error en guardar les dades al fitxer: " + e.getMessage());
        }
    }
    public Adaptador carregaDades(String camiOrigen) throws BiblioException {
        File fitxer = new File(camiOrigen);
        try(FileInputStream fin = new FileInputStream(fitxer);
            ObjectInputStream ois = new ObjectInputStream(fin)) {
            Adaptador ad = (Adaptador) ois.readObject();
            fin.close();
            ois.close();
            return ad;

        } catch (IOException | ClassNotFoundException e) {
            throw new BiblioException("Error en carregar les dades del fitxer: " + e.getMessage());
        }
    }

}
