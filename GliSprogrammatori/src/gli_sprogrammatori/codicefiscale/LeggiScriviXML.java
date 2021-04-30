package gli_sprogrammatori.codicefiscale;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

/**
 * <p>
 * La classe <strong>LeggiScriviXML</strong> è la classe dove vengono letti e
 * scritti i file XML
 * </p>
 * 
 * @author Alessandro Muscio, Tommaso Bianchin, Gianmarco Gamo
 * @version 1.0
 */
public class LeggiScriviXML {
  /**
   * Rappresenta la input factory per creare i vari stream reader
   */
  private XMLInputFactory xml_input_factory = XMLInputFactory.newInstance();
  /**
   * Rappresenta la output factory per creare i vari stream writer
   */
  private XMLOutputFactory xml_output_factory = XMLOutputFactory.newInstance();
  /**
   * Rappresenta lo stream reader usato per leggere il file delle persone
   */
  private static XMLStreamReader xml_stream_reader_persone;
  /**
   * Rappresenta lo stream reader usato per leggere il file dei comuni
   */
  private static XMLStreamReader xml_stream_reader_comuni;
  /**
   * Rappresenta lo stream reader usato per leggere il file dei codice fiscali
   */
  private static XMLStreamReader xml_stream_reader_codici_fiscali;
  /**
   * Rappresenta lo stream writer usato per scrivere il file di output
   */
  private static XMLStreamWriter xml_stream_writer;

  /**
   * Mi permette du creare un oggetto del tipo <strong>LeggiScriviXML</strong>
   * 
   * @param file_path_persone        Percorso per il file delle persone
   * @param file_path_codici_fiscali Percorso per il file dei codici fiscali
   * @param file_path_comuni         Percorso per il file dei comuni
   * @param file_path_output         Percorso per il file di output
   * @param output_encoding          Rappresenta l'encoding dell'output
   * @throws FileNotFoundException Eccezione per i file
   * @throws XMLStreamException    Eccezione per xml
   */
  public LeggiScriviXML(String file_path_persone, String file_path_codici_fiscali, String file_path_comuni,
      String file_path_output, String output_encoding) throws FileNotFoundException, XMLStreamException {
    xml_stream_reader_persone = xml_input_factory.createXMLStreamReader(file_path_persone,
        new FileInputStream(file_path_persone));
    xml_stream_reader_codici_fiscali = xml_input_factory.createXMLStreamReader(file_path_codici_fiscali,
        new FileInputStream(file_path_codici_fiscali));
    xml_stream_reader_comuni = xml_input_factory.createXMLStreamReader(file_path_comuni,
        new FileInputStream(file_path_comuni));
    xml_stream_writer = xml_output_factory.createXMLStreamWriter(new FileOutputStream(file_path_output),
        output_encoding);
  }

  /**
   * Metodo usato per leggere il file delle persone
   * 
   * @return Ritorna un <code>ArrayList</code> rappresentante le persone lette nel
   *         file
   * @throws XMLStreamException Eccezione per xml
   */
  public ArrayList<Persona> leggiPersone() throws XMLStreamException {
    ArrayList<Persona> lista_persone = new ArrayList<Persona>(); // inizializzazione variabili con scope di funzione
    String nome = "", cognome = "", comune_nascita = "", data_nascita = "";
    char sesso = '\0';
    int id = -1, indice_elemento = -1;

    while (xml_stream_reader_persone.hasNext()) { // continua a leggere finché ha eventi a disposizione
      switch (xml_stream_reader_persone.getEventType()) {
        case XMLStreamConstants.START_ELEMENT: // inizio di un elemento
          switch (xml_stream_reader_persone.getLocalName()) {
            case "persona":
              for (int i = 0; i < xml_stream_reader_persone.getAttributeCount(); i++)
                id = Integer.parseInt(xml_stream_reader_persone.getAttributeValue(i));
              break;

            case "nome":
              indice_elemento = 0;
              break;

            case "cognome":
              indice_elemento = 1;
              break;

            case "sesso":
              indice_elemento = 2;
              break;

            case "comune_nascita":
              indice_elemento = 3;
              break;

            case "data_nascita":
              indice_elemento = 4;
              break;

            default:
              indice_elemento = -1;
              break;
          }
          break;

        case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento
          if (xml_stream_reader_persone.getText().trim().length() > 0) { // controlla se il testo non contiene solo
                                                                         // spazi
            switch (indice_elemento) {
              case 0:
                nome = xml_stream_reader_persone.getText();
                break;

              case 1:
                cognome = xml_stream_reader_persone.getText();
                break;

              case 2:
                sesso = xml_stream_reader_persone.getText().charAt(0);
                break;

              case 3:
                comune_nascita = xml_stream_reader_persone.getText();
                break;

              case 4:
                data_nascita = xml_stream_reader_persone.getText();
                break;
            }
          }
          break;

        case XMLStreamConstants.END_ELEMENT: // fine di un elemento
          if (xml_stream_reader_persone.getLocalName().equals("persona"))
            lista_persone.add(new Persona(id, nome, cognome, sesso, comune_nascita, data_nascita));

          break;
      }
      xml_stream_reader_persone.next();
    }
    xml_stream_reader_persone.close();

    return lista_persone;
  }

