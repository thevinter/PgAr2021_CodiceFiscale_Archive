package it.unibs.arnaldo.cf;

import it.unibs.arnaldo.cf.Persona.Sesso;


/**
 * Genera il codice fiscale e ne controlla la validità
 * @author toBdefined
 *
 */
public class CodiceFiscale {
	private static final String MESI = "ABCDEHLMPRST";
	private static final String VOCALI = "AEIOU";
	private String cf;
	
	/**
	* Metodo costruttore della classe CodiceFiscale,
	* prende come parametri i dati della persona e genera il codice fiscale
	*
	* @param  cognome  Rossi
	* @param  nome  Mario
	* @param  sesso  Sesso.M
	* @param  nascita  1990-05-21
	* @param  comune  Brescia
	*/
	public CodiceFiscale(String cognome, String nome, Sesso sesso, String nascita, String comune) {
		String parts[] = new String[8];
		
		//Genero le parti di nome e cognome
		parts[0] = generate3Chars(cognome);
		parts[1] = generate3Chars(nome);
		
		
		//Nascita, genero le varie parti		
		//ANNO (scarto le prime 2 cifre)
		parts[2] = String.format("%2s", nascita.substring(2,4));
		//MESE 
		parts[3] = "" + getMonth(Integer.parseInt(nascita.substring(5,7)));
		//GIORNO
		int giorno = Integer.parseInt(nascita.substring(8,10));
		//Se è donna, aggiungo 40
		if(sesso == Sesso.F)
			giorno += 40;
		parts[4] = String.format("%02d", giorno);
		
		
		//Comune
		parts[5] = ElencoComuni.getCodice(comune);
		
		//Codice di controllo
		parts[6] = "" + getControllo(new String(parts[0] + parts[1] + parts[2] + parts[3] + parts[4] + parts[5]));
		
		//CodiceFiscale, unisco tutte le parti per avere il codice intero
		cf = new String(parts[0] + parts[1] + parts[2] + parts[3] + parts[4] + parts[5] + parts[6]);
	}
	
	/*METODI DI UTILITA'*/
	
	/**
	 * Ritorna true se il carattere passato è una vocale
	 * @param c 'A'
	 * @return true or false
	 */	
	public static boolean isVowel(char c) {
		String vowels = VOCALI;
		return vowels.indexOf(c) != -1;
	}
	
	
	/**
	 * Ritorna il carattere corrispondente al numero del mese passato
	 * @param mese 11
	 * @return carattere associato al mese 
	 */
	private static char getMonth(int mese) {
		char mesi[] = {'A','B','C','D','E','H','L','M','P','R','S','T'};
		return mesi[mese-1];
	}
	
	
	/**
	 * Ritorna true se il carattere passato rappresenta un mese
	 * @param c 'D'
	 * @return la posizione del mese nell'ArrayList 
	 */
	private static int getMonthPosition(char c) {
		String mesi = MESI;
		return mesi.indexOf(c);
	}
	
	
	/*METODI PER CREARE E CONTROLLARE IL CF*/
	/**
	 * Metodo per generare nome e cognome nel codice fiscale 
	 * @param part stringa contenente il nome o il cognome
	 * @return stringa contenente i caratteri che nel codice rappresetano il nome e il cognome
	 */	
	private static String generate3Chars(String part) {
		char src[] = part.toCharArray();
		String chars = "";
		
		//Prima aggiungo le consonanti
		for(int i=0; i<src.length && chars.length() < 3; i++)
			if(!isVowel(src[i]))
				chars += src[i];
		
		//Se le consonanti non sono abbastanza aggiungo le vocali
		if(chars.length() < 3)
			for(int i=0; i<src.length && chars.length() < 3; i++)
				if(isVowel(src[i]))
					chars += src[i];
		
		//Se le lettere non bastano aggiungo X
		for(int i=0; i<src.length && chars.length() < 3; i++)
			chars += 'X';
		
		return chars;
	}
	
