package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

public class LlistaExemplars extends Llista<Exemplar> implements Serializable {
    public LlistaExemplars() {
        super();
    }

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

    public boolean contains(String id) {
        // Mètode de suport per a la validació ràpida de l'existència d'un exemplar abans d'executar operacions de gestió o consulta.
        for (Exemplar exemplar : llista) {
            if (exemplar.getId().equals(id)) return true;
        }
        return false;
    }
}