  /**
   * Metodo usato per leggere il file dei comuni
   * 
   * @return Ritorna una <code>HashMap</code> rappresentante i comuni e i codici
   *         letti
   * @throws XMLStreamException Eccezione per xml
   */
  public HashMap<String, String> leggiCodiciComuni() throws XMLStreamException {
    HashMap<String, String> codici_comuni = new HashMap<String, String>();
    String nome_comune = "", codice_comune = "";
    int indice_elemento = -1;

    while (xml_stream_reader_comuni.hasNext()) {
      switch (xml_stream_reader_comuni.getEventType()) {
        case XMLStreamConstants.START_ELEMENT:
          switch (xml_stream_reader_comuni.getLocalName()) {
            case "nome":
              indice_elemento = 0;
              break;

            case "codice":
              indice_elemento = 1;
              break;

            default:
              indice_elemento = -1;
              break;
          }
          break;

        case XMLStreamConstants.CHARACTERS:
          if (xml_stream_reader_comuni.getText().trim().length() > 0) { // controlla se il testo non contiene
                                                                        // solo spazi
            switch (indice_elemento) {
              case 0:
                nome_comune = xml_stream_reader_comuni.getText();
                break;

              case 1:
                codice_comune = xml_stream_reader_comuni.getText();
                break;
            }
          }
          break;

        case XMLStreamConstants.END_ELEMENT: // fine di un elemento
          if (xml_stream_reader_comuni.getLocalName().equals("comune"))
            codici_comuni.put(nome_comune, codice_comune);

          break;
      }

      xml_stream_reader_comuni.next();
    }
    xml_stream_reader_comuni.close();

    return codici_comuni;
  }

  /**
   * Metodo usato per leggere il file dei codici fiscali
   * 
   * @return Ritorna un <code>ArrayList</code> rappresentante i codici fiscali
   *         letti nel file
   * @throws XMLStreamException Eccezione per xml
   */
  public ArrayList<String> leggiCodiciFiscali() throws XMLStreamException {
    ArrayList<String> codici_fiscali = new ArrayList<String>();
    String codice_da_aggiungere = "";
    int indice_elemento = -1;

    while (xml_stream_reader_codici_fiscali.hasNext()) {
      switch (xml_stream_reader_codici_fiscali.getEventType()) {
        case XMLStreamConstants.START_ELEMENT:
          if (xml_stream_reader_codici_fiscali.getLocalName().equals("codice"))
            indice_elemento = 0;
          else
            indice_elemento = -1;

          break;

        case XMLStreamConstants.CHARACTERS:
          if (indice_elemento == 0)
            codice_da_aggiungere = xml_stream_reader_codici_fiscali.getText();

          break;

        case XMLStreamConstants.END_ELEMENT: // fine di un elemento
          if (xml_stream_reader_codici_fiscali.getLocalName().equals("codice"))
            codici_fiscali.add(codice_da_aggiungere);

          break;
      }
      xml_stream_reader_codici_fiscali.next();
    }
    xml_stream_reader_codici_fiscali.close();

    return codici_fiscali;
  }

