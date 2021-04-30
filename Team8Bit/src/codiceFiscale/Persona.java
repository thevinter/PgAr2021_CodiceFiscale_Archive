package codiceFiscale;

public class Persona {
    private String id;
    private String nome;
    private String cognome;
    private char sesso;
    private String dataDiNascita;
    private Comune comune;
    private CodiceFiscale cf;

    public Persona(){}
  
    /**
     * ritorna l'id della persona
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * imposta l'id della persona
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * ritorna il nome della persona
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * imposta il nome della persona
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * ritorna il cognome della persona
     * @return
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * imposta il cognome della persona
     * @param cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * ritorna il sesso della persona
     * @return
     */
    public char getSesso() {
        return sesso;
    }

    /**
     * imposta il sesso della persona
     * @param sesso
     */
    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    /**
     * ritorna il sesso della persona
     * @return
     */
    public String getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * imposta il sesso della persona
     * @param dataDiNascita
     */
    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    /**
     * ritorna il comune della persona
     * @return
     */
    public Comune getComune() {
        return comune;
    }

    /**
     * imposta il comune della persona
     * @param comune
     */
    public void setComune(Comune comune) {
        this.comune = comune;
    }

    /**
     * ritorna il codice fiscale della persona
     * @return
     */
    public CodiceFiscale getCf() {
        return cf;
    }

    /**
     * imposta il codice fiscale della persona
     * @param cf
     */
    public void setCf(CodiceFiscale cf) {
        this.cf = cf;
    }
}