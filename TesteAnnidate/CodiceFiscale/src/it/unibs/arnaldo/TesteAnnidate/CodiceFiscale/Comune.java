package it.unibs.arnaldo.TesteAnnidate.CodiceFiscale;

public class Comune {
	private String nome_comune;
	private String codice;

	// costruttore che imposta solo il nome_comune (usato nella lettura del file
	// inputPersone.xml)
	public Comune(String nome_comune) {
		this.nome_comune = nome_comune;
	}

	// costruttore che crea a null un Comune (usato nella lettura del file
	// comuni.xml)
	public Comune() {
		this.nome_comune = "";
		this.codice = "";
	}

	// Getters e setters

	public String getNome_comune() {
		return nome_comune;
	}

	public void setNome_comune(String nome_comune) {
		this.nome_comune = nome_comune;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

}