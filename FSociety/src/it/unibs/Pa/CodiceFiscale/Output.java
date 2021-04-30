package it.unibs.Pa.CodiceFiscale;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Output {

   public static final String OutputXml ="./codiceFiscale.xml";

   public void stampa( ArrayList<Persona> p,  ArrayList<String> codiciSpaiati ,ArrayList<String> codiciErrati, ArrayList<String> codiciAppaiati) {

      XMLOutputFactory xmlof = null;
      XMLStreamWriter xmlw = null;
      try {
         xmlof = XMLOutputFactory.newInstance();
         xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("fileOutput.xml"), "utf-8");
         ((XMLStreamWriter) xmlw).writeStartDocument("utf-8", "1.0");
      } catch (Exception e) {
         System.out.println("Errore nell'inizializzazione del writer:");
         System.out.println(e.getMessage());
      }
    // esempio di dati da scrivere
      try { // blocco try per raccogliere eccezioni
         xmlw.writeStartElement("programmaArnaldo"); // scrittura del tag radice <programmaArnaldo>
         xmlw.writeComment("INIZIO LISTA"); // scrittura di un commento
         for (int i = 0; i < p.size(); i++) {
            xmlw.writeStartElement("persona"); // scrittura del tag autore...
            xmlw.writeAttribute("id", Integer.toString(i)); // ...con attributo id...
            // inserimento nome
            xmlw.writeStartElement("nome");
            xmlw.writeCharacters(p.get(i).getNome());
            xmlw.writeEndElement();
            // inserimento cognome
            xmlw.writeStartElement("cognome");
            xmlw.writeCharacters(p.get(i).getCognome());
            xmlw.writeEndElement();

            // inserimento sesso
            xmlw.writeStartElement("sesso");
            xmlw.writeCharacters(p.get(i).getSesso());
            xmlw.writeEndElement();

            // inserimento comune di nascita

            xmlw.writeStartElement("comune_nascita");
            xmlw.writeCharacters(p.get(i).getComuneNascita());
            xmlw.writeEndElement();

            // inserimento data nascita
            xmlw.writeStartElement("data_nascita");
            xmlw.writeCharacters(p.get(i).getData_nascita());
            xmlw.writeEndElement();


            // inserimento codice fiscale
            xmlw.writeStartElement("codice_fiscale");
            if(codiciAppaiati.contains(p.get(i).getCodice_fiscale())) {
               xmlw.writeCharacters(p.get(i).getCodice_fiscale());
            } else {
               xmlw.writeCharacters("ASSENTE");
            }
            xmlw.writeEndElement();

            xmlw.writeEndElement(); // chiusura di </Persona>
         }

         // CREAZIONE ERRATI
         xmlw.writeStartElement("errati"); // scrittura del tag autore...
         for (int i = 0; i < codiciErrati.size(); i++) {

            // inserimento codice
            xmlw.writeStartElement("codice");
            xmlw.writeCharacters(codiciErrati.get(i));
            xmlw.writeEndElement();


         }
         xmlw.writeEndElement(); // chiusura di </errati>



         // STAMPA SPAIATI
         xmlw.writeStartElement("spaiati"); // scrittura del tag autore...
         for (int i = 0; i < codiciSpaiati.size(); i++) {

            // inserimento codice
            xmlw.writeStartElement("codice");
            xmlw.writeCharacters(codiciSpaiati.get(i));
            xmlw.writeEndElement();


         }
         xmlw.writeEndElement(); // chiusura di </codie>







         xmlw.writeEndElement(); // chiusura di </programmaArnaldo>
         xmlw.writeEndDocument(); // scrittura della fine del documento
         xmlw.flush(); // svuota il buffer e procede alla scrittura
         xmlw.close(); // chiusura del documento e delle risorse impiegate
      } catch (Exception e) { // se c’è un errore viene eseguita questa parte
         System.out.println("Errore nella scrittura");
         e.printStackTrace();

   }
   }

















     /* Document dom;
      Element e = null;

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
       try {
          DocumentBuilder  db = dbf.newDocumentBuilder();
          // CREATA UN ISTANZA
          dom = db.newDocument();

          // CREO IL ROOT ELEMENT
          for( int i = 0 ; i< p.size() ; i++) {
             Element rootEle = dom.createElement("persona");

             // CREAZIONE DEI ROOT
             e = dom.createElement("nome");
             e.appendChild(dom.createTextNode(p.get(i).getNome()));
             rootEle.appendChild(e);

             // CREAZIONE DEI ROOT
             e = dom.createElement("cognome");
             e.appendChild(dom.createTextNode(p.get(i).getCognome()));
             rootEle.appendChild(e);


             // CREAZIONE DEI ROOT
             e = dom.createElement("sesso");
             e.appendChild(dom.createTextNode(p.get(i).getSesso()));
             rootEle.appendChild(e);

             // CREAZIONE DEI ROOT
             e = dom.createElement("comuneNascita");
             e.appendChild(dom.createTextNode(p.get(i).getComuneNascita()));
             rootEle.appendChild(e);

             // CREAZIONE DEI ROOT
             e = dom.createElement("dataNascita");
             e.appendChild(dom.createTextNode(p.get(i).getData_nascita()));
             rootEle.appendChild(e);

             // CREAZIONE DEI ROOT
             e = dom.createElement("codiceFiscale");
             e.appendChild(dom.createTextNode(p.get(i).getCodice_fiscale()));
             rootEle.appendChild(e);


             // OUTPUT CONFRONTO CODICE FISCALE

             dom.appendChild(rootEle);
       }

          try{
             Transformer tr = TransformerFactory.newInstance().newTransformer();
             tr.setOutputProperty(OutputKeys.INDENT, "yes");
             tr.setOutputProperty(OutputKeys.METHOD, "xml");
             tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
             tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
//                tr.setOutputProperty("./xml.codiceFiscale.xml", "4");
//





             tr.transform(new DOMSource(dom),
                     new StreamResult(new FileOutputStream(xml)));

          } catch (TransformerException te) {
             System.out.println(te.getMessage());
          } catch (IOException ioe) {
             System.out.println(ioe.getMessage());
          }
       } catch (ParserConfigurationException pce) {
          System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
       }


   }
*/

      // PROVA OUTPUT

      /*try {
         FileOutputStream fos = new FileOutputStream(new File("./codiceFiscale.xml"));
         XMLEncoder encoder = new XMLEncoder(fos);
         encoder.writeObject(p1);
         encoder.close();
         fos.close();

      }catch (IOException ex){
         ex.printStackTrace();
      }*/




//
   }

