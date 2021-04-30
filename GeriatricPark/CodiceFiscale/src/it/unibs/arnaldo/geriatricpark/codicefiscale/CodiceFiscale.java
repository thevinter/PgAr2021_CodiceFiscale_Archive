package it.unibs.arnaldo.geriatricpark.codicefiscale;

import java.util.HashMap;

import it.unibs.fp.mylib.InputDati;

/**
 * Modellizza un codice fiscale, caratterizzato da una stringa di 16 caratteri.
 * @author Geriatric Park (Wiam Nasr Allah, Nicol Stocchetti, Mohamed Nassar).
 */
public class CodiceFiscale {

	private String valore;
	private Persona persona;

	/**
	 * Metodo costruttore del Codice Fiscale.
	 * @param codiceF il codice fiscale, String.
	 **/
	public CodiceFiscale(String codiceF) {
		this.valore = codiceF;
		this.persona = null;
	}

	/**
	 * Metodo costruttore del Codice Fiscale.
	 * @param persona della quale creare il codice fiscale, it.unibs.arnaldo.geriatricpark.codicefiscale.Persona.
	 **/
	public CodiceFiscale(Persona persona) {
		//calcolo codice fiscale dai dati della persona
		this.valore = generaCodiceFiscale(persona);
		this.persona = persona;
	}

