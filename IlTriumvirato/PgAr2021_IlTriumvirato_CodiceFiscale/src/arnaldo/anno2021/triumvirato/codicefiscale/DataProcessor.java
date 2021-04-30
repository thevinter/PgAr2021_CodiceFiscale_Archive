package arnaldo.anno2021.triumvirato.codicefiscale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Questa classe serve per esonerare il main dalle procedure di elaborazione dei dati presi in input.<br>
 * I dati presi in input vengono salvati qui nella forma di tre array(datiPersone, datiCodiciFiscali, datiComuni).<br>
 * Questi dati vengono elaborati da questa classe(facendo appello ai metodi di Persona, la classe Maps e le costanti) per costruire i dati da dare in output.<br>
 * I dati da dare in output sono: i dati delle persone con l'aggiunta dei codici fiscali e la listaErrati, che è la lista di codici anomali, ossia invalidi e spaiati.<br>
 */
public class DataProcessor {
	private static boolean nessunErrore=true;
	private static boolean canExecuteProgram=true;
	private static ArrayList<Persona> datiPersone;
	private static ArrayList<String>  datiCodiciFiscali;
	private static ArrayList<Comune>  datiComuni;
	private static CodiciProblematici listaErrati;
	
	/** Viene invocato dopo che sono stati presi in input i dati e ne avvia l'elaborazione, generando i codici fiscali per tutte le persone prese in input e andando a verificare la presenza e la validità dei codici fiscali presi in input.<br>
	 *  I risultati
	*/
	public static void elaboraDati() {
		
		if(canExecuteProgram) {
			
			Maps.inizializzaMappe(datiComuni); //l'unica mappa che ha bisogno dei dati presi in input è quella dei comuni
			
			for(Persona individuo:datiPersone) {
				individuo.generaCodiceFiscale();
			}
			
			// contiene i codici fiscali delle persone non presenti in datiPersone, vengono filtrati dall'istruzione che segue
			ArrayList<String> invalidiESpaiati=DataProcessor.controllaPresenze(datiPersone, datiCodiciFiscali);
			
			//controlla quali codici sono errati
			listaErrati=DataProcessor.selezioneAnomalie(invalidiESpaiati);
				
		}
		
	}
	
	public static void errorOccourrence() {
		nessunErrore=false;
	}
	
	/**
	 * 
	 * @return ritorna true se il programma non ha presentato nessun errore di esecuzione, false altrimenti
	 */
	public static boolean isErrorLess() {
		return nessunErrore;
	}
	
	public static void setUnexecutable() {
		canExecuteProgram=false;
		System.out.println(Constants.ERROR_MESSAGE_EXECUTION_STOPPED);
	}
	
	public static boolean isExecutable() {
		return canExecuteProgram;
	}
	
	public static void setDatiPersone(ArrayList<Persona> datiPersone) {
		DataProcessor.datiPersone = datiPersone;
	}

	public static void setDatiCodiciFiscali(ArrayList<String> datiCodiciFiscali) {
		DataProcessor.datiCodiciFiscali = datiCodiciFiscali;
	}

	public static void setDatiComuni(ArrayList<Comune> datiComuni) {
		DataProcessor.datiComuni = datiComuni;
	}

	/** Questo metodo salva i valori presi in input in modo da consentire al programma di produrre il risultato richiesto a partire da essi
	  * @param datiPersone è la lista di persone prese in input
	  * @param datiCodiciFiscali è la lista di codici fiscali presi in input
	  * @param datiComuni è la lista di comuni presi in input
	  * 
	 */
	public static void setInitialData(ArrayList<Persona> datiPersone,ArrayList<String> datiCodiciFiscali,ArrayList<Comune> datiComuni) {
		setDatiPersone(datiPersone);
		setDatiCodiciFiscali(datiCodiciFiscali);
		setDatiComuni(datiComuni);
	}
	
