package cdf;

import java.text.SimpleDateFormat;

public class CodiceFiscale {
	//indici di suddivisione del codice fiscali in 7 macrogruppi, ciascuno rappresentante una specifica informazione della persona
	private static final int INIZIO_BLOCCO7 = 15;
	private static final int INIZIO_BLOCCO6 = 11;
	private static final int INIZIO_BLOCCO5 = 9;
	private static final int INIZIO_BLOCCO4 = 8;
	private static final int INIZIO_BLOCCO3 = 6;
	private static final int INIZIO_BLOCCO2 = 3;
	private static final int INIZIO_BLOCCO1 = 0;
	
	private static final int DIVISORE = 26; //divisore per il calcolo del carattere di controllo
	private static final int ADD_GIORNO_NASCITA = 40;
	private static final String FEMMINA = "F";
	private static final String CARATTERE_AGGIUNTIVO = "X";//carattere aggiunto nel caso in cui le lettere del nome o cognome della persona
	                                                       //non siano sufficienti per la creazione del codice
	private static final int NUM_CARATTERI=3;
	private static final char [] VOCALI = {'A', 'E', 'I', 'O', 'U'};
	private static final char [] CONSONANTI = {'B', 'C', 'D', 'F', 'H', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'Z'};
		
	private String codice;
	private boolean is_consonante = true;
	private boolean is_vocale = false;
	private boolean is_corretto;
	private boolean is_appaiato=false;
	
	public CodiceFiscale() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return stringa corrispondente al codice fiscale
	 */
	public String getCodice() {
		return codice;
	}
	
	/**
	 * settaggio dell'attributo corrispondente al codice fiscale
	 * @param codice
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	/**
	 * @return true se il codice fiscale e' corretto, false altrimenti
	 */
	public boolean getIs_corretto() {
		return is_corretto;
	}

	/**
	 * set true se il codice fiscale e' corretto, false altrimenti
	 * @param is_corretto
	 */
	public void setIs_corretto(boolean is_corretto) {
		this.is_corretto = is_corretto;
	}

	/**
	 * @return true se il codice fiscale appartiene ad una persona, false altrimenti
	 */
	public boolean getIs_appaiato() {
		return is_appaiato;
	}
	
	/**
	 * set true se il codice fiscale appartiene ad una persona, false altrimenti
	 * @param is_appaiato
	 */
	public void setIs_appaiato(boolean is_appaiato) {
		this.is_appaiato = is_appaiato;
	}

	/**
	 * data una variabile di tipo Persona come argomento, vengono estrapolate le informazioni necessarie 
	 * per la creazione del codice fiscale
	 * @param cognom_nom
	 * @return il codice fiscale della persona fornita come argomento
	 */
	public static String generazioneCodiceFiscale(Persona persona) {
		String codice_fiscale = "";
		Boolean is_nome;//true se indica il nome, false se indica il cognome
		SimpleDateFormat format_anno= new SimpleDateFormat("yyyy");
		SimpleDateFormat format_mese= new SimpleDateFormat("MM");
		SimpleDateFormat format_giorno= new SimpleDateFormat("dd");
		
		String caratteri_cognome = "";
		String caratteri_nome = "";
		String caratteri_anno = "";
		String carattere_mese = "";
		String caratteri_giorno = "";
		String caratteri_comune = "";
		String carettere_controllo = "";
		
		is_nome=false;
		caratteri_cognome = calcolaStringa(persona.getCognome(), is_nome);//salvo caratteri cognome
		
		is_nome=true;
		caratteri_nome = calcolaStringa(persona.getNome(), is_nome);//salvo caratteri nome
		
		caratteri_anno=format_anno.format(persona.getData_nascita());
		caratteri_anno=caratteri_anno.substring(2, 4);//salvo caratteri anno
		
		carattere_mese= calcolaCarattereMese(Integer.parseInt(format_mese.format(persona.getData_nascita()) ));	//salvo carattere del mese
		
		caratteri_giorno= celcolaCaratteriGiorno(Integer.parseInt(format_giorno.format(persona.getData_nascita())), persona.getSesso());//salvo caratteri del giorno di nascita
		
		caratteri_comune= persona.getComune_nascita().getCodice();//salvo caratteri del comune di nascita
		
		codice_fiscale = caratteri_cognome+caratteri_nome+caratteri_anno+carattere_mese+caratteri_giorno+caratteri_comune;//salvo stringa codice fiscale senza il carattere di controllo
		carettere_controllo=calcolaCarattereControllo(codice_fiscale);//salvo il carattere di controllo	
		
		codice_fiscale = caratteri_cognome+caratteri_nome+caratteri_anno+carattere_mese+caratteri_giorno+caratteri_comune+carettere_controllo;//salvo stringa codice fiscale con carattere di controllo
		return codice_fiscale;
	}
	
