package it.unibs.pgar.codicefiscale;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Scrive il file xml codiciPersone associando alle persone di InputPersone.xml il rispettivo codice fiscale se presente in
 * codiciFiscali.xml. Inoltre scrive i codici fiscali non abbinati distinguendoli in invalidi e spaiati
 */
public class ScritturaCodiciPersone {

    public static final String ASSENTE = "ASSENTE";

    /**
     * Inizializza e scrive il file codiciPersone.xml
     * Questo file viene diviso in 3 sezioni:
     * -Persone, ottenute da inputPersone.xml, e il relativo codice fiscale(se presente in codiciFiscali.xml)
     * -Invalidi, ovvero i codici di codiciFiscali.xml che non seguono le regole illustrare su https://it.wikipedia.org/wiki/Codice_fiscale
     * -Spaiati, ovvero i codici di codiciFiscali.xml che, nonostante siano corretti, non individuano nessuna persona di inputPersone.xml
     *
     * @param persone:       ArrayList Persone
     * @param codiciFiscali: ArrayList String
     * @param comuni:        ArrayList Comune
     */
    public static void esecuzioneScritturaCodiciPersone(ArrayList<Persona> persone, ArrayList<String> codiciFiscali, ArrayList<Comune> comuni) {

        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("src/codiciPersone.xml"), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }

