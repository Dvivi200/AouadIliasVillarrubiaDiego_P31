package prog2.model;

import java.io.Serializable;

/**
 * @author Diego Villarrubia
 * LlistaPrestecs hereta de {@link Llista} i
 * guarda els prestecs de la nostra biblioteca
 */
public class LlistaPrestecs extends Llista<Prestec> implements Serializable {

    /**
     * Constructor de LlistaPrestecs
     */
    public LlistaPrestecs(){
        super();
    }
}
