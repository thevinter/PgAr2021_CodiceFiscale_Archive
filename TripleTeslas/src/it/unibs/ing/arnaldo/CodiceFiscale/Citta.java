package it.unibs.ing.arnaldo.CodiceFiscale;

import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class Citta {

    private String nomeCitta;
    private String codiceCitta;



    public Citta(String []attributiCitta) {
        this.nomeCitta = attributiCitta[0];
        this.codiceCitta = attributiCitta[1];
    }


    public String getNomeCitta() {
        return nomeCitta;
    }

    public String getCodiceCitta() {
        return codiceCitta;
    }

}
