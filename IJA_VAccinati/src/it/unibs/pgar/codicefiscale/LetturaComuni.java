package it.unibs.pgar.codicefiscale;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Si occupa in primis della lettura del file xml comuni.
 * Traduce, organizza e memorizza inoltre i dati letti.
 */
public class LetturaComuni {
    /**
     * Salva i comuni, con i rispettivi nomi e codici, scritti in comuni.xml
     * in un array di oggetti Comune
     *
     * @return comuni: ArrayList Comune
     */
    public static ArrayList<Comune> esecuzioneLetturaComuni() {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        ArrayList<Comune> comuni = new ArrayList<Comune>();

        try {
            String path = new File("src/comuni.xml").getPath();
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));

            int i = 0;

            String variabileDaAggiornare = "";

            String nome = "";
            String codice = "";

            while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
                switch (xmlr.getEventType()) { // switch sul tipo di evento
                    case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: salva il nome del tag
                        variabileDaAggiornare = xmlr.getLocalName();
                        break;

                    case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: salva le informazione contenute
                        if (xmlr.getText().trim().length() > 0) { // controlla se il testo non contiene solo spazi
                            if (variabileDaAggiornare.equals("nome"))
                                nome = xmlr.getText();
                            else if (variabileDaAggiornare.equals("codice"))
                                codice = xmlr.getText();
                        }
                        break;
                }
                if (!nome.equals("") && !codice.equals("")) {
                    comuni.add(i, new Comune(nome, codice));
                    nome = "";
                    codice = "";
                    i++;
                }

                xmlr.next();
            }

        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        return comuni;

    }

}
