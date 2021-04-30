package arnaldo.anno2021.triumvirato.codicefiscale;

import java.time.LocalDate;

/**
 * @author david
 *
 */
public class Persona {

	private int id;
	private String nome;
	private String cognome;
	private char sesso;
	private String comune_nascita;
	private LocalDate data_nascita;
	private String codiceFiscale;
	private boolean codiceAssente;
	
	public Persona(String nome, String cognome, char sesso, String comune_nascita, LocalDate data_nascita) {
		this.nome=nome;
		this.cognome = cognome;
		this.setSesso(sesso);
		this.comune_nascita = comune_nascita;
		this.data_nascita = data_nascita;
		codiceAssente=false;
	}
	

	public Persona(int id) {
		this.id=id;
	}
	
	public boolean getCodiceAssente() {
		return codiceAssente;
	}
	
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
		
		//in caso di un errore in cui l'utente interpreta i caratteri del sesso dell'individuo come "Uomo" e "Donna" e scrive U e D, viene fatta una conversione:
		if(sesso=='U') {
			sesso='M';
		}else {
			if(sesso=='D') {
				sesso='F';
			}
		}
		
		if(sesso!='M'&&sesso!='F') {
			sesso='N'; //N di "non-definito" per quando viene stampato, in caso la lettura non sia riuscita correttamente, in tal caso nel codice fiscale interpreta il giorno come nel caso "uomo"
		}
		
	}

	public String getComune_nascita() {
		return comune_nascita;
	}

	public void setComune_nascita(String comune_nascita) {
		this.comune_nascita = comune_nascita;
	}

	public LocalDate getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(LocalDate data_nascita) {
		this.data_nascita = data_nascita;
	}
	
	
	private String getStringaAnnoNascita() {
		String stringaAnno=""+data_nascita.getYear();
		while(stringaAnno.length()<2) {  //caso estremo
			stringaAnno="0"+stringaAnno;
		}
		
		return stringaAnno.substring(stringaAnno.length()-2,stringaAnno.length());
	}

	/*
	 * Prende una stringa di 3 lettere che rappresenta il nome nel codice fiscale, l'algoritmo è il seguente: se ci sono solo 3 consonanti, le prende in ordine, se ce ne sono di più prende la prima, la terza e la quarta
	 * Se invece ci sono meno di 3 consonanti, le prende in ordine aggiungendo vocali in ordine dal nome per arrivare a 3
	 * Se non bastano neanche le vocali, inserisce il carattere presente in Constants.FILLER_CHAR
	 */
	private String getThreeLetterName() {
		StringBuffer tln=new StringBuffer("");
		
		String digitLessName=DataProcessor.getDigitlessString(nome); //controllo a prova di caso particolare in cui uno per errore o per altro ha dei numeri nel nome
		String vowelLessName=DataProcessor.getVowellessString(digitLessName);
		
		
		if(vowelLessName.length()==3) {
			
			tln.append(vowelLessName);
			
		}else if(vowelLessName.length()>3) {
			String toAppend=""+vowelLessName.charAt(0)+""+vowelLessName.charAt(2)+""+vowelLessName.charAt(3);
			tln.append(toAppend);
			
		}else{ //se il nome ha meno di tre consonanti, i casi sono due: se ci sono poi vocali per completarlo, mette prima le consonanti poi le vocali necessarie in ordine; se invece non ci sono neanche quelle, aggiunge delle X
			
				
				tln.append(vowelLessName);
				int i=0;
				int counter=vowelLessName.length();
				while(counter<3) {
					
					if(i<nome.length()){
						if(DataProcessor.isVowel(nome.charAt(i))) {
							tln.append(nome.charAt(i));
							counter++;
						}
						
						i++;
					}else {
						tln.append(Constants.FILLER_CHAR);
						counter++;
					}		
					
				}
				
			
			
		}
		
		return new String(tln);
	}
		
	/*
	 * Prende una stringa di 3 lettere che rappresenta il nome nel codice fiscale, l'algoritmo è il seguente: se ci sono 3 o più consonanti, prende le prime 3 in ordine
	 * Se invece ci sono meno di 3 consonanti, le prende in ordine aggiungendo vocali in ordine dal cognome per arrivare a 3
	 * Se non bastano neanche le vocali, inserisce il carattere presente in Constants.FILLER_CHAR
	 */
	private String getThreeLetterSurname() {
		
		StringBuffer tls=new StringBuffer("");
		
		String digitLessSurname=DataProcessor.getDigitlessString(cognome); //controllo a prova di caso particolare in cui uno per errore o per altro ha dei numeri nel nome
		String vowelLessSurname=DataProcessor.getVowellessString(digitLessSurname);
		
			
		if(vowelLessSurname.length()>=3){
			
			tls.append(vowelLessSurname.substring(0,3));
			
		}else{ //se il nome ha meno di tre consonanti, i casi sono due: se ci sono poi vocali per completarlo, mette prima le consonanti poi le vocali necessarie in ordine; se invece non ci sono neanche quelle, aggiunge delle X
			
			
			tls.append(vowelLessSurname);
			int i=0;
			int counter=vowelLessSurname.length();
			while(counter<3) {
				
				if(i<cognome.length()){
					if(DataProcessor.isVowel(cognome.charAt(i))) {
						tls.append(cognome.charAt(i));
						counter++;
					}
					
					i++;
				}else {
					tls.append(Constants.FILLER_CHAR);
					counter++;
				}		
				
			}
			
		
		
	}
		
		return new String(tls);
	}
	
	/*
	 * Il codice fiscale contiene in due cifre sia l'informazione del giorno di nascita sia del sesso dell'individuo: da 1 a 31 il giorno, al quale viene aggiunto Constants.GENDER_NUMBER_TO_ADD se l'individuo è di sesso femminile
	 */
	private String getGiornoNascitaComposito() {
		int giornoNascita=data_nascita.getDayOfMonth();
		if(sesso=='F')giornoNascita+=Constants.GENDER_NUMBER_TO_ADD;
		
		if(giornoNascita<10) {
			return ("0"+giornoNascita);
		}else{
			return (""+giornoNascita);
		}
		
	}
	
	private String getCodiceComune() {
		String codiceComune=Maps.getMappaCodiciComuniDalNome().get(this.comune_nascita);
		
		if(codiceComune==null) {
			return (Constants.FILLER_CHAR+"000");
		}else{
			return codiceComune;
		}
	}
	
	
	/**
	 * Metodo che viene utilizzato per generare il codice fiscale delle persone
	 */
	public void generaCodiceFiscale() {
		this.codiceFiscale="";
		StringBuffer cf=new StringBuffer("");
		cf.append("");
		
		//genera cognome
		cf.append(getThreeLetterSurname());

		//genera nome
		cf.append(getThreeLetterName());

		//anno di nascita
		cf.append(this.getStringaAnnoNascita());
		
		
		//mese di nascita
		cf.append(Constants.MONTH_CHARS[data_nascita.getMonthValue()-1]);
		
		
		//dato composito costituito dal giorno di nascita, al quale viene aggiunto 40 in caso di sesso femminile, in modo da utilizzare un carattere 
		cf.append(getGiornoNascitaComposito());
		
		String codiceComune=getCodiceComune();
		cf.append(codiceComune);
		if(codiceComune.equals(Constants.FILLER_CHAR+"000")) {
			this.codiceAssente=true;
		}
		
		char carattereDiControllo=DataProcessor.calcolaCarattereControllo(new String(cf));
		cf.append(carattereDiControllo);
		
		
		this.codiceFiscale=new String(cf);
		this.codiceFiscale=codiceFiscale.toUpperCase();
	}
	
	
	/**
	 * Questo metodo fornisce il codice fiscale, viene chiamato solo in parti del codice dove è già stato generato il codice fiscale col metodo generaCodiceFiscale
	 * @see generaCodiceFiscale()
	 * @return ritorna il codice fiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	
	public boolean isCodiceAssente() {
		return codiceAssente;
	}

	public void setCodiceAssente(boolean codiceAssente) {
		this.codiceAssente = codiceAssente;
	}
}