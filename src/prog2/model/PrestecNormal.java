package prog2.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Diego Villarrubia
 * La classe PrestecNormal hereta de
 * {@link Prestec} i determina tant el
 * tipus de prestec com la seva durada.
 */
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
    public void retorna() {
        setRetornat(true);
        exemplar.setDisponible(true);
        usuari.setNumPrestecsNormals(usuari.getNumPrestecsNormals() - 1);
    }
}