	/**
	 * @return ritorna la lista delle persone prese precedentemente in input, coi relativi dati
	 */
	public static ArrayList<Persona> getListaPersone(){
		return datiPersone;
	}
	/**	
	 * @return ritorna la lista dei codici che risultano spaiati o invalidi, suddivisi nelle due categorie per mezzo della classe CodiciProblematici
	 */
	public static CodiciProblematici getListaErrati() {
		return listaErrati;
	}

	
	
	//metodi di appoggio per il controllo di caratteri e stringhe e metodo di appoggio per ottenere il codice di un comune
	
	/** Ritorna true se la lettera analizzata è una vocale(funziona solo con gli uppercase perché viene usato su stringhe per le quali è già verificato che siano completamente in uppercase
	 * @param letter è la lettera da analizzare
	 */
	public static boolean isVowel(char letter) {
		return letter=='A'||letter=='E'||letter=='I'||letter=='O'||letter=='U';
	}
	
	/**
	 * Controllo per verificare che il carattere x dato si trovi nel range compreso tra base e ceil
	 * @param x
	 * @param base
	 * @param ceil
	 * @return
	 */
	public static boolean controlloRange(char x, char base, char ceil) {
		boolean prova;
		if(base<=x&&x<=ceil) {
			prova = true;
		} else {
			prova = false;
		}
		
		return prova;
	}
	
	/**
	 * Ritorna la stringa, ma le vengono rimosse le vocali
	 * @param s è la stringa dalla quale vengono rimosse le vocali
	 * @return ritorna la stringa dopo l'eliminazione delle vocali
	 */
	public static String getVowellessString(String s) {
		StringBuffer sb=new StringBuffer("");
		for(int i=0;i<s.length();i++) {
			if(!isVowel(s.charAt(i))) {
				sb.append(s.charAt(i));
			}
		}
		return new String(sb);
	}
	
	/**
	 * Ritorna la stringa, ma le vengono rimosse le cifre numeriche
	 * @param s è la stringa dalla quale vengono rimosse le cifre numeriche
	 * @return ritorna la stringa dopo l'eliminazione delle cifre numeriche
	 */
	public static String getDigitlessString(String s) {
		StringBuffer sb=new StringBuffer("");
		for(int i=0;i<s.length();i++) {
			if(!controlloRange(s.charAt(i), '0', '9')) {
				sb.append(s.charAt(i));
			}
		}
		return new String(sb);
	}
	
	
	/**
	 * Metodo per il controllo della presenza dei codici fiscali delle persone tra quelli ottenuti dal file dei codici fiscali
	 * @param datiPersone è la lista di persone, per ciascuna delle quali è stato già generato il codice fiscale(che è da trovare nella lista dei codici fiscali)
	 * @param datiCodiciFiscali è la lista dei codici fiscali
	 * @return ritorna la lista di tutti i codici fiscali che non sono stati trovati nella lista dei codici fiscali delle persone
	 */
	public static ArrayList<String> controllaPresenze(ArrayList<Persona> datiPersone, ArrayList<String> datiCodiciFiscali) {
		
		for(int i=0; i<datiPersone.size(); i++) {
			datiPersone.get(i).setCodiceAssente(true); //conviene mettere direttamente nel costruttuore Cod.ass.=true
		}
		
		for(int i=0; i<datiPersone.size(); i++) {
			for(int j=0; j<datiCodiciFiscali.size(); j++) {
				if(datiPersone.get(i).getCodiceFiscale().equals(datiCodiciFiscali.get(j))){
					datiPersone.get(i).setCodiceAssente(false);
					datiCodiciFiscali.remove(j);
					j--;
				}
			}
		}
		
		//spaiati invalidi
		return datiCodiciFiscali;
	}
	
