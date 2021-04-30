package it.unibs.arnaldo.TesteAnnidate.CodiceFiscale;

public class Persona {

	private String nome;
	private String cognome;
	private Data dataDiNascita;
	private char sesso;
	private Comune luogoDiNascita; // oppure una classe Comuni
	private CodiceFiscale cf;


	
	
	// Getters e getters
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

	public Data getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Data dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
	}

	public Comune getLuogoDiNascita() {
		return luogoDiNascita;
	}

	public void setLuogoDiNascita(Comune luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}

	public CodiceFiscale getCf() {
		return cf;
	}

	public void setCf(CodiceFiscale cf) {
		this.cf = cf;
	}

}
