package it.unibs.ing.arnaldo.CodiceFiscale;

import java.io.FileInputStream;

import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


public class XMLReaderCF {

    public static final int NUM_ATTRIBUTI_PERSONA = 5;
    public static final int NUM_ATTRIBUTI_CITTA = 2;

    private String filename;//percorso file da leggere

    private String[] attributiPersona = new String[NUM_ATTRIBUTI_PERSONA]; //attributi di ciascuna persona
    private ArrayList<Persona> people = new ArrayList<Persona>();//salvo tutte le persone con i loro dati

    private String[] attributiCitta = new String[NUM_ATTRIBUTI_CITTA];
    private ArrayList<Citta> cities = new ArrayList<Citta>();


    public XMLReaderCF(String filename) {
        this.filename = filename;
    }

    public ArrayList<Citta> getCities() {
        return cities;
    }

    public void setCities(ArrayList<Citta> cities) {
        this.cities = cities;
    }

    public String[] getAttributiPersona() {
        return attributiPersona;
    }

    public void setAttributiPersona(String[] attributiPersona) {
        this.attributiPersona = attributiPersona;
    }

    public String[] getAttributiCitta() {
        return attributiCitta;
    }

    public void setAttributiCitta(String[] attributiCitta) {
        this.attributiCitta = attributiCitta;
    }


    public String getFilename() {
        return filename;
    }


    private void readFilePersona(String nomeFile, XMLStreamReader xmlr) throws XMLStreamException {
        if (nomeFile.equalsIgnoreCase("inputPersone.xml") && xmlr.getLocalName().equalsIgnoreCase("persona")) {

            //salvo gli attributi della persona nell'array

            for (int i = 0; i < 5; i++) {
                xmlr.next();
                attributiPersona[i] = xmlr.getAttributeValue(i);

            }
        }
    }


    private void readFileComuni(String nomeFile, XMLStreamReader xmlr) {
        if (nomeFile.equalsIgnoreCase("comuni.xml")) {
            for (int i = 0; i < xmlr.getAttributeCount(); i++) {
                attributiCitta[i] = xmlr.getAttributeValue(i);
            }
        }
    }


    private void aggiungiPersonaAllaLista(String[] attributiPersona) {
        //creo la persona temporanea con gli attributi nell'array denominato "attrbutiPersona"
        if (filename.equalsIgnoreCase("inputPersone.xml")) {
            Persona personaTemp = new Persona(attributiPersona);
            people.add(personaTemp);
        }
    }
}
