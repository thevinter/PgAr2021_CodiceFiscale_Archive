package it.unibs.arnaldo.codiceFiscale;

import mylib.ControlloDati;

/**
 * <p>
 *     La classe <strong>Persona</strong> intende rappresentare una Persona(uomo o donna) e contiene
 *     i principali dati anagrafici che contraddistinguno un singolo individuo.
 *     Questa classe è stata ideata <strong>perlopiù</strong> per ricavare il codice fiscale di una persona , data una serie
 *     di informazioni necessarie per la sua creazione.
 *     <br>Inoltre Persona, utilizza la classe istanziabile {@link Comune} (di nascita) come informazione necessaria
 *     per la generazione del suo codice fiscale.
 * </p>
 *
 *  @author Gruppo Programmazione di Fondamenti
 *  @version 1.0
 *
 * @see Comune
 **/
public class Persona {

    //ATTRIBUTI
    private String nome;
    private String cognome;
    private char sesso;
    private String data_di_nascita;
    private Comune comune_di_nascita;
    //private char carattere_controllo;
    private String codice_fiscale;

    //costruttore
    /**
     * <h5>Descrizione Costruttore Classe:</h5>
     *  <p>
     *  	Creazione di una Persona in base a cinque parametri da specificare: nome,cognome,sesso,data di nascita e
     *      comune di nascita.
     * 	    <b>N.B.</b>La classe Persona gode di cinque attributi tra cui un attributo di tipo/classe Comune
     * 	    che indica il comune di nascita di tale persona.
     * </p>
     *
     * @param nome Una Stringa che rappresenta il nome della Persona
     * @param cognome Una Stringa che rappresenta il Cognome della Persona
     * @param sesso Una Carattere che rappresenta il Sesso della Persona
     * @param data_di_nascita Una stringa che rappresenta la data di nascita della Persona
     * @param comune_di_nascita Rappresenta un'istanza di classe {@link Comune} indicante il comune di nascita della Persona
     *
     * @see Comune
     * */
    public Persona(String nome, String cognome, char sesso, String data_di_nascita, Comune comune_di_nascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.data_di_nascita = data_di_nascita;
        this.comune_di_nascita = comune_di_nascita;
        //this.carattere_controllo = carattere_controllo;
        //this.codice_fiscale = codice_fiscale;
    }

   /* public Persona(String _nome){
        this.nome=_nome;
    }*/

    /*
    public Persona(char _sesso, String _data_di_nascita){
        this.sesso = _sesso;
        this.data_di_nascita = _data_di_nascita;
    }*/

    //Getters and Setters

    /**
     * Restituisce il nome della Persona
     * @return Una Stringa che specifica il nome della Persona.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome della Persona
     * @param nome Rappresenta il nome della Persona
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome della Persona
     * @return Una Stringa che specifica il cognnome della Persona.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome della Persona
     * @param cognome Rappresenta il cognome della Persona
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce il sesso della Persona
     * @return Un carattere che specifica il sesso della Persona.
     */
    public char getSesso() {
        return sesso;
    }

    /**
     * Imposta il sesso della Persona
     * @param sesso Rappresenta il sesso della Persona
     */
    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    /**
     * Restituisce la data di nascita della Persona
     * @return Una stringa che specifica la data di nascita della Persona.
     */
    public String getData_di_nascita() {
        return data_di_nascita;
    }

    /**
     * Imposta la data di nascita della Persona
     * @param data_di_nascita Rappresenta la data di nascita della Persona
     */
    public void setData_di_nascita(String data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }

    /**
     * Restituisce il comune di nascita della Persona
     * @return Un {@link Comune} che specifica il comune di nascita della Persona.
     */
    public Comune getComune_di_nascita() {
        return comune_di_nascita;
    }

    /**
     * Imposta il comune di nascita della Persona
     * @param comune_di_nascita Rappresenta il comune di nascita della Persona
     *
     * @see Comune
     */
    public void setComune_di_nascita(Comune comune_di_nascita) {
        this.comune_di_nascita = comune_di_nascita;
    }

