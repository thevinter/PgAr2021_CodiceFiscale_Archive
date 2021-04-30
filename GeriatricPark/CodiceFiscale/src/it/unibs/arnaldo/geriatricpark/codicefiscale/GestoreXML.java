package it.unibs.arnaldo.geriatricpark.codicefiscale;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * Gestisce la lettura e scrittura su file XML, contiene metodi per il parsing degli specifici file
 * contenenti i dati dell'esercizio sui codici fiscali per il programma Arnaldo.
 * @author Geriatric Park (Wiam Nasr Allah, Nicol Stocchetti, Mohamed Nassar).
 *
 */
public class GestoreXML {
	private static XMLInputFactory inputFactory = creaInputFactory();
	private static XMLOutputFactory outputFactory = creaOutputFactory();
	
	private static XMLStreamReader reader = null;
	private static XMLStreamWriter writer = null;

	/**
	 * Crea e ritorna un oggetto di tipo XMLInputFactory, che viene usato per inizializzare
	 * la relativa variabile di classe.
	 * @return la Input Factory, XMLInputFactory.
	 */
	private static XMLInputFactory creaInputFactory() {
		XMLInputFactory inFactory = null;
		try {
			inFactory = XMLInputFactory.newInstance();
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione dell'Input Factory: ");
			System.out.println(e.getMessage());
		}
		return inFactory;
	}
	
	/**
	 * Crea e ritorna un oggetto di tipo XMLOutputFactory, che viene usato per inizializzare
	 * la relativa variabile di classe.
	 * @return la Output Factory, XMLOutputFactory.
	 */
	private static XMLOutputFactory creaOutputFactory() {
		XMLOutputFactory outFactory = null;
		try {
			outFactory = XMLOutputFactory.newInstance();
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione dell'Output Factory: ");
			System.out.println(e.getMessage());
		}
		return outFactory;
	}
	
	/**
	 * Inizializza lo stream reader, fornendogli il file XML da leggere.
	 * @param percorsoFile il percorso del file da leggere, String.
	 * @return lo stream reader, XMLStreamReader.
	 */
	public static XMLStreamReader impostaReader(String percorsoFile) {
		try {
			GestoreXML.reader = GestoreXML.inputFactory.createXMLStreamReader(percorsoFile, new FileInputStream(percorsoFile));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader: ");
			System.out.println(e.getMessage());
			System.exit(0); // dato che non c'è il file è inutile che il programma prosegua
		}
		return GestoreXML.reader; //in teoria non dovrebbe comunque servire in quanto tutto l'XML viene gestito
								  //esclusivamente all'interno dei metodi di questa classe.
	}
	
	/**
	 * Inizializza lo stream writer e crea il documento XML su cui scrivere.
	 * @param percorsoFile il percorso del file da creare, String.
	 * @return lo stream writer, XMLStreamWriter.
	 */
	public static XMLStreamWriter impostaWriter(String percorsoFile) {
		try {
			GestoreXML.writer = GestoreXML.outputFactory.createXMLStreamWriter(new FileOutputStream(percorsoFile), "utf-8");
			GestoreXML.writer.writeStartDocument("utf-8", "1.0");
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del writer: ");
			System.out.println(e.getMessage());
			System.exit(0); // dato che non c'è il file è inutile che il programma prosegua
		}
		return GestoreXML.writer; //in teoria non dovrebbe comunque servire in quanto tutto l'XML viene gestito
		  						  //esclusivamente all'interno dei metodi di questa classe.
	}

	/**
	 * Ritorna l'ultimo reader creato.
	 * @return il reader, XMLStreamReader.
	 */
	public static XMLStreamReader getReader() {
		return reader;
	}

	/**
	 * Ritorna l'ultimo writer creato.
	 * @return il writer, XMLStreamWriter.
	 */
	public static XMLStreamWriter getWriter() {
		return writer;
	}
	
