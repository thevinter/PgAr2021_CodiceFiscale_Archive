package it.unibs.fp.codicefiscale;

import java.util.Arrays;

/**
 *Classe  contenente i dati di una persona da cui generare il corrispondente
 *codice fiscale dati i parametri neccessari quali il nome, il cognome,
 *il comune di nascita,la data di nascita e il sesso
 *
 */

public class Persona {

	private String nome;
	private String cognome;
	private String codicefiscale;

	private String sesso;
	private String comune;
	private Data data;
	/**
	 * variabile usata per il carattere di controllo, tiene traccia di tutte le lettere
	 * possibili e viene usata per il calcolo dei valori in posizione pari
 	 */
	private final static char[] LETTEREPARI={'0','1','2','3','4','5','6','7','8','9','A','B',
			'C','D','E','F','G','H','I','J','K','L','M','N',
			'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	/**
	 * variabile usata per il carattere di controllo, contiene i valori delle lettere in posizione dispari
 	 */
	private final static int[] LETTEREDISPARI={1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 1, 0, 5, 7, 9, 13,
			15, 17, 19, 21, 2, 4, 18, 20, 11, 3, 6, 8, 12, 14, 16,
			10, 22, 25, 24, 23
	};


	public String getNome() {
		return nome;
	}


	public String getCognome() {
		return cognome;
	}


	public String getSesso() {
		return sesso;
	}


	public String getComune() {
		return comune;
	}


	public String getCompleanno() {
		
		return data.getDay()+"-"+data.getMonth()+"-"+data.getYear();
	}


	/**
	 * costruttore della classe persona: i parametri da passare in oridene sono: nome,cognome,sesso
	 * comune,data di nascita
	 * @param nome
	 * @param cognome
	 * @param sesso
	 * @param comune
	 * @param data
	 */
	public Persona(String nome, String cognome,String sesso, String comune,Data data) {

		this.nome = nome;
		this.cognome = cognome;
		this.data=data;
	     this.sesso=sesso;
		this.comune = comune;
		this.codicefiscale = codice().toString();
	}


	/**
	 * Metodo per generare il codice fiscale della persona
	 *dal nome, cognome,comune, genere,data di nascita
	 * @return
	 */
	public StringBuffer codice() {
		StringBuffer codiceFiscale = new StringBuffer();
		String cod;

		//prime 3 lettere del cognome
		if (cognome.length() < 3) {
			codiceFiscale.append(cognome);
			codiceFiscale.append("X");
		} else codiceFiscale.append(lastNametLetters(cognome));

		//lettere nome

		if(nome.length()<3){
			codiceFiscale.append(nome);
			codiceFiscale.append("X");
		}else codiceFiscale.append(nameLetters(nome));
	//lettere anno di nascita
	codiceFiscale.append(data.getLastTwoDigitsYear());
	codiceFiscale.append(data.mese(data.getMonth()));
	//genere

		if (sesso.equalsIgnoreCase("m")) {
			codiceFiscale.append(data.formatta(data.getDay()));
		} else {
			codiceFiscale.append(data.getDayFemale());
		}
		//lettere comune
		System.out.println("----------"+Comune.getCodiceComune(comune.trim()));
		codiceFiscale.append(Comune.getCodiceComune(comune.trim()));
		//carattere controllo
		
		codiceFiscale.append(lastCharacter(codiceFiscale.toString()));

		return codiceFiscale;
	}


	

	public String getCodiceFiscale() {
 
		return codicefiscale;
	}

	/**
	 * metodo che accetta una stringa e restituisce una stringa contenete
	 * le consonanti presenti nella stringa
	 * @param checkCon
	 * @return
	 */
	private StringBuffer consonant(String checkCon){
		StringBuffer cons = new StringBuffer();


		for (int i = 0; i < checkCon.length(); i++)
			if (isConsonant(checkCon.charAt(i))) {
				cons.append(checkCon.charAt(i));
			}
		return cons;

	}

	/**
	 * metodo che accetta una stringa e restituisce una stringa contenete
	 * 	le vocali presenti nella stringa
	 * @param checkVow
	 * @return
	 */
	private StringBuffer vowel(String checkVow){ //restituisce una stringa contenente le vocali
	StringBuffer vow = new StringBuffer();
	for (int i = 0; i < checkVow.length(); i++)
		if (!isConsonant(checkVow.charAt(i))) {
			vow.append(checkVow.charAt(i));
		}
		return vow;
}

	/**
	 * metodo usato per generare le 3 lettere del cognome della persona,
	 * viene passato come parametro il cognome come stringa e restituisce
	 *  una stringa che contiene le prime 3 consonanti in ordine se ce ne sono
	 *  sufficienti,altrimenti prende le vocali aggiungiendole alla fine sempre in oridne
	 * @param check
	 * @return
	 */
	private StringBuffer lastNametLetters(String check) {
		StringBuffer lett = new StringBuffer(3);

		StringBuffer lastNameCons=new StringBuffer(consonant(check));
		StringBuffer lastNameVow=new StringBuffer(vowel(check));
		lastNameCons.append(lastNameVow);//unisco le stringhe delle consonanti e delle vocali
		lett.append(lastNameCons.substring(0,3));//prendo solo i primi 3 caratteri


			return lett;


	}

	/**
	 * metodo usato per generare le 3 lettere del nome della persona,
	 * come parametro passiamo il nome e ne restituira una stringa che contiene
	 * 3 lettere seguendo alcune regole
	 *
	 * @param check
	 * @return
	 */
	private StringBuffer nameLetters(String check){
		StringBuffer lett = new StringBuffer(3);

//verifico che il nome contenga 4 o più consonanti, in questo caso aggiungo la prima,terza e quarta consonante
		StringBuffer nameCons=new StringBuffer(consonant(check));
		if(nameCons.length()>=4){
			lett.append(nameCons.charAt(0));
			lett.append(nameCons.charAt(2));
			lett.append(nameCons.charAt(3));
		}else{  //altrimenti ne aggiungo 3 in oridne, aggiungendo vocali in caso di neccessita(le vocali andranno sempre dopo le consonanti)
			StringBuffer nameVow=new StringBuffer(vowel(check));
			nameCons.append(nameVow);
			lett.append(nameCons.substring(0,3));

		}

return lett;


	}

	/**
	 * metodo usato per generare il carattere di controllo, prendendo i caratteri
	 * ricavati in precedenza mettendo da parte
	 * i caratteri in posizione pari e in posizione dispari e assegnandoli un valore:
	 * i valori che si ottengono dai caratteri alfanumerici pari e dispari vanno sommati tra di loro e il risultato va diviso per 26;
	 * il resto della divisione fornirà il codice identificativo in base a una tabella di conversione
	 *
	 * @param codice
	 * @return
	 */

	public static char lastCharacter(String codice){
		int controllo,sommaPari=0,sommaDispari=0,indice;
		char chr;
		char lastChar;
		for (int i=0;i<codice.length();i++){
			chr=codice.charAt(i);
			if((i+1)%2==0){// il primo carattere parte da 0, devo quindi aggiungere una unita
				indice= Arrays.binarySearch(LETTEREPARI,chr);
				sommaPari+=(indice>=10) ? indice-10:indice;
				//vedendo la tabella di conversione delle lettere pari, le prime 10 lettere
				//hanno i valori da 0-9, a partire da A si ha di nuovo 0 quindi al indice trovato si puo sottrare 10
				//unita(per esempio Z: valore 25 indice 35)
			}else {
				indice=Arrays.binarySearch(LETTEREPARI,chr);
				sommaDispari+=LETTEREDISPARI[indice];
			}
		}
			controllo=(sommaPari+sommaDispari)%26;
		lastChar=LETTEREPARI[controllo+10];//aggiungendo 10 dieci posso prendere dal array LETTEREPARI a partire dalla lettera A(che è 10 posizioni piu avanti)

		return lastChar;
								//esempio: se il resto e' 18(lettera S nella tabella resto di wiki) aggiungendo 10 vado in posizione 28 del array(lettera S)
								//N.B il resto non sara mai maggiore di 25

}

	/**
	 * controlla se un carattere e determina se si tratta di una vocale o consonante
	 * @param check
	 * @return
	 */
	private boolean isConsonant(char check) {


		String vocali = "aeiouAEIOU";
		return (vocali.indexOf(check) == -1) ? true : false;
	}

}
