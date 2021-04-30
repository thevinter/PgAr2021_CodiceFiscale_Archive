package codiceFiscale;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReaderXML {
    private static final String STRINGAINIZIOLETTURA = "inzio lettura file: ";
    private static final String STRINGAFINELETTURA = "fine lettura file: ";
    private static final String ERROREREADER = "Errore nell'inizializzazione del reader:";
    private static final String COMUNE = "comune";

    private Map<String, String> elenco_comuni;
    private ArrayList<CodiceFiscale> elenco_codici_fiscali;
    private ArrayList<CodiceFiscale> elenco_codici_invalidi;
    private ArrayList<Persona> elenco_persone;

    //getters and setters

    /**
     * ritorna la map dei comunu <nome, codice>
     * @return
     */
    public Map<String, String> getElenco_comuni() {
        return elenco_comuni;
    }

    /**
     * ritorna l'arrayList di tutti i codici fiscali validi (spaiati e non)
     * @return
     */
    public ArrayList<CodiceFiscale> getElenco_codici_fiscali() {
        return elenco_codici_fiscali;
    }

    /**
     * imposta l'arrayList dei codici fiscali
     * @param elenco_codici_fiscali
     */
    public void setElenco_codici_fiscali(ArrayList<CodiceFiscale> elenco_codici_fiscali) {
        this.elenco_codici_fiscali = elenco_codici_fiscali;
    }

    /**
     * ritorna l'arraylist di tutti i codici fiscali invalidi
     * @return
     */
    public ArrayList<CodiceFiscale> getElenco_codici_invalidi() {
        return elenco_codici_invalidi;
    }

    /**
     * imposta l'arrayList dei codici fiscali invalidi
     * @param elenco_codici_invalidi
     */
    public void setElenco_codici_invalidi(ArrayList<CodiceFiscale> elenco_codici_invalidi) {
        this.elenco_codici_invalidi = elenco_codici_invalidi;
    }

    /**
     * ritorna l'arraylist di tutte le persone
     * @return
     */
    public ArrayList<Persona> getElenco_persone() {
        return elenco_persone;
    }

    /**
     * imposta l'arrayList di tutte le persone
     * @param elenco_persone
     */
    public void setElenco_persone(ArrayList<Persona> elenco_persone) {
        this.elenco_persone = elenco_persone;
    }

    /**
     * Costruttore di ReaderXML.
     * Inizializza gli ArrayList e la HashMap che verranno riempiti durante la lettura dell'xml.
     */
    public ReaderXML() {
        elenco_codici_fiscali = new ArrayList<CodiceFiscale>();
        elenco_codici_invalidi = new ArrayList<CodiceFiscale>();
        elenco_persone = new ArrayList<Persona>();
        elenco_comuni=new HashMap<>();
    }

    /**
     * Metodo che serve per leggere il file comuni.xml, analizzare i dati contenuti nell'xml attraverso
     * i metodi dello XMLStreamReader e creare una Map<String, String> costituita dal nome del comune
     * e dal rispettivo codice.
     * @param filename il file comuni.xml che verrà fornito al metodo alla chiamata nel Main.
     */
    public void LeggiXMLComuni (String filename) {
        //Questo frammento di codice serve a creare ed istanziare la variabile xmlr di tipo XMLStreamReader, che sarà
        //utilizzata per leggere il file XML
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
            Comune c = null;
            System.out.println(STRINGAINIZIOLETTURA+filename);
            //Legge il File xml fino a quando ci sono eventi di parsing disponibili
            while (xmlr.hasNext()) {
                //Se trova un evento di tipo START.ELEMENT controlla il nome del tag dell'elemento corrente
                if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    String nome_tag = xmlr.getLocalName();
                    switch (nome_tag) {
                        //Se il tag è "comune" allora crea un nuovo oggetto Comune
                        case COMUNE:
                            c = new Comune();
                            break;
                        //Se il tag è "nome" allora prende il testo all'interno dell'elemento e lo assegna
                        //all'attributo nome del Comune creato precedentemente
                        case WriterXML.NOME:
                            xmlr.next();
                            c.setNome(xmlr.getText());
                            xmlr.next();
                            break;
                        //Se il tag è "codice" allora prende il testo all'interno dell'elemento e lo assegna
                        //all'attributo codice del Comune creato precedentemente
                        case WriterXML.CODICE:
                            xmlr.next();
                            c.setCodice(xmlr.getText());
                            xmlr.next();
                            break;
                    }
                //Se trova un evento di tipo END.ELEMENT controlla il nome del tag dell'elemento corrente
                } else if (xmlr.getEventType() == XMLStreamConstants.END_ELEMENT) {
                    //Se il tag è "comune" allora assegna alla Map elenco_comuni i valori del Comune (nome, codice)
                    if (xmlr.getLocalName().equals(COMUNE)) {
                        elenco_comuni.put(c.getNome(), c.getCodice());
                    }
                }
                //Passa all’evento successivo
                xmlr.next();
            }
            System.out.println(STRINGAFINELETTURA+filename);
        } catch (Exception e) {
            System.out.println(ERROREREADER);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo che serve per leggere il file codiciFiscali.xml, analizzare i dati contenuti nell'xml attraverso
     * i metodi dello XMLStreamReader e aggiungere i codici fiscali nei 2 ArrayList di CodiceFiscale
     * inizializzati nel costruttore del ReaderXML. I codici fiscali validi vengono aggiunti
     * nell'ArrayList elenco_codici_fiscali, quelli non validi nell'ArrayList elenco_codici_invalidi.
     * @param filename il file codiciFiscali.xml che verrà fornito al metodo alla chiamata nel Main.
     */
    public void LeggiXMLCodiciFiscali (String filename) {
        //Questo frammento di codice serve a creare ed istanziare la variabile xmlr di tipo XMLStreamReader, che sarà
        //utilizzata per leggere il file XML
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
            CodiceFiscale c = null;
            System.out.println(STRINGAINIZIOLETTURA+filename);
            //Legge il File xml fino a quando ci sono eventi di parsing disponibili
            while (xmlr.hasNext()) {
                //Se trova un evento di tipo START.ELEMENT controlla il nome del tag dell'elemento corrente
                if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    String nome_tag = xmlr.getLocalName();
                    //Se il tag è "codice" crea un nuovo oggetto CodiceFiscale e gli assegna il codice trovato all'interno dell'elemento
                    if (nome_tag.equals(WriterXML.CODICE)) {
                        c = new CodiceFiscale();
                        xmlr.next();
                        c.setCodice(xmlr.getText());
                    }
                //Se trova un evento di tipo END.ELEMENT controlla il nome del tag dell'elemento corrente
                } else if (xmlr.getEventType() == XMLStreamConstants.END_ELEMENT) {
                    //Se il tag è "codice", aggiunge il CodiceFiscale all'ArrayList opportuno
                    if (xmlr.getLocalName().equals(WriterXML.CODICE)){
                        if (MetodiDiControllo.isValido(c.getCodice())) {
                            elenco_codici_fiscali.add(c);
                        }
                        //Se il codice fiscale NON è valido lo aggiunge a elenco_codici_invalidi
                        else elenco_codici_invalidi.add(c);
                    }
                }
                //Passa all’evento successivo
                xmlr.next();
            }
            System.out.println(STRINGAFINELETTURA+filename);
        } catch (Exception e) {
            System.out.println(ERROREREADER);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo che serve per leggere il file inputPersone.xml e analizzare i dati contenuti nell'xml attraverso
     * i metodi dello XMLStreamReader. Crea oggetti di classe Persona che hanno come attributi id, nome, cognome, sesso,
     * data di nascita, Comune (nome e codice attraverso la Map elenco_comuni) e crea per ognuno il codice fiscale. I vari
     * oggetti Persona vengono aggiunti all'ArrayList elenco_persone, già inizializzato nel costruttore del ReaderXML.
     * @param filename il file inputPersone.xml che verrà fornito al metodo alla chiamata nel Main.
     */
    public void LeggiXMLInputPersone (String filename){
        //Questo frammento di codice serve a creare ed istanziare la variabile xmlr di tipo XMLStreamReader, che sarà
        //utilizzata per leggere il file XML
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
            Persona p = null;
            System.out.println(STRINGAINIZIOLETTURA+filename);
            //Legge il File xml fino a quando ci sono eventi di parsing disponibili
            while (xmlr.hasNext()){
                //Se trova un evento di tipo START.ELEMENT controlla il nome del tag dell'elemento corrente
                if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT){
                    String nome_tag = xmlr.getLocalName();
                    switch (nome_tag){
                        //Se il tag è "persona" allora crea un nuovo oggetto Persona e assegna l'id
                        case WriterXML.PERSONA:
                            p = new Persona();
                            p.setId(xmlr.getAttributeValue(0));
                            break;
                        //Se il tag è "nome" allora prende il testo all'interno dell'elemento e lo assegna
                        //all'attributo nome della Persona creata precedentemente
                        case WriterXML.NOME:
                            xmlr.next();
                            p.setNome(xmlr.getText());
                            xmlr.next();
                            break;
                        //Se il tag è "cognome" allora prende il testo all'interno dell'elemento e lo assegna
                        //all'attributo cognome della Persona
                        case WriterXML.COGNOME:
                            xmlr.next();
                            p.setCognome(xmlr.getText());
                            xmlr.next();
                            break;
                        //Se il tag è "sesso" allora prende il testo all'interno dell'elemento e lo assegna
                        //all'attributo sesso della Persona
                        case WriterXML.SESSO:
                            xmlr.next();
                            p.setSesso(xmlr.getText().charAt(0));
                            xmlr.next();
                            break;
                        //Se il tag è "comune_nascita" allora prende il testo all'interno dell'elemento e utilizzando la
                        //Map elenco_comuni, crea un nuovo Comune dotato di nome e codice e lo assegna alla Persona
                        case WriterXML.COMUNE_NASCITA:
                            xmlr.next();
                            String comune = xmlr.getText();
                            p.setComune(new Comune(comune, elenco_comuni.get(comune)));
                            xmlr.next();
                            break;
                        //Se il tag è "data_nascita" allora prende il testo all'interno dell'elemento e lo assegna
                        //all'attributo dataDiNascita della Persona
                        case WriterXML.DATA_NASCITA:
                            xmlr.next();
                            p.setDataDiNascita(xmlr.getText());
                            xmlr.next();
                            break;
                    }
                }
                //Se trova un evento di tipo END.ELEMENT controlla il nome del tag dell'elemento corrente
                else if (xmlr.getEventType() == XMLStreamConstants.END_ELEMENT){
                    //Se il tag è "persona", crea il codice fiscale della Persona presi tutti i dati della persona, lo
                    //assegna all'attributo cf di Persona.
                    if (xmlr.getLocalName().equals(WriterXML.PERSONA)) {
                        p.setCf(new CodiceFiscale(p.getNome(), p.getCognome(), p.getDataDiNascita(), p.getSesso(), p.getComune().getCodice()));
                        //Aggiunge la persona all'ArrayList elenco.persone
                        elenco_persone.add(p);
                    }
                }
                //Passa all’evento successivo
                xmlr.next();
            }
            System.out.println(STRINGAFINELETTURA+filename);
        } catch (Exception e) {
            System.out.println(ERROREREADER);
            System.out.println(e.getMessage());
        }
    }
}