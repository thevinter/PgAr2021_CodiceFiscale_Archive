package it.unibs.pga.CodiceFiscale;

import javax.xml.stream.XMLStreamException;

public class GeneratoreCodiceFiscale {

    /**
     * Richiamando più medoti andiamo a creare una stringa che rappresenta il codice fiscale
     * reale delle persone che ci vengono passate nel file di input
     *
     * @param id della persona che stiamo prendendo in considerazione per creare il suo codice
     * @return codice fiscale reale della persona
     * @throws XMLStreamException
     */
    public String generatore(int id) throws XMLStreamException {

        String id_string= String.valueOf(id);
        String cognome= prendi_cognome(id_string);

        String nome = prendi_nome(id_string);
        String data_nasctita = prendiDataNascita(id_string);
        String codice_comune = prendiComune(id_string);
        //la stringa che segue ci serve per calcolare la lettera di controllo
        String stringa_preliminare = cognome + nome + data_nasctita + codice_comune;
        String carattere= letteraDiControllo(stringa_preliminare);
        //codice finale completo
        String cog_nome = cognome + nome + data_nasctita + codice_comune + carattere;

        return cog_nome;
    }

    /**
     * Da input leggiamo il cognome della persona e torniamo una stringa di tre caratteri che rappresenteranno la prima parte
     * del codice fiscale
     *
     * @param id
     * @return stringa codice cognome
     * @throws XMLStreamException
     */
    public String prendi_cognome(String id) throws XMLStreamException {

        Gestione gs= new Gestione();
        InterazioneXML interagisci = new InterazioneXML();

        String cognome = interagisci.leggiDatoXML(id, Costanti.TAG_COGNOME); //lettura da file xml
        String cognomeCF= "";

        /*
        segue il procedimento per la creazione del cognome per il codice fiscale
        (preso da wikipedia)
         */
        for(int i = 0; i < cognome.length() && cognomeCF.length() < 3; i++){
            if (gs.controlloConsonanti(cognome.charAt(i))){
                cognomeCF+=cognome.charAt(i);
            }
        }

        for (int j = 0; j < cognome.length(); j++){
            if (cognomeCF.length()<3 && gs.controlloVocali(cognome.charAt(j))){
                cognomeCF+= cognome.charAt(j);
            }
        }
        int cdf_ln = cognomeCF.length();
        for (int k=0; k< 3-cdf_ln; k++){
            cognomeCF+= "X";
        }

        return cognomeCF;
    }

    /**
     * Da input leggiamo il nome della persona e torniamo una stringa di tre caratteri che rappresenteranno la seconda parte
     * del codice fiscale
     *
     * @param id
     * @return stringa codice nome
     * @throws XMLStreamException
     */
    public String prendi_nome(String id) throws XMLStreamException {

        Gestione gs= new Gestione();
        InterazioneXML interagisci = new InterazioneXML();

        String nome = interagisci.leggiDatoXML(id, Costanti.TAG_NOME);
        String nomeCF= "";

        /*
        contiamo quante consonati ci sono nel nome. se ce ne sono 4 o più prendiamo la prima terza e quarta consonate
        altrimenti se ce ne sono tre prendiamo le tre in ordine
        se ci sono di meno si passa alle vocali oppure con l'aggiunta delle X
         */
        int conta_consonanti = 0;
        for (int i = 0; i < nome.length(); i++){
            if (gs.controlloConsonanti(nome.charAt(i))){
                conta_consonanti++;
            }
        }

        /*
        segue il procedimento per la creazione del cognome per il codice fiscale
        (preso da wikipedia)
         */
        if (conta_consonanti >= 4){
            int conto = 0;
            for (int k = 0; k < nome.length() && nomeCF.length() < 3; k++){
                if (gs.controlloConsonanti(nome.charAt(k))){
                    if (conto == 1){
                        conto++;
                        continue;
                    }
                    nomeCF += nome.charAt(k);
                    conto++;
                }
            }
        }else {
            for (int i = 0; i < nome.length() && nomeCF.length() < 3; i++) {
                if (gs.controlloConsonanti(nome.charAt(i))) {
                    nomeCF += nome.charAt(i);
                }
            }
        }

        for (int j=0; j< nome.length(); j++){
            if (nomeCF.length()<3 && gs.controlloVocali(nome.charAt(j))){
                nomeCF+= nome.charAt(j);
            }
        }
        int cdf_ln =nomeCF.length();
        for (int k=0; k< 3-cdf_ln; k++){
            nomeCF+= "X";
        }

        return nomeCF;
    }


    /**
     * mi legge dal file xml la data di nascita e mi torna il pezzo di codice corrispondente
     *
     * @param id
     * @return
     * @throws XMLStreamException
     */
    public String prendiDataNascita(String id) throws XMLStreamException{

        Gestione gs = new Gestione();
        InterazioneXML interagisci = new InterazioneXML();

        String data = interagisci.leggiDatoXML(id, Costanti.TAG_DATA_DI_NASCITA);
        String sesso = interagisci.leggiDatoXML(id, Costanti.TAG_SESSO);
        String codice_nascita = "";
        String anno = String.valueOf(data.charAt(2)) + String.valueOf(data.charAt(3));
        String mese = String.valueOf(data.charAt(5)) + String.valueOf(data.charAt(6));
        Integer ms = Integer.valueOf(mese);
        char codice_mese = gs.conversioneMese(ms);
        String giorno = giorno(sesso, data);
        codice_nascita = anno + codice_mese + giorno;

        return codice_nascita;
    }

    /**
     * Questo metodo serve per modificare la data.
     * In caso si tratti di una femmina verrà aggiunto 40 al numero.
     * Tramite l'utilizzo del metodo split delle stringhe andrà a prendere il giorno, andando in seguito a manipolarlo
     * trasformandolo prima in un intero poi di nuovo in una stringa, restituendola.
     * @param sesso
     * @param data
     * @return
     */
    public String giorno(String sesso, String data){

        String[] parti = data.split("-");
        String data_giorno = parti[2];
        int risultato_intero = Integer.parseInt(data_giorno);

        if(sesso.equals("F")) {
            risultato_intero = risultato_intero + 40;
        }
        String risultato = String.valueOf(risultato_intero);

        if (risultato.length()== 1){
            risultato= "0"+ risultato;
        }
        return risultato;
    }


    /**
     * Metodo che da input prende il conume di nascita e andando a far scorrere il file dei comuni ritorna il
     * codice corrispondente
     *
     * @param id
     * @return
     * @throws XMLStreamException
     */
    public String prendiComune(String id) throws XMLStreamException{

        InterazioneXML interagisci = new InterazioneXML();

        String comune_di_nascita = interagisci.leggiDatoXML(id, Costanti.TAG_COMUNE_DI_NASCITA);
        String codice_comune = interagisci.leggiComuneXML(comune_di_nascita);

        return codice_comune;
    }


    /**
     * algoritmo per la generazione della lettera di controllo
     * (procedimento preso da wikipedia)
     *
     * @param codice_fiscale
     * @return
     */
    public String letteraDiControllo (String codice_fiscale){

        Gestione gs = new Gestione();
        int valore = 0;

        for(int i = 0; i < 15; i = i + 2){
            valore += gs.tabellaDispari(codice_fiscale.charAt(i));
        }

        for(int i = 1; i < 15; i = i + 2){
            valore += gs.tabellaPari(codice_fiscale.charAt(i));
        }

        valore = valore % 26;
        char carattere_controllo = gs.tabellaConversione(valore);
        String caratterino_finale = String.valueOf(carattere_controllo);

        return caratterino_finale;
    }



}