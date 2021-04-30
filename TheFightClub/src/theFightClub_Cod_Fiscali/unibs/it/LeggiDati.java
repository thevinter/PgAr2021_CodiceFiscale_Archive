package theFightClub_Cod_Fiscali.unibs.it;

import javax.annotation.processing.SupportedSourceVersion;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LeggiDati {

    private static final ArrayList<Persona> listaPersone = new ArrayList<>();


    public static ArrayList<Persona> estraggoDati() {
        Persona persona = null;
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        try {
            //aggiungo i file xml
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader("inputPersone.xml", new FileInputStream("inputPersone.xml"));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader: ");
            System.out.println(e.getMessage());
        }

        try {
            String elementoAttuale = "";
            while (xmlr.hasNext()) {
                switch (xmlr.getEventType()) {
                    case XMLStreamConstants.END_ELEMENT:
                        elementoAttuale = "";
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        elementoAttuale = xmlr.getLocalName();
                        if(elementoAttuale.equals("persona")) {
                            if(persona != null) listaPersone.add(persona);
                            persona = new Persona();
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS: {
                        if (elementoAttuale.equalsIgnoreCase("cognome")) {
                            String cognome = xmlr.getText();
                            persona.setCOGNOME(cognome);
                        }
                        if (elementoAttuale.equalsIgnoreCase("nome")) {
                            String nome = xmlr.getText();
                            persona.setNOME(nome);
                        }
                        if (elementoAttuale.equalsIgnoreCase("data_nascita")) {
                            String anno = xmlr.getText().substring(0, 4);
                            persona.setANNO(anno);
                            String mese = xmlr.getText().substring(5, 7);
                            persona.setMESE(mese);
                            String giorno = xmlr.getText().substring(8, 10);
                            persona.setGIORNO(giorno);
                        }
                        if (elementoAttuale.equalsIgnoreCase("sesso")) {
                            String sesso = xmlr.getText();
                            persona.setSESSO(sesso);
                        }
                        if (elementoAttuale.equalsIgnoreCase("comune_nascita")) {
                            String comune = xmlr.getText();
                            String codice = estraggoCodiceComune(comune);
                            persona.setCOMUNE(codice);
                        }
                        break;
                    }
                }
                xmlr.next();
            }
            xmlr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaPersone;
    }


    public static String estraggoCodiceComune(String comune) {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr2 = null;


        try {
            //aggiungo i file xml
            xmlif = XMLInputFactory.newInstance();
            xmlr2 = xmlif.createXMLStreamReader("comuni.xml", new FileInputStream("comuni.xml"));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader: ");
            System.out.println(e.getMessage());
        }


        try {
            String elementoAttuale = "";
            String codiceComune = "";
            String nomeComune = "";
            while (xmlr2.hasNext()) {

                switch (xmlr2.getEventType()) {
                    case XMLStreamConstants.END_ELEMENT:

                        if (nomeComune.equalsIgnoreCase(comune) && !codiceComune.equals("")) {
                            xmlr2.close();
                            return codiceComune;
                        }
                        elementoAttuale = "";

                        if(!codiceComune.equals("") && !nomeComune.equals("")) {
                            codiceComune = "";
                            nomeComune = "";
                        }
                        break;

                    case XMLStreamConstants.START_ELEMENT:
                        elementoAttuale = xmlr2.getLocalName();

                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (elementoAttuale.equalsIgnoreCase("nome"))
                            nomeComune = String.valueOf(xmlr2.getText());
                        if (elementoAttuale.equalsIgnoreCase("codice"))
                            codiceComune = String.valueOf(xmlr2.getText());
                        break;
                }
                xmlr2.next();
            }
            xmlr2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}