	/**
	 * data una stringa come argomento viene determinato la sezione di codice fiscale a seconda del fatto 
	 * che sia un nome o cognome (is_nome)
	 * @param stringa
	 * @param is_nome
	 * @return codice del nome/cognome
	 */
	public static String calcolaStringa(String stringa, Boolean is_nome) {
		String stringa_calcolata="";
		stringa = stringa.replaceAll(" ", "");		// Rimuovo eventuali spazi
	    stringa = stringa.toUpperCase();			// Rendo tutte le lettere maiuscole
	    
	    String consonanti_stringa = getConsonanti(stringa); //salvo le consonanti contenute nella stringa che passo come argomento
	    String vocali_stringa = getVocali(stringa); //salvo le vocali contenute nella stringa che passo come argomento
		
	    if(consonanti_stringa.length()==NUM_CARATTERI) {	    	
	    	stringa_calcolata = consonanti_stringa;	  //se il numero delle consonanti coincide con 3, il codice del nome/cognome sarà dato da quelle consonanti  	
	    }else if(consonanti_stringa.length()>NUM_CARATTERI){
	    	if(is_nome) {//se le consonanti sono maggiori di 3 e la stringa passata come argomento si riferisce ad un nome allora estraggo le consonanti in specifiche posizioni
	    		stringa_calcolata = ""+consonanti_stringa.charAt(0)+consonanti_stringa.charAt(2)+consonanti_stringa.charAt(3);
	    	}else {//se le consonanti sono maggiori di 3 e la stringa passata si riferisce ad un cognome allora considero le prime tre consonanti
	    		stringa_calcolata = consonanti_stringa.substring(0,3);
	    	}
	    }else if(consonanti_stringa.length()<NUM_CARATTERI && stringa.length()<NUM_CARATTERI) {
	    	/*se le consonanti non sono sufficienti per la creazione del codice e la stringa considerata non possiede altri caratteri,
	    	 *allora inserisco le vocali della stessa ed infine tanti caratteri 'X' quanti ne servono per il completamento del codice (del nome/cognome) 
	    	 */
	    	
	    	stringa_calcolata = consonanti_stringa;
	    	stringa_calcolata = stringa_calcolata+vocali_stringa; 
	    	while(stringa_calcolata.length()<3){
	    		stringa_calcolata=stringa_calcolata+CARATTERE_AGGIUNTIVO;
	    	}
	    }else if(consonanti_stringa.length()<NUM_CARATTERI && stringa.length()>NUM_CARATTERI){
	    	/*se le consonanti non sono sufficienti per la creazione del codic, ma la stringa considerata
	    	 * possiede anche delle vocali, le prelevo fino al completamento del codice
	    	 */
	    	stringa_calcolata = consonanti_stringa;
	    	for(int i=0; stringa_calcolata.length()<3; i++){
	    		stringa_calcolata=stringa_calcolata+vocali_stringa.charAt(i);
	    	}
	    }
	    
		return stringa_calcolata;
	}
	
	/**
	 * data la stringa passata come argomento, ritorna il contenuto della stessa privata delle sue vocali
	 * @param stringa
	 * @return stringa di sole consonanti
	 */
	public static String getConsonanti(String stringa) {
		stringa = stringa.toUpperCase();
		stringa=stringa.replaceAll("[AEIOU]","");
	    return stringa;
	}
	
	/**
	 * data la stringa passata come argomento, ritorna il contenuto della stessa privata delle sue consonanti
	 * @param stringa
	 * @return stringa di sole vocali
	 */
	public static String getVocali(String stringa) {
		stringa = stringa.toUpperCase();
		stringa=stringa.replaceAll("[^ AEIOU]","");
	    return stringa;
	}
	
