package it.unibs.prgarnaldo.cuorisolitari.codicefiscale;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileInputStream;;

public class Xlm {

    XMLInputFactory xmlif = null;
    XMLStreamReader xmlr = null;


    try{
        xmlif = XMLInputFactory.newInstance();
        xmlr = xmlif.createFilteredReader(codiciFiscali, new FileInputStream(codiciFiscali.xml));
    }

    catch( Exception e)
    {
        System.out.println("Errore nell'inizializzazione del reader:");
        System.out.println(e.getMessage());
    }


}
