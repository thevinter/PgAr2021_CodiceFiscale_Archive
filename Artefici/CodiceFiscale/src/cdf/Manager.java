package cdf;

import java.util.ArrayList;

public class Manager {
	private static final String SCRITTURA_IN_CORSO = "Scrittura del file codiciPersone.xml in corso...";
	private static final String SCRITTURA_COMPLETATA = "Scrittura completata: puoi reperire il file all'interno della cartella del progetto \n";
	private static final String VERIFICA_PRESENZA_CODICE_FISCALE = "Verifica presenza codice fiscale nel file codiciFiscali.xml";
	private static final String VERIFICA_COMPLETATA = "Verifica completata \n";
	private static final String VERIFICA_VALIDITA_CODICI_FISCALI = "Verifica validita' codici fiscali in corso...";
	private static final String GENERAZIONE_CODICI_FISCALI = "Generazione codici fiscali in corso...";
	private static final String TENTATIVO_LETTURA_FILE = "Tentativo lettura file %s";
	private static final String GENERAZIONE_CODICI_FISCALI_COMPLETATA = "Generazione codici fiscali completata \n";
	private static final String LETTURA_CONCLUSA = "Lettura %s conclusa \n";
	
	private static ArrayList<Persona> persone;
	private static ArrayList<Comune> comuni;
	private static ArrayList<CodiceFiscale> codici_fiscali;
	private static int numer_codici_non_corretti=0;
	private static int numero_codici_appaiati=0;
	
	/**
	 * lettura del file passato come argomento e istaziamento di un'ArrayList contenente le informazioni ricavate 
	 * @param nome_file
	 */
	public static void prelievoDatiComuni(String nome_file) {
		System.out.println(String.format(TENTATIVO_LETTURA_FILE, nome_file));
		comuni=InputXML.leggiXMLComune(nome_file);
		System.out.println(String.format(LETTURA_CONCLUSA, nome_file));
	}
	
	/**
	 * lettura del file passato come argomento e istaziamento di un'ArrayList contenente le informazioni ricavate 
	 * @param nome_file
	 */
	public static void prelievoDatiPersone(String nome_file) {
		System.out.println(String.format(TENTATIVO_LETTURA_FILE, nome_file));
		persone=InputXML.leggiXMLPersone(nome_file);
		System.out.println(String.format(LETTURA_CONCLUSA, nome_file));
	}
	
	/**
	 * lettura del file passato come argomento e istaziamento di un'ArrayList contenente le informazioni ricavate 
	 * @param nome_file
	 */
	public static void prelievoDatiCodiciFiscali(String nome_file) {
		System.out.println(String.format(TENTATIVO_LETTURA_FILE, nome_file));
		codici_fiscali=InputXML.leggiXMLCodiceFiscale(nome_file);
		System.out.println(String.format(LETTURA_CONCLUSA, nome_file));
	}
	
	/**
	 * generazione e istanziamento del codice fiscale ad ogni persona estratta dall'ArrayList di tipo Persona
	 */
	public static void generaCodiciFiscaliPersone() {
		System.out.println(GENERAZIONE_CODICI_FISCALI);
		String cod_fisc;
		for(Persona pers: persone) {
			cod_fisc=CodiceFiscale.generazioneCodiceFiscale(pers);
			pers.setCodice_fiscale(cod_fisc);
		}
		System.out.println(GENERAZIONE_CODICI_FISCALI_COMPLETATA);
	}
	
	/**
	 * ritorna l'oggetto Comune avente come nome il valore del parametro fornito
	 * @param nome_comune
	 * @return oggetto Comune
	 */
	public static Comune getComune(String nome_comune) {
		for(Comune com: comuni) {
			if(com.getNome().equalsIgnoreCase(nome_comune)) {
				return com;
			}
		}
		return null;
	}
	
	/**
	 *verifica la validità del codice fiscale
	 */
	public static void verificaValiditaCodiciFiscali() {
		System.out.println(VERIFICA_VALIDITA_CODICI_FISCALI);
		boolean corretto;
		for(CodiceFiscale cod_fisc: codici_fiscali) {
			corretto = cod_fisc.verificaCodiceFiscale(cod_fisc.getCodice());
			cod_fisc.setIs_corretto(corretto);
		}
		System.out.println(VERIFICA_COMPLETATA);
	}
	
	/**
	 *controllo corrispondenza del codice fiscale di una persona con  quelli presenti nell'ArrayList codici_fiscali
	 */
	public static void verificaPresenzaCodiceFiscaleInFile() {
		System.out.println(VERIFICA_PRESENZA_CODICE_FISCALE);
		boolean presente;
		for(CodiceFiscale cod_fisc: codici_fiscali) {
			if(cod_fisc.getIs_corretto()) {
				for(Persona pers: persone) {
					if(cod_fisc.getCodice().equals(pers.getCodice_fiscale())) { //confronto dei codici estratti dal file codici.xml e quelli generati per ogni persona 
							presente=true;
							cod_fisc.setIs_appaiato(presente);
							pers.setIs_presente(presente);
							numero_codici_appaiati++;//incremento contatore del numero di codici appaiati
					}
				}
			}else {
				numer_codici_non_corretti++; //incremento contatore dei codici errati
			}
		}
		System.out.println(VERIFICA_COMPLETATA);
	}
	
	/**
	 * Generazione di un file .xml di output
	 */
	public static void scritturaFileXML() {
		System.out.println(SCRITTURA_IN_CORSO);
		int num_spaiati=codici_fiscali.size()-numero_codici_appaiati-numer_codici_non_corretti;
		OutputXML.scriviXML(persone, codici_fiscali, num_spaiati, numer_codici_non_corretti);
		System.out.println(SCRITTURA_COMPLETATA);
	}
}