	/**
	 * Crea un oggetto di tipo Persona parsando il contenuto di un elemento di tipo persona, partendo a scorrere dal tag di apertura
	 * fino ad arrivare al corrispondente tag di chiusura.
	 * @return un oggetto contenente i dati parsati a partire da un elemento XML di tipo persona, Persona.
	 */
	private static Persona estraiPersona() {
		int id = 0;
		String nome = "0";
		String cognome = "0";
		char sesso = '0';
		String comuneDiNascita = "0";
		int annoDiNascita = 0;
		int meseDiNascita = 0;
		int giornoDiNascita = 0;
		String dataDiNascita = "0";
		
		try {
			id = Integer.parseInt(GestoreXML.reader.getAttributeValue(0)); //prende l'id dallo START_ELEMENT di questa persona
			GestoreXML.reader.next(); // va al CHARACTER tra persona e nome
			GestoreXML.reader.next(); // va allo START_ELEMENT nome
			
			while (reader.getLocalName() != "persona") { //fino a quando non arrivo alla fine di questa persona, sull'END_ELEMENT che si chiama "persona".
				switch (reader.getLocalName()) {
				case "nome":
						reader.next(); //va nel campo CHARACTERS
						nome = reader.getText(); //prende il testo contenuto in CHARACTERS
						reader.next(); //va all'END_ELEMENT
						reader.next(); //salta il CHARACTERS (vuoto) tra la fine di un elemento e l'inizio di un altro
						reader.next(); //va al prossimo START_ELEMENT
					break;
				case "cognome":
						reader.next(); //va nel campo CHARACTERS
						cognome = reader.getText(); //prende il testo contenuto in CHARACTERS
						reader.next(); //va all'END_ELEMENT
						reader.next(); //salta il CHARACTERS (vuoto) tra la fine di un elemento e l'inizio di un altro
						reader.next(); //va al prossimo START_ELEMENT
					break;
				case "sesso":
						reader.next(); //va nel campo CHARACTERS
						sesso = reader.getText().charAt(0); //prende il testo contenuto in CHARACTERS
						reader.next(); //va all'END_ELEMENT
						reader.next(); //salta il CHARACTERS (vuoto) tra la fine di un elemento e l'inizio di un altro
						reader.next(); //va al prossimo START_ELEMENT
					break;
				case "comune_nascita":
						reader.next(); //va nel campo CHARACTERS
						comuneDiNascita = reader.getText(); //prende il testo contenuto in CHARACTERS
						reader.next(); //va all'END_ELEMENT
						reader.next(); //salta il CHARACTERS (vuoto) tra la fine di un elemento e l'inizio di un altro
						reader.next(); //va al prossimo START_ELEMENT
					break;
				case "data_nascita":
						reader.next(); //va nel campo CHARACTERS
						dataDiNascita = reader.getText(); //prende il testo contenuto in CHARACTERS
						reader.next(); //va all'END_ELEMENT
						reader.next(); //salta il CHARACTERS (vuoto) tra la fine di un elemento e l'inizio di un altro
						reader.next(); //va all'END_ELEMENT di persona
					break;
				default:
					System.out.println("C'è stato qualche errore nel meccanismo di parsing...");
					break;
				}
			}
		} catch (XMLStreamException e) {
			System.out.println("Eccezione: ");
			e.printStackTrace();
		}
		
		annoDiNascita = Integer.parseInt(dataDiNascita.substring(0, 4));
		meseDiNascita = Integer.parseInt(dataDiNascita.substring(5, 7));
		giornoDiNascita = Integer.parseInt(dataDiNascita.substring(8, 10));
		
		return new Persona(id, nome, cognome, sesso, comuneDiNascita, annoDiNascita, meseDiNascita, giornoDiNascita);
	}
	
	/**
	 * Parsa tutte le persone presenti nel file XML inputPersone e le ritorna sotto forma di ArrayList di oggetti di tipo Persona.
	 * @return un ArrayList di persone, ArrayList<Persona>.
	 */
	public static ArrayList<Persona> creaArrayListPersone() {
		ArrayList<Persona> persone = new ArrayList<Persona>();
		//int numeroDiPersone = 0;
		
		impostaReader("./xml/inputPersone.xml");
		try {
			GestoreXML.reader.next(); //salta lo START_DOCUMENT e va allo START_ELEMENT persone
			//numeroDiPersone = Integer.parseInt(GestoreXML.reader.getAttributeValue(0));
			GestoreXML.reader.next();//va al CHARACTER tra persone e persona
			GestoreXML.reader.next(); //va allo START_ELEMENT della prima persona;
			
			do {
				persone.add(estraiPersona());
				
				GestoreXML.reader.next(); // va al CHARACTER tra l'END_ELEMENT di questa persona e lo START_ELEMENT di una nuova persona.
				GestoreXML.reader.next(); //va allo START_ELEMENT di una nuova persona oppure all'END_ELEMENT di persone, se arriviamo alla fine.
			} while(GestoreXML.reader.getLocalName() != "persone");
		
		} catch (XMLStreamException e) {
			System.out.println("Eccezione: ");
			e.printStackTrace();
		}
		return persone;
	}
	
