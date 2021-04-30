package it.unibs.arnaldo.codiceFiscale;


import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.ArrayList;

public class ServizioFileXml {

    //Costanti
    private static final String ERRORE_INIZIALIZZAZIONE_READER="Errore nell'inizializzazione del reader: ";
    private static final String ERRORE_LETTURA="Errore durante la lettura del file %s.Per ulteriori informazioni: ";
/*
    //da finire
    public static ArrayList<Persona> leggiPersone(String filename){
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlreader = null;
        ArrayList<Persona> persone=new ArrayList<Persona>();

        //try catch per gestire eventuali eccezioni durante l'inizializzazione
        try{
            xmlif=XMLInputFactory.newInstance();
            xmlreader= xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
        }catch(Exception e){
            System.out.println(ERRORE_INIZIALIZZAZIONE_READER);
            System.out.println(e.getMessage());
        }
        //try catch per gestire evemtuali eccezioni durante la lettura del file XML contenente dei dati relativi ad una persona
        try{
            while (xmlreader.hasNext()) { // continua a leggere finché ha eventi a disposizione
                switch (xmlreader.getEventType()) { // switch sul tipo di evento
                    case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
                        System.out.println("Start Read Doc " + filename); break;
                    case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
                        System.out.println("Tag " + xmlreader.getLocalName());
                        for (int i = 0; i < xmlreader.getAttributeCount(); i++)
                            System.out.printf(" => attributo %s->%s%n", xmlreader.getAttributeLocalName(i), xmlreader.getAttributeValue(i));
                        break;
                    case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
                        System.out.println("END-Tag " + xmlreader.getLocalName()); break;
                    case XMLStreamConstants.COMMENT:
                        System.out.println("// commento " + xmleader.getText()); break; // commento: ne stampa il contenuto
                    case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
                        if (xmlreader.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            System.out.println("-> " + xmlreader.getText());
                        break;
                }
                xmlreader.next();
            }
        }catch(XMLStreamException e){
            System.out.println(String.format(ERRORE_LETTURA,filename));
            System.out.println(e.getMessage());
        }
    }*/
}