   /* public char getCarattere_controllo() {
        return carattere_controllo;
    }

    public void setCarattere_controllo(char carattere_controllo) {
        this.carattere_controllo = carattere_controllo;
    }*/

    /*
    public String getCodice_fiscale() {
        return codice_fiscale;
    }

    public void setCodice_fiscale(String codice_fiscale) {
        this.codice_fiscale = codice_fiscale;
    }*/

    /**
     * Calcola il codice fiscale di un'istanza di classe Persona sulla base di una serie di dati anagrafici.
     *
     * @return Una Stringa maiuscola rappresentante il codice fiscale di un'istanza di classe Persona
     * */
    public String calcolaCodiceFiscale(){
        String codice = "";
        codice += calcolaLettereCognome();
        codice += calcolaLettereNome();
        codice += calcolaCaratteriNascita();
        codice += calcolaCaratteriLuogo();
        codice += calcolaCarattereControllo(codice);
        return codice;
    }

    /*
    * Metodo privato utilizzato in concomitanza con calcolaCodiceFiscale().
    * Il suo scopo è quello di determinare le tre lettere rapppresentanti il cognome all'interno del codice
    * fiscale di un'istanza di classe Persona.
    * Ritorna una stringa di tre caratteri
    * */
    private String calcolaLettereCognome(){

        //rendo maiuscole le lettere del cognome
        String cognome_maiuscolo = this.cognome.toUpperCase();

        //creo un array di caratteri contenente le lettere del cognome
        char [] cognome = cognome_maiuscolo.toCharArray();

        //creo un array di caratteri, con le lettere che rappresenteranno il cognome
        char [] lettere_cognome = new char[Costanti.DIM_COGNOME_NOME];

        //inizializzo l'array di caratteri con le lettere di default
        lettere_cognome[0] = 'X';
        lettere_cognome[1] = 'X';
        lettere_cognome[2] = 'X';

        //creo un contatore che conta le lettere adatte per rappresentare il cognome
        int numero_lettere = 0;

        //in questo primo ciclo cerco di riempire le 3 lettere con delle consonanti
        for (int i=0; i<cognome.length && numero_lettere != 3; i++){
            if(ControlloDati.seConsonante(cognome[i])){
                lettere_cognome[numero_lettere] = cognome[i];
                numero_lettere++;
            }
        }
        //se le consonanti sono meno di 3, passo alle vocali
        for(int i=0; i<cognome.length && numero_lettere != 3; i++){
            if(ControlloDati.seVocale(cognome[i])){
                lettere_cognome[numero_lettere] = cognome[i];
                numero_lettere++;
            }
        }
        //infine creo la stringa finale da passare
        String tre_lettere = "";
        //itero il ciclo 3 volte
        for (int i=0; i<Costanti.DIM_COGNOME_NOME; i++)
            //accodo alla stringa le lettere
            tre_lettere += lettere_cognome[i];
        return tre_lettere;
    }

