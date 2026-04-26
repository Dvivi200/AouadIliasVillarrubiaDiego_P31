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
        for(Exemplar ex : llistaExemplars.getArrayList()){
            if(ex.getId().equals(id))
                throw new BiblioException("Aquest exemplar ya existeix");
        }
        Exemplar exemplar = new Exemplar(id, autor, titol, admetPrestecLlarg);
        llistaExemplars.afegir(exemplar);
    }

    @Override
    public ArrayList<Exemplar> recuperaExemplars() {
        return llistaExemplars.getArrayList();
    }

    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        for(Usuari us : llistaUsuaris.getArrayList()){
            if(us.getEmail().equals(email))
                throw new BiblioException("Aquest usuari ya existeix");
        }
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
        if(exemplarPos >= llistaExemplars.getSize())
            throw new BiblioException("Aquest valor no esta vinculat a cap exemplar");
        Exemplar ex = llistaExemplars.getAt(exemplarPos);
        Usuari us = llistaUsuaris.getAt(usuariPos);
        if(esLlarg) {
            if(!ex.getAdmetPrestecLlarg())
                throw new BiblioException("Aquest exemplar no admet prestec a llarg termini");
            else if(us.getNumPrestecsLlargs() >= us.getMaxPrestecsLlargs())
                throw new BiblioException("Aquest usuari ja no pot tenir mes prestecs a llarg termini");
            prestec = new PrestecLlarg(ex, us);
            us.setNumPrestecsLlargs(us.getNumPrestecsLlargs() + 1);
        }
        else {
            if(us.getNumPrestecsNormals() >= us.getMaxPrestecsNormals())
                throw new BiblioException("Aquest usuari ja no pot tenir mes prestecs normals");
            prestec = new PrestecNormal(ex, us);
            us.setNumPrestecsNormals(us.getNumPrestecsNormals() + 1);
        }
        ex.setDisponible(false);
        llistaPrestecs.afegir(prestec);
    }

    @Override
    public void retornarPrestec(int position) throws BiblioException {
        Prestec pr = llistaPrestecs.getAt(position);
        pr.retorna();
        llistaPrestecs.esborrar(pr);
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