	/**
	 *Parsa tutti i codici fiscali presenti nel file XML codiciFiscali e li ritorna sotto forma di ArrayList di oggetti di tipo CodiceFiscale.
	 * @return un ArrayList di codici fiscali, ArrayList<CodiceFiscale>.
	 */
	public static ArrayList<CodiceFiscale> estraiCF(){
		ArrayList<CodiceFiscale> codici = new ArrayList<CodiceFiscale>();
		int beforeElementType = 0;
		String beforeElementName = "";
		
		try {
			GestoreXML.impostaReader("./xml/codiciFiscali.xml");
			
			while(reader.getEventType() != XMLStreamConstants.CHARACTERS) {
				beforeElementType = reader.getEventType();
				if(reader.getEventType() == XMLStreamConstants.START_ELEMENT)
					beforeElementName = reader.getLocalName();
				
					reader.next();
			}//Arriva al primo campo CHARACTERS
			
			while(reader.hasNext()) {
				
				if(beforeElementType == XMLStreamConstants.START_ELEMENT && beforeElementName.equals("codice"))
					codici.add(new CodiceFiscale(reader.getText()));
				
				beforeElementType = reader.getEventType();
				if(reader.getEventType() == XMLStreamConstants.START_ELEMENT)
					beforeElementName = reader.getLocalName();
				reader.next();
			}
		} catch (XMLStreamException e) {
			System.out.println("Eccezione: ");
			e.printStackTrace();
		}
		return codici;
	}
	
	/**
	 * Dato il nome di un comune estrae il codice corrispondente dal file XML comuni.
	 * @param comune, il nome del comune, String.
	 * @return il codice associato al comune, String.
	 */
	public static String estraiCodiceComune(String comune) {
		int beforeElementType = 0;
		String beforeElementName = "";
		
		try {
			GestoreXML.impostaReader("./xml/comuni.xml");
			
			while(reader.getEventType() != XMLStreamConstants.CHARACTERS) {
				beforeElementType = reader.getEventType();
				if(reader.getEventType() == XMLStreamConstants.START_ELEMENT)
					beforeElementName = reader.getLocalName();
				
					reader.next();
			}//Arriva al primo campo CHARACTERS
			
			while(reader.hasNext()) {
				
				if(beforeElementType == XMLStreamConstants.START_ELEMENT && beforeElementName.equals("nome")) {
					if (reader.getText().equals(comune)) {
						reader.next();
						reader.next();
						reader.next();
						reader.next();
						return reader.getText();
					}
				}
				beforeElementType = reader.getEventType();
				if(reader.getEventType() == XMLStreamConstants.START_ELEMENT)
					beforeElementName = reader.getLocalName();
				reader.next();
			}
		} catch (XMLStreamException e) {
			System.out.println("Eccezione: ");
			e.printStackTrace();
		}
		
		return "NON TROVATO";
	}
	
