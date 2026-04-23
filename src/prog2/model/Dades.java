package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.ArrayList;

public class Dades implements InDades, Serializable {

    private LlistaExemplars llistaExemplars;
    private LlistaUsuaris llistaUsuaris;
    private LlistaPrestecs llistaPrestecs;

    public Dades() {
        llistaExemplars = new LlistaExemplars();
        llistaUsuaris = new LlistaUsuaris();
        llistaPrestecs = new LlistaPrestecs();
    }

    @Override
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        Exemplar exemplar = new Exemplar(id, autor, titol, admetPrestecLlarg);
        llistaExemplars.afegir(exemplar);
    }

    @Override
    public ArrayList<Exemplar> recuperaExemplars() {
        return llistaExemplars.getArrayList();
    }

    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        Usuari usuari;
        if(esEstudiant) usuari = new Estudiant(email, nom, adreca);
        else usuari = new Professor(email, nom, adreca);
        llistaUsuaris.afegir(usuari);
    }

    @Override
    public ArrayList<Usuari> recuperaUsuaris() {
        return llistaUsuaris.getArrayList();
    }

    @Override
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        Prestec prestec;
        if(esLlarg) prestec = new PrestecLlarg(llistaExemplars.getAt(exemplarPos), llistaUsuaris.getAt(usuariPos));
        else prestec = new PrestecNormal(llistaExemplars.getAt(exemplarPos), llistaUsuaris.getAt(usuariPos));
        llistaPrestecs.afegir(prestec);
    }

    @Override
    public void retornarPrestec(int position) throws BiblioException {

    }

    @Override
    public ArrayList<Prestec> recuperaPrestecs() {
        return llistaPrestecs.getArrayList();
    }

    @Override
    public ArrayList<Prestec> recuperaPrestecsNoRetornats() {
        return null;
    }
}
