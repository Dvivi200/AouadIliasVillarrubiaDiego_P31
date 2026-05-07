package prog2.model;

import java.io.Serializable;

/**
 * @author Diego Villarrubia
 * La classe Estudiant hereta de
 * {@link Usuari}
 * i determina el maxim de prestecs
 * normals i llargs que pot fer
 */
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