	/**
	 * Genera il codice fiscale a partire da una persona.
	 * @param persona la persona, it.unibs.arnaldo.geriatricpark.codicefiscale.Persona.
	 * @return il codice fiscale, String.
	 */
	public String generaCodiceFiscale(Persona persona) {
		
		String cognome = persona.getCognome();
		String nome = persona.getNome();
		int anno = persona.getAnnoDiNascita();
		int mese = persona.getMeseDiNascita();
		int giorno = persona.getGiornoDiNascita();
		String comune = persona.getComuneDiNascita();
		char sesso = persona.getSesso();
		
		StringBuffer codiceGenerato = new StringBuffer();
		int contaCostanti = 0;
		
		//genera cognome 
		
	 		for(int i=0; i < cognome.length(); i++){
	 			if((cognome.charAt(i) != 'A' && cognome.charAt(i) != 'E' 
	 			   && cognome.charAt(i) != 'I' && cognome.charAt(i) != 'O' 
	 			   && cognome.charAt(i) != 'U' && cognome.charAt(i) != 'a' && cognome.charAt(i) != 'e' 
	 			   && cognome.charAt(i) != 'i' && cognome.charAt(i) != 'o' 
	 			   && cognome.charAt(i) != 'u') && codiceGenerato.length() < 3) {
	 				codiceGenerato.append(cognome.substring(i, i+1).toUpperCase());
	 			}
	 		}
	 		
	 		for(int i = 0; i < cognome.length(); i ++){
	 			if((cognome.charAt(i) == 'A' || cognome.charAt(i) == 'a' || 
	 				cognome.charAt(i) == 'E' || cognome.charAt(i) == 'e' || 
	 				cognome.charAt(i) == 'I' || cognome.charAt(i) == 'i' || 
	 				cognome.charAt(i) == 'O' || cognome.charAt(i) == 'o' || 
	 				cognome.charAt(i) == 'U' || cognome.charAt(i) == 'u') && codiceGenerato.length() < 3){
	 				codiceGenerato.append(cognome.substring(i, i+1).toUpperCase());
	 			}
	 		}
	 		//Caso cognome tipo FO diventa FOX
	 		if(codiceGenerato.length() < 3){
	 			while(codiceGenerato.length() < 3)
	 				codiceGenerato.append('X');

	 		}
	 	
	 	//genera nome
	 		
	 		for(int i = 0; i < nome.length(); i++) {
	 			if(nome.charAt(i) != 'A' && nome.charAt(i) != 'a' && 
	 			   nome.charAt(i) != 'E' && nome.charAt(i) != 'e' &&
	 			   nome.charAt(i) != 'I' && nome.charAt(i) != 'i' &&
	 			   nome.charAt(i) != 'O' && nome.charAt(i) != 'o' &&
	 			   nome.charAt(i) != 'U'&& nome.charAt(i) != 'u') {
	 				
	 				 contaCostanti++;
	 			}
	 		}
	 			
	 		if(contaCostanti <= 3) {
	 			for(int i = 0; i < nome.length(); i ++){
	 				if((nome.charAt(i) != 'A' && nome.charAt(i) != 'a' && 
	 		 			   nome.charAt(i) != 'E' && nome.charAt(i) != 'e' &&
	 		 			   nome.charAt(i) != 'I' && nome.charAt(i) != 'i' &&
	 		 			   nome.charAt(i) != 'O' && nome.charAt(i) != 'o' &&
	 		 			   nome.charAt(i) != 'U'&& nome.charAt(i) != 'u') && codiceGenerato.length() < 6) {
	 					
	 					   codiceGenerato.append(nome.substring(i, i+1).toUpperCase());
	 				}
	 			}
	 			
	 			for(int i = 0; i < nome.length(); i ++){
	 				if((nome.charAt(i) == 'A' || nome.charAt(i) == 'a' || 
	 		 				nome.charAt(i) == 'E' || nome.charAt(i) == 'e' || 
	 		 				nome.charAt(i) == 'I' || nome.charAt(i) == 'i' || 
	 		 				nome.charAt(i) == 'O' || nome.charAt(i) == 'o' || 
	 		 				nome.charAt(i) == 'U' || nome.charAt(i) == 'u') && codiceGenerato.length() < 6){
	 					codiceGenerato.append(nome.substring(i, i+1).toUpperCase());
	 				}
	 			}
	 			
	 			if(codiceGenerato.length() < 6){
	 				while(codiceGenerato.length() < 6) {
	 					codiceGenerato.append('X');
	 				}
	 			}
	 			
	 		} else {
	 			int pos = 0;
	 			
	 			for(int i = 0; i < nome.length(); i++) {
	 				if((nome.charAt(i) != 'A' && nome.charAt(i) != 'a' && 
		 		 			   nome.charAt(i) != 'E' && nome.charAt(i) != 'e' &&
		 		 			   nome.charAt(i) != 'I' && nome.charAt(i) != 'i' &&
		 		 			   nome.charAt(i) != 'O' && nome.charAt(i) != 'o' &&
		 		 			   nome.charAt(i) != 'U'&& nome.charAt(i) != 'u') && codiceGenerato.length() < 6) {
	 					pos++;
	 					
	 					if(pos != 2)
	 						codiceGenerato.append(nome.substring(i, i+1).toUpperCase());
	 				}
	 			}
	 		}
	 		
		//genera anno nascita
	 		
	 		anno = anno % 100;
	 		if (anno < 10)
				codiceGenerato.append("0" + anno);
			else
				codiceGenerato.append(anno);
	 		
		//genera mese nascita
	 		
	 		switch(mese) {
				case 1:
					codiceGenerato.append('A');
				break;
				
				case 2:
					codiceGenerato.append('B');
				break;
				
				case 3:
					codiceGenerato.append('C');
				break;
				
				case 4:
					codiceGenerato.append('D');
				break;
				
				case 5:
					codiceGenerato.append('E');
				break;
				
				case 6:
					codiceGenerato.append('H');
				break;
				
				case 7:
					codiceGenerato.append('L');
				break;
				
				case 8:
					codiceGenerato.append('M');
				break;
				
				case 9:
					codiceGenerato.append('P');
				break;
				
				case 10:
					codiceGenerato.append('R');
				break;
				
				case 11:
					codiceGenerato.append('S');
				break;
				
				case 12:
					codiceGenerato.append('T');
				break;
				
				default:
					System.out.println("Errore: mese non valido");
				break;
			}
			
		//genera giorno nascita
	 		
		if(sesso == 'F' ||sesso == 'f') {
			codiceGenerato.append(giorno + 40);
		}
		else if(sesso == 'M' ||sesso == 'm') {
			if (giorno < 10)
				codiceGenerato.append("0" + giorno);
			else
				codiceGenerato.append(giorno);
				
		} else {
			System.out.println("Errore: sesso non valido");
		}
		
		//genera comune
		
		codiceGenerato.append(GestoreXML.estraiCodiceComune(comune));
		
		//genera carattere controllo
		
		codiceGenerato.append(generaCarattereDiControllo(codiceGenerato));
		
		return codiceGenerato.toString();
	}
	
