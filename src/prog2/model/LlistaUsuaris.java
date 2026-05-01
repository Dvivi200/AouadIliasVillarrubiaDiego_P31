package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

public class LlistaUsuaris extends Llista<Usuari> implements Serializable {
    public LlistaUsuaris(){
        super();
    }

    @Override
    public void afegir(Usuari us) throws BiblioException {
        Iterator<Usuari> itUsuaris = llista.iterator();
        while(itUsuaris.hasNext()){
            Usuari usuari = itUsuaris.next();
            if(usuari.getEmail().equals(us.getEmail())){
                throw new BiblioException("Ja hi ha un usuari registrat amb aquest correu.");
            }
        }
        llista.add(us);
    }

    public boolean contains(String email){
        for(Usuari usuari : llista) {
            if(usuari.getEmail().equals(email)) return true;
        }
        return false;
    }
}