	/**
	 * Controlla se il codice fornito corrisponde a quello di un comune.
	 * @param codice il codice, String.
	 * @return true se esiste un comune corrispondente, altrimenti false, boolean.
	 */
	public static boolean esistenzaCodiceComune(String codice) {
		int beforeElementType = 0;
		String beforeElementName = "";
		
		try {
			GestoreXML.impostaReader("./xml/comuni.xml");
			
			while(reader.getEventType() != XMLStreamConstants.CHARACTERS) {
				beforeElementType = reader.getEventType();
				if(reader.getEventType() == XMLStreamConstants.START_ELEMENT)
					beforeElementName = reader.getLocalName();
				
					reader.next();
			}//Arriva al primo campo CHARACTERS
			
			while(reader.hasNext()) {
				
				if(beforeElementType == XMLStreamConstants.START_ELEMENT && beforeElementName.equals("nome")) {
					reader.next();
					reader.next();
					reader.next();
					reader.next();
					if (reader.getText().equals(codice))
						return true;		
				}
				beforeElementType = reader.getEventType();
				if(reader.getEventType() == XMLStreamConstants.START_ELEMENT)
					beforeElementName = reader.getLocalName();
				reader.next();
			}
		} catch (XMLStreamException e) {
			System.out.println("Eccezione: ");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * A partire da un oggetto di tipo CodiceFiscale genera il codice XML che descrive la persona ad esso associata.
	 * @param cf il codice fiscale, it.unibs.arnaldo.geriatricpark.codicefiscale.CodiceFiscale
	 * @param codiciEstratti i codici fiscali estratti dal file XML codiciFiscali, ArrayList<CodiceFiscale>
	 */
	private static void scriviPersona(CodiceFiscale cf, ArrayList<CodiceFiscale> codiciEstratti) {
		
		boolean presenteInElenco = false;
		
		try {
			writer.writeStartElement("persona"); // scrittura del tag di apertura persona
			writer.writeAttribute("id", Integer.toString(cf.getPersona().getId())); // inserisce l'attributo id
			
			writer.writeStartElement("nome"); // scrittura del tag di apertura nome
			writer.writeCharacters(cf.getPersona().getNome()); // inserisce nome nel CHARACTERS
			writer.writeEndElement(); // chiusura dell'ultimo tag aperto
			
			writer.writeStartElement("cognome"); // scrittura del tag di apertura cognome
			writer.writeCharacters(cf.getPersona().getCognome()); // inserisce cognome nel CHARACTERS
			writer.writeEndElement(); // chiusura dell'ultimo tag aperto
			
			writer.writeStartElement("sesso"); // e così via...
			writer.writeCharacters(cf.getPersona().getSesso() + ""); 
			writer.writeEndElement();
			
			writer.writeStartElement("comune_nascita");
			writer.writeCharacters(cf.getPersona().getComuneDiNascita()); 
			writer.writeEndElement();
			
			writer.writeStartElement("data_nascita");
			writer.writeCharacters(Integer.toString(cf.getPersona().getAnnoDiNascita()) + "-" + Integer.toString(cf.getPersona().getMeseDiNascita()) + "-" +  Integer.toString(cf.getPersona().getGiornoDiNascita())); 
			writer.writeEndElement();
			
			writer.writeStartElement("codice_fiscale");
			
			for(CodiceFiscale codiceEstratto : codiciEstratti) {
				if(codiceEstratto.getValore().equals(cf.getValore())) {
					presenteInElenco = true;
					break;
				}
			}
			
			if(presenteInElenco)
				writer.writeCharacters(cf.getValore()); 
			else
				writer.writeCharacters("ASSENTE"); 
			
			writer.writeEndElement();
			
			writer.writeEndElement(); //chiusura del tag persona
			
		} catch (Exception e) {
			System.out.println("Eccezione: ");
			}
	}
	
	/**
	 * Genera il documento XML codiciPersone e lo compila come richiesto.
	 * @param codiciPersone i codici fiscali generati dal documento inputPersone, ArrayList<CodiceFiscale>
	 * @param codiciEstratti i codici fiscali generati dal documento codiciFiscali, ArrayList<CodiceFiscale>
	 * @param codiciInvalidi i codici fiscali generati dal documento codiciFiscali che risultano invalidi, ArrayList<CodiceFiscale>
	 * @param codiciSpaiati i codici fiscali generati dal documento codiciFiscali che risultano spaiati rispetto a quelli generati
	 * dal file inputPersone, ArrayList<CodiceFiscale>
	 */
	public static void scriviDocumento(ArrayList<CodiceFiscale> codiciPersone, ArrayList<CodiceFiscale> codiciEstratti, ArrayList<CodiceFiscale> codiciInvalidi, ArrayList<CodiceFiscale> codiciSpaiati) {
		impostaWriter("./xml/codiciPersone.xml");
		
		try {
			writer.writeStartElement("output"); // apertura del tag radice output
			writer.writeStartElement("persone"); // apertura del tag persone
			writer.writeAttribute("numero", Integer.toString(codiciPersone.size())); // inserisce l'attributo id
			
			for (CodiceFiscale codicePersona : codiciPersone)
				scriviPersona(codicePersona, codiciEstratti);
			
			writer.writeEndElement(); // chiusura del tag persone
			
			
			writer.writeStartElement("codici"); // apertura del tag codici
			
			writer.writeStartElement("invalidi"); // apertura del tag invalidi
			writer.writeAttribute("numero", Integer.toString(codiciInvalidi.size())); // inserisce l'attributo numero
			
			for (CodiceFiscale cf : codiciInvalidi) {
				writer.writeStartElement("codice");
				writer.writeCharacters(cf.getValore());
				writer.writeEndElement();
			}
			
			writer.writeEndElement(); // chiusura del tag invalidi
			
			writer.writeStartElement("spaiati"); // apertura del tag spaiati
			writer.writeAttribute("numero", Integer.toString(codiciSpaiati.size())); // inserisce l'attributo numero
			
			for (CodiceFiscale cf : codiciSpaiati) {
				writer.writeStartElement("codice");
				writer.writeCharacters(cf.getValore());
				writer.writeEndElement();
			}
			
			writer.writeEndElement(); // chiusura del tag spaiati
			
			writer.writeEndElement(); // chiusura del tag codici
			
			writer.writeEndElement(); // chiusura del tag radice output
			writer.writeEndDocument(); // apertura della fine del documento
			writer.flush(); // svuota il buffer e procede alla scrittura
			writer.close(); // chiusura del documento e delle risorse impiegate
			} catch (Exception e) { // se c’è un errore viene eseguita questa parte
			System.out.println("Errore nella scrittura");
			}
	}
	
}