    /*
     * Metodo privato utilizzato in concomitanza con calcolaCodiceFiscale().
     * Il suo scopo è quello di determinare le tre lettere rapppresentanti il nome all'interno del codice
     * fiscale di un'istanza di classe Persona.
     * Ritorna una stringa di tre caratteri
     * */
    private String calcolaLettereNome(){

        //Stringa che conterrà le 3 lettere costituenti il nome all'interno del codice fiscale
        String lettere_nome="";

        //Stringa di supporto-->indica quali consonanti sono presenti nel nome
        String consonanti_trovate="";
        int num_consonanti=0;

        //Conversione in maiuscolo del nome dellan Persona. Volendo si potrebbe riaggiornare direttamente il nome della persona (l'attributo) o qui o nel costruttore direttamente
        String nome_maiuscolo=this.nome.toUpperCase();

        //conto quante consonanti sono presenti nel nome
        for(int i=0;i<nome_maiuscolo.length();i++){
            if(ControlloDati.seConsonante(nome_maiuscolo.charAt(i))){//se è una consonante
                consonanti_trovate=consonanti_trovate.concat(""+nome_maiuscolo.charAt(i));
                num_consonanti+=1;//num_consonanti++;
            }
        }

        //se il numero di consonanti è maggiore o uguale a 4 allora si prende la prima ,la terza e la quarta consonante
        if(num_consonanti>=4){
           lettere_nome=lettere_nome.concat(""+ consonanti_trovate.charAt(0)+ consonanti_trovate.charAt(2)+ consonanti_trovate.charAt(3));
        }

        //se il numero di consonanti è esattamente 3 si prendono le prime tre consonanti in ordine
        if(num_consonanti==3){
            //lettere_nome=lettere_nome.concat(""+this.nome.charAt(0)+this.nome.charAt(1)+this.nome.charAt(2));
            lettere_nome= consonanti_trovate;
        }

        //se il numero di consonanti è minore di 3 ,di quello previsto, si contano anche le vocali in ordine
        if(num_consonanti<3){
           int num_vocali_necessarie=3-num_consonanti;

           lettere_nome= consonanti_trovate;

           //in base a quante vocali sono necessarie per arrivare a 3 lettere estrapolo le prime vocali presenti nel nome,sempre in ordine
           //N.B.-->j è un contatore usato per scandire nome_maiuscolo; volte --> contatore che viene aggiornato se trovata una vocale
            for(int volte=0,j=0;volte<num_vocali_necessarie && j<nome_maiuscolo.length();j++) {//j+=1
                if(ControlloDati.seVocale(nome_maiuscolo.charAt(j))){
                    lettere_nome=lettere_nome.concat(""+nome_maiuscolo.charAt(j));
                    volte+=1;//volte++
                }
           }
        }

        //se invece il nome della persona ha meno di tre caratteri si aggiungono delle X a completamento dei caratteri costituenti il nome
        if(nome_maiuscolo.length()<3){
            int numero_x_necessarie=3-nome_maiuscolo.length();

            lettere_nome=nome_maiuscolo;

            for(int t=0;t<numero_x_necessarie;t++){
                lettere_nome=lettere_nome.concat("X");
            }
        }
        return lettere_nome;
    }

    /*
     * Metodo privato utilizzato in concomitanza con calcolaCodiceFiscale().
     * Il suo scopo è quello di determinare i 5 caratteri rapppresentanti la data di nascita all'interno del codice
     * fiscale di un'istanza di classe Persona(di un individuo) tenendo in considerazione il sesso dell'individuo.
     * Ritorna una stringa di cinque caratteri.
     */

    private String calcolaCaratteriNascita(){
        String data_di_nascita = "";        //creo la stringa che il metodo ritorna e che concatena al codice fiscale
        char sesso = this.sesso;

        //la stringa data_di_nascita è nel formato "YYYY-MM-DD"
        //col metodo substring riesco a ricavare le sottostringhe che mi servono in base alla posizione dei caratteri

        String anno_di_nascita = this.data_di_nascita.substring(2,4);   //Prende le ultime due cifre dell'anno come stringa
        String mese_di_nascita = this.data_di_nascita.substring(5,7);   //Prende i due caratteri riferiti al mese come stringa(considera già l'eventuale 0)
        String giorno_di_nascita = this.data_di_nascita.substring(8,10);    //Prende i due caratteri riferiti al giorno come stringa(considera già l'eventuale 0)

        data_di_nascita += anno_di_nascita; //aggiungo le cifre dell'anno che vanno già bene così

        int mese = Integer.parseInt(mese_di_nascita); //trasforma la stringa corrispondente al mese in un intero che va da 1 a 12

        /*
        Aggiunta della lettera corretta corrispondente al mese di nascita
         */
        switch (mese) {
            case 1: data_di_nascita += "A";   //Gennaio
                    break;
            case 2: data_di_nascita += "B";   //Febbraio 28
                    break;
            case 3: data_di_nascita += "C";   //Marzo
                    break;
            case 4: data_di_nascita += "D";   //Aprile 30
                    break;
            case 5: data_di_nascita += "E";   //Maggio
                    break;
            case 6: data_di_nascita += "H";   //Giugno 30
                    break;
            case 7: data_di_nascita += "L";   //Luglio
                    break;
            case 8: data_di_nascita += "M";   //Agosto
                    break;
            case 9: data_di_nascita += "P";   //Settembre 30
                    break;
            case 10: data_di_nascita += "R";  //Ottobre
                    break;
            case 11: data_di_nascita += "S";  //Novembre 30
                    break;
            case 12: data_di_nascita += "T";  //Dicembre
                    break;
        }

        int giorno = Integer.parseInt(giorno_di_nascita); //trasforma la stringa corrispondente al giorno in un intero che va da 1 a 31

        if (sesso == 'M') {
            if (giorno <= 9) data_di_nascita += "0";   //se minore di 10 aggiunge uno zero
            data_di_nascita += giorno;
        }
        else if (sesso == 'F'){
            giorno += Costanti.NUMERO_DONNA;    //se si tratta di una donna allora si aggiunge 40 al giorno (non c'è dunque bisogno di fare il controllo se il numero è ad una singola cifra
            data_di_nascita += giorno;
        }

        return data_di_nascita;
    }

