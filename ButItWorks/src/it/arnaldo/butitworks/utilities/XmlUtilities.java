package it.arnaldo.butitworks.utilities;
import it.arnaldo.butitworks.model.Comune;
import it.arnaldo.butitworks.model.Persona;
import it.arnaldo.butitworks.utilities.CodiceFiscaleUtilities;
import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Metodo it.arnaldo.butitworks.utilities.XmlUtilities con soli metodi statici utilizzata per interagire
 * con i file xml riguardo alle operazioni di lettura e la scrittura
 * @author ButItWork
 * @version 1.0
 */
public class XmlUtilities {
    /**
     * Metodo statico per ottenere un ArrayList di persone dalla lettura del file inputPersone.xml nella cartella inputFiles
     * @return ArrayList di classe <b>it.arnaldo.butitworks.model.Persona</b> contenente le persone lette
     * @throws XMLStreamException
     * @throws FileNotFoundException
     */
    public static ArrayList<Persona> leggiPersone() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        String filepath = "inputFiles/inputPersone.xml";

        xmlif = XMLInputFactory.newInstance();
        xmlr = xmlif.createXMLStreamReader("inputPersone", new FileInputStream(filepath));

        ArrayList<Persona> persone = new ArrayList<Persona>();
        Persona persona = new Persona();

        while (xmlr.hasNext()) {
            switch (xmlr.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    if(xmlr.getLocalName().equals("persona")) {
                        persona = new Persona();
                        persona.setId(Integer.parseInt(xmlr.getAttributeValue(0)));
                    } else if(xmlr.getLocalName().equals("nome")) {
                        xmlr.next();
                        persona.setNome(xmlr.getText());
                    } else if(xmlr.getLocalName().equals("cognome")) {
                        xmlr.next();
                        persona.setCognome(xmlr.getText());
                    } else if(xmlr.getLocalName().equals("sesso")) {
                        xmlr.next();
                        persona.setSesso(xmlr.getText().charAt(0));
                    } else if(xmlr.getLocalName().equals("comune_nascita")) {
                        xmlr.next();
                        persona.setComuneNascita(xmlr.getText());
                    } else if(xmlr.getLocalName().equals("data_nascita")) {
                        xmlr.next();
                        persona.setDataNascita(LocalDate.parse(xmlr.getText()));
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if(xmlr.getLocalName().equals("persona")) {
                        persone.add(persona);
                        persona = new Persona();
                    }
                    break;
            }
            xmlr.next();
        }

        return persone;
    }

    /**
     * Metodo statico per ottenere un ArrayList di comuni dalla lettura del file comuni.xml nella cartella inputFiles
     * @return ArrayList di classe <b>it.arnaldo.butitworks.model.Comune</b> contenente i comuni letti
     * @throws XMLStreamException
     * @throws FileNotFoundException
     */
    public static ArrayList<Comune> leggiComuni() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        String filepath = "inputFiles/comuni.xml";

        xmlif = XMLInputFactory.newInstance();
        xmlr = xmlif.createXMLStreamReader("comuni", new FileInputStream(filepath));

        ArrayList<Comune> comuni = new ArrayList<Comune>();
        Comune comune = new Comune();

        while (xmlr.hasNext()) {
            switch (xmlr.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    if(xmlr.getLocalName().equals("comune")) {
                        comune = new Comune();
                    } else if(xmlr.getLocalName().equals("nome")) {
                        xmlr.next();
                        comune.setNome(xmlr.getText());
                    } else if(xmlr.getLocalName().equals("codice")) {
                        xmlr.next();
                        comune.setCodice(xmlr.getText());
                    }

                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if(xmlr.getLocalName().equals("comune")) {
                        comuni.add(comune);
                        comune = new Comune();
                    }
                    break;
            }
            xmlr.next();
        }

