package it.unibs.fp.codicefiscale;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XmlReader {
	
	public static ArrayList<Persona> readerPersona(){
		File input = new File("src//xml//inputPersone.xml");
		String nome = new String();
		String cognome = new String();
		String sesso = new String();
		String comune_di_nascita = new String();
		String data_di_nascita = new String();
		ArrayList<Persona> persone = new ArrayList<Persona>();
		XMLInputFactory xmlif = XMLInputFactory.newFactory();
		XMLStreamReader xmlr = null;
		ArrayList<String> nomi = new ArrayList<String>();
		ArrayList<String> cognomi = new ArrayList<String>();
		ArrayList<String> sessi = new ArrayList<String>();
		ArrayList<String> comuni_di_nascita = new ArrayList<String>();
		ArrayList<String> date_di_nascita = new ArrayList<String>();
		
		try {
			xmlr = xmlif.createXMLStreamReader(new FileInputStream(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (xmlr.hasNext()) {
				if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT) {
					// lettura nome e inizializzazione in un array di string
					if (xmlr.getLocalName().equals("nome")) {
						xmlr.next();
						if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
							nome = xmlr.getText();
							nomi.add(nome);
						}
					}
					// lettura cognome e inizializzazione in  un array di string
					else if (xmlr.getLocalName().equals("cognome")) {
						xmlr.next();
						if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
							cognome = xmlr.getText();
							cognomi.add(cognome);
						}
					}
					// lettura sesso e inizializzazione in  un array di string
					else if (xmlr.getLocalName().equals("sesso")) {
						xmlr.next();
						if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
							sesso = xmlr.getText();
							sessi.add(sesso);
						}
					}
					// lettura comune di nascita e inizializzazione in  un array di string
					else if (xmlr.getLocalName().equals("comune_nascita")) {
						xmlr.next();
						if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
							comune_di_nascita = xmlr.getText();
							comuni_di_nascita.add(comune_di_nascita);
						}
					}
					// lettura date di nascita e inizializzazione in  un array di string
					else if (xmlr.getLocalName().equals("data_nascita")) {
						xmlr.next();
						if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
							data_di_nascita = xmlr.getText();
							date_di_nascita.add(data_di_nascita);
						}
					}
				}
				xmlr.next();
			}
		}
		catch (XMLStreamException e) {
			e.printStackTrace();
		}
		//iserimento dei dati in persona
		for(int i=0;i<nomi.size();i++) {
			Persona persona = new Persona(nomi.get(i), cognomi.get(i), sessi.get(i), comuni_di_nascita.get(i), date_di_nascita.get(i));
			persone.add(persona);
		}
		return persone;
	}
	
	public static ArrayList<Comune> readerComuni() {
		File input = new File("src//xml//comuni.xml");
		String nome = new String();
		String codice = new String();
		ArrayList<Comune> comuni = new ArrayList<Comune>();
		XMLInputFactory xmlif = XMLInputFactory.newFactory();
		XMLStreamReader xmlr = null;
		ArrayList<String> nomi = new ArrayList<String>();
		ArrayList<String> codici = new ArrayList<String>();
		
		try {
			xmlr = xmlif.createXMLStreamReader(new FileInputStream(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (xmlr.hasNext()) {
				if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT) {
					// lettura codice e inizializzazione in un array di string
					if (xmlr.getLocalName().equals("nome")) {
						xmlr.next();
						if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
							nome = xmlr.getText();
							nomi.add(nome);
						}
					}
					// lettura nome e inizializzazione in  un array di string
					else if (xmlr.getLocalName().equals("codice")) {
						xmlr.next();
						if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
							codice = xmlr.getText();
							codici.add(codice);
						}
					}
				}
				xmlr.next();
			}
		}
		catch (XMLStreamException e) {
			e.printStackTrace();
		}
		//iserimento codici e comuni
		for(int i=0;i<nomi.size();i++) {
			Comune comune = new Comune(nomi.get(i),codici.get(i));
			comuni.add(comune);
		}
		return comuni;
	}
	
	public static ArrayList<String> readerCodiciFiscali() {
		File input = new File("src//xml//codiciFiscali.xml");
		String codice = new String();
		ArrayList<String> codici = new ArrayList<String>();
		XMLInputFactory xmlif = XMLInputFactory.newFactory();
		XMLStreamReader xmlr = null;
		
		try {
			xmlr = xmlif.createXMLStreamReader(new FileInputStream(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (xmlr.hasNext()) {
				if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT) {
					// lettura codice e inizializzazione in un array di string
					if (xmlr.getLocalName().equals("codice")) {
						xmlr.next();
						if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
							codice = xmlr.getText();
							codici.add(codice);
						}
					}
				}
				xmlr.next();
			}
		}
		catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return codici;
	}
}