	/**
	 * dato un intero passato come argomento, rappresentante il mese, ritorna il carattere relativo ad esso,
	 * rispettando le regole per la creazione del codice fiscale
	 * @param mese
	 * @return carattere del mese
	 */
	public static String calcolaCarattereMese(int mese) {
		String carattere="";
		mese=mese-1;	// l'idice del Enum parte da 0;
		carattere=Mese.getById(mese).getValore();
		return carattere;
	}
	
	/**
	 * dato un intero e una stringa passato come argomento, rappresentanti rispettivamneto il giorno e il sesso,  ritorna una stringa
	 * rappresentante il giorno di nascita, rispettando le regole per la creazione del codice fiscale in base ai due generi
	 * @param giorno
	 * @param sesso
	 * @return caratteri riferiti al giorno di nascita
	 */
	public static String celcolaCaratteriGiorno(int giorno, String sesso) {
		String caratteri="";
		sesso=sesso.toUpperCase();
		if(sesso.substring(0, 1).equals(FEMMINA)) {
			giorno=giorno+ADD_GIORNO_NASCITA;//aggiungo 40 al giorno di nascita se si tratta di una Femmina
		}
		caratteri=String.format("%02d",giorno);
		return caratteri;
	}
	
	/**
	 * data una stringa, rappresentante i primi 15 caratteri del codice fiscale, ritorna il carattere di controllo
	 * rispettando le regole per la creazione del codice fiscale
	 * @param codice_fisc
	 * @return carattere di controllo
	 */
	public static String calcolaCarattereControllo(String codice_fisc) {
		String carattere="";
		
		int somma_caratteri_pari=0;
		int somma_caratteri_dispari=0;
		int resto;
		
		for(int i=0; i<codice_fisc.length(); i++){
			if((i+1)%2 == 0) {
				somma_caratteri_pari=somma_caratteri_pari + CaratteriPari.getValoreDaNome(Character.toString(codice_fisc.charAt(i)));//somma caratteri in posizione pari
			}else {
				somma_caratteri_dispari =somma_caratteri_dispari +CaratteriDispari.getValoreDaNome(Character.toString(codice_fisc.charAt(i)));//somma caratteri in posizione dispari
			}
		}
		resto=(somma_caratteri_pari+somma_caratteri_dispari)%DIVISORE;
		
		carattere=RestoCaratteri.getNomeDaValore(resto).name().substring(1);
		return carattere;
	}
	
	/**
	 * data una stringa passata come argomento, verifica se i caratteri rispettano le regole poste per
	 * la creazione della sezione del codice fiscale del cognome e del nome 
	 * @param cognom_nom
	 * @return true se i caratteri sono corretti, false altrimenti
	 */
	public boolean verificaNomeCognome( String cognom_nom) {
		Character carattere;
		for(int i=0; i<cognom_nom.length(); i++) {
			carattere = cognom_nom.charAt(i);
	        for(int j = 0; j < CONSONANTI.length && is_consonante;j++) { //verifico che il carattere sia una consonante
	        	if(j==(CONSONANTI.length-1) && !carattere.equals(CONSONANTI[i])) {
	        		is_consonante = false;
	    	        is_vocale = true;
	        		break;
	        	}
	        }
	        for(int j = 0; j < VOCALI.length && !is_vocale; j++) {//verifico che il carattere sia una vocale
	        	if(j==(VOCALI.length-1) && !carattere.equals(VOCALI[j])) {
	        		is_vocale = false;
	        		break;
	        	}
	        }
	        if(!is_consonante && !is_vocale && !carattere.equals(CARATTERE_AGGIUNTIVO)) return false; //verifico che il carattere coincida con il carattere aggiuntivo 'X'
	    }
	    
		
		return true;
	}
	