	/**
	 * Dato un codice fiscale completo (tranne che per l'ultimo carattere di controllo) genera e ritorna il corrispondente carattere di controllo.
	 * @param codiceGenerato il codice fiscale, StringBuffer.
	 * @return il carattere di controllo, char.
	 */
	private char generaCarattereDiControllo(StringBuffer codiceGenerato) {
		
		StringBuffer caratteriPari = new StringBuffer();
		StringBuffer caratteriDispari = new StringBuffer();
		int somma=0;
		
		//https://www.w3schools.com/java/java_hashmap.asp
		 HashMap<Character, Integer> dispari = new HashMap<Character, Integer>();
		 HashMap<Character, Integer> pari = new HashMap<Character, Integer>();
		 HashMap<Integer, Character> resto = new HashMap<Integer, Character>();
		 

	    // Add keys and values (Country, City)
	    dispari.put('0', 1);
	    dispari.put('1', 0);
	    dispari.put('2', 5);
	    dispari.put('3', 7);
	    dispari.put('4', 9);
	    dispari.put('5', 13);
	    dispari.put('6', 15);
	    dispari.put('7', 17);
	    dispari.put('8', 19);
	    dispari.put('9', 21);
	    dispari.put('A', 1);
	    dispari.put('B', 0);
	    dispari.put('C', 5);
	    dispari.put('D', 7);
	    dispari.put('E', 9);
	    dispari.put('F', 13);
	    dispari.put('G', 15);
	    dispari.put('H', 17);
	    dispari.put('I', 19);
	    dispari.put('J', 21);
	    dispari.put('K', 2);
	    dispari.put('L', 4);
	    dispari.put('M', 18);
	    dispari.put('N', 20);
	    dispari.put('O', 11);
	    dispari.put('P', 3);
	    dispari.put('Q', 6);
	    dispari.put('R', 8);
	    dispari.put('S', 12);
	    dispari.put('T', 14);
	    dispari.put('U', 16);
	    dispari.put('V', 10);
	    dispari.put('W', 22);
	    dispari.put('X', 25);
	    dispari.put('Y', 24);
	    dispari.put('Z', 23);
	    
	    pari.put('0', 0);
	    pari.put('1', 1);
	    pari.put('2', 2);
	    pari.put('3', 3);
	    pari.put('4', 4);
	    pari.put('5', 5);
	    pari.put('6', 6);
	    pari.put('7', 7);
	    pari.put('8', 8);
	    pari.put('9', 9);
	    pari.put('A', 0);
	    pari.put('B', 1);
	    pari.put('C', 2);
	    pari.put('D', 3);
	    pari.put('E', 4);
	    pari.put('F', 5);
	    pari.put('G', 6);
	    pari.put('H', 7);
	    pari.put('I', 8);
	    pari.put('J', 9);
	    pari.put('K', 10);
	    pari.put('L', 11);
	    pari.put('M', 12);
	    pari.put('N', 13);
	    pari.put('O', 14);
	    pari.put('P', 15);
	    pari.put('Q', 16);
	    pari.put('R', 17);
	    pari.put('S', 18);
	    pari.put('T', 19);
	    pari.put('U', 20);
	    pari.put('V', 21);
	    pari.put('W', 22);
	    pari.put('X', 23);
	    pari.put('Y', 24);
	    pari.put('Z', 25);
	    
	    resto.put(0, 'A');
	    resto.put(1, 'B');
	    resto.put(2, 'C');
	    resto.put(3, 'D');
	    resto.put(4, 'E');
	    resto.put(5, 'F');
	    resto.put(6, 'G');
	    resto.put(7, 'H');
	    resto.put(8, 'I');
	    resto.put(9, 'J');
	    resto.put(10, 'K');
	    resto.put(11, 'L');
	    resto.put(12, 'M');
	    resto.put(13, 'N');
	    resto.put(14, 'O');
	    resto.put(15, 'P');
	    resto.put(16, 'Q');
	    resto.put(17, 'R');
	    resto.put(18, 'S');
	    resto.put(19, 'T');
	    resto.put(20, 'U');
	    resto.put(21, 'V');
	    resto.put(22, 'W');
	    resto.put(23, 'X');
	    resto.put(24, 'Y');
	    resto.put(25, 'Z');
	    
		for(int i=0; i<codiceGenerato.length();i++) {
			//primo elemento è considerato in posizione dispari
			if(i%2 == 0)
				caratteriDispari.append(codiceGenerato.charAt(i));
			else
				caratteriPari.append(codiceGenerato.charAt(i));
		}
		
		
		for(int i=0; i<caratteriDispari.length(); i++) {
			
			somma += dispari.get(caratteriDispari.charAt(i));	
		}
		
		for(int i=0; i<caratteriPari.length(); i++) {
			
			somma += pari.get(caratteriPari.charAt(i));
		}
		
		somma = somma % 26 ;
		
		return resto.get(somma);
	}
	
	
	/**
	 * Metodo getter dell'attributo valore.
	 * 
	 * @return valore, String
	 */
	public String getValore() {
		return this.valore;
	}
	
	public Persona getPersona() {
		return this.persona;
	}

