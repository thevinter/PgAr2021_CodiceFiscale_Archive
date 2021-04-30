package it.unibs.fp.codicefiscale;

public class Persona {

	private String nome;
	private String cognome;
	private String sesso;
	private String comune;
	private String data_di_nascita;
	private String codice_fiscale;

	public Persona(String nome, String cognome, String sesso, String comune, String data_di_nascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.comune = comune;
		this.data_di_nascita = data_di_nascita;
	}

	public String getData_di_nascita() {
		return data_di_nascita;
	}

	public void setData_di_nascita(String data_di_nascita) {
		this.data_di_nascita = data_di_nascita;
	}

	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
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

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}
	
	@Override
	public String toString() {
		return "Persona [nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", comune=" + comune
				+ ", data_di_nascita=" + data_di_nascita + "]";
	}

}