	/**
	 * data una stringa passata come argomento, verifica se contiene solo caratteri numerici
	 * @param year
	 * @return true se i caratteri sono corretti, false altrimenti
	 */
	public boolean verificaAnno(String year) {
		for( int indice = 0; indice <year.length(); indice++) {
			if(year.charAt(indice)< '0' || year.charAt(indice) > '9' ) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * dati come argomenti le stringhe rappresentanti il numero del mese e il numero del giorno viene 
	 * verificato che il giorno appartenga la mese
	 * @param carattere_mese
	 * @param giorno
	 * @return true se il giorno appartiene al mese, false altrimenti 
	 */
	public boolean verificaMeseGiorno(String carattere_mese, String giorno) {
		
		if(Mese.isPresente(carattere_mese)){
			if(Integer.parseInt(giorno)<= Mese.getNomeDaValore(carattere_mese).getGiorni() && Integer.parseInt(giorno)>0)return true; //verifica appartenenza del giorno nel caso in cui si tratta di un uomo
			if(Integer.parseInt(giorno)<= Mese.getNomeDaValore(carattere_mese).getGiorni()+ADD_GIORNO_NASCITA && Integer.parseInt(giorno)>ADD_GIORNO_NASCITA)return true; //verifica appartenenza del giorno nel caso in cui si tratta di un donna
		}
		return false;
	}
		
	/**
	 * data una stringa passata come argomento, viene verificato che il primo carattere sia una lettera 
	 * e che i restanti siano dei caratteri numerici
	 * @param code
	 * @return true se il primo carattere è una lettera e i restanti sono caratteri numerici, false altrimenti
	 */
	public boolean verificaCodiceComune(String code) {
		int i;
		if(code.charAt(0)<'A' || code.charAt(0)>'Z') {
			return false;
		}
		else {
			for(i=1; i<code.length(); i++) {
				if(code.charAt(i)<'0' || code.charAt(i)>'9') {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * date due stringhe passate come argomenti, che rappresentano rispettivamente i primi 15 caratteri di un codice fiscale ed 
	 * un carattere di controllo, viene verificato che il carattere di controllo risultante dal codice fiscale 
	 * fornito coincida con quello passato come argomento 
	 * @param codice_fisc
	 * @param carattere
	 * @return true se i caratteri di controllo coincidono, false altrimenti
	 */
	public boolean verificaCarattereControllo(String codice_fisc, String carattere) {
		String carattere_calcolato;
		carattere_calcolato=calcolaCarattereControllo(codice_fisc);
		
		if(carattere_calcolato.equalsIgnoreCase(carattere)) {
			return true;
		}
		return false;
	}


	/**
	 *data una stringa che rappresenta un codice fiscale passata come argomento, viene verificata la sua validità
	 * @param codice_fiscale
	 * @return true se il codice fiscale è corretto, false altrimenti
	 */
	public boolean verificaCodiceFiscale(String codice_fiscale) {
		String cognome=codice_fiscale.substring(INIZIO_BLOCCO1, INIZIO_BLOCCO2);//blocco caratteri cognome
		String nome=codice_fiscale.substring(INIZIO_BLOCCO2, INIZIO_BLOCCO3);// blocco caratteri nome
		String anno=codice_fiscale.substring(INIZIO_BLOCCO3, INIZIO_BLOCCO4);// blocco caratteri anno di nascita
		String mese=codice_fiscale.substring(INIZIO_BLOCCO4, INIZIO_BLOCCO5);//blocco caratteri mese di nascita
		String giorno=codice_fiscale.substring(INIZIO_BLOCCO5, INIZIO_BLOCCO6);//blocco caratteri giorno di nascita
		String comune=codice_fiscale.substring(INIZIO_BLOCCO6, INIZIO_BLOCCO7);//blocco caratteri luogo di nascita
		String cf_senza_carattere_controllo=codice_fiscale.substring(INIZIO_BLOCCO1, INIZIO_BLOCCO7);//blocco comprendente tutti i primi 15 caratteri
		String carattere_controllo=codice_fiscale.substring(INIZIO_BLOCCO7);// blocco carattere di controllo
		
		if(!verificaNomeCognome(cognome)) return false;//verifico validità blocco caratteri cognome
		if(!verificaNomeCognome(nome)) return false;//verifico validità blocco caratteri nome
		if(!verificaAnno(anno)) return false;//verifico validità blocco caratteri anno di nascita
		if(!verificaMeseGiorno(mese, giorno)) return false;//verifico validità blocco caratteri mese di nascita
		if(!verificaCodiceComune(comune)) return false;//verifico validità blocco caratteri giorno di nascita
		if(!verificaCarattereControllo(cf_senza_carattere_controllo,carattere_controllo))return false;//verifico validità blocco carattere di controllo
		return true;
	}

}