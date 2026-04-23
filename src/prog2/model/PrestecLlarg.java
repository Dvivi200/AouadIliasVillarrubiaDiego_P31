package prog2.model;

import java.io.Serializable;
import java.util.Date;

public class PrestecLlarg extends Prestec implements Serializable {
    public PrestecLlarg(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        super(exemplar, usuari, dataCreacio);
    }

    @Override
    public String tipusPrestec() {
        return "Llarg";
    }

    @Override
    public long duradaPrestec() {
        return 140000;
    }
}