	/**
	 * Metodo che individua i codici non validi tra tutti quelli che non sono stati trovati tra i codici fiscali delle persone e li separa in due array, precisamente nel formato offerto dalla classe CodiciProblematici
	 * @param codiciAnomali è la lista con i codici fiscali in cui si vogliono separare quelli invalidi da quelli spaiati
	 * @return 
	 */
	public static CodiciProblematici selezioneAnomalie(ArrayList<String> codiciAnomali) {
		ArrayList<String> invalidi=new ArrayList<String>();
		ArrayList<String> spaiati=new ArrayList<String>();
		
		for(String codice:codiciAnomali) {
			
			if(controllaCodice(codice)) {
				spaiati.add(new String(codice));
			}else {
				invalidi.add(new String(codice));
			}
			
		}
		
		return new CodiciProblematici(invalidi,spaiati);
	}
	

	/**
	 * Metodo che ritorna true se il codice fiscale fornitogli è un codice possibile/corretto, ritorna false altrimenti
	 * @param codice
	 * @return
	 */
	public static boolean controllaCodice(String codice) {
		
			boolean r=true;
			
			//la notazione r=r&&variabile_booleana indica che r rimane true solamente se lo è anche variabile_booleana, se invece r era già false, rimarrà false a prescindere
			r=codice.length()==Constants.CF_SIZE;
			
			r=r&&controlloTipologiaCaratteri(codice);
			
			if(!r)return false; //una piccola ottimizzazione siccome i primi due controlli generalmente sono quelli che mandano via più caratteri
			
			
			//controllo sul giorno di nascita
			r=r&&controlloGiornoNascita(codice);
							
			//verifica del carattere di controllo
			r=r&&(codice.charAt(Constants.CF_SIZE-1)==calcolaCarattereControllo(codice.substring(0,Constants.CF_SIZE-1)));
			
			//controllo sulla correttezza del carattere mese
			r=r&&controlloMese(codice);
					
			//controllo della correttezza dei caratteri cognome
			r=r&&controlloCorrettezzaNome(codice);
			
			//controllo della correttezza dei caratteri nome			
			r=r&&controlloCorrettezzaCognome(codice);
			
         
			
			return r;
	}
	
	/**
	 * Metodo di appoggio che controlla che la tipologia dei caratteri sia opportuna per ogni carattere del codice fiscale(fa un ulteriore controllo sulla dimensione del giorno e sul carattere del mese per ridurre il numero di operazioni, ma il controllo completo per quei dati è svolto mediante un altro metodo
	 * @param codice fiscale analizzato
	 * @return
	 */
	private static boolean controlloTipologiaCaratteri(String codice) {
		boolean ritorno=true;
		
		for(int i=0;i<Constants.CF_SIZE;i++) {
			
			ritorno=ritorno&&controlloRange(codice.charAt(i), Constants.ESTREMI_INFERIORI_CARATTERI_CF[i],Constants.ESTREMI_SUPERIORI_CARATTERI_CF[i]);  
			
			if(!ritorno)break;//so che sarebbe più efficiente metterlo nella condizione del for, ma lo metto qui per visibilità
		}
		
		return ritorno;
	}

	/**
	 * Metodo di appoggio che controlla che il mese di nascita all'interno del codice fiscale sia corretto e che il numero di giorni rientri nell'intervallo pre-stabilito
	 * @param codice fiscale analizzato
	 * @return
	 */
	private static boolean controlloMese(String codice) {
		boolean ritorno=false;
		
		char carattereMese=codice.charAt(Constants.POSIZIONE_CARATTERE_MESE);
		
		//controlla che il carattere del mese sia un effettivo carattere previsto
		for(int i=0;i<12;i++) {
			if(carattereMese==Constants.MONTH_CHARS[i]) {
				ritorno=true;
				break;
			}
		}
		
		
		if(!ritorno) {//se il carattere non rientra tra i caratteri previsti, ritorna false
			return false;
			
		}else {//se effettivamente esiste, deve passare però il controllo sul numero di giorni in quel mese. Si rifiuta il 29/2
			
			Map<Character,Integer> mappaGiorniMesi=Maps.getMappaGiorniMesi();
			
			int numeroGiorno=Integer.parseInt(codice.substring(Constants.POSIZIONE_CARATTERE_MESE+1,Constants.POSIZIONE_CARATTERE_MESE+3));
			
			//prima di controllare che sia nel range mensile, normalizza il giorno del mese (in caso il codice sia di un individuo di sesso femminile)
			if(numeroGiorno>=31&&numeroGiorno<=71) {
				numeroGiorno-=40;
			}
			
			if(numeroGiorno>mappaGiorniMesi.get(carattereMese)) {
				ritorno=false;
			}
			
			return ritorno;
			
		}
		
		
	}

