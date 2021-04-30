package it.unibs.pgar.codicefiscale;

import java.util.ArrayList;

/**
 * Una classe apposita per salvare gli attributi fondamentali dei comuni: il nome e il codice
 */
public class Comune {

    private String nome;
    private String codice;

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

    /**
     * Permette di creare un oggetto comune a partire
     * dal nome consultando il file xml comuni per ottenerne il codice
     *
     * @param comuni:     ArrayList Comune
     * @param nomeComune: String
     * @return comuneCercato: Comune
     */
    public static Comune generaComune(ArrayList<Comune> comuni, String nomeComune) {
        String codiceComune = "";

        for (int i = 0; i < comuni.size(); i++) {
            if (nomeComune.equals(comuni.get(i).getNome())) {
                codiceComune = comuni.get(i).getCodice();
                break;
            }
        }

        return new Comune(nomeComune, codiceComune);
    }

}