    /*
     * Metodo privato utilizzato in concomitanza con calcolaCodiceFiscale().
     * Il suo scopo è quello di determinare i 4 caratteri rapppresentantiil luogo di nascita all'interno del codice
     * fiscale di un'istanza di classe Persona(di un individuo).
     * Ritorna una stringa di quattro caratteri.
     */
    private String calcolaCaratteriLuogo(){
        return this.comune_di_nascita.getCodice();
    }

    /*
     * Metodo privato utilizzato in concomitanza con calcolaCodiceFiscale().
     * Il suo scopo è quello di determinare il carattere di controllo presente all'interno di ogni codice
     * fiscale di un'istanza di classe Persona(di un individuo) come ultimo carattere/lettera.
     * Ritorna una stringa di un carattere./un char
     */
    public char calcolaCarattereControllo(String codice_fiscale_senza_controllo){
        char [] codice_15_caratteri = codice_fiscale_senza_controllo.toCharArray();
        int resto, somma=0;
        for (int i=0; i<Costanti.DIM_CODICE_FISCALE-1; i++){
            if(i%2 == 0){
                somma += valoreControlloPari(codice_15_caratteri[i]);
            }else if(i%2 != 0){
                somma += valoreControlloDispari(codice_15_caratteri[i]);
            }
        }
        resto = somma%26;
        char carattere_controllo = valoreCarattereControllo(resto);
        return carattere_controllo;
    }

    /**
     * Confronata il codice fiscale di un'istanza di classe Persona sulla base di un dato codice fiscale.
     *
     * @param codice_da_comparare Una Stringa rappresentante un codice fiscale da confrontare con quello dell'istanza di classe Persona
     * @return Un boolean rappresentante l'esito del confronto: <strong>true</strong> se i codici fiscali confrontati
     *          sono identici, <strong>false</strong> altrimenti.
     * */
    public boolean confrontaCodiciFiscali(String codice_da_comparare){
        return this.calcolaCodiceFiscale().equals(codice_da_comparare);
    }

    /*
     * Metodo privato utilizzato in concomitanza con calcolaCarattereControllo().
     * Il suo scopo è quello di determinare l'intero corrispondente ad un determinato char in posizione
     * pari all'interno del "futuro" codice fiscale secondo una tabulazione fissata dal Ministero delle
     * Finanze Italiano.
     * Ritorna un intero necessario per la determinazione del carattere di controllo.
     */
    private int valoreControlloPari(char carattere){

        if(Character.isDigit(carattere)){
            return (int)carattere;
        }

        switch (carattere){

            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'J':
                return 9;
            case 'K':
                return 10;
            case 'L':
                return 11;
            case 'M':
                return 12;
            case 'N':
                return 13;
            case 'O':
                return 14;
            case 'P':
                return 15;
            case 'Q':
                return 16;
            case 'R':
                return 17;
            case 'S':
                return 18;
            case 'T':
                return 19;
            case 'U':
                return 20;
            case 'V':
                return 21;
            case 'W':
                return 22;
            case 'X':
                return 23;
            case 'Y':
                return 24;
            case 'Z':
                return 25;
            default: return -1;
        }
    }

