package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

public class LlistaExemplars extends Llista<Exemplar> implements Serializable {
    public LlistaExemplars(){
        super();
    }

    @Override
    public void afegir(Exemplar ex) throws BiblioException {
        Iterator<Exemplar> itExemplars = llista.iterator();
        while(itExemplars.hasNext()){
            Exemplar exemplar = itExemplars.next();
            if(exemplar.getId().equals(ex.getId())){
                throw new BiblioException("Ja hi ha un exemplar amb aquest id.");
            }
        }
        llista.add(ex);
    }
}
