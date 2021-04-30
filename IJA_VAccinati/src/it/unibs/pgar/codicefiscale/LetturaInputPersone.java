package it.unibs.pgar.codicefiscale;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Si occupa della lettura in primis della lettura del file xml inputPersone.
 * Traduce, organizza e memorizza inoltre i dati letti.
 */
public class LetturaInputPersone {
    /**
     * Salva in un array i dati(nome, cognome, sesso, data di nascita e comune di nascita) delle persone contenute
     * nel file in un array. Inoltre per ciascuna di esse ne calcola il codice fiscale.
     *
     * @param comuni: ArrayList Comune
     * @return persone: ArrayList Persone
     */
    public static ArrayList<Persona> esecuzioneLetturaPersone(ArrayList<Comune> comuni) {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        ArrayList<Persona> persone = new ArrayList<Persona>();

        try {
            String path = new File("src/inputPersone.xml").getPath();
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));

            int i = 0;

            String variabileDaAggiornare = "";

            String nome = "";
            String cognome = "";
            Sesso sesso = Sesso.DA_DEFINIRE;
            Comune comune_nascita = new Comune("", "");
            Data data_nascita = new Data(0, 0, 0);

            while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
                switch (xmlr.getEventType()) { // switch sul tipo di evento
                    case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
                        variabileDaAggiornare = xmlr.getLocalName();
                        break;

                    case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
                        if (xmlr.getText().trim().length() > 0) { // controlla se il testo non contiene solo spazi
                            if (variabileDaAggiornare.equals("nome"))
                                nome = xmlr.getText();
                            else if (variabileDaAggiornare.equals("cognome"))
                                cognome = xmlr.getText();
                            else if (variabileDaAggiornare.equals("sesso")) {
                                switch (xmlr.getText()) {
                                    case "M":
                                        sesso = Sesso.M;
                                        break;
                                    case "F":
                                        sesso = Sesso.F;
                                        break;
                                    default:
                                        sesso = Sesso.DA_DEFINIRE;
                                        break;
                                }
                            } else if (variabileDaAggiornare.equals("comune_nascita")) {
                                comune_nascita = Comune.generaComune(comuni, xmlr.getText());
                            } else if (variabileDaAggiornare.equals("data_nascita"))
                                data_nascita = Data.generaData(xmlr.getText());
                        }
                        break;
                }

                if (!nome.equals("") && !cognome.equals("") && !sesso.equals(Sesso.DA_DEFINIRE) && !comune_nascita.getNome().equals("") && data_nascita.getAnno() != 0) {
                    persone.add(i, new Persona(nome, cognome, sesso, comune_nascita, data_nascita));
                    nome = "";
                    cognome = "";
                    sesso = Sesso.DA_DEFINIRE;
                    comune_nascita = new Comune("", "");
                    data_nascita = new Data(0, 0, 0);
                    i++;
                }

                xmlr.next();
            }

        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < persone.size(); i++)
            persone.get(i).setCodiceFiscale(persone.get(i).generaCodiceFiscale());

        return persone;

    }

}
