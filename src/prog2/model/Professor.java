package prog2.model;

import java.io.Serializable;

public class Professor extends Usuari implements Serializable {
    public Professor(String email, String nom, String adreca) {
        super(email, nom, adreca);
        this.tipusUsuari = "Professor";
    }

    @Override
    public int getMaxPrestecsNormals() {
        return 2;
    }

    @Override
    public int getMaxPrestecsLlargs() {
        return 2;
    }
}
