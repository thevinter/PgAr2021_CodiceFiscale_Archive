package it.unibs.arnaldo.TesteAnnidate.CodiceFiscale;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import java.io.*;
import java.util.ArrayList;


public class Input {
    //metodo statico che legge e ritorna un array di persone
    //MATILDE

    public static ArrayList<Persona> leggiPersone() throws XMLStreamException {

        String file_name = "src/XMLFiles/inputPersone.xml";
        ArrayList<Persona> lista_persone = new ArrayList<Persona>();
        Persona persona = null;

        //inizializzazione variabili per la lettura

        XMLInputFactory xmlif = null;
        XMLStreamReader reader = null;

        try {
            xmlif = XMLInputFactory.newInstance();
            reader = xmlif.createXMLStreamReader(file_name, new FileInputStream(file_name));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        while (reader.hasNext()) { // continua a leggere finché ha eventi a disposizione

            switch (reader.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:  //salva il nome del tag di apertura in start_element_name per usarlo sotto

                    if (reader.getLocalName().equals("persona"))
                        persona = new Persona();
                    else if (reader.getLocalName().equals("nome")) {   //se il tag di apertura (evento prima) è = a nome
                        reader.next();                          //passo all'evento dopo per ottenere il content
                        persona.setNome(reader.getText());    //allora setto l'attributo nome della persona
                    } else if (reader.getLocalName().equals("cognome")) { //setto il cognome
                        reader.next();
                        persona.setCognome(reader.getText());
                    } else if (reader.getLocalName().equals("sesso")) {
                        reader.next();
                        persona.setSesso(reader.getText().charAt(0));      //imposta il sesso come un char
                    } else if (reader.getLocalName().equals("comune_nascita")) {
                        reader.next();
                        persona.setLuogoDiNascita(new Comune(reader.getText()));
                        //il codice del comune viene impostato dopo
                    } else if (reader.getLocalName().equals("data_nascita")) {
                        reader.next();
                        persona.setDataDiNascita(new Data(reader.getText()));
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if(reader.getLocalName().equals("persona"))    //controllo se il carattere del sesso è giusto
                         lista_persone.add(persona);

                    break;

            default: break;
            }

            reader.next();   //passa all'evento successivo

        }
        return lista_persone;   //ritorna la lista completa di persone ottenute dall'XML
    }



    //metodo statico che legge e restituisce un array di comuni
    public static ArrayList<Comune> leggiComuni() throws XMLStreamException {

        String filename = "src/XMLFiles/comuni.xml";
        Comune comune = null;
        ArrayList<Comune> lista_comuni = new ArrayList<Comune>();

        XMLInputFactory xmlif = null;
        XMLStreamReader reader = null;

        try {
            xmlif = XMLInputFactory.newInstance();
            reader = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        while (reader.hasNext()) { // continua a leggere finché ha eventi a disposizione

            switch (reader.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:

                    if(reader.getLocalName().equals("comune"))
                        comune = new Comune();
                    else if (reader.getLocalName().equals("nome")) {   //se il tag di apertura (evento prima) è = a nome
                        reader.next();
                        comune.setNome_comune(reader.getText());    //allora setto l'attributo nome della persona
                    } else if (reader.getLocalName().equals("codice")) { //setto il cognome
                        reader.next();
                        comune.setCodice(reader.getText());
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:   //se ho concluso la lettura di un comune allora lo aggiungo all'array
                    if(reader.getLocalName().equals("comune"))
                        lista_comuni.add(comune);
                    break;

                default: break;
            }

            reader.next();   //passa all'evento successivo

        }
        return lista_comuni;
    }



    //metodo che ritorna un array di codici fiscali (quelli del file -xml)

    public static ArrayList<CodiceFiscale> leggiCodiciFiscali() throws XMLStreamException {
        String filename = "src/XMLFiles/codiciFiscali.xml";
        CodiceFiscale codice_fiscale = null;
        ArrayList<CodiceFiscale> lista_codici_fiscali = new ArrayList<CodiceFiscale>();

        XMLInputFactory xmlif = null;
        XMLStreamReader reader = null;

        try {
            xmlif = XMLInputFactory.newInstance();
            reader = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        while (reader.hasNext()) { // continua a leggere finché ha eventi a disposizione

            switch (reader.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:
                    if(reader.getLocalName().equals("codice")) {
                        reader.next();
                        codice_fiscale = new CodiceFiscale(reader.getText());
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if(reader.getLocalName().equals("codice"))
                        lista_codici_fiscali.add(codice_fiscale);
                    break;

                default: break;
            }
        reader.next();
        }
        return lista_codici_fiscali;

    }
}