package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @author Diego Villarrubia
 * LlistaExemplars hereta de {@link Llista} i
 * guarda els exemplars de la nostra biblioteca
 */
public class LlistaExemplars extends Llista<Exemplar> implements Serializable {

    /**
     * Constructor de LlistaExemplars
     */
    public LlistaExemplars() {
        super();
    }

    /**
     * Mètode sobreescrit per afegir un exemplar a la llista.
     * Llença excepció en cas d'intentar afegir un
     * exemplar amb un id igual a altre de la llista.
     * @param ex
     * @throws BiblioException
     */
    @Override
    public void afegir(Exemplar ex) throws BiblioException {
        // Implementació del recorregut mitjançant Iterator per complir amb els requeriments de disseny de la pràctica.
        Iterator<Exemplar> itExemplars = llista.iterator();
        while (itExemplars.hasNext()) {
            Exemplar exemplar = itExemplars.next();
            // Restricció de model: es garanteix que cada còpia física tingui un identificador únic per evitar duplicitats al catàleg,.
            if (exemplar.getId().equals(ex.getId())) {
                throw new BiblioException("Ja hi ha un exemplar amb aquest id.");
            }
        }
        llista.add(ex);
    }

    /**
     * Mètode per comprobar que un exemplar
     * amb l'id com a paràmetre està a la llista.
     * @param id
     * @return boolean
     */
    public boolean contains(String id) {
        Iterator<Exemplar> it = llista.iterator();
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) return true;
        }
        return false;
    }
}