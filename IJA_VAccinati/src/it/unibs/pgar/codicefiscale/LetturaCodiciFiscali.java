package it.unibs.pgar.codicefiscale;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Si occupa in primis della lettura del file xml codiciFiscali.
 * Traduce, organizza e memorizza inoltre i dati letti.
 */
public class LetturaCodiciFiscali {
    /**
     * Estrapola dalla lettura del file xml codiciFiscali un array di stringhe
     * contenente tutti i codici
     *
     * @return codiciFiscali: ArrayList String
     */
    public static ArrayList<String> esecuzioneLetturaCodiciFiscali() {

        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        ArrayList<String> codiciFiscali = new ArrayList<String>();

        try {
            String path = new File("src/codiciFiscali.xml").getPath();
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));

            int i = 0;
            String variabileDaAggiornare = "";
            String codiceFiscale = "";

            while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
                switch (xmlr.getEventType()) { // switch sul tipo di evento
                    case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: salva il nome del tag
                        variabileDaAggiornare = xmlr.getLocalName();
                        break;
                    case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: salva il codice fiscale
                        if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            if (variabileDaAggiornare.equals("codice"))
                                codiceFiscale = xmlr.getText();
                        break;
                }

                if (!codiceFiscale.equals("")) {
                    codiciFiscali.add(i, codiceFiscale);
                    codiceFiscale = "";
                    i++;
                }

                xmlr.next();
            }

        } catch (
                Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        return codiciFiscali;

    }

}
