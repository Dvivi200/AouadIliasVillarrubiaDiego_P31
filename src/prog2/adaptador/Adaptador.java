package prog2.adaptador;

import prog2.model.Dades;
import prog2.model.Exemplar;
import prog2.model.Prestec;
import prog2.model.Usuari;
import prog2.vista.BiblioException;

import java.io.*;

public class Adaptador implements Serializable {

    private Dades dades;

    public Adaptador() { dades = new Dades(); }

    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        dades.afegirExemplar(id, titol, autor, admetPrestecLlarg);
    }

    public String visualitzarExemplars() {
        StringBuilder llista = new StringBuilder();
        for(Exemplar ex : dades.recuperaExemplars()){
            llista.append(ex.toString()).append("\n");
        }
        return llista.toString();
    }

    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }

    public String visualitzarUsuaris() {
        StringBuilder llista = new StringBuilder();
        for(Usuari us : dades.recuperaUsuaris()){
            llista.append(us.toString()).append("\n");
        }
        return llista.toString();
    }

    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    public void retornarPrestec(int position) throws BiblioException { dades.retornarPrestec(position); }

    public String visualitzarPrestecs() {
        StringBuilder llista = new StringBuilder();
        for(Prestec pr : dades.recuperaPrestecs()){
            llista.append(pr.toString()).append("\n");
        }
        return llista.toString();
    }

    public String visualitzarPrestecsNoRetornats() {
        StringBuilder llista = new StringBuilder();
        for(Prestec pr : dades.recuperaPrestecsNoRetornats()){
            llista.append(pr.toString()).append("\n");
        }
        return llista.toString();
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
