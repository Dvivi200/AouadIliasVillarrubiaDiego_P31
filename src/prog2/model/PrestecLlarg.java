package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Date;

public class PrestecLlarg extends Prestec implements Serializable {
    public PrestecLlarg(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        super(exemplar, usuari, dataCreacio);
    }

    public PrestecLlarg(Exemplar exemplar, Usuari usuari){
        super(exemplar, usuari);
    }

    @Override
    public String tipusPrestec() {
        return "Llarg";
    }

    @Override
    public long duradaPrestec() {
        return 140000;
    }

    @Override
    public void retorna() {
        setRetornat(true);
        exemplar.setDisponible(true);
        usuari.setNumPrestecsLlargs(usuari.getNumPrestecsLlargs() - 1);
    }
}
