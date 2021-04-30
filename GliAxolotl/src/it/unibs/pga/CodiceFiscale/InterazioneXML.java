package it.unibs.pga.CodiceFiscale;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamException;

public class InterazioneXML {

    /**
     * Metodo che serve a generare il file xml di output, andando a leggere i vari file dati in partenza.
     * Produrrà un file contenente tutti i dati delle persone (dirà se il codice fiscale della persona esiste nella lista fornita),
     * specificcando poi quali tra i codici forniti sono invalidi e quali sono corretti ma non hanno una persona corrispondente
     * nel file di input
     * @param num_pers
     * @throws XMLStreamException
     */
    public void ScritturaXML(int num_pers) throws XMLStreamException {

        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        try {//inizzializzazione del writer
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(Costanti.NOME_FILE_DI_OUTPUT_XML), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println(Costanti.ERRORE_NELL_INIZIALIZZAZIONE_DEL_WRITER);
            System.out.println(e.getMessage());
        }

        GeneratoreCodiceFiscale gcf = new GeneratoreCodiceFiscale();
        ArrayList <String> totali = leggiCodiceXML();
        ArrayList <String> accoppiati = new ArrayList<>();

        try { // blocco try per raccogliere eccezioni
            xmlw.writeCharacters("\n");
            xmlw.writeStartElement(Costanti.TAG_OUTPUT);
            xmlw.writeCharacters("\n");

            xmlw.writeStartElement(Costanti.TAG_PERSONE);
            xmlw.writeAttribute(Costanti.TAG_NUMERO, Integer.toString(num_pers));

            for (int i = 0; i< num_pers; i++){//questo for prende un persona alla volta e va a inserire nel file di output i suoi dati e il suo codice, se presente

                xmlw.writeCharacters("\n    ");
                xmlw.writeStartElement(Costanti.TAG_PERSONA);
                xmlw.writeAttribute("id", Integer.toString(i));
                String id_string= String.valueOf(i);
                xmlw.writeCharacters("\n        ");


                String nome = leggiDatoXML(id_string, Costanti.TAG_NOME);
                xmlw.writeStartElement(Costanti.TAG_NOME);
                xmlw.writeCharacters(nome);
                xmlw.writeEndElement();
                xmlw.writeCharacters("\n        ");

                String cognome = leggiDatoXML(id_string, Costanti.TAG_COGNOME);
                xmlw.writeStartElement(Costanti.TAG_COGNOME);
                xmlw.writeCharacters(cognome);
                xmlw.writeEndElement();
                xmlw.writeCharacters("\n        ");


                String sesso = leggiDatoXML(id_string, Costanti.TAG_SESSO);
                xmlw.writeStartElement(Costanti.TAG_SESSO);
                xmlw.writeCharacters(sesso);
                xmlw.writeEndElement();
                xmlw.writeCharacters("\n        ");

                String comune = leggiDatoXML(id_string, Costanti.TAG_COMUNE_DI_NASCITA);
                xmlw.writeStartElement(Costanti.TAG_COMUNE_DI_NASCITA);
                xmlw.writeCharacters(comune);
                xmlw.writeEndElement();
                xmlw.writeCharacters("\n        ");

                String data = leggiDatoXML(id_string, Costanti.TAG_DATA_DI_NASCITA);
                xmlw.writeStartElement(Costanti.TAG_DATA_DI_NASCITA);
                xmlw.writeCharacters(data);
                xmlw.writeEndElement();
                xmlw.writeCharacters("\n        ");

                String codice = gcf.generatore(i);
                xmlw.writeStartElement(Costanti.TAG_CODICE_FISCALE);
                if(controllaCodiciFiscaliXML(codice)){
                    xmlw.writeCharacters(codice);
                    accoppiati.add(codice);
                }else{
                    xmlw.writeCharacters(Costanti.MESSAGGIO_ASSENTE);
                }
                xmlw.writeEndElement();//chiude codice fiscale
                xmlw.writeCharacters("\n    ");

                xmlw.writeEndElement(); //chiude persona
            }

            xmlw.writeCharacters("\n");
            xmlw.writeEndElement(); //chiusura persone
            xmlw.writeCharacters("\n");

            xmlw.writeStartElement(Costanti.TAG_CODICI);
            xmlw.writeCharacters("\n    ");

            totali.removeAll(accoppiati); //qui totali rappresenta la somma tra i codici fiscali non validi e quelli corretti ma spaiati
            ArrayList <String> invalidi = controllaCodiciInvalidi(totali);
            int numero_invalidi = invalidi.size();

            xmlw.writeStartElement(Costanti.TAG_INVALIDI);
            xmlw.writeAttribute(Costanti.TAG_NUMERO, Integer.toString(numero_invalidi));
            for(int i = 0; i < numero_invalidi; i++){//ciclo che va ad inserire nel file di output i codici errati forniti

                xmlw.writeCharacters("\n      ");
                xmlw.writeStartElement(Costanti.TAG_CODICE);
                xmlw.writeCharacters(invalidi.get(i));
                xmlw.writeEndElement();
            }
            xmlw.writeCharacters("\n    ");
            xmlw.writeEndElement(); //chiude invalidi
            xmlw.writeCharacters("\n    ");


            totali.removeAll(invalidi); //ciò che rimane in totali sono i codici spaiati

            int numero_spaiati = totali.size();
            xmlw.writeStartElement(Costanti.TAG_SPAIATI);
            xmlw.writeAttribute(Costanti.TAG_NUMERO, Integer.toString(numero_spaiati));


            for(int i = 0; i < numero_spaiati; i++){//ciclo che va a inserire nel file di output i codici spaiati
                xmlw.writeCharacters("\n      ");
                xmlw.writeStartElement(Costanti.TAG_CODICE);
                xmlw.writeCharacters(totali.get(i));
                xmlw.writeEndElement(); //chiude codice
            }

            xmlw.writeCharacters("\n    ");
            xmlw.writeEndElement(); //chiude spaiati

            xmlw.writeCharacters("\n");
            xmlw.writeEndElement(); //chiude codici
            xmlw.writeCharacters("\n");

            xmlw.writeEndDocument();
            xmlw.flush();
            xmlw.close();

        } catch (Exception e) { // se c’è un errore viene eseguita questa parte
            System.out.println(Costanti.ERRORE_NELLA_SCRITTURA);
        }



    }


