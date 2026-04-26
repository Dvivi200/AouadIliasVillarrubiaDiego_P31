package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Date;

public class PrestecNormal extends Prestec implements Serializable {
    public PrestecNormal(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        super(exemplar, usuari, dataCreacio);
    }

    public PrestecNormal(Exemplar exemplar, Usuari usuari){
        super(exemplar, usuari);
    }

    @Override
    public String tipusPrestec() {
        return "Normal";
    }

    @Override
    public long duradaPrestec() {
        return 70000;
    }

    @Override
    public void retorna() throws BiblioException {
        if(getRetornat()) throw new BiblioException("Aquest exemplar ja ha sigut retornat");
        setRetornat(true);
        exemplar.setDisponible(true);
        usuari.setNumPrestecsNormals(usuari.getNumPrestecsNormals() - 1);
    }
}
