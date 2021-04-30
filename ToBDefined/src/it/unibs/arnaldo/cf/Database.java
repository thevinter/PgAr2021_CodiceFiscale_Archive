package it.unibs.arnaldo.cf;

import java.util.ArrayList;


/**
 * Metodo che gestisce tutti i dati
 * Contiene un ArrayList con tutte le persone e i codici fiscali
 * Vengono estratti dai cfs quelli invalidi e quelli spaiati, immagazzinati separatamente
 * 
 * @author toBdefined
 *
 */
public class Database {
	private static final String FEEDBACK_SCRITTURA_FILE = "Fine scrittura file.";
	private static final String XML_OUTPUT_PERSONE = "codiciPersone.xml";
	private static final String XML_CODICI_FISCALI = "codiciFiscali.xml";
	private static final String XML_INPUT_PERSONE = "inputPersone.xml";
	private ArrayList<Persona> persone;
	private ArrayList<String> cfs;
	private ArrayList<String> cfsInvalidi;
	private ArrayList<String> cfsSpaiati;
	
	/**
	 * Metodo costruttore del database, crea l'arraylist di persone e metto a null gli altri
	 */
	public Database() {
		XMLReaderPersone xmlr = new XMLReaderPersone(XML_INPUT_PERSONE);
		this.persone = xmlr.read();
		cfs = null;
		cfsInvalidi = null;
		cfsSpaiati = null;
	}
	
	/**
	 * Getter dell'arraylist persone
	 * @return persone
	 */
	public ArrayList<Persona> getPersone(){
		return persone;
	}
	
	/**
	 * Getter dell'arraylist cfsInvalidi
	 * @return cfsInvalidi
	 */
	public ArrayList<String> getCfsInvalidi(){
		return cfsInvalidi;
	}
	
	/**
	 * Getter dell'arraylist cfsSpaiati
	 * @return cfsSpaiati
	 */
	public ArrayList<String> getCfsSpaiati(){
		return cfsSpaiati;
	}
	
	/**
	 * Ritorna la posizione della persona, dato il suo codice fiscale
	 * @param cf Stringa con il codice fiscale
	 * @return la posizione della persona, -1 se è assente
	 */
	public int getPersonaByCF(String cf) {
		for(Persona p:persone)
			if(p.getCf().equals(cf))
				return persone.indexOf(p);
		
		return -1;
	}
	
	/**
	 * Metodo usato per leggere i codici fiscali da file
	 * Istanzia la classe XMLReaderCF passando il path al costruttore e usa il metodo per la lettura
	 */
	public void readCfs() {
		XMLReaderCF xmlrc = new XMLReaderCF(XML_CODICI_FISCALI);
		cfs = xmlrc.read();
	}
	
	/**
	 * Metodo usato per scrivere il file con i dati
	 * Istanzia la classe GestoreXMLWriter passando il path al costruttore 
	 * Usa il metodo per la scrittura, passandogli gli arraylist di persone, cfsInvalidi, cfsSpaiati
	 */
	public void writeCfs() {
		GestoreXMLWriter xmlw = new GestoreXMLWriter(XML_OUTPUT_PERSONE);
		xmlw.scriviXML(persone, cfsInvalidi, cfsSpaiati);
		System.out.println(FEEDBACK_SCRITTURA_FILE);
	}
	
	/**
	 * Metodo che verifica la validità dei codici fiscali
	 * I codici non validi vengono tolti da cfs e messi in cfsInvalidi
	 */
	public void checkCfsValidi(){
		cfsInvalidi = new ArrayList<String>();
		int i=0;
		
		while(i < cfs.size()) {
			if(CodiceFiscale.validateCF(cfs.get(i)) == false) {
				cfsInvalidi.add(cfs.get(i));
				cfs.remove(i);
			}
			else i++;
		}
	}

	/**
	 * Metodo che verifica se i codici fiscali sono spaiati e imposta la presenza dei cf nelle persone
	 * I codici spaiati vengono tolti da cfs e messi in cfsSpaiati
	 */
	public void checkCfsSpaiati(){
		cfsSpaiati = new ArrayList<String>();
		//i = iteratore di cfs, pos = posizione della persona
		int i=0, pos;
		
		while(i < cfs.size()) {
			pos = getPersonaByCF(cfs.get(i));
			//se la persona non viene trovata = -1 = cf spaiato
			if(pos == -1) {
				cfsSpaiati.add(cfs.get(i));
				cfs.remove(cfs.get(i));
			}
			else{
				persone.get(pos).setAssente(false);
				i++;
			}
		}
	}
	
	/**
	 * Metodo per genererare tutti i codici fiscali di persone
	 */
	public void genAll() {
		for(Persona p : persone)
			p.genCodiceFiscale();
	}
	
	/**
	 * Metodo per stampare tutte le persone [Per il debug e i test]
	 */
	public void printAllPersone() {
		for(Persona p : persone)
			System.out.println(p);
	}
	
	/**
	 * Metodo per stampare tutte le persone che non sono assenti [Per il debug e i test]
	 */
	public void printAllPresenti() {
		for(Persona p : persone)
			if(!p.isAssente())
				System.out.println(p);
	}
	
	/**
	 * Metodo per la scrittura di tutti i codici fiscali [Per il debug e i test]
	 */
	public void printAllCFs() {
		for(String s : cfs)
			System.out.println(s);
	}
	
	/**
	 * Metodo per la scrittura di tutti i codici fiscali invalidi [Per il debug e i test]
	 */
	public void printAllCFsInvalidi() {
		for(String s : cfsInvalidi)
			System.out.println(s);
	}
	
	/**
	 * Metodo per la scrittura di tutti i codici spaiati [Per il debug e i test]
	 */
	public void printAllCFsSpaiati() {
		for(String s : cfsSpaiati)
			System.out.println(s);
	}
}
