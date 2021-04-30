package codiceFiscale;

public class Comune {
    String nome;
    String codice;


    /**
     * crea un istanza dato il nome e il codice del comune
     * @param nome      nome del comune
     * @param codice    codice del comune (lettera + 3 numeri)
     */
    public Comune(String nome, String codice) {
        this.nome = nome;
        this.codice = codice;
    }

    /**
     * costruttore vuoto in caso di necessita
     */
    public Comune(){}

    /**
     * ritorna il nome del comune
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * imposta il nome del comune
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * ritorna il codice del comune
     * @return
     */
    public String getCodice() {
        return codice;
    }

    /**
     * imposta il codice del comune
     * @param codice
     */
    public void setCodice(String codice) {
        this.codice = codice;
    }
}
