package it.unibs.Pa.CodiceFiscale;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.ArrayList;

public class LettoreXML {


    /**
     * crazione Persona
     *
     * @throws XMLStreamException
     * @ArrayList
     */
    public static ArrayList leggi_inputPersone() throws XMLStreamException {
        ArrayList<Persona> persone = new ArrayList<Persona>();
        Persona pers = null;
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        String FileXML = "inputPersone.xml";

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(FileXML, new FileInputStream(FileXML));
        } catch (Exception e) {
            System.out.println("Errore nell'inzializzazione del reader");
            System.out.println(e.getMessage());
        }

        /*L'istruzione nel while dava errore: add exception to method signatur
        Ho usato la correzione automatica del sistema cliccando sull'errore*/

        while (xmlr.hasNext()) {
            switch (xmlr.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    if (xmlr.getLocalName().equals("persona")) {
                        pers = new Persona();
                    } else if (xmlr.getLocalName().equals("nome")) {
                        xmlr.next();
                        pers.setNome(xmlr.getText());
                    } else if (xmlr.getLocalName().equals("cognome")) {
                        xmlr.next();
                        pers.setCognome(xmlr.getText());
                    } else if (xmlr.getLocalName().equals("sesso")) {
                        xmlr.next();
                        pers.setSesso(xmlr.getText());
                    } else if (xmlr.getLocalName().equals("comune_nascita")) {
                        xmlr.next();
                        pers.setComuneNascita(xmlr.getText());
                    } else if (xmlr.getLocalName().equals("data_nascita")) {
                        xmlr.next();
                        pers.setData_nascita(xmlr.getText());
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (xmlr.getLocalName().equals("persona")) {
                        persone.add(pers);
                    }
                    break;

                default:
                    break;
            }
            xmlr.next();
        }

        return persone;
    }

    // CREAZIONE COMUNE

    public static ArrayList leggi_Comune() throws XMLStreamException {

        Comune com = null;
        ArrayList<Comune> comune = new ArrayList<Comune>();
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        String FileXML = "comuni.xml";

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(FileXML, new FileInputStream(FileXML));
        } catch (Exception e) {
            System.out.println("Errore nell'inzializzazione del reader");
            System.out.println(e.getMessage());
        }

        /*L'istruzione nel while dava errore: add exception to method signatur
        Ho usato la correzione automatica del sistema cliccando sull'errore*/

        while (xmlr.hasNext()) {
            switch (xmlr.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    if (xmlr.getLocalName().equals("comune")) {
                        com = new Comune();
                    } else if (xmlr.getLocalName().equals("nome")) {
                        xmlr.next();
                        com.setNome(xmlr.getText());
                    } else if (xmlr.getLocalName().equals("codice")) {
                        xmlr.next();
                        com.setCodice(xmlr.getText());

                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (xmlr.getLocalName().equals("comune")) {
                        comune.add(com);
                    }
                    break;
                default:
                    break;
            }
            xmlr.next();
        }
        return comune;
    }

    /**
     * @r
     * @ return codiceFiscale
     */

    public static ArrayList leggiCodiciFiscali() throws XMLStreamException {
        ArrayList<String> codiceFiscale = new ArrayList<String>();
        String codice = null;
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        String FileXML = "codiciFiscali.xml";

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(FileXML, new FileInputStream(FileXML));
        } catch (Exception e) {
            System.out.println("Errore nell'inzializzazione del reader");
            System.out.println(e.getMessage());
        }


        while (xmlr.hasNext()) {
            switch (xmlr.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    if (xmlr.getLocalName().equals("codice")) {
                        codice = new String();
                        xmlr.next();
                        codice = xmlr.getText();
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (xmlr.getLocalName().equals("codice")) {
                        codiceFiscale.add(codice);
                    }
                    break;

                default:
                    break;
            }
            xmlr.next();
        }

        return codiceFiscale;
    }
}