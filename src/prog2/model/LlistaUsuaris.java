package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

public class LlistaUsuaris extends Llista<Usuari> implements Serializable {
    public LlistaUsuaris() {
        super();
    }

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

    public boolean contains(String email) {
        // Mètode de conveniència per verificar l'existència d'un usuari al sistema abans de procedir a operacions de préstec [6, 7].
        for (Usuari usuari : llista) {
            if (usuari.getEmail().equals(email)) return true;
        }
        return false;
    }
}
