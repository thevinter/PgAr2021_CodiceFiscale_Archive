package it.unibs.pgar.codicefiscale;

import java.util.ArrayList;

/**
 * Main class, have you ever heard of it?
 */
public class ClasseMain {

    /**
     * Richiamiamo solo i metodi delle altre classi nel main
     *
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<Comune> comuni = LetturaComuni.esecuzioneLetturaComuni();
        ArrayList<Persona> persone = LetturaInputPersone.esecuzioneLetturaPersone(comuni);
        generazioneCodiciFiscali(persone);
        ArrayList<String> codiciFiscali = LetturaCodiciFiscali.esecuzioneLetturaCodiciFiscali();
        ScritturaCodiciPersone.esecuzioneScritturaCodiciPersone(persone, codiciFiscali, comuni);
    }

    /**
     * Inizializza l'attributo codice fiscale per tutti gli oggetti Persona in un array.
     *
     * @param persone
     */
    public static void generazioneCodiciFiscali(ArrayList<Persona> persone) {
        for (int i = 0; i < persone.size(); i++) {
            persone.get(i).generaCodiceFiscale();
        }
    }

}
