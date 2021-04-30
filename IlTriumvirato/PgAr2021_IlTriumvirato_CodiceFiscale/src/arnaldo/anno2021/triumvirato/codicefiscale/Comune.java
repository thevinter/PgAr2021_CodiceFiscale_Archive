package arnaldo.anno2021.triumvirato.codicefiscale;

/**
 * Questa classe contiene le informazioni su un comune, che consistono solo nel suo nome e nel codice che gli corrisponde
 */
public class Comune {
	private String nome;
	private String codice;
	
	public Comune() {
		
	}
	
	public Comune(String nome, String codice) {
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
	
	
	
}