	/**
	 * Ricerca del carattere di controllo seguendo l'algoritmo ufficiale (fonte: wiki)
	 * @param partialCF stringa contenente la parte precedente al carattere di controllo del codice fiscale
	 * @return il carattere finale di controllo
	 */
	private static char getControllo(String partialCF) {
		//Caratteri di controllo per le posizioni dispari (vedi tabella), le pari corrispondono al numero/lettera
		int dispari[] = {1,0,5,7,9,13,15,17,19,21,1,0,5,7,9,13,15,17,19,21,2,4,18,20,11,3,6,8,12,14,16,10,22,25,24,23};
		int sumDispari = 0;
		int sumPari = 0;
		
		char chars[] = partialCF.toCharArray();
		
		for(int i=0; i<chars.length; i++) {
			//Caso pari
			if((i+1)%2 == 0) {
				//Il valore da aggiungere corrisponde al valore del numero/lettera
				sumPari += (Character.isDigit(chars[i])) ? Character.getNumericValue(chars[i]) : (Character.toUpperCase(chars[i]) - 'A');
			}
			//Caso dispari
			else {
				//il valore da aggiungere corrisponde al valore nella x-esima posizione di dispari[] (da tabella)
				//la posizione x corrisponde al numero stesso, oppure al "valore" della cifra + 10 [0,...,9,A,...,Z]
				int pos = (Character.isDigit(chars[i])) ? Character.getNumericValue(chars[i]) : 10 + (Character.toUpperCase(chars[i]) - 'A');
				sumDispari += dispari[pos];
			}
		}
		int ris = (sumPari+sumDispari)%26;
		
		return (char) ('A' + ris);
	}
	
	
	/**
	 * Controllo del codice fiscale suddiviso ogni "parte" (nome, congome, data di nascita...)
	 * Ritorna true se il codice controllato è valido
	 * @param cf stringa con il codice fiscale
	 * @return true se è tutto corretto altrimenti false se una "parte" è errata
	 */
	public static boolean validateCF(String cf) {
		//Rimuovo i caratteri inutili e prendo la stringa in maiuscolo
		cf = cf.trim().toUpperCase();
		
		//Se la lunghezza è sbagliata ritorno false, non serve fare altri controlli
		if(cf.length() != 16)
			return false;
		
		//Creo un array di caratteri dalla nostra stringa
		char caratteri[] = cf.toCharArray();
		
		
		/*****INIZIO DEI CONTROLLI*****/
		
		/*****COGNOME E NOME*****/
		//Controllo che i primi 6 caratteri siano compresi tra A e Z
		for(int i=0; i<6; i++)
			if(caratteri[i] < 'A' || caratteri[i] > 'Z')
				return false;
		//Controllo la validità dei nomi e dei cognomi
		String cognome = cf.substring(0,3);
		String nome = cf.substring(3,6);
		//Se il nome o il cognome non passa la verifica ritorno false
		if(!checkNominativo(cognome) || !checkNominativo(nome))
			return false;
		/*****COGNOME E NOME*****/
		
		
		/*****ANNO*****/
		//Controllo i 2 numeri dell'anno
		for(int i=6; i<8; i++)
			if(!Character.isDigit(caratteri[i]))
				return false;
		/*****ANNO*****/
		
		
		/*****MESE*****/
		//Controllo del mese
		int posizioneMese = getMonthPosition(caratteri[8]);
		if(posizioneMese == -1)
			return false;
		//Prendo la durata del mese per utilizzarla in seguito
		int durate[] = {31,28,31,30,31,30,31,31,30,31,30,31};
		int durata = durate[posizioneMese];
		/*****MESE*****/
		
		
		/*****GIORNO*****/
		//Controllo i 2 numeri del giorno
		for(int i=9; i<11; i++)
			if(!Character.isDigit(caratteri[i]))
				return false;		
		//Prendo il giorno e lo parso a intero
		int d = Integer.parseInt(cf.substring(9, 11));
		//Caso donna
		if(d >= 41 && d <= 71) {
			d -= 40;
		}
		//Verifico che il giorno sia valido
		if(d < 1 || d > durata)
			return false;
		/*****GIORNO*****/
		
		
		/*****COMUNE NASCITA*****/
		//Per evitare di fare la ricerca (costosa) inutilmente, prima verifichiamo se il formato è corretto
		if(caratteri[11]<'A' || caratteri[11]>'Z')
			return false;
		for(int i=12; i<15; i++)
			if(!Character.isDigit(caratteri[i]))
				return false;
		//Controllo il codice del comune di nascita
		if(!ElencoComuni.isValid(cf.substring(11,15)))
			return false;
		/*****COMUNE NASCITA*****/
		
		
		/*****CARATTERE DI CONTROLLO*****/
		//Controllo il carattere di controllo
		if(caratteri[15] != getControllo(cf.substring(0,15)))
			return false;
		/*****CARATTERE DI CONTROLLO*****/

		
		return true;
	}
	
	/**
	 * Verifica che il nome (o cognome) siano validi
	 * @param str nome/cognome
	 * @return true/false
	 */
	public static boolean checkNominativo(String str) {
		char caratteri[] = str.toCharArray();
		int i;
		//Scorro fino alla prima vocale
		for(i=0; i<caratteri.length && !isVowel(caratteri[i]); i++);
		for(; i<caratteri.length; i++) {
			if(!isVowel(caratteri[i]) && caratteri[i] != 'X')
				return false;
		};
		
		return true;
	}
	
	/**
	 * Metodo toString
	 * @return ritorna il codice fiscale
	 */
	public String toString() {
		return cf;
	}
	
}
