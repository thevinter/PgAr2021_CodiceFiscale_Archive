package codiceFiscale;

import java.util.ArrayList;

public class CodiceFiscale {
    private String codice;
  
    private final char UOMO='M';
    private final int DIMENSIONE_PARTE_CF=3;
    private final int POSIZIONEVOCALI=0;
    private final int POSIZIONICONSONANTI=1;
	  private final char[] arrayCodiceMese={'A','B','C','D','E','H','L','M','P','R','S','T'};

    public CodiceFiscale(String nome,String cognome, String dataDiNascita, char sesso, String codiceComune) {
        this.codice = "";
        this.codice+=this.generaCognomeCF(cognome);
        this.codice+=this.generaNomeCF(nome);
        this.codice+=this.generaDataDiNascitaCF(dataDiNascita);
        this.codice+=this.generaGiornoESessoCF(dataDiNascita, sesso);
        this.codice+=codiceComune;
        this.codice+=MetodiDiControllo.generaCarattereDiControlloCF(this.codice);
    }

    public CodiceFiscale() {
    }

    /**
     * ritorna il codice fiscale
     * @return
     */
    public String getCodice() {
        return codice;
    }

    /**
     * imposta il codice fiscale
     * @param codice
     */
    public void setCodice(String codice) {
        this.codice = codice;
    }

    /**
     *
     * @param elenco_codici_fiscali
     * @return
     */
    public boolean isPresente (ArrayList<CodiceFiscale> elenco_codici_fiscali){
        for(int i=0; i<elenco_codici_fiscali.size(); i++){
            if (this.getCodice().equals(elenco_codici_fiscali.get(i).getCodice())) {
                elenco_codici_fiscali.remove(i);
                return true;
            }
        }
        return false;
    }


    /**
     * Vengono prese le consonanti del nome (o dei nomi, se ve ne è più di uno) nel loro ordine (primo nome, di seguito il secondo e così via) in questo modo:
     * se il nome contiene quattro o più consonanti, si scelgono la prima, la terza e la quarta (per esempio: Gianfranco → GFR),
     * altrimenti le prime tre in ordine (per esempio: Tiziana → TZN). Se il nome non ha consonanti a sufficienza, si prendono anche le vocali;
     * in ogni caso le vocali vengono riportate dopo le consonanti (per esempio: Luca → LCU).
     * Nel caso in cui il nome abbia meno di tre lettere la parte di codice viene completata aggiungendo la lettera X.
     * @param nome      il nome per intero della persona
     * @return          ritorna le prime 3 lettere del codice fiscale secondo lo standard id wikipedia
     */
    public String generaNomeCF(String nome){
        char[][] m=getMatriceVocaliConsonanti(nome.toUpperCase());
        if(m[POSIZIONICONSONANTI].length==DIMENSIONE_PARTE_CF)return String.valueOf(m[POSIZIONICONSONANTI]);
        else if(m[POSIZIONICONSONANTI].length>DIMENSIONE_PARTE_CF)return String.valueOf(m[POSIZIONICONSONANTI][0])+String.valueOf(m[POSIZIONICONSONANTI],2,2);
        else{
            String r=String.valueOf(m[POSIZIONICONSONANTI])+String.valueOf(m[POSIZIONEVOCALI]);
            if(r.length()<DIMENSIONE_PARTE_CF)r+="XXX";
            return r.substring(0,3);
        }
    }

    /**
     * questo metodo, data una stringa, ritorna un array bidimensionale formato da un array di consonanti e uno di vocali rispetto al nome
     * @param nome      la stringa indica una la parola da suddividere in vocali e consonanti
     * @return          ritorna una matrice formata da un array di vocali (0) e uno di consonanti (1)
     */
    public char[][] getMatriceVocaliConsonanti(String nome){
        String consonantiNome="";
        String vocaliNome="";
        //divido il nome nelle sue rispettive consonanti e vocali
        for(int i=0; i<nome.length();i++){
            if(MetodiDiControllo.isVocale(nome.charAt(i)))vocaliNome+=Character.toUpperCase(nome.charAt(i));
            else consonantiNome+=Character.toUpperCase(nome.charAt(i));
        }
        char[][] m=new char[2][];
        m[POSIZIONEVOCALI]=vocaliNome.toCharArray();
        m[POSIZIONICONSONANTI]=consonantiNome.toCharArray();
        return m;
    }

    /**
     * Vengono prese le consonanti del cognome (o dei cognomi, se ve ne è più di uno) nel loro ordine (primo cognome, di seguito il secondo e così via).
     * Se le consonanti sono insufficienti, si prelevano anche le vocali (se sono sufficienti le consonanti si prelevano la prima, la seconda e la terza consonante),
     * sempre nel loro ordine e, comunque, le vocali vengono riportate dopo le consonanti (per esempio: Rosi → RSO). Nel caso in cui un cognome abbia meno di tre lettere,
     * la parte di codice viene completata aggiungendo la lettera X (per esempio: Fo → FOX). Per le donne, viene preso in considerazione il solo cognome da nubile.
     * @param cognome   stringa che indica il cognome della persona
     * @return          ritorna una stringa di 3 carattero che indica il cognome secondo lo standard
     */
    public String generaCognomeCF(String cognome){
        char[][] m=getMatriceVocaliConsonanti(cognome.toUpperCase());
        if(m[POSIZIONICONSONANTI].length==DIMENSIONE_PARTE_CF) return String.valueOf(m[POSIZIONICONSONANTI]);
        else if(m[POSIZIONICONSONANTI].length>DIMENSIONE_PARTE_CF) return String.valueOf(m[POSIZIONICONSONANTI],0,3);
        else{
            String r=String.valueOf(m[POSIZIONICONSONANTI])+String.valueOf(m[POSIZIONEVOCALI]);
            if(r.length()<DIMENSIONE_PARTE_CF)r+="XXX";
            return r.substring(0,3);
        }
    }

    /**
     * Anno di nascita (due cifre): si prendono le ultime due cifre dell'anno di nascita;
     * Mese di nascita (una lettera): a ogni mese dell'anno viene associata una lettera in base all'array arrayCodiceMese
     * @param data      data per intero (anno-mese-giorno)
     * @return          ritorna una stringa formata da 2 numeri e un carattere presente in arrayCodiceMese
     */
    public String generaDataDiNascitaCF(String data){
        return data.substring(2,4)+String.valueOf(arrayCodiceMese[Integer.parseInt(data.substring(5,7))-1]);
    }

    /**
     * Si prendono le due cifre del giorno di nascita (se è compreso tra 1 e 9 si pone uno zero come prima cifra);
     * per i soggetti di sesso femminile, a tale cifra va sommato il numero 40. In questo modo il campo contiene la doppia informazione giorno di nascita e sesso.
     * Avremo pertanto la seguente casistica: i maschi avranno il giorno con cifra da 01 a 31, mentre per le donne la cifra relativa al giorno sarà da 41 a 71.
     * @param data      data per intero (anno-mese-giorno)
     * @param sesso     un char che indica il sesso di una persona ('M' o 'F')
     * @return          ritorna una stringa formata da 2 cifre
     */
    public String generaGiornoESessoCF(String data, char sesso){
        String d=data.substring(data.length()-2);
        if(Character.toUpperCase(sesso)==UOMO) return d;
        else return String.valueOf(Integer.parseInt(d)+40);
    }   
}