    /**
     * Metodo che genera un arrayList con TUTTI i codici fiscali forniti nel file xml dato
     * @return
     * @throws XMLStreamException
     */
    public ArrayList <String> leggiCodiceXML() throws XMLStreamException {

        ArrayList <String> codici_totali = new ArrayList<>();
        boolean trovato = false;
        int corretti = 0;
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(Costanti.NOME_FILE_CODICI_FISCALI_XML, new FileInputStream(Costanti.NOME_FILE_CODICI_FISCALI_XML));
        } catch (Exception e) {
            System.out.println(Costanti.ERRORE_NELL_INIZIALIZZAZIONE_DEL_READER);
            System.out.println(e.getMessage());
        }
        while (xmlr.hasNext() && !trovato){

            if (xmlr.getEventType()==XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equals(Costanti.TAG_CODICE)) {
                xmlr.next();
                if(xmlr.getText().trim().length() > 0){
                    CodiceFiscale cf = new CodiceFiscale(xmlr.getText());
                    codici_totali.add(cf.getCodice_fiscale());
                }
            }
            xmlr.next();
        }
        return codici_totali;
    }


    /**
     * Metodo che verifica la validità di un codice fiscale
     * @param totali (array di codici fiscali errati e spaiati)
     * @return array  di codici invalidi
     * @throws XMLStreamException
     */
    public ArrayList <String> controllaCodiciInvalidi(ArrayList<String> totali){

        ArrayList <String> codici_invalidi = new ArrayList<>();
        boolean trovato = false;
        for (int i=0; i< totali.size(); i++){
            CodiceFiscale cf = new CodiceFiscale(totali.get(i));
            if(!cf.codiceValido(cf.getCodice_fiscale())){
                codici_invalidi.add(cf.getCodice_fiscale());
            }
        }
        return codici_invalidi;
    }

    /**
     * Metodo che controlla se il codice prodotto a partire dai dati delle persona sia presente nella lista di tutti i codici
     * @param stringa_da_trovare
     * @return boolean
     * @throws XMLStreamException
     */
    public boolean controllaCodiciFiscaliXML(String stringa_da_trovare) throws XMLStreamException{

        boolean trovato = false;

        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(Costanti.NOME_FILE_CODICI_FISCALI_XML, new FileInputStream(Costanti.NOME_FILE_CODICI_FISCALI_XML));
        } catch (Exception e) {
            System.out.println(Costanti.ERRORE_NELL_INIZIALIZZAZIONE_DEL_READER);
            System.out.println(e.getMessage());
        }

        while (xmlr.hasNext() && !trovato){
            if (xmlr.getEventType()==XMLStreamConstants.CHARACTERS && xmlr.getText().equals(stringa_da_trovare)) {
                trovato = true;
                break;
            }
            xmlr.next();
        }
        return trovato;
    }

    /**
     * Metodo di lettura del codice di un comune (ex. B157) dato un comune(BRESCIA)
     * @param stringa_da_trovare
     * @return
     * @throws XMLStreamException
     */
    public String leggiComuneXML(String stringa_da_trovare) throws XMLStreamException{

        boolean trovato = false;

        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(Costanti.NOME_FILE_COMUNI_XML, new FileInputStream(Costanti.NOME_FILE_COMUNI_XML));
        } catch (Exception e) {
            System.out.println(Costanti.ERRORE_NELL_INIZIALIZZAZIONE_DEL_READER);
            System.out.println(e.getMessage());
        }

        String codice_comune = "";
        while (xmlr.hasNext() && !trovato){
            if (xmlr.getEventType()== XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equals(Costanti.TAG_COMUNE)) {
                while (!controlloComuni(xmlr)){
                    if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equals(Costanti.TAG_NOME)) {
                        xmlr.next();
                        if (xmlr.getEventType()==XMLStreamConstants.CHARACTERS && xmlr.getText().equals(stringa_da_trovare)){

                            do{
                                xmlr.next();
                            }while(!controlloCodiceComune(xmlr));

                            codice_comune += xmlr.getText();
                            trovato = true;
                            break;
                        }
                    }
                    xmlr.next();
                }
            }
            xmlr.next();
        }
        return codice_comune;

    }

    /**
     * Metodo di sostegno per il metodo leggiComuni, controlla di essere ancora all'interno dell'elemento "comune"
     * @param xmlr
     * @return
     */
    public boolean controlloComuni (XMLStreamReader xmlr){
        if (xmlr.getEventType()== XMLStreamConstants.END_ELEMENT){
            if (xmlr.getLocalName().equals(Costanti.TAG_COMUNE)){
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo di appoggio per leggiComuni, controlla di star leggendo un character non vuoto(ossia il codice di un comune)
     * @param xmlr
     * @return
     */
    public boolean controlloCodiceComune(XMLStreamReader xmlr){
        if (xmlr.getEventType()==XMLStreamConstants.CHARACTERS && xmlr.getText().trim().length() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Metodo di lettura del file xml contenete tutti i dati di una persona, in base al dato(nome, cognome, sesso...)
     * specifico che viene passato restituisce il character corrispondente
     * @param id
     * @param elemento_necessario
     * @return
     * @throws XMLStreamException
     */
    public String leggiDatoXML(String id, String elemento_necessario) throws XMLStreamException{

        String carattere_necessario= null;
        boolean trovato = false;

        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(Costanti.NOME_FILE_DI_INPUT, new FileInputStream(Costanti.NOME_FILE_DI_INPUT));
        } catch (Exception e) {
            System.out.println(Costanti.ERRORE_NELL_INIZIALIZZAZIONE_DEL_READER);
            System.out.println(e.getMessage());
        }
        while (xmlr.hasNext() && !trovato){

            if (xmlr.getEventType()==XMLStreamConstants.START_ELEMENT && xmlr.getAttributeCount()>0 && xmlr.getAttributeValue(0).equals(id)) {//START_ELEMENT dà come risultato un intero
                xmlr.next();
                while (!controlloInput(xmlr)) {
                    xmlr.next();

                    if (xmlr.getEventType()==XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equals(elemento_necessario)){
                        xmlr.next();
                        carattere_necessario= xmlr.getText();
                        trovato= true;
                        break;
                    }

                }
            }
            xmlr.next();
        }
        return carattere_necessario;
    }

    /**
     * Metodo di appoggio per leggiDato, controlla di essere ancora all'interno dell'elemento persona
     * @param xmlr
     * @return
     */
    public boolean controlloInput (XMLStreamReader xmlr){
        if (xmlr.getEventType()== XMLStreamConstants.END_ELEMENT){
            if (xmlr.getLocalName().equals(Costanti.TAG_PERSONA)){
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo che leggendo il file di input dei dati restituisce quante persone ci siano al suo interno
     * @return
     * @throws XMLStreamException
     */
    public int numero_persone() throws XMLStreamException{

        int num_pers=0;
        boolean trovato = false;

        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(Costanti.NOME_FILE_DI_INPUT, new FileInputStream(Costanti.NOME_FILE_DI_INPUT));
        } catch (Exception e) {
            System.out.println(Costanti.ERRORE_NELL_INIZIALIZZAZIONE_DEL_READER);
            System.out.println(e.getMessage());
        }
        while (xmlr.hasNext() && !trovato){
            if (xmlr.getEventType()==XMLStreamConstants.START_ELEMENT){
                if (xmlr.getAttributeCount()>0){
                    String numero_di_persone= xmlr.getAttributeValue(0);
                    num_pers= Integer.valueOf(numero_di_persone);
                    trovato= true;
                }
            }
            xmlr.next();
        }
        return  num_pers;
    }
}