  /**
   * Scrive in l'output desiderato su di un apposito file
   * 
   * @param persone                 Le persone da scrivere sul file
   * @param codici_fiscali_invalidi I codici fiscali invalidi
   * @param codici_fiscali_spaiati  I codici fiscali spaiati
   * @throws XMLStreamException Eccezione per xml
   */
  public void scriviOutput(ArrayList<Persona> persone, ArrayList<String> codici_fiscali_invalidi,
      ArrayList<String> codici_fiscali_spaiati) throws XMLStreamException {
    int i;

    xml_stream_writer.writeStartDocument("utf-8", "1.0");
    xml_stream_writer.writeCharacters("\n");

    xml_stream_writer.writeStartElement("output");
    xml_stream_writer.writeCharacters("\n\t");

    xml_stream_writer.writeStartElement("persone");
    xml_stream_writer.writeAttribute("numero", String.valueOf(persone.size()));

    for (i = 0; i < persone.size(); i++) {
      xml_stream_writer.writeCharacters("\n\t\t");
      xml_stream_writer.writeStartElement("persona");
      xml_stream_writer.writeAttribute("id", String.valueOf(i));
      xml_stream_writer.writeCharacters("\n\t\t\t");

      xml_stream_writer.writeStartElement("nome");
      xml_stream_writer.writeCharacters(persone.get(i).getNome());
      xml_stream_writer.writeEndElement();
      xml_stream_writer.writeCharacters("\n\t\t\t");

      xml_stream_writer.writeStartElement("cognome");
      xml_stream_writer.writeCharacters(persone.get(i).getCognome());
      xml_stream_writer.writeEndElement();
      xml_stream_writer.writeCharacters("\n\t\t\t");

      xml_stream_writer.writeStartElement("sesso");
      xml_stream_writer.writeCharacters(String.valueOf(persone.get(i).getSesso()));
      xml_stream_writer.writeEndElement();
      xml_stream_writer.writeCharacters("\n\t\t\t");

      xml_stream_writer.writeStartElement("comune_nascita");
      xml_stream_writer.writeCharacters(persone.get(i).getComuneNascita());
      xml_stream_writer.writeEndElement();
      xml_stream_writer.writeCharacters("\n\t\t\t");

      xml_stream_writer.writeStartElement("data_nascita");
      xml_stream_writer.writeCharacters(persone.get(i).getDataNascita());
      xml_stream_writer.writeEndElement();
      xml_stream_writer.writeCharacters("\n\t\t\t");

      xml_stream_writer.writeStartElement("codice_fiscale");
      xml_stream_writer.writeCharacters(persone.get(i).getCodiceFiscale());
      xml_stream_writer.writeEndElement();
      xml_stream_writer.writeCharacters("\n\t\t");

      xml_stream_writer.writeEndElement();
    }
    xml_stream_writer.writeCharacters("\n\t");

    xml_stream_writer.writeEndElement();
    xml_stream_writer.writeCharacters("\n\t");

    xml_stream_writer.writeStartElement("codici");
    xml_stream_writer.writeCharacters("\n\t\t");

    xml_stream_writer.writeStartElement("invalidi");
    xml_stream_writer.writeAttribute("numero", String.valueOf(codici_fiscali_invalidi.size()));

    for (i = 0; i < codici_fiscali_invalidi.size(); i++) {
      xml_stream_writer.writeCharacters("\n\t\t\t");
      xml_stream_writer.writeStartElement("codice");
      xml_stream_writer.writeCharacters(codici_fiscali_invalidi.get(i));
      xml_stream_writer.writeEndElement();
    }
    xml_stream_writer.writeCharacters("\n\t\t");

    xml_stream_writer.writeEndElement();
    xml_stream_writer.writeCharacters("\n\t\t");

    xml_stream_writer.writeStartElement("spaiati");
    xml_stream_writer.writeAttribute("numero", String.valueOf(codici_fiscali_spaiati.size()));

    for (i = 0; i < codici_fiscali_spaiati.size(); i++) {
      xml_stream_writer.writeCharacters("\n\t\t\t");
      xml_stream_writer.writeStartElement("codice");
      xml_stream_writer.writeCharacters(codici_fiscali_invalidi.get(i));
      xml_stream_writer.writeEndElement();
    }
    xml_stream_writer.writeCharacters("\n\t\t");

    xml_stream_writer.writeEndElement();
    xml_stream_writer.writeCharacters("\n\t");

    xml_stream_writer.writeEndElement();
    xml_stream_writer.writeCharacters("\n");

    xml_stream_writer.writeEndElement();

    xml_stream_writer.writeEndDocument();
    xml_stream_writer.flush();
    xml_stream_writer.close();
  }
}