        try { // blocco try per raccogliere eccezioni
            xmlw.writeCharacters("\n");
            xmlw.writeStartElement("output"); // scrittura del tag radice <output>

            xmlw.writeCharacters("\n\t");
            xmlw.writeStartElement("persone");
            xmlw.writeAttribute("numero", Integer.toString(persone.size()));
            for (int i = 0; i < persone.size(); i++) {
                xmlw.writeCharacters("\n\t\t");
                xmlw.writeStartElement("persona");
                xmlw.writeAttribute("id", Integer.toString(i));

                xmlw.writeCharacters("\n\t\t\t");
                xmlw.writeStartElement("nome");
                xmlw.writeCharacters(persone.get(i).getNome());
                xmlw.writeEndElement();

                xmlw.writeCharacters("\n\t\t\t");
                xmlw.writeStartElement("cognome");
                xmlw.writeCharacters(persone.get(i).getCognome());
                xmlw.writeEndElement();

                xmlw.writeCharacters("\n\t\t\t");
                xmlw.writeStartElement("sesso");
                xmlw.writeCharacters(persone.get(i).getSesso().toString());
                xmlw.writeEndElement();

                xmlw.writeCharacters("\n\t\t\t");
                xmlw.writeStartElement("comune_nascita");
                xmlw.writeCharacters(persone.get(i).getComune().getNome());
                xmlw.writeEndElement();

                xmlw.writeCharacters("\n\t\t\t");
                xmlw.writeStartElement("data_nascita");
                xmlw.writeCharacters(persone.get(i).getDataDiNascita().toString());
                xmlw.writeEndElement();

                xmlw.writeCharacters("\n\t\t\t");
                xmlw.writeStartElement("codice_fiscale");
                if (isPresente(persone.get(i).getCodiceFiscale(), codiciFiscali))
                    xmlw.writeCharacters(persone.get(i).getCodiceFiscale());
                else
                    xmlw.writeCharacters(ASSENTE);
                xmlw.writeEndElement();

                xmlw.writeCharacters("\n\t\t");
                xmlw.writeEndElement();
            }
            xmlw.writeCharacters("\n\t");
            xmlw.writeEndElement();

            ArrayList<String> invalidi = cercaInvalidi(codiciFiscali, comuni);
            ArrayList<String> spaiati = cercaSpaiati(persone, codiciFiscali, invalidi);

            xmlw.writeCharacters("\n\t");
            xmlw.writeStartElement("invalidi");
            xmlw.writeAttribute("numero", Integer.toString(invalidi.size()));
            for (int i = 0; i < invalidi.size(); i++) {
                xmlw.writeCharacters("\n\t\t");
                xmlw.writeStartElement("codice");
                xmlw.writeCharacters(invalidi.get(i));
                xmlw.writeEndElement();
            }
            xmlw.writeCharacters("\n\t");
            xmlw.writeEndElement();

            xmlw.writeCharacters("\n\t");
            xmlw.writeStartElement("spaiati");
            xmlw.writeAttribute("numero", Integer.toString(spaiati.size()));
            for (int i = 0; i < spaiati.size(); i++) {
                xmlw.writeCharacters("\n\t\t");
                xmlw.writeStartElement("codice");
                xmlw.writeCharacters(spaiati.get(i));
                xmlw.writeEndElement();
            }
            xmlw.writeCharacters("\n\t");
            xmlw.writeEndElement();

            xmlw.writeCharacters("\n");
            xmlw.writeEndElement(); // chiusura di </output>
            xmlw.writeEndDocument(); // scrittura della fine del documento
            xmlw.flush(); // svuota il buffer e procede alla scrittura
            xmlw.close(); // chiusura del documento e delle risorse impiegate
        } catch (Exception e) { // se c’è un errore viene eseguita questa parte
            System.out.println("Errore nella scrittura " + e.getMessage());
        }
    }

    /**
     * Individua se una stringa (un codice fiscale) e' presente in un array di stringhe(insieme di codici fiscali)
     *
     * @param codiceFiscalePersona: String
     * @param codiciFiscali:        ArrayList String
     * @return isPresente: boolean
     */
    public static boolean isPresente(String codiceFiscalePersona, ArrayList<String> codiciFiscali) {
        for (int i = 0; i < codiciFiscali.size(); i++)
            if (codiceFiscalePersona.equals(codiciFiscali.get(i)))
                return true;
        return false;
    }

    /**
     * Determina quali codici fiscali sono errati (ci basiamo su https://it.wikipedia.org/wiki/Codice_fiscale) in
     * un array di stringhe, ciascuna delle quali e' un potenziale codice fiscale
     *
     * @param codiciFiscali: ArrayList String
     * @param comuni:        ArrayList Comune
     * @return invalidi: ArrayList String
     */
    public static ArrayList<String> cercaInvalidi(ArrayList<String> codiciFiscali, ArrayList<Comune> comuni) {
        ArrayList<String> invalidi = new ArrayList<String>();

        for (int i = 0; i < codiciFiscali.size(); i++) {
            String codice = codiciFiscali.get(i);
            char[] codiceArray = codice.toCharArray();

            //controllo lunghezza
            if (codice.length() != 16) {
                invalidi.add(codice);
                continue;
            }

            //controllo lettere nei primi 6 caratteri
            boolean deviUscire = false;
            for (int j = 0; j < 6 && !deviUscire; j++) {
                if (codiceArray[j] > 'Z' || codiceArray[j] < 'A') {
                    invalidi.add(codice);
                    deviUscire = true;
                }
            }
            if (deviUscire == true)
                continue;

            //controllo numeri nei caratteri 7 e 8
            for (int j = 6; j < 8 && !deviUscire; j++) {
                if (codiceArray[j] > '9' || codiceArray[j] < '0') {
                    invalidi.add(codice);
                    deviUscire = true;
                }
            }
            if (deviUscire == true)
                continue;

            //controllo caratteri mesi
            char[] mesi = {'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T'};
            int mese = -1;
            for (int j = 0; j < mesi.length && !deviUscire; j++) {
                if (codiceArray[8] == mesi[j]) {
                    mese = j;
                    break;
                }
                if (mesi[j] == 'T') {
                    invalidi.add(codice);
                    deviUscire = true;
                }
            }
            if (deviUscire == true)
                continue;

            //controllo giorno data di nascita
            if ((codiceArray[9] < '0' && codiceArray[9] > '9') || (codiceArray[10] < '0' && codiceArray[10] > '9')) {
                invalidi.add(codice);
                continue;
            }
            int giorno = (codiceArray[9] - '0') * 10 + (codiceArray[10] - '0');
            if ((giorno < 1) || (giorno > 31 && giorno < 41) || (giorno > 71)) {
                invalidi.add(codice);
                continue;
            }
            int[] giorniPerMese = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int anno = (codiceArray[6] - '0') * 10 + codiceArray[7] - '0';
            if (anno % 4 == 0)
                giorniPerMese[1]++;
            if (mese != -1 && (giorno < 41 && giorno > giorniPerMese[mese]) || (giorno > giorniPerMese[mese] + 40)) {
                invalidi.add(codice);
                continue;
            }

            //controllo codice comune
            String codiceComune = codice.substring(11, 15);
            for (int j = 0; j < comuni.size() && !deviUscire; j++) {
                if (codiceComune.equals(comuni.get(j).getCodice()))
                    deviUscire = true;
            }
            if (!deviUscire) {
                invalidi.add(codice);
                continue;
            }
            deviUscire = false;

            //controllo carattere di controllo
            int count = 0;
            for (int j = 0; j < codiceArray.length - 1; j++) {
                for (int k = 0; k < ValoriCaratteri.values().length; k++) {
                    if (codiceArray[j] == ValoriCaratteri.values()[k].getCosaRappresentano()) {
                        //correzione di 1 perché lo Stato conta da 1... che stupidi
                        if (j % 2 == 0) {
                            count += ValoriCaratteri.values()[k].getValoreDispari();
                            break;
                        } else {
                            count += ValoriCaratteri.values()[k].getValorePari();
                            break;
                        }
                    }
                }
            }
            int resto = count % 26;
            final char[] ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
            if (codiceArray[15] != ALFABETO[resto]) {
                invalidi.add(codice);
                continue;
            }
        }
        return invalidi;
    }

    /**
     * Individua per esclusione (codici non relativi ad una persona di inputPersone.xml ne' invalidi) quali codici di codiciFiscali.xml
     * sono corretti, ma non individuano una persona in inputPersone.xml
     *
     * @param persone:       ArrayList Persona
     * @param codiciFiscali: ArrayList String
     * @param invalidi:      ArrayList String
     * @return spaiati: ArrayList String
     */
    public static ArrayList<String> cercaSpaiati(ArrayList<Persona> persone, ArrayList<String> codiciFiscali, ArrayList<String> invalidi) {
        ArrayList<String> spaiati = new ArrayList<String>();
        boolean trovato = false;
        for (int i = 0; i < codiciFiscali.size(); i++) {
            for (int j = 0; j < persone.size() && !trovato; j++) {
                if (codiciFiscali.get(i).equals(persone.get(j).getCodiceFiscale())) {
                    trovato = true;
                }
            }
            for (int k = 0; k < invalidi.size() && !trovato; k++) {
                if (invalidi.get(k).equals(codiciFiscali.get(i))) {
                    trovato = true;
                }
            }
            if (trovato == false)
                spaiati.add(codiciFiscali.get(i));
            trovato = false;
        }

        return spaiati;
    }
}
