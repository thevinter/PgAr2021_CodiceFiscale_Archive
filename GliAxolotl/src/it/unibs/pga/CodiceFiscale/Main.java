package it.unibs.pga.CodiceFiscale;

import javax.xml.stream.XMLStreamException;

public class Main {


    public static void main(String[] args) throws XMLStreamException {

        System.out.println(Costanti.MESSAGGIO_SALUTO);

        InterazioneXML interazione = new InterazioneXML();

        int num_pers= interazione.numero_persone();

        System.out.println(Costanti.MESSAGGIO_DI_ATTESA);
        interazione.ScritturaXML(num_pers);
        System.out.println(Costanti.MESSAGGIO_DI_FINE);

    }
}
