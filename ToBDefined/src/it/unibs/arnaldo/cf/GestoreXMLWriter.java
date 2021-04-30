package it.unibs.arnaldo.cf;

import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Metodo per la scrittura del file contenenti i dati elaborati
 * @author toBdefined
 *
 */
public class GestoreXMLWriter {
	private XMLOutputFactory xmlof = null;
	private XMLStreamWriter xmlw = null;
	private int nTabs = 0;
	
	/**
	 * Metodo costruttore del writer, crea un writer dato il path
	 * @param path percorso del file
	 */
	public GestoreXMLWriter(String path) {
		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(path), "utf-8");
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del writer:");
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo per la scrittura dell'XML completo,
	 * richiama i metodi creati in seguito, usati opportunamente e con dei cicli
	 * @param persone arraylist contenente le persone
	 * @param cfsInvalidi arraylist contenente i codici fiscali invalidi
	 * @param cfsSpaiati arraylist contenente i codici fiscali spaiati
	 */
	public void scriviXML(ArrayList<Persona> persone, ArrayList<String> cfsInvalidi, ArrayList<String> cfsSpaiati) {
		try {
			iniziaXML();
				apriTag("output");
					apriTagConAttr("persone", "numero", ""+persone.size());
					for(Persona p : persone) {
						writePersona(p);
					}
					chiudiTag();
					
					
					apriTag("codici");
						apriTagConAttr("invalidi", "numero", ""+cfsInvalidi.size());
						for(String s : cfsInvalidi) {
							writeSimpleTag("codice", s);
						}
						chiudiTag();
						
						apriTagConAttr("spaiati", "numero", ""+cfsSpaiati.size());
						for(String s : cfsSpaiati) {
							writeSimpleTag("codice", s);
						}
						chiudiTag();
					chiudiTag();
					
				chiudiTag();
			chiudiXML();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo che inizia il file XML, scrivendo l'intestazione (va a capo)
	 * @throws XMLStreamException
	 */
	private void iniziaXML() throws XMLStreamException {
		xmlw.writeStartDocument("utf-8", "1.0");aCapo();
	}

	/**
	 * Metodo che termina l'XML, scrive i dati immagazzinati nella cache, dentro il file e chiude
	 * @throws XMLStreamException
	 */
	private void chiudiXML() throws XMLStreamException {
		xmlw.writeEndDocument();
		xmlw.flush();
		xmlw.close();
	}

	/**
	 * Metodo da usare per aprire i tag (tabula, stampa e va a capo)
	 * @param s stringa del nome del tag
	 * @throws XMLStreamException
	 */
	private void apriTag(String tagName) throws XMLStreamException {
		tabula(nTabs++);
		xmlw.writeStartElement(tagName);
		aCapo();
	}

	/**
	 * Metodo da usare per aprire i tag con attributo (tabula, stampa e va a capo)
	 * @param s stringa col nome del tag
	 * @param attr stringa col nome dell'attributo
	 * @param val stringa col valore dell'attributo
	 * @throws XMLStreamException
	 */
	private void apriTagConAttr(String tagName, String attr, String val) throws XMLStreamException {
		tabula(nTabs++);
		xmlw.writeStartElement(tagName);
		xmlw.writeAttribute(attr, val);
		aCapo();
	}

	/**
	 * Metodo da usare per chiudere i tag (tabula, stampa e va a capo)
	 * @throws XMLStreamException
	 */
	private void chiudiTag() throws XMLStreamException {
		tabula(--nTabs);
		xmlw.writeEndElement();
		aCapo();
	}

	/**
	 * Metodo da usare per la scrittura di una persona (tabula, stampa e va a capo)
	 * @param p persona da stampare
	 * @throws XMLStreamException
	 */
	private void writePersona(Persona p) throws XMLStreamException {
		tabula(nTabs++);
		xmlw.writeStartElement("persona");
		xmlw.writeAttribute("id", ""+p.getId());
		aCapo();
		writeSimpleTag("nome", p.getNome());
		writeSimpleTag("cognome", p.getCognome());
		writeSimpleTag("sesso", p.getSesso().toString());
		writeSimpleTag("comune_nascita", p.getComune());
		writeSimpleTag("data_nascita", p.getNascita());
		//Se il codice fiscale è assente scrivo "ASSENTE" altrimenti il codice fiscale
		writeSimpleTag("codice_fiscale", (p.isAssente() ? "ASSENTE" : p.getCf()));
		chiudiTag();
	}

	/**
	 * Metodo da usare per la scrittura dei tag semplici (tabula, stampa e va a capo)
	 * tag semplici =solo testo, no attributi, no sotto-tag
	 * @param tagName stringa col nome del tag
	 * @param characters stringa da stampare tra i tag
	 */
	public void writeSimpleTag(String tagName, String characters) {
		try {
			tabula(nTabs);
			xmlw.writeStartElement(tagName);
			xmlw.writeCharacters(characters);
			xmlw.writeEndElement();
			aCapo();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo da usare per inserire n tabulazioni
	 * @param n numero di indentazioni
	 */
	public void tabula(int n) {
		try {
			for(;n>0;n--)
				xmlw.writeCharacters("\t");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo da usare per andare a capo
	 */
	public void aCapo() {
		try {
			xmlw.writeCharacters("\r\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
