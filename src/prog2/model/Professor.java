package prog2.model;

import java.io.Serializable;

/**
 * @author Diego Villarrubia
 * La classe Professor hereta de
 * {@link Usuari}
 * i determina el maxim de prestecs
 * normals i llargs que pot fer
 */
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
