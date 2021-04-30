package it.unibs.arnaldo.cf;

import java.util.HashMap;


/**
 * Classe che contiene tutti i comuni
 * Sono presenti dei metodi statici che permettono la ricerca dei comuni e del loro codice
 * @author toBdefined
 *
 */
public class ElencoComuni {
	private static HashMap<String, String> comuni;
	
	
	/**
	 * Metodo che inizializza il contenuto della classe
	 * legge il file comuni.xml e mette il contenuto nell'hashmap comuni
	 */		
	public static void init() {
		XMLReaderComuni xmlr = new XMLReaderComuni("comuni.xml");
		try {
			comuni = xmlr.read();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	/**
	 * Ritorna il codice del comune passato
	 * @param nome stringa con il nome del comune
	 * @return il codice del comune
	 */	
	public static String getCodice(String nome) {
		return comuni.get(nome.toUpperCase());
	}
	
	
	/**
	 * Verifica della validità del codice del comune cercandone la corrispondenza nell'hashmap (presa dal file Comuni.xml)
	 * @param codice stringa con il codice del comune
	 * @return true/false validità del codice
	 */	
	public static boolean isValid(String codice) {
		return comuni.containsValue(codice);
	}
	
	
	/**
	 * Stampa di ogni codice e di ogni comune presente [Per il debug e i test]
	 */	
	public static void printAll() {
		for (String comune: comuni.keySet()) {
		    String key = comune.toString();
		    String value = comuni.get(comune).toString();
		    System.out.println(key + " " + value);
		}
	}

}
