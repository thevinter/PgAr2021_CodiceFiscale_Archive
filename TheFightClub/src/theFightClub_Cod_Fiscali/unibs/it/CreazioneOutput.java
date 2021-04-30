package theFightClub_Cod_Fiscali.unibs.it;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class CreazioneOutput {

    public void creazioneOutput (ArrayList<Persona> listaPersone)
    {


        /*

        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        try
        {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("CodiciPersone.xml"), "utf-8");
            xmlw.writeStartDocument("utf-8","1.0");
        } catch (Exception e)
            {
                System.out.println("Errore nell'inizializzazione del writer:");
                System.out.println(e.getMessage());
            }
        try { // blocco try per raccogliere eccezioni
            xmlw.writeStartElement("output"); // scrittura del tag radice
            xmlw.writeComment("INIZIO LISTA"); // scrittura di un commento
            xmlw.writeStartElement("persone");
            xmlw.writeAttribute("numero", String.valueOf(listaPersone.size()));

            for (int i = 0; i < listaPersone.size(); i++)
            {                         //elemento persone
                xmlw.writeStartElement("persona");
                xmlw.writeAttribute("id", Integer.toString(i));
                xmlw.writeStartElement("nome");
                xmlw.writeCharacters(listaPersone.get(i).getNOME());
                xmlw.writeEndElement();
                xmlw.writeStartElement("cognome");
                xmlw.writeCharacters(listaPersone.get(i).getCOGNOME());
                xmlw.writeEndElement();
                xmlw.writeStartElement("sesso");
                xmlw.writeCharacters(listaPersone.get(i).getSESSO());
                xmlw.writeEndElement();
                xmlw.writeStartElement("comune nascita");
                xmlw.writeCharacters(listaPersone.get(i).getCOMUNE());
                xmlw.writeEndElement();
                xmlw.writeStartElement("data nascita");
                xmlw.writeCharacters(listaPersone.get(i).getANNO()+"-"+listaPersone.get(i).getMESE()+"-"+listaPersone.get(i).getGIORNO());
                xmlw.writeEndElement();
                xmlw.writeStartElement("codice fiscale");
                xmlw.writeCharacters(); //dove prendo il codice fiscale o absent??
                xmlw.writeEndElement();
                xmlw.writeEndElement();
            }

            xmlw.writeEndElement();

            xmlw.writeStartElement("codici");

            xmlw.writeStartElement("invalidi"); //da adattare al passo 3, controllo codici con salvataggio da rimettere qui
            xmlw.writeAttribute("numero", );
            xmlw.writeEndElement();

            xmlw.writeStartElement("spaiati"); //controlo codici inserire qui gli spaiati
            xmlw.writeAttribute("numero =", );
            xmlw.writeEndElement();

            xmlw.writeEndElement();


            xmlw.writeEndElement(); // chiusura
            xmlw.writeEndDocument(); // scrittura della fine del documento
            xmlw.flush(); // svuota il buffer e procede alla scrittura
            xmlw.close(); // chiusura del documento e delle risorse impiegate
        } catch (Exception e) { // se c’è un errore viene eseguita questa parte
            System.out.println("Errore nella scrittura");
*/

        }
    }