    /*
     * Metodo privato utilizzato in concomitanza con calcolaCarattereControllo().
     * Il suo scopo è quello di determinare l'intero corrispondente ad un determinato char in posizione
     * dispari all'interno del "futuro" codice fiscale secondo una tabulazione fissata dal Ministero delle
     * Finanze Italiano.
     * Ritorna un intero necessario per la determinazione del carattere di controllo.
     */
    private int valoreControlloDispari(char carattere){
        switch (carattere){
            case 'A':
            case '0':
                return 1;
            case 'B':
            case '1':
                return 0;
            case 'C':
            case '2':
                return 5;
            case 'D':
            case '3':
                return 7;
            case 'E':
            case '4':
                return 9;
            case 'F':
            case '5':
                return 13;
            case 'G':
            case '6':
                return 15;
            case 'H':
            case '7':
                return 17;
            case 'I':
            case'8':
                return 19;
            case 'J':
            case '9':
                return 21;
            case 'K':
                return 2;
            case 'L':
                return 4;
            case 'M':
                return 18;
            case 'N':
                return 20;
            case 'O':
                return 11;
            case 'P':
                return 3;
            case 'Q':
                return 6;
            case 'R':
                return 8;
            case 'S':
                return 12;
            case 'T':
                return 14;
            case 'U':
                return 16;
            case 'V':
                return 10;
            case 'W':
                return 22;
            case 'X':
                return 25;
            case 'Y':
                return 24;
            case 'Z':
                return 23;
            default: return -1;
        }
    }

    /*
     * Metodo privato utilizzato in concomitanza con calcolaCarattereControllo().
     * Il suo scopo è quello di determinare il carattere corrispondente ad un determinato resto
     * secondo una tabulazione fissata dal Ministero delle Finanze Italiano.
     * Il suo utilizzo è logicamente corretto se posticipato all'uso di valoreControlloPari e valoreControlloDispari:
     * il resto della divisione ottenuto in calcolaCarattereControllo,fornirà il codice identificativo, ottenuto da
     * una tabella di conversione stabilita.
     * Ritorna un char necessario che determina il carattere di controllo.
     */
    private char valoreCarattereControllo(int resto){
        switch (resto){
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';
            case 4:
                return 'E';
            case 5:
                return 'F';
            case 6:
                return 'G';
            case 7:
                return 'H';
            case 8:
                return 'I';
            case 9:
                return 'J';
            case 10:
                return 'K';
            case 11:
                return 'L';
            case 12:
                return 'M';
            case 13:
                return 'N';
            case 14:
                return 'O';
            case 15:
                return 'P';
            case 16:
                return 'Q';
            case 17:
                return 'R';
            case 18:
                return 'S';
            case 19:
                return 'T';
            case 20:
                return 'U';
            case 21:
                return 'V';
            case 22:
                return 'W';
            case 23:
                return 'X';
            case 24:
                return 'Y';
            case 25:
                return 'Z';
            default: return '0';
        }
    }

    /**
     * Restituisce una stringa descrittiva di un'istanza della classe Persona
     * @return Una stringa che specifica il valore dei vari attributi di un'istanza della classe Persona.
     */
    @Override
    public String toString() {
        return "Persona{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", sesso=" + sesso +
                ", data_di_nascita=" + data_di_nascita +
                ", comune_di_nascita=" + comune_di_nascita +
                //", carattere_controllo=" + carattere_controllo +
                //", codice_fiscale='" + codice_fiscale + '\'' +
                ", codice_fiscale='" + this.calcolaCodiceFiscale()+'\''+
                '}';

    }


}

