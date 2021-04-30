import it.arnaldo.butitworks.model.Comune;
import it.arnaldo.butitworks.model.Persona;
import it.arnaldo.butitworks.utilities.CodiceFiscaleUtilities;
import it.arnaldo.butitworks.utilities.XmlUtilities;

import java.util.ArrayList;

public class MainCodiceFiscale {

    private static final String BENVENUTO_MSG = "********* PROGRAMMA CODICE FISCALE *********";
    private static final String LETTURA_FILE_MSG = "Lettura %s in corso...";
    private static final String LETTURA_FILE_RIUSCITA_MSG = "Lettura del file %s completata";
    private static final String SCRITTURA_FILE_MSG  = "Creazione del file %s in corso...";
    private static final String SCRITTURA_FILE_RIUSCITA_MSG = "File creato correttamente";

    public static void main(String[] args) {
        try {
            System.out.println(BENVENUTO_MSG);

            //Lettura file inputFiles/inputPersone.xml
            System.out.println(String.format(LETTURA_FILE_MSG, "inputPersone.xml"));
            ArrayList<Persona> persone = XmlUtilities.leggiPersone();
            System.out.println(String.format(LETTURA_FILE_RIUSCITA_MSG, "inputPersone.xml"));

            //Lettura file inputFiles/comuni.xml
            System.out.println(String.format(LETTURA_FILE_MSG, "comuni.xml"));
            ArrayList<Comune> comuni = XmlUtilities.leggiComuni();
            System.out.println(String.format(LETTURA_FILE_RIUSCITA_MSG, "comuni.xml"));

            //Lettura file inputFiles/codicifiscali.xml
            System.out.println(String.format(LETTURA_FILE_MSG, "codicifiscali.xml"));
            ArrayList<String> codiciFiscali = XmlUtilities.leggiCodiciFiscali();
            System.out.println(String.format(LETTURA_FILE_RIUSCITA_MSG, "codicifiscali.xml"));

            //Inserisce il codice fiscale rispettivo ad ogni persona
            for (Persona p : persone) {
                p.setCodiceFiscale(CodiceFiscaleUtilities.generaCodiceFiscale(p, comuni));
            }

            //Creazione file codiciPersone.xml
            System.out.println(String.format(SCRITTURA_FILE_MSG, "codiciPersone.xml"));
            XmlUtilities.produciOutput(codiciFiscali, persone, comuni);
            System.out.println(String.format(SCRITTURA_FILE_RIUSCITA_MSG, "codiciPersone.xml"));

        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

    }

}