	public boolean correttezzaSintassi() {
		int lunghezzaCf = valore.length();
		
		//controllo lunghezza corretta
		if (lunghezzaCf != 16)
			return false;
	
		//controllo lettere al posto giusto
		if (!correttezzaLettere())
			return false;
		
		//controllo numeri al posto giusto
		if (!correttezzaNumeri())
			return false;
		
		//controllo correttezza formato nome e cognome
		if(!correttezzaNomeCognome())
			return false;
		
		//controllo sull'esistenza della data (es. NO 31 Aprile)
		if (!correttezzaData())
			return false;
		
		//controllo sul comune di nascita
		if (!correttezzaLuogoNascita())
			return false;
		
		//controllo sul carattere di controllo
		if (!correttezzaCarattereControllo())
			return false;

		return true;
	}

	/**
	 * Controlla che i caratteri del codice fiscale siano nel posto giusto.
	 * @return true se il formato è corretto, altrimenti false, boolean.
	 */
	private boolean correttezzaLettere() {
		String nome = valore.substring(0, 3);
		String cognome = valore.substring(3, 6);
		char mese = valore.charAt(8);
		String comune = valore.substring(11, 15);
		char controllo = valore.charAt(15);
		
		// controllo su nome e cognome
		for (int i = 0; i < nome.length(); i++) {
			if ((nome.charAt(i) < 65) || (nome.charAt(i) > 90 && nome.charAt(i) < 97) || (nome.charAt(i) > 122)) {
				return false;
			} // nome non corretto

			if ((cognome.charAt(i) < 65) || (cognome.charAt(i) > 90 && cognome.charAt(i) < 97)
					|| (cognome.charAt(i) > 122)) {
				return false;
			}

		}
		
		if (!(mese == 'B' || mese == 'b' || mese == 'S' || mese == 'D' || mese == 'H' || mese == 'P' || mese == 's'
				|| mese == 'd' || mese == 'h' || mese == 'p' || mese == 'A' || mese == 'C' || mese == 'E' || mese == 'L'
				|| mese == 'M' || mese == 'R' || mese == 'T' || mese == 'a' || mese == 'c' || mese == 'e' || mese == 'l'
				|| mese == 'm' || mese == 'r' || mese == 't'))
			return false;

		if ((comune.charAt(0) < 65) || ((comune.charAt(0) > 90 && comune.charAt(0) < 97)) || (comune.charAt(0) > 122))
			return false;


		if (controllo < 65 || (controllo > 90 && controllo < 97) || controllo > 122)
			return false;

		
		return true;
	}

