package it.unibs.ing.arnaldo.CodiceFiscale;

import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class XMLWriterCF {

    private static final String VERSION = "1.0";
    private static final String ENCODING = "utf-8";

    String filename;//file su cui scrivere

    public XMLWriterCF(String filename) {
        this.filename = filename;
    }

    public void writeFile() {

        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;

        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename), ENCODING);
            xmlw.writeStartDocument(ENCODING, VERSION);


        }catch(Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }

    }

}
