package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @author Diego Villarrubia
 * LlistaUsuaris hereta de {@link Llista} i
 * guarda els usuaris de la nostra biblioteca
 */
public class LlistaUsuaris extends Llista<Usuari> implements Serializable {

    /**
     * Constructor de LlistaUsuaris
     */
    public LlistaUsuaris() {
        super();
    }

    /**
     * Mètode sobreescrit per afegir un usuari a la llista.
     * Llença excepció en cas d'intentar afegir un
     * usuari amb un email igual a altre de la llista.
     * @param us
     * @throws BiblioException
     */
    @Override
    public void afegir(Usuari us) throws BiblioException {
        // Es fa servir un Iterator per al recorregut de la llista de manera explícita, complint amb els requeriments de disseny de la pràctica [1].
        Iterator<Usuari> itUsuaris = llista.iterator();
        while (itUsuaris.hasNext()) {
            Usuari usuari = itUsuaris.next();
            // L'email s'utilitza com a identificador únic per a cada usuari; el sistema prohibeix duplicats per garantir la integritat del cens [2, 3].
            if (usuari.getEmail().equals(us.getEmail())) {
                // Es llança l'excepció personalitzada per informar a la vista d'un conflicte en l'alta de l'usuari [4, 5].
                throw new BiblioException("Ja hi ha un usuari registrat amb aquest correu.");
            }
        }
        llista.add(us);
    }

    /**
     * Mètode per comprobar que un usuari
     * amb el seu email com a paràmetre està a la llista.
     * @param email
     * @return boolean
     */
    public boolean contains(String email) {
        Iterator<Usuari> it = llista.iterator();
        while (it.hasNext()) {
            if (it.next().getEmail().equals(email)) return true;
        }
        return false;
    }
}
