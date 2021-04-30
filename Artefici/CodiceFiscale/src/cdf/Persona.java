package cdf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import  java.util.Date;  

public class Persona {
	
	private String id;
	private String nome;
	private String cognome;
	private String sesso;
	private Comune comune_nascita;
	private Date data_nascita;
	private String codice_fiscale;
	private boolean is_presente=false;

	public Persona() {
	}

	/**
	 * @return stringa corrispondente al nome della persona
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * set id della persona
	 * @param string
	 */
	public void setId(String string) {
		this.id = string;
	}

	/**
	 * @return stringa corrispondente all'id della persona
	 */
	public String getId() {
		return id;
	}

	/**
	 * set nome della persona
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return stringa corrispondente al cognome della persona
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * set cognome della persona
	 * @param cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return stringa corrispondente al sesso della persona
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * set sesso della persona
	 * @param sesso
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	
	/**
	 * @return stringa corrispondente al comune di nascita della persona
	 */
	public Comune getComune_nascita() {
		return comune_nascita;
	}

	/**
	 * set comune di nascita della persona
	 * @param comune_nascita
	 */
	public void setComune_nascita(Comune comune_nascita) {
		this.comune_nascita = comune_nascita;
	}

	/**
	 * @return stringa corrispondente al data di nascita della persona
	 */
	public Date getData_nascita() {
		return data_nascita;
	}

	/**
	 *istanzia una variabile di tipo Data con la data rappresentata dalla stringa passata come argomento
	 * @param stringa_data
	 */
	public void setData_nascita(String stringa_data) {
		try {
			this.data_nascita = new SimpleDateFormat("yyyy-MM-dd").parse(stringa_data);
		} catch (ParseException e) {
			e.printStackTrace();
		} 		
	}
	
	/**
	 * @return stringa corrispondente al codice fiscale della persona
	 */
	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	/**
	 * set codice fiscale della persona
	 * @param codice_fiscale
	 */
	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}

	/**
	 * @return true se il codice fiscale è associato ad una persona
	 */
	public boolean getIs_presente() {
		return is_presente;
	}

	/**
	 * set true se il codice fiscale è associato ad una persona, false altrimenti
	 * @param is_presente
	 */
	public void setIs_presente(boolean is_presente) {
		this.is_presente = is_presente;
	}


}
