package it.unibs.Pa.CodiceFiscale;

public class ADriverPersona {

    private String nome ;
    private String cognome ;
    private String eta;


    public ADriverPersona(String nome, String cognome, String eta) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEta() {
        return eta;
    }
}
