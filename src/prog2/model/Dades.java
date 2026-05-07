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
        // Garantim la unicitat de l'identificador tal com exigeix la lògica de LlistaExemplars.
        Exemplar exemplar = new Exemplar(id, titol, autor, admetPrestecLlarg);
        llistaExemplars.afegir(exemplar);
    }

    @Override
    public ArrayList<Exemplar> recuperaExemplars() {
        return llistaExemplars.getArrayList();
    }

    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        // Apliquem polimorfisme per assignar els límits de préstecs segons el rol (Estudiant/Professor).
        Usuari usuari;
        if (esEstudiant) usuari = new Estudiant(email, nom, adreca);
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
        // Validació de seguretat d'índexs abans d'accedir a les llistes del model.
        if (exemplarPos >= llistaExemplars.getSize())
            throw new BiblioException("Aquest valor no esta vinculat a cap exemplar");

        Exemplar ex = llistaExemplars.getAt(exemplarPos);
        // Un exemplar només es pot prestar si el seu estat actual és disponible.
        if (!ex.isDisponible())
            throw new BiblioException("Aquest exemplar no està disponible");

        if (usuariPos >= llistaUsuaris.getSize())
            throw new BiblioException("Aquest valor no esta vinculat a cap usuari");

        Usuari us = llistaUsuaris.getAt(usuariPos);

        // Verificació de la restricció de bloqueig per retards en devolucions anteriors.
        for (Prestec p : llistaPrestecs.getArrayList()) {
            if (p.getUsuari().equals(us) && p.prestecEndarrerit())
                throw new BiblioException("Aquest usuari té prestecs endarrerits, " +
                        "per tant no podra fer més prestecs fins que retorni els endarrerits");
        }

        if (esLlarg) {
            // Validació de si l'exemplar físic permet, per configuració, el préstec de llarga durada.
            if (!ex.getAdmetPrestecLlarg())
                throw new BiblioException("Aquest exemplar no admet prestec a llarg termini");

            // Control de topall de préstecs llargs (1 per Estudiants, 2 per Professors).
            if (us.getNumPrestecsLlargs() >= us.getMaxPrestecsLlargs())
                throw new BiblioException("Aquest usuari ja no pot tener mes prestecs a llarg termini");

            prestec = new PrestecLlarg(ex, us);
            us.setNumPrestecsLlargs(us.getNumPrestecsLlargs() + 1);
        } else {
            // Control de topall de préstecs normals (màxim 2 per a ambdós tipus d'usuari).
            if (us.getNumPrestecsNormals() >= us.getMaxPrestecsNormals())
                throw new BiblioException("Aquest usuari ja no pot tenir mes prestecs normals");

            prestec = new PrestecNormal(ex, us);
            us.setNumPrestecsNormals(us.getNumPrestecsNormals() + 1);
        }

        // En formalitzar el préstec, l'exemplar queda automàticament bloquejat per a altres usuaris.
        ex.setDisponible(false);
        llistaPrestecs.afegir(prestec);
    }

    @Override
    public void retornarPrestec(int position) throws BiblioException {
        Prestec pr = llistaPrestecs.getAt(position);
        // Evitem la redundància en el retorn segons les especificacions de control d'errors.
        if (pr.getRetornat()) throw new BiblioException("Aquest exemplar ja s'ha retornat");
        pr.retorna();
    }

    @Override
    public ArrayList<Prestec> recuperaPrestecs() {
        return llistaPrestecs.getArrayList();
    }

    @Override
    public ArrayList<Prestec> recuperaPrestecsNoRetornats() {
        // Filtrar manualment per gestionar la visualització de préstecs actius al sistema.
        LlistaPrestecs prestecs = new LlistaPrestecs();
        for (Prestec p : llistaPrestecs.getArrayList()) {
            if (!p.getRetornat()) {
                try {
                    prestecs.afegir(p);
                } catch (BiblioException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return prestecs.getArrayList();
    }
}
