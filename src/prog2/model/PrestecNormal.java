package prog2.model;

import java.io.Serializable;
import java.util.Date;

public class PrestecNormal extends Prestec implements Serializable {
    public PrestecNormal(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        super(exemplar, usuari, dataCreacio);
    }

    @Override
    public String tipusPrestec() {
        return "Normal";
    }

    @Override
    public long duradaPrestec() {
        return 70000;
    }
}
