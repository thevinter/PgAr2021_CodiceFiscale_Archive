package gli_sprogrammatori.codicefiscale;

/**
 * <p>
 * La classe <strong>Persona</strong> mi permette di rappresentare una persona
 * generica
 * </p>
 * 
 * @author Alessandro Muscio, Tommaso Bianchin, Gianmarco Gamo
 * @version 1.0
 */
public class Persona {
  /**
   * Indica l'<strong>id</strong> che viene assegnato alla <em>persona</em>
   */
  private final int ID;
  /**
   * Indica il <strong>nome</strong> che viene assegnato alla <em>persona</em>
   */
  private String nome;
  /**
   * Indica il <strong>cognome</strong> che viene assegnato alla <em>persona</em>
   */
  private String cognome;
  /**
   * Indica il <strong>sesso</strong> che viene assegnato alla <em>persona</em>
   */
  private char sesso;
  /**
   * Indica il <strong>comune di nascita</strong> che viene assegnato alla
   * <em>persona</em>
   */
  private String comune_nascita;
  /**
   * Indica la <strong>data di nascita</strong> che viene assegnato alla
   * <em>persona</em>
   */
  private String data_nascita;
  /**
   * Indica il <strong>codice fiscale</strong> che viene assegnato alla
   * <em>persona</em>
   */
  private String codice_fiscale;

  /**
   * Crea un oggetto della classe <strong>Persona</strong> specificando
   * <em>id</em>, <em>nome</em>, <em>cognome</em>, <em>sesso</em>, <em>comune di
   * nascita</em> e <em>data di nascita</em>. Verrà poi generato il codice fiscale
   * grazie a questi dati
   * 
   * @param ID             Indica l'id della <em>persona</em>
   * @param nome           Indica il nome della <em>persona</em>
   * @param cognome        Indica il cognome della <em>persona</em>
   * @param sesso          Indica il sesso della <em>persona</em>
   * @param comune_nascita Indica il comune di nascita della <em>persona</em>
   * @param data_nascita   Indica la data di nascita della <em>persona</em>
   */
  public Persona(int ID, String nome, String cognome, char sesso, String comune_nascita, String data_nascita) {
    this.ID = ID;
    this.nome = nome;
    this.cognome = cognome;
    this.sesso = sesso;
    this.comune_nascita = comune_nascita;
    this.data_nascita = data_nascita;
    generaCodiceFiscale();
  }

  /**
   * Restituisce l'<strong>id</strong> della <em>persona</em>
   * 
   * @return Un <code>int</code> rappresentante l'id
   */
  public int getID() {
    return ID;
  }

  /**
   * Restituisce il <strong>nome</strong> della <em>persona</em>
   * 
   * @return Una <code>String</code> rappresentante il nome
   */
  public String getNome() {
    return nome;
  }

  /**
   * Restituisce il <strong>cognome</strong> della <em>persona</em>
   * 
   * @return Una <code>String</code> rappresentante il cognome
   */
  public String getCognome() {
    return cognome;
  }

  /**
   * Restituisce il <strong>sesso</strong> della <em>persona</em>
   * 
   * @return Un <code>char</code> rappresentante il sesso
   */
  public char getSesso() {
    return sesso;
  }

  /**
   * Restituisce il <strong>nome</strong> della <em>persona</em>
   * 
   * @return Una <code>String</code> rappresentante il nome
   */
  public String getComuneNascita() {
    return comune_nascita;
  }

  /**
   * Restituisce la <strong>data di nascita</strong> della <em>persona</em>
   * 
   * @return Una <code>String</code> rappresentante la data di nascita
   */
  public String getDataNascita() {
    return data_nascita;
  }

  /**
   * Restituisce il <strong>codice fiscale</strong> della <em>persona</em>
   * 
   * @return Una <code>String</code> rappresentante il codice fiscale
   */
  public String getCodiceFiscale() {
    return codice_fiscale;
  }

  /**
   * Imposta il <strong>nome</strong> della <em>persona</em>
   * 
   * @param nome Valore da assegnare al <strong>nome</strong>
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Imposta il <strong>cognome</strong> della <em>persona</em>
   * 
   * @param cognome Valore da assegnare al <strong>cognome</strong>
   */
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Imposta il <strong>sesso</strong> della <em>persona</em>
   * 
   * @param sesso Valore da assegnare al <strong>sesso</strong>
   */
  public void setSesso(char sesso) {
    this.sesso = sesso;
  }

  /**
   * Imposta il <strong>comune di nascita</strong> della <em>persona</em>
   * 
   * @param comune_nascita Valore da assegnare al <strong>comune di
   *                       nascita</strong>
   */
  public void setComuneNascita(String comune_nascita) {
    this.comune_nascita = comune_nascita;
  }

  /**
   * Imposta la <strong>data di nascita</strong> della <em>persona</em>
   * 
   * @param data_nascita Valore da assegnare alla <strong>data di nascita</strong>
   */
  public void setDataNascita(String data_nascita) {
    this.data_nascita = data_nascita;
  }

  /**
   * Imposta il <strong>codice fiscale</strong> della <em>persona</em>
   * 
   * @param codice_fiscale Valore da assegnare al <strong>codice fiscale</strong>
   */
  public void setCodiceFiscale(String codice_fiscale) {
    this.codice_fiscale = codice_fiscale;
  }

  /**
   * Grazie agli attributi settati dal costruttore genera il codice fiscale
   */
  public void generaCodiceFiscale() {
    String codice_generato = "";

    codice_generato += GestioneCF.codificaCognomeNome(cognome, true);
    codice_generato += GestioneCF.codificaCognomeNome(nome, false);
    codice_generato += GestioneCF.codificaDataNascitaEsesso(data_nascita, sesso);
    codice_generato += GestioneCF.getCodiceComune(comune_nascita);
    codice_generato += GestioneCF.generazioneCarattereControllo(codice_generato);

    codice_fiscale = codice_generato;
  }

  /**
   * Restituisce una rappresentazione leggibile di un oggetto della classe
   * <strong>Persona</strong>
   * 
   * @return Una <code>String</code> che rappresenta una <strong>persona</strong>
   */
  @Override
  public String toString() {
    return "Persona [ID=" + ID + ", nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", comune_nascita="
        + comune_nascita + ", data_nascita=" + data_nascita + ", cf=" + codice_fiscale + "]";
  }
}
