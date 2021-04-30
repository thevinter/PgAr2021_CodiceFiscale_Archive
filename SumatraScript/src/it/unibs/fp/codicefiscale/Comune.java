package it.unibs.fp.codicefiscale;

public class Comune {
	private String nome;
	private String codice;

	public Comune(String nome, String codice) {
		super();
		this.nome = nome;
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCodice() {
		return codice;
	}



	public void setCodice(String codice) {
		this.codice = codice;
	}



	@Override
	public String toString() {
		return "Comune [nome=" + nome + ", codice=" + codice + "]";
	}

}
