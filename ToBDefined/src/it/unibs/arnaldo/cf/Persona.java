package it.unibs.arnaldo.cf;

/**
 * Classe persona, contiene tutte le caratteristiche di una persona necessarie per generare il codice fiscale
 * @author toBdefined
 *
 */
public class Persona {
	public enum Sesso {M, F};
	
	private int id;
	private String cognome;
	private String nome;
	private Sesso sesso;
	private String nascita;
	private String comune;
	private CodiceFiscale cf;
	private boolean assente;
	
	/**
	 * Metodo costruttore di persona, assegna i valori passati, mette cf = null e assente = true
	 * @param id 0
	 * @param cognome Rossi
	 * @param nome Mario
	 * @param sesso M
	 * @param nascita 1990-05-21
	 * @param comune Brescia
	 */
	public Persona(int id, String cognome, String nome, String sesso, String nascita, String comune) {
		this.id = id;
		this.cognome = cognome;
		this.nome = nome;
		this.sesso = Sesso.valueOf(sesso);
		this.nascita = nascita;
		this.comune = comune;
		this.cf = null;
		this.assente = true;
	}
	
	/**
	 * Metodo per settare l'attributo assente
	 * @param tf booleano true o false
	 */
	public void setAssente(boolean tf) {
		this.assente = tf;
	}
	
	/**
	 * Metodo che ritorna se la persona è assente
	 * @return true/false
	 */
	public boolean isAssente() {
		return this.assente;
	}
	
	/**
	 * Metodo che genera il codice fiscale richiamando il costruttore
	 */
	public void genCodiceFiscale() {
		this.cf = new CodiceFiscale(cognome, nome, sesso, nascita, comune);
	}
	
	/**
	 * Metodo toString, stampa cognome, nome e (se esiste) il codice fiscale
	 */
	public String toString() {
		return cognome + " " + nome + (cf != null ? " " + cf : "");
	}
	
	/*GETTERS*/
	
	/**
	 * Ritorna l'id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Ritorna il nome
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Ritorna il cognome
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * Ritorna il sesso
	 * @return sesso Sesso.M
	 */
	public Sesso getSesso() {
		return sesso;
	}
	
	/**
	 * Ritorna il comune
	 * @return comune
	 */
	public String getComune() {
		return comune;
	}
	
	/**
	 * Ritorna la data di nascita
	 * @return nascita
	 */
	public String getNascita() {
		return nascita;
	}
	
	/**
	 * Ritorna il codice fiscale
	 * @return cf.toString()
	 */
	public String getCf() {
		return cf.toString();
	}
}