	/**
	 * Controlla che i numeri del codice fiscale siano nel posto giusto.
	 * @return true se il formato è corretto, altrimenti false, boolean.
	 */
	private boolean correttezzaNumeri() {
		String anno = valore.substring(6, 8);
		String giorno = valore.substring(9, 11);
		String comune = valore.substring(11, 15);
		
		// controllo sull'anno che sia numero
		if (!((anno.charAt(0) >= 48 && anno.charAt(0) <= 57) && (anno.charAt(1) >= 48 && anno.charAt(1) <= 57)))
			return false;

		// caratteri controllati tramite tabella ASCII
		if (!(giorno.charAt(0) >= 48 && giorno.charAt(0) <= 55)) // decine da 0 e 7
			return false;

		if (giorno.charAt(0) == 48 || giorno.charAt(0) == 52) {// gg decina a 0 o a 4 deve avere unita da 1 a 9

			if (!(giorno.charAt(1) >= 49 && giorno.charAt(1) <= 57)) {
				return false;
			}
		}

		if (giorno.charAt(0) == 51 || giorno.charAt(0) == 55) {// gg decina 3 o 7 deve avere unita o 0 o 1
			if (!(giorno.charAt(1) >= 48 && giorno.charAt(1) <= 49)) {
				return false;
			}
		}

		if (giorno.charAt(0) == 49 || giorno.charAt(0) == 50 || giorno.charAt(0) == 53 || giorno.charAt(0) == 54) {// gg decina vale 1, 2,
																													// 5, 6 unità va da 0 a 9
																												
			if (!(giorno.charAt(1) >= 48 && giorno.charAt(1) <= 57)) {
				return false;
			}
		}

		for (int i = 1; i < comune.length(); i++) { //controlla che siano cifre numeriche
			if (!(comune.charAt(i) >= 48 && comune.charAt(i) <= 57)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Controlla che la data sia corretta.
	 * @return true se la data è corretta, altrimenti false, boolean.
	 */
	private boolean correttezzaData() {
		String stringaAnno = valore.substring(6, 8);
		char charMese = valore.charAt(8);
		String stringaGiorno = valore.substring(9, 11);
		
		int giorno = 0;
		int mese = 0;
		int anno = 0;
		
		giorno = Integer.parseInt(stringaGiorno);
		if (giorno > 31)
			giorno = giorno - 40;
		
		switch(charMese) {
			case 'A': case 'a':
				mese = 1;
			break;
			
			case 'B': case 'b':
				mese = 2;
			break;
			
			case 'C': case 'c':
				mese = 3;
			break;
			
			case 'D': case 'd':
				mese = 4;
			break;
			
			case 'E': case 'e':
				mese = 5;
			break;
			
			case 'H': case 'h':
				mese = 6;
			break;
			
			case 'L': case 'l':
				mese = 7;
			break;
			
			case 'M': case 'm':
				mese = 8;
			break;
			
			case 'P': case 'p':
				mese = 9;
			break;
			
			case 'R': case 'r':
				mese = 10;
			break;
			
			case 'S': case 's':
				mese = 11;
			break;
			
			case 'T': case 't':
				mese = 12;
			break;
			
			default:
				return false;
		}
		
		anno = Integer.parseInt(stringaAnno);
		anno = anno + 1900;

		return InputDati.verificaCorrettezzaData(giorno, mese, anno);
	}

	/**
	 * Controlla che il formato di nome e cognome sia corretto.
	 * @return true se il formato è corretto, altrimenti false, boolean.
	 */
	private boolean correttezzaNomeCognome() {
		
		String cognome = valore.substring(0,3);
		String nome = valore.substring(3,6);
	
	 for(int i=0; i<cognome.length()-1; i++){
			if(((cognome.charAt(i) == 'A' || cognome.charAt(i) == 'a' || 
	 				cognome.charAt(i) == 'E' || cognome.charAt(i) == 'e' || 
	 				cognome.charAt(i) == 'I' || cognome.charAt(i) == 'i' || 
	 				cognome.charAt(i) == 'O' || cognome.charAt(i) == 'o' || 
	 				cognome.charAt(i) == 'U' || cognome.charAt(i) == 'u')) 
					&& ((cognome.charAt(i+1) != 'A' && cognome.charAt(i+1) != 'E' 
		 			&& cognome.charAt(i+1) != 'I' && cognome.charAt(i+1) != 'O' 
		 			&& cognome.charAt(i+1) != 'U' && cognome.charAt(i+1) != 'a' 
		 			&& cognome.charAt(i+1) != 'e' && cognome.charAt(i+1) != 'i' 
		 			&& cognome.charAt(i+1) != 'o' && cognome.charAt(i+1) != 'u'
		 			&& cognome.charAt(i+1) != 'X' && cognome.charAt(i+1) != 'x'))) {
				return false;
			}
		}
		

	 for(int i=0; i<nome.length()-1; i++){
			if(((nome.charAt(i) == 'A' || nome.charAt(i) == 'a' || 
	 				nome.charAt(i) == 'E' || nome.charAt(i) == 'e' || 
	 				nome.charAt(i) == 'I' || nome.charAt(i) == 'i' || 
	 				nome.charAt(i) == 'O' || nome.charAt(i) == 'o' || 
	 				nome.charAt(i) == 'U' || nome.charAt(i) == 'u')) 
					&& ((nome.charAt(i+1) != 'A' && nome.charAt(i+1) != 'E' 
		 			&& nome.charAt(i+1) != 'I' && nome.charAt(i+1) != 'O' 
		 			&& nome.charAt(i+1) != 'U' && nome.charAt(i+1) != 'a' 
		 			&& nome.charAt(i+1) != 'e' && nome.charAt(i+1) != 'i' 
		 			&& nome.charAt(i+1) != 'o' && nome.charAt(i+1) != 'u'
		 			&& nome.charAt(i+1) != 'X' && nome.charAt(i+1) != 'x'))) {
				return false;
				
			}
		}
		
	return true;
	}

	/**
	 * Controlla che i caratteri del luogo di nascita corrispondano al codice di un comune davvero esistente.
	 * @return true, boolean.
	 */
	private boolean correttezzaLuogoNascita() {
		String codiceComune = this.valore.substring(11, 15);
	
		return GestoreXML.esistenzaCodiceComune(codiceComune);
	}
	
	/**
	 * Verifica che in un codice fiscale il carattere di controllo sia corretto.
	 * @return true se il carattere di controllo risulta corretto, altrimenti false, boolean.
	 */
	private boolean correttezzaCarattereControllo() {
		StringBuffer cfSenzaControllo = new StringBuffer(this.valore.substring(0, this.valore.length()-1));
		char controlloPresente = this.valore.charAt(this.valore.length()-1);
		char controlloCalcolato = generaCarattereDiControllo(cfSenzaControllo);
		
		if(controlloPresente == controlloCalcolato)
			return true;
		
		return false;
	}
}
