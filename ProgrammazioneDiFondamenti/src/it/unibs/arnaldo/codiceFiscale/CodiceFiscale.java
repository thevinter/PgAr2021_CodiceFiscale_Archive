package it.unibs.arnaldo.codiceFiscale;

import mylib.ControlloDati;

public class CodiceFiscale {

    private String cognome;
    private String nome;
    private String anno_di_nascita;
    private char mese_di_nascita;
    private String giorno_di_nascita;
    private String luogo_di_nascita;
    private char controllo;

//GETTERS AND SETTERS

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnno_di_nascita() {
        return anno_di_nascita;
    }

    public void setAnno_di_nascita(String anno_di_nascita) {
        this.anno_di_nascita = anno_di_nascita;
    }

    public char getMese_di_nascita() {
        return mese_di_nascita;
    }

    public void setMese_di_nascita(char mese_di_nascita) {
        this.mese_di_nascita = mese_di_nascita;
    }

    public String getGiorno_di_nascita() {
        return giorno_di_nascita;
    }

    public void setGiorno_di_nascita(String giorno_di_nascita) {
        this.giorno_di_nascita = giorno_di_nascita;
    }

    public String getLuogo_di_nascita() {
        return luogo_di_nascita;
    }

    public void setLuogo_di_nascita(String luogo_di_nascita) {
        this.luogo_di_nascita = luogo_di_nascita;
    }

    public char getControllo() {
        return controllo;
    }

    public void setControllo(char controllo) {
        this.controllo = controllo;
    }

    //COSTRUTTORE
    public CodiceFiscale(String cognome, String nome, String anno_di_nascita, char mese_di_nascita, String giorno_di_nascita, String luogo_di_nascita, char controllo) {
        this.cognome = cognome;
        this.nome = nome;
        this.anno_di_nascita = anno_di_nascita;
        this.mese_di_nascita = mese_di_nascita;
        this.giorno_di_nascita = giorno_di_nascita;
        this.luogo_di_nascita = luogo_di_nascita;
        this.controllo = controllo;
    }

    public boolean validaCodiceFiscale(){
        return (controllaNomeCognome() && controllaAnnoDiNascita() && controllaMeseDiNascita() &&
                controllaGiornoDiNascita() && controllaLuogoDiNascita() && controllaControllo() );
    }

    private boolean controllaNomeCognome(){
        char[] lettere_nome = nome.toCharArray();
        if( (ControlloDati.seVocale(lettere_nome[0]) || ControlloDati.seConsonante(lettere_nome[0])) &&
                (ControlloDati.seVocale(lettere_nome[1]) || ControlloDati.seConsonante(lettere_nome[1])) &&
                (ControlloDati.seVocale(lettere_nome[2]) || ControlloDati.seConsonante(lettere_nome[2])) &&
                (ControlloDati.seVocale(lettere_nome[3]) || ControlloDati.seConsonante(lettere_nome[3]))){
            return true;
        }else
            return false;
    }

    private boolean controllaMeseDiNascita(){
        return mese_di_nascita == 'A' || mese_di_nascita == 'B' || mese_di_nascita == 'C' ||
               mese_di_nascita == 'D' || mese_di_nascita == 'E' || mese_di_nascita == 'H' ||
               mese_di_nascita == 'L' || mese_di_nascita == 'P' || mese_di_nascita == 'M' ||
               mese_di_nascita == 'R' || mese_di_nascita == 'S' || mese_di_nascita == 'T';
    }

    private boolean controllaAnnoDiNascita(){
        char [] carattere_anno = this.anno_di_nascita.toCharArray();
        return (ControlloDati.seNumero(carattere_anno[0]) && ControlloDati.seNumero(carattere_anno[1]));
    }

    private boolean controllaGiornoDiNascita(){
        char [] carattere_giorno = this.giorno_di_nascita.toCharArray();
        //boolean procedere = false;
        if (!ControlloDati.seNumero(carattere_giorno[0]) || !ControlloDati.seNumero(carattere_giorno[1])){
            return false;
        }
        else {
            int numero = Integer.parseInt(giorno_di_nascita);
            if( numero<1 || (numero > 31 && numero <41) || numero>71){
                return false;
            }
            else {
                //30 giorni a Novembre, con April, Giugno e Settembre...
                if((mese_di_nascita == 'D' || mese_di_nascita == 'H' || mese_di_nascita == 'P' || mese_di_nascita == 'S') && numero > 30) return false;
                //...di 28 ce n'è uno...
                else if(mese_di_nascita == 'B' && numero > 28) return false;
            }
        }
        return true;
    }


    /**
     * Controlla se il primo carattere della stringa è una lettera (un non numero) e se i 3 caratteri successivi sono numeri
     * @return
     */
    private boolean controllaLuogoDiNascita(){
        char [] caratteri_luogo = this.luogo_di_nascita.toCharArray(); //questo è un vettore lungo 4 che contiene 1 char e un 3 char che corrispondono a interi
        return (!ControlloDati.seNumero(caratteri_luogo[0]) && ControlloDati.seNumero(caratteri_luogo[1]) &&
                ControlloDati.seNumero(caratteri_luogo[2]) && ControlloDati.seNumero(caratteri_luogo[3]));

    }

    private boolean controllaControllo(){
        //creo una stringa senza l'ultimo carattere del controllo
        String stringa_senza_controllo = cognome + nome + anno_di_nascita + mese_di_nascita + giorno_di_nascita + luogo_di_nascita;
        //converto la stringa in un arrAay di caratteri
        char[] caratteri_senza_controllo = stringa_senza_controllo.toCharArray();
        //ora calcolo il carattere di controllo, poi lo comparerò con quello corrente per vedere se combaciano, in caso sarà valido
        char carattere_controllo ;
        return true;//DA TOGLIERE IL RETURN TRUE
    }

    //TOSTRING
    @Override
    public String toString() {
        return "CodiceFiscale{" +
                "cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                ", anno_di_nascita=" + anno_di_nascita +
                ", mese_di_nascita=" + mese_di_nascita +
                ", giorno_di_nascita=" + giorno_di_nascita +
                ", luogo_di_nascita='" + luogo_di_nascita + '\'' +
                ", controllo=" + controllo +
                '}';
    }


}
