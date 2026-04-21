package prog2.model;

import java.io.Serializable;

public class Estudiant extends Usuari implements Serializable {
    public Estudiant(String email, String nom, String adreca) {
        super(email, nom, adreca);
        this.tipusUsuari = "Estudiant";
    }

    @Override
    public int getMaxPrestecsNormals() {
        return 2;
    }

    @Override
    public int getMaxPrestecsLlargs() {
        return 1;
    }
}