        return comuni;
    }

    /**
     * Metodo statico per ottenere un ArrayList di comuni dalla lettura del file comuni.xml nella cartella inputFiles
     * @return ArrayList di <b>String</b> contenente i codici fiscali letti
     * @throws XMLStreamException
     * @throws FileNotFoundException
     */
    public static ArrayList<String> leggiCodiciFiscali() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        String filepath = "inputFiles/codiciFiscali.xml";

        xmlif = XMLInputFactory.newInstance();
        xmlr = xmlif.createXMLStreamReader("codiciFiscali", new FileInputStream(filepath));

        ArrayList<String> codiciFiscali = new ArrayList<String>();

        while (xmlr.hasNext()) {
            if(xmlr.getEventType() == XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equals("codice")) {
                xmlr.next();
                codiciFiscali.add(xmlr.getText());
            }
            xmlr.next();
        }

        return codiciFiscali;
    }

    /**
     * Metodo statico per generare un file codiciPersone.xml nella root del progetto, contenente le persone
     * con il relativo codice fiscale e i codici invalidi e spaiati
     * @param codiciFiscali ArrayList di String
     * @param persone ArrayList di classe <b>it.arnaldo.butitworks.model.Persona</b>
     * @param comuni ArrayList di classe <b>it.arnaldo.butitworks.model.Comune</b>
     * @throws XMLStreamException
     * @throws FileNotFoundException
     */
    public static void produciOutput(ArrayList<String> codiciFiscali, ArrayList<Persona> persone , ArrayList<Comune> comuni) throws XMLStreamException, FileNotFoundException {
        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        String filepath = "codiciPersone.xml";

        ArrayList<String> codiciFiscaliAppaiati = new ArrayList<String>();
        ArrayList<String> codiciFiscaliSpaiati = new ArrayList<String>();
        ArrayList<String> codiciFiscaliInvalidi = new ArrayList<String>();

        //Riempimento codiciFiscaliInvalidi e codiciFiscaliAppaiati
        for (String cf : codiciFiscali) {
            if(!CodiceFiscaleUtilities.controlloCodiceFiscale(cf, comuni)) {
                codiciFiscaliInvalidi.add(cf);
            } else {
                for (Persona p: persone) {
                    if(p.getCodiceFiscale().equals(cf)) {
                        codiciFiscaliAppaiati.add(cf);
                    }
                }
            }
        }

        //Riempimento codiciFiscaliSpaiati
        for (String cf : codiciFiscali) {
            if(!codiciFiscaliAppaiati.contains(cf) && !codiciFiscaliInvalidi.contains(cf)) {
                codiciFiscaliSpaiati.add(cf);
            }
        }

        //Creazione del file
        xmlof = XMLOutputFactory.newInstance();
        xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filepath), "utf-8");
        xmlw.writeStartDocument("utf-8", "1.0");
        xmlw.writeStartElement("output");
        xmlw.writeStartElement("persone");
        xmlw.writeAttribute("numero", Integer.toString(persone.size()));

        //STAMPA PERSONE
        for (Persona p : persone) {
            //APERTURA TAG PERSONA
            xmlw.writeStartElement("persona");
            xmlw.writeAttribute("id", Integer.toString(p.getId()));

            //NOME PERSONA
            xmlw.writeStartElement("nome");
            xmlw.writeCharacters(p.getNome());
            xmlw.writeEndElement();

            //COGNOME PERSONA
            xmlw.writeStartElement("cognome");
            xmlw.writeCharacters(p.getCognome());
            xmlw.writeEndElement();

            //SESSO PERSONA
            xmlw.writeStartElement("sesso");
            xmlw.writeCharacters(Character.toString(p.getSesso()));
            xmlw.writeEndElement();

            //COMUNE DI NASCITA PERSONA
            xmlw.writeStartElement("comune_nascita");
            xmlw.writeCharacters(p.getComuneNascita());
            xmlw.writeEndElement();

            //COMUNE DI NASCITA PERSONA
            xmlw.writeStartElement("data_nascita");
            xmlw.writeCharacters(p.getDataNascita().toString());
            xmlw.writeEndElement();

            //CODICE FISCALE PERSONA
            xmlw.writeStartElement("codice_fiscale");
            if(codiciFiscaliAppaiati.contains(p.getCodiceFiscale())) {
                xmlw.writeCharacters(p.getCodiceFiscale());
            } else {
                xmlw.writeCharacters("ASSENTE");
            }
            xmlw.writeEndElement();

            //CHIUSURA TAG PERSONA
            xmlw.writeEndElement();
        }
        xmlw.writeEndElement();

        //CODICI INVALIDI E APPAIATI
        xmlw.writeStartElement("codici");

        //CODICI INVALIDI
        xmlw.writeStartElement("invalidi");
        xmlw.writeAttribute("numero", Integer.toString(codiciFiscaliInvalidi.size()));
        for (String cf : codiciFiscaliInvalidi) {
            //STAMPA CODICI INVALIDI
            xmlw.writeStartElement("codice");
            xmlw.writeCharacters(cf);
            xmlw.writeEndElement();
        }
        xmlw.writeEndElement();

        //CODICI SPAIATI
        xmlw.writeStartElement("spaiati");
        xmlw.writeAttribute("numero", Integer.toString(codiciFiscaliSpaiati.size()));
        for (String cf : codiciFiscaliSpaiati) {
            //STAMPA CODICI SPAIATI
            xmlw.writeStartElement("codice");
            xmlw.writeCharacters(cf);
            xmlw.writeEndElement();
        }
        xmlw.writeEndElement();

        //CHIUSURA CODICI INVALIDI E APPAIATI
        xmlw.writeEndElement();


        xmlw.writeEndElement();
        xmlw.writeEndDocument();
        xmlw.flush();
        xmlw.close();
    }

}
