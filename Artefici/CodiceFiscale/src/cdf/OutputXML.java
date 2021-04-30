package cdf;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class OutputXML {
	
	private static final String TAG_ASSENTE = "ASSENTE";
	private static final String ERRORE_SCRITTURA = "Errore nella scrittura";
	private static final String TAG_SPAIATI = "spaiati";
	private static final String TAG_CODICE = "codice";
	private static final String TAG_INVALIDI = "invalidi";
	private static final String TAG_CODICI = "codici";
	private static final String TAG_CODICE_FISCALE = "codice_fiscale";
	private static final String TAG_DATA_NASCITA = "data_nascita";
	private static final String TAG_COMUNE_NASCITA = "comune_nascita";
	private static final String TAG_SESSO = "sesso";
	private static final String TAG_COGNOME = "cognome";
	private static final String TAG_NOME = "nome";
	private static final String ATTRIBUTE_ID = "id";
	private static final String TAG_PERSONA = "persona";
	private static final String ATTRIBUTE_NUMERO = "numero";
	private static final String TAG_PERSONE = "persone";
	private static final String TAG_OUTPUT = "output";
	private static final String ERRORE_WRITER = "Errore nell'inizializzazione del writer:";
	private static final String FORMAT_DATE = "yyyy-MM-dd";
	private static final String NOME_FILE_OUTPUT = "codiciPersone.xml";

	
	
	/**
	 * scrittura del file utilizzando le informazioni estratte dalle variabili passate come argomento
	 * @param persone
	 * @param codice_fiscali
	 * @param num_spaiati
	 * @param num_non_corretti
	 */
	public static void scriviXML(ArrayList<Persona> persone, ArrayList<CodiceFiscale> codice_fiscali, int num_spaiati, int num_non_corretti) {
		SimpleDateFormat stf=new SimpleDateFormat(FORMAT_DATE);
		
		
		//creazione della variabile xmlw di tipo XMLStreamWriter
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;
		try {
			//istanziamento della variabile xmlw 
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(NOME_FILE_OUTPUT), "utf-8");
			xmlw.writeStartDocument("utf-8", "1.0");
		} catch (Exception e) {
			System.out.println(ERRORE_WRITER);
			System.out.println(e.getMessage());
		}
		try { // blocco try per raccogliere eccezioni
			xmlw.writeStartElement(TAG_OUTPUT); //scrittura del tag output

			xmlw.writeStartElement(TAG_PERSONE); // scrittura del tag persone
			xmlw.writeAttribute(ATTRIBUTE_NUMERO, String.valueOf(persone.size())); // ...con attributo numero...
			
			for (int i = 0; i < persone.size(); i++) {
				Persona persona= persone.get(i);
				xmlw.writeStartElement(TAG_PERSONA);// scrittura del tag persone
				xmlw.writeAttribute(ATTRIBUTE_ID, persona.getId());// ...con attributo numero...
				
				xmlw.writeStartElement(TAG_NOME);// scrittura del tag persona
				xmlw.writeCharacters(persona.getNome());// scrittura del content di  nome
				xmlw.writeEndElement(); //chiusura del tag persona
				
				xmlw.writeStartElement(TAG_COGNOME);//apertura del tag cognome
				xmlw.writeCharacters(persona.getCognome());// scrittura del content di  cognome
				xmlw.writeEndElement();//chiusura del tag cognome
				
				xmlw.writeStartElement(TAG_SESSO);//apertura del tag sesso 
				xmlw.writeCharacters(persona.getSesso());// scrittura del content di  sesso
				xmlw.writeEndElement(); //chiusura del tag sesso
				
				xmlw.writeStartElement(TAG_COMUNE_NASCITA);//apertura del tag comune_nascita
				xmlw.writeCharacters(persona.getComune_nascita().getNome());// scrittura del content di  comune_nascita
				xmlw.writeEndElement(); //chiusura del tag comune_nascita
				
				xmlw.writeStartElement(TAG_DATA_NASCITA);//apertura del tag data_nascita
				xmlw.writeCharacters(stf.format(persona.getData_nascita()));// scrittura del content di  data_nascita
				xmlw.writeEndElement();//chiusura del tag data_nascita
				
				xmlw.writeStartElement(TAG_CODICE_FISCALE);//apertura del tag codice_fiscale
				if(persona.getIs_presente())//se il codice fiscale di una persona coincide con uno di quelli estratti dal file codici.xml...
					xmlw.writeCharacters(persona.getCodice_fiscale());// ...scrittura del codice fiscale come content del tag codice_fiscale
				else//altrimenti
					xmlw.writeCharacters(TAG_ASSENTE);// ...scrittura "ASSENTE" come content del tag codice_fiscale
				xmlw.writeEndElement(); //chiusura del tag codice_fiscale
				
				xmlw.writeEndElement(); //chiusura del tag persona
				
			}
			xmlw.writeEndElement(); // chiusura del tag persone
			
			xmlw.writeStartElement(TAG_CODICI);//apetura del tag codici 
			
			xmlw.writeStartElement(TAG_INVALIDI);//apertura del tag invalidi
			xmlw.writeAttribute(ATTRIBUTE_NUMERO, String.valueOf(num_non_corretti));
			for(int i=0; i<codice_fiscali.size(); i++) {
				CodiceFiscale cod=codice_fiscali.get(i);
				if(!cod.getIs_corretto()) {
					xmlw.writeStartElement(TAG_CODICE);//apertura del tag codice
					xmlw.writeCharacters(cod.getCodice());
					xmlw.writeEndElement(); //chiusura del tag codice
				}
			}
			xmlw.writeEndElement(); // chiusura del tag invalidi
			
			xmlw.writeStartElement(TAG_SPAIATI);//apertura del tag spaiati
			xmlw.writeAttribute(ATTRIBUTE_NUMERO, String.valueOf(num_spaiati));
			for(int i=0; i<codice_fiscali.size(); i++) {
				CodiceFiscale cod=codice_fiscali.get(i);
				if(cod.getIs_corretto() && !cod.getIs_appaiato()) {
					xmlw.writeStartElement(TAG_CODICE);//apertura del tag codice
					xmlw.writeCharacters(cod.getCodice());
					xmlw.writeEndElement(); //chiusura del tag codice
				}
			}
			xmlw.writeEndElement(); // chiusura del tag spaiati
			
			xmlw.writeEndElement(); // chiusura del tag codici
			
			xmlw.writeEndDocument(); // scrittura della fine del documento
			xmlw.flush(); // svuota il buffer e procede alla scrittura
			xmlw.close(); // chiusura del documento e delle risorse impiegate
		} catch (Exception e) { // se c’è un errore viene eseguita questa parte
			System.out.println(ERRORE_SCRITTURA);
		}


	}
}