	/**
	 * Metodo di appoggio che controlla che il giorno di nascita all'interno del codice fiscale sia corretto
	 * @param codice fiscale analizzato
	 * @return
	 */
	private static boolean controlloGiornoNascita(String codice) {
		boolean ritorno=true;
		int giornoNascita=Integer.parseInt(codice.substring(9,11));
		if(giornoNascita>31&&giornoNascita<1+Constants.GENDER_NUMBER_TO_ADD || giornoNascita>31+Constants.GENDER_NUMBER_TO_ADD) {
			ritorno=false;
		}
		
		return ritorno;
	}
	
	/**
	 * Metodo di appoggio che controlla che il nome all'interno del codice fiscale sia corretto 
	 * @param codice fiscale analizzato
	 * @return
	 */
	private static boolean controlloCorrettezzaNome(String codice) {
		boolean ritorno=true;
		boolean vocaleTrovata=false;
		boolean fillerCharTrovato=false;
		for(int i=0;i<3;i++) {
			if(isVowel(codice.charAt(i))){
				if(fillerCharTrovato) {
					ritorno=false;
					break;
				}else{
					vocaleTrovata=true;
				}

			}else{
				if(vocaleTrovata){
					if(codice.charAt(i)!=Constants.FILLER_CHAR) {
						ritorno=false;
						break;
					}else {
						fillerCharTrovato=true;
					}
				}
			}
		}
		
		return ritorno;
	}

	/**
	 * Metodo di appoggio che controlla che il cognome all'interno del codice fiscale sia corretto 
	 * @param codice fiscale analizzato
	 * @return
	 */
	
	
	private static boolean controlloCorrettezzaCognome(String codice) {
		boolean ritorno=true;
		boolean vocaleTrovata=false;
		boolean fillerCharTrovato=false;
		for(int i=3;i<6;i++) {
			if(isVowel(codice.charAt(i))){
				if(fillerCharTrovato) {
					ritorno=false;
					break;
				}else{
					vocaleTrovata=true;
				}

			}else{
				if(vocaleTrovata){
					if(codice.charAt(i)!=Constants.FILLER_CHAR) {
						ritorno=false;
						break;
					}else {
						fillerCharTrovato=true;
					}
				}
			}
		}
		
		return ritorno;
	}
	
	/**
	 * metodo che calcola il carattere di controllo(carattere finale) di un codice fiscale a partire dalle sue prime 15=Constants.CF_SIZE-1 lettere
	 * @param cfIncompleto è la stringa di 15 lettere che rappre
	 * @return carattere di controllo calcolato a partire dalle prime 15 lettere di un codice fiscale
	 */
	public static char calcolaCarattereControllo(String cfIncompleto) {
		if(cfIncompleto.length()==Constants.CF_SIZE-1) {
			int somma=0;
			for(int i=1;i<15;i+=2) {
				
				somma+=Maps.getMappaPari().get(cfIncompleto.charAt(i));
			}
			
			for(int i=0;i<Constants.CF_SIZE-1;i+=2) {
				somma+=Maps.getMappaDispari().get(cfIncompleto.charAt(i));
			}
			

			char codiceControllo=(char)('A'+somma%Constants.DIVISORE_CARATTERE_CONTROLLO);
			return codiceControllo;
			
		}else {
			return ' ';//errore
		}
		
	}
	


}
	
	
	
