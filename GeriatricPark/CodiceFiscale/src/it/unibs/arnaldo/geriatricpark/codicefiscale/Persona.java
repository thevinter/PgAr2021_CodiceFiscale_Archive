package it.unibs.arnaldo.geriatricpark.codicefiscale;

/**Definisce una persona, caratterizzata da un id, un nome, un cognome, 
* un comune di nascita e una data di nascita.
* @author Geriatric Park (Nicol Stocchetti, Wiam Nasr Allah, Mohamed Nassar).
*
*/

public class Persona { 
	private int id ;
	private String nome ;
	private String cognome;
	private char sesso;
	private String comuneDiNascita;
	private int annoDiNascita;
	private int meseDiNascita;
	private int giornoDiNascita;
	
	/**
	* Costruttore generale 
	* @param id codice che identifica la persona, int.
	* @param nome il nome della persona, String.
	* @param cognome il cognome della persona, String.
	* @param sesso il sesso della persona, char.
	* @param comuneDiNascita il comune di nascita della persona, String.
	* @param annoDiNascita l'anno di nascita della persona, int.
	* @param meseDiNascita il mese di nascita della persona, int.
	* @param giornoDiNascita il giorno di nascita della persona, int.
	*/
	
	public Persona (int id, String nome, String cognome, char sesso,
			String comuneDiNascita, int annoDiNascita, int meseDiNascita, int giornoDiNascita) {
		
		this.id=id;
		this.nome=nome;
		this.cognome=cognome;
		this.sesso=sesso;
		this.comuneDiNascita=comuneDiNascita;
		this.annoDiNascita=annoDiNascita;
		this.meseDiNascita=meseDiNascita;
		this.giornoDiNascita=giornoDiNascita;
	
	}
	/**
	* ritorna il codice che identifica la persona.
	* @return l'id della persona, int.
	*/
	public int getId() {
		return id;
	}
	
	/**
	* ritorna il nome della persona
	* @return il nome della persona, String.
	*/
	
	public String getNome() {
		return nome;
	}
	
	/**
	* ritorna il Cognome della persona.
	* @return il cognome della persona, String.
	*/
	
	public String getCognome() {
		return cognome;
	}
	
	/**
	* ritorna il sesso della persona.
	* @return il sesso della persona, String.
	*/
	public char getSesso() {
		return sesso;
	}
	
	/**
	* ritorna il Comune di Nascita della persona.
	* @return il comune di nascita  della persona, String.
	*/
	public String getComuneDiNascita() {
		return comuneDiNascita;
	}
	
	/**
	* ritorna l'anno di Nascita della persona.
	* @return l'anno di nascita  della persona, int.
	*/
	
	public int getAnnoDiNascita() {
		return annoDiNascita;
	}
	
	/**
	* ritorna il mese di Nascita della persona.
	* @return il mese di nascita della persona, int.
	*/
	
	public int getMeseDiNascita() {
		return meseDiNascita;
	}
	
	
	/**
	* ritorna il giorno di Nascita della persona.
	* @return il giorno di nascita della persona, int.
	*/
	
	public int getGiornoDiNascita() {
		return giornoDiNascita;
	}
	
	
	@Override
	public String toString() {
		return String.format(id + " " + nome + " " + cognome + " " + sesso + " " + comuneDiNascita + " " + annoDiNascita + " "+ meseDiNascita + " "+ giornoDiNascita + "\n");////////////////
				
	}
	
	
}

