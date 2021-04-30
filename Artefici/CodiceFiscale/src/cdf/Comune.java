package cdf;

public class Comune {
	private String nome;
	private String codice;
	
	public Comune() {
	}
	
	/**
	 * @return stringa corrispondente al nome del comune
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * set nome del comune
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return stringa corrispondete al codice del comune
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * set codice del comune
	 * @param codice
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
}
