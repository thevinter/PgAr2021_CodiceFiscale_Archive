package codiceFiscale;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class WriterXML {
    private static final String OUTPUT = "output";
    private static final String CODICI = "codici";
    private static final String NUMERO = "numero";
    private static final String CODICE_FISCALE = "codice_fiscale";
    private static final String ASSENTE = "ASSENTE";
    private static final String INVALIDI = "invalidi";
    private static final String SPAIATI = "spaiati";
    private static final String PERSONE = "persone";
    public static final String PERSONA = "persona";
    private static final String ID = "id";
    public static final String NOME = "nome";
    public static final String COGNOME = "cognome";
    public static final String SESSO = "sesso";
    public static final String COMUNE_NASCITA = "comune_nascita";
    public static final String DATA_NASCITA = "data_nascita";
    public static final String CODICE = "codice";
    private static final String ERROREWRITER = "Errore nell'inizializzazione del writer:";

    /**Costruttore del WriterXML
     */
    public WriterXML(){}

    /**Metodo utilizzato per creare il file codiciPersone.xml
     * Il file codiciPersone.xml è costituito nel seguente modo (i dati inseriti sono esempi):
     * <output>
     *     <persone numero="1">
     *         <persona id="0">
     *             <nome>Chiara</nome>
     *             <cognome>Morra</cognome>
     *             <sesso>F</sesso>
     *             <comune_nascita>Onzo</comune_nascita>
     *             <data_nascita>1957-12-04</data_nascita>
     *             <codice_fiscale>ASSENTE</codice_fiscale>
     *         </persona>
     *     </persone>
     *     <codici>
     *         <invalidi numero=”2”>
     *             <codice>GDALKU87F92E2D2R</codice>
     *             <codice>ABCDEF78B57A024T</codice>
     *         </invalidi>
     *         <spaiati numero=”2”>
     *             <codice>MNTPRZ24T67M178V</codice>
     *             <codice>PVNDSR19T68A293P</codice>
     *         </spaiati>
     *     </codici>
     * </output>
     * In "codice fiscale" viene inserito il codice fiscale della persona se esiste anche in elenco_codici_fiscali,
     * altrimenti viene scritto ASSENTE
     * @param elenco_persone ArrayList di Persona, dal quale verranno presi i dati e scritti sul file xml.
     * @param elenco_codici_fiscali ArrayList di CodiceFiscale, contenente codici fiscali validi, che nel file xml verranno divisi in spaiati e appaiati(con una Persona).
     * @param elenco_codici_invalidi ArrayList di CodiceFiscale contenente codici fiscali non validi, che verranno scritti sul file xml.
     * @param filePath il file codiciPersone.xml creato
     */
    public void ScriviXML(ArrayList<Persona> elenco_persone, ArrayList<CodiceFiscale> elenco_codici_fiscali, ArrayList<CodiceFiscale> elenco_codici_invalidi,String filePath) {
        //Questo frammento di codice serve a creare ed istanziare la variabile xmlw di tipo XMLStreamWriter, che
        //sarà utilizzata per scrivere il file XML. Viene inoltre inizializzato il documento XML.
        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filePath), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println(ERROREWRITER);
            System.out.println(e.getMessage());
            return;
        }
        try {
            //Scrive un tag d’apertura di "output"
            xmlw.writeStartElement(OUTPUT);
            //Scrive un tag d’apertura di "persone"
            xmlw.writeStartElement(PERSONE);
            //Scrive l'attributo "numero" di "persone", ovvero il numero di persone presenti nell'elenco
            xmlw.writeAttribute(NUMERO, Integer.toString(elenco_persone.size()));
            System.out.println("numero persone da aggiungere: "+elenco_persone.size());
            //Ciclo utilizzato per scrivere i dati di tutte le persone presenti in elenco_persone
            for (int i = 0; i < elenco_persone.size(); i++) {
                //Scrive un tag d’apertura di "persona"
                xmlw.writeStartElement(PERSONA);
                //Scrive l'attributo "id" di "persona"
                xmlw.writeAttribute(ID, elenco_persone.get(i).getId());
                //Scrive un tag d’apertura di "nome"
                xmlw.writeStartElement(NOME);
                //Scrive il nome della Persona corrente
                xmlw.writeCharacters(elenco_persone.get(i).getNome());
                //Scrive il tag di chiusura di "nome"
                xmlw.writeEndElement();
                //Scrive un tag d’apertura di "cognome"
                xmlw.writeStartElement(COGNOME);
                //Scrive il cognome della Persona corrente
                xmlw.writeCharacters(elenco_persone.get(i).getCognome());
                //Scrive il tag di chiusura di "cognome"
                xmlw.writeEndElement();
                //Scrive un tag d’apertura di "sesso"
                xmlw.writeStartElement(SESSO);
                //Scrive il sesso della Persona corrente
                xmlw.writeCharacters(Character.toString(elenco_persone.get(i).getSesso()));
                //Scrive il tag di chiusura di "sesso"
                xmlw.writeEndElement();
                //Scrive un tag d’apertura di "comune nascita"
                xmlw.writeStartElement(COMUNE_NASCITA);
                //Scrive il nome del Comune della Persona corrente
                xmlw.writeCharacters(elenco_persone.get(i).getComune().getNome());
                //Scrive il tag di chiusura di "comune nascita"
                xmlw.writeEndElement();
                //Scrive un tag d’apertura di "data nascita"
                xmlw.writeStartElement(DATA_NASCITA);
                //Scrive la dataDiNascita della Persona corrente
                xmlw.writeCharacters(elenco_persone.get(i).getDataDiNascita());
                //Scrive il tag di chiusura di "data nascita"
                xmlw.writeEndElement();
                //Scrive un tag d’apertura di "codice fiscale"
                xmlw.writeStartElement(CODICE_FISCALE);
                //Prende il codice fiscale della Persona corrente
                CodiceFiscale cf = elenco_persone.get(i).getCf();
                //Se il codice fiscale è presente in elenco_codici_fiscali, scrive il codice fiscale della Persona
                //(e il metodo isPresente rimuove il codice fiscale corrente da elenco_codici_fiscali)
                if (cf.isPresente(elenco_codici_fiscali))
                    xmlw.writeCharacters(elenco_persone.get(i).getCf().getCodice());
                //Se il codice fiscale non è presente in elenco_codici_fiscali, scrive "ASSENTE"
                else xmlw.writeCharacters(ASSENTE);
                //Scrive il tag di chiusura di "codice fiscale"
                xmlw.writeEndElement();
                //Scrive il tag di chiusura di "persona"
                xmlw.writeEndElement();
            }
            //Scrive il tag di chiusura di "persone"
            xmlw.writeEndElement();
        } catch (Exception e) { // se trova un errore viene eseguita questa parte
            System.out.println("Errore nella scrittura persone");
            return;
        }
        try {
            //Scrive un tag d’apertura di "codici"
            xmlw.writeStartElement(CODICI);
            //Scrive un tag d’apertura di "invalidi"
            xmlw.writeStartElement(INVALIDI);
            //Scrive l'attributo "numero" di "invalidi", ovvero il numero di elementi presenti in elenco_codici_invalidi
            xmlw.writeAttribute(NUMERO, Integer.toString(elenco_codici_invalidi.size()));
            System.out.println("numero codici Invalidi da aggiungere: "+elenco_codici_invalidi.size());
            //Ciclo che stampa i codici invalidi
            for (int i=0; i<elenco_codici_invalidi.size(); i++) {
                //Scrive un tag d’apertura di "codice"
                xmlw.writeStartElement(CODICE);
                //Scrive il codice fiscale corrente
                xmlw.writeCharacters(elenco_codici_invalidi.get(i).getCodice());
                //Scrive il tag di chiusura di "codice"
                xmlw.writeEndElement();
            }
            //Scrive il tag di chiusura di "invalidi"
            xmlw.writeEndElement();
            //Scrive un tag d’apertura di "spaiati"
            xmlw.writeStartElement(SPAIATI);
            //Scrive l'attributo "numero" di "spaiati", ovvero il numero di elementi presenti in elenco_codici_invalidi
            xmlw.writeAttribute(NUMERO, Integer.toString(elenco_codici_fiscali.size()));
            System.out.println("numero codici spaiati da aggiungere: "+elenco_codici_fiscali.size());
            //Ciclo che stampa i codici spaiati
            for (int i=0; i<elenco_codici_fiscali.size(); i++) {
                //Scrive un tag d’apertura di "codice"
                xmlw.writeStartElement(CODICE);
                //Scrive il codice fiscale corrente
                xmlw.writeCharacters(elenco_codici_fiscali.get(i).getCodice());
                //Scrive il tag di chiusura di "codice"
                xmlw.writeEndElement();
            }
            //Scrive il tag di chiusura di "spaiati"
            xmlw.writeEndElement();
            //Scrive il tag di chiusura di "codici"
            xmlw.writeEndElement();
            //Scrive il tag di chiusura di "output"
            xmlw.writeEndElement();
            //Termina la scrittura del documento
            xmlw.writeEndDocument();
            //Permette di scrivere su file i dati ancora in attesa nel buffer
            xmlw.flush();
            //Chiude il writer e libera tutte le risorse ad esso associate
            xmlw.close();
        } catch (Exception e) { // se trova un errore viene eseguita questa parte
            System.out.println(ERROREWRITER);
            return;
        }
    }
}