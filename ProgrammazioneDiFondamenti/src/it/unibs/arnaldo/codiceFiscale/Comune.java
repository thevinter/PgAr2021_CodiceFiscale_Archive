package it.unibs.arnaldo.codiceFiscale;

/**
 * <p>
 *     La classe <strong>Comune</strong> intende rappresentare un luogo,un comune dello Stato nazionale
 *     Italiano.
 *     Questa classe è stata ideata per essere utilizzata in concomitanza con la classe istanziabile
 *     Persona e volta a rappresentarne il comune di nascita di un singolo individuo.
 * </p>
 *
 *  @author Gruppo Programmazione di Fondamenti
 *  @version 1.0
 *
 * @see Persona
 **/

public class Comune {

    //ATTRIBUTI
    private String nome;
    private String codice;

    //Costruttore
    /**
     * <h5>Descrizione Costruttore Classe:</h5>
     *  <p>
     *  	Creazione di un Comune in base a due parametri Stringa da specificare: nome e codice identificativo.
     * </p>
     *
     * @param nome Una Stringa che rappresenta il nome del Comune
     * @param codice Una Stringa che rappresenta il codice identificativo del Comune da utilizzare per la generazione
     *               di un eventuale codice fiscale di un'istanza di classe {@link Persona}
     * */
    public Comune(String nome, String codice) {
        this.nome = nome;
        this.codice = codice;
    }

    //Getters and Setters

    /**
     * Restituisce il nome del Comune
     * @return Una Stringa che specifica il nome del Comune.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il codice identificativo del Comune
     * @return Una Stringa che specifica il codice identificativo del Comune.
     */
    public String getCodice() {
        return codice;
    }

    //ToString

    /**
     * Restituisce una stringa descrittiva di un'istanza della classe Comune
     * @return Una stringa che specifica il valore dei vari attributi di un'istanza della classe Comune.
     */
    @Override
    public String toString() {
        return "Comune{" +
                "nome='" + nome + '\'' +
                ", codice='" + codice + '\'' +
                '}';
    }

}

