import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

public class GestionePersone {

    private final static String NOME_FILE_PERSONE = "Codice_Fiscale/file/inputPersone.xml";
    private final static String NOME_FILE_CODICI = "Codice_Fiscale/file/codiciFiscali.xml";
    private final static String ERRORE_READER = "Errore nell'inizializzazione del reader:";
    private final static String NOME_FILE_OUTPUT = "Codice_Fiscale/file/fileGenerato.xml";
    private final static String ERRORE_WRITER = "Errore nell'inizializzazione del writer:";
    private final static String ERRORE_SCRITTURA_FILE = "Errore nella scrittura del file";

    private ArrayList<Persona> persone;
    private ArrayList<String> codiciCorretti;
    private ArrayList<String> codiciInvalidi;
    private ArrayList<String> codiciSpaiati;

    private Persona nuovaPersona;

    private int numeroPersone, numeroCodici, id;
    private String nome, cognome, comuneNascita, dataNascita;
    private char sesso;


    /**
     *
     * Metodo costruttore.<br>
     * Inizializza le seguenti ArrayList: persone, codiciCorretti, codiciInvalidi, codiciSpaiati.
     *
     */
    public GestionePersone() {
        this.persone = new ArrayList<Persona>();
        this.codiciCorretti = new ArrayList<String>();
        this.codiciInvalidi = new ArrayList<String>();
        this.codiciSpaiati = new ArrayList<String>();
    }

    public int getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(int numeroPersone) {
        this.numeroPersone = numeroPersone;
    }

    public int getNumeroCodici() {
        return numeroCodici;
    }

    public void setNumeroCodici(int numeroCodici) {
        this.numeroCodici = numeroCodici;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getComuneNascita() {
        return comuneNascita;
    }

    public void setComuneNascita(String comuneNascita) {
        this.comuneNascita = comuneNascita;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public char getSesso() {
        return sesso;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }


    /**
     *
     * Effettua la lettura del file codiciFiscali.xml verificandone la validità: se validi, verranno inseriti
     * in ArrayList codiciCorretti, altrimenti in ArrayList codiciInvalidi.
     *
     */
    public void importCodici(){

        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        boolean errore = false;
        String codice;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(NOME_FILE_CODICI, new FileInputStream(NOME_FILE_CODICI));
        } catch (Exception e) {
            errore = true;
            System.out.println(ERRORE_READER);
            System.out.println(e.getMessage());
        }

        if(!errore){
            try{

                while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione

                    switch (xmlr.getEventType()) { // switch sul tipo di evento

                        case XMLStreamConstants.START_ELEMENT: // inizio di un elemento

                            switch (xmlr.getLocalName()){

                                case "codici":
                                    setNumeroCodici(Integer.parseInt(xmlr.getAttributeValue(0))); //imposta numero codici = all'attributo di <codici>
                                    break;

                                case "codice":
                                    xmlr.next(); //passa al testo di codice
                                    codice = xmlr.getText(); //ottiene il testo

                                    //controllo se il codice è valido ed in base a ciò lo aggiungo ad Arraylist codiciCorretti o codiciInvalidi
                                    if(verificaValidita(codice)){
                                        codiciCorretti.add(codice);
                                    }else{
                                        codiciInvalidi.add(codice);
                                    }

                                    break;

                            }

                            break;

                    }

                    xmlr.next();

                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }


        /**
         *
         * Verifica la validità del codice fiscale effettuando i seguenti controlli:
         * <ul>
         *     <li> controllo che lettere e numeri siano nelle posizioni corrette; </li>
         *     <li> controllo che il giorno sia compreso fra 1 e 31 o fra 41 e 71; </li>
         *     <li> controllo validità del mese e numero giorni max in un mese; </li>
         *     <li> validità del carattere di controllo; </li>
         * </ul>
         *
         * @param codice
         * @return true se il codice è valido, false altrimenti.
         */
        public boolean verificaValidita(String codice){

        int posLettere[] = {0,1,2,3,4,5,8,11,15};
        int posNumeri[] = {6,7,9,10,12,13,14};

        codice.toUpperCase();

        //controllo lettere in posizione corretta
        for(int i=0; i < posLettere.length; i++){
            if( codice.charAt(posLettere[i]) < 65 || codice.charAt(posLettere[i]) > 90){
                return false;
            }
        }

        //controllo numeri in posizione corretta
        for(int i=0; i < posNumeri.length; i++){
            if( codice.charAt(posNumeri[i]) < 48 || codice.charAt(posNumeri[i]) > 57){
                return false;
            }
        }

        //controllo giorno compreso fra 1 e 31 o fra 41 e 71
        if((Integer.parseInt(codice.substring(9,11)) < 1) || ((Integer.parseInt(codice.substring(9,11)) > 31) && (Integer.parseInt(codice.substring(9,11)) < 41)) || (Integer.parseInt(codice.substring(9,11)) > 71)){
            return false;
        }

        //controllo validità del mese e validità numero giorni in un mese
        switch (codice.charAt(8)){
            case 'A'://gennaio
                if((Integer.parseInt(codice.substring(9,11)) > 31 ) || (Integer.parseInt(codice.substring(9,11)) > 71)){
                    return false;
                }
                break;
            case 'B'://febbraio
                if((Integer.parseInt(codice.substring(9,11)) > 28 ) || (Integer.parseInt(codice.substring(9,11)) > 68)){
                    return false;
                }
                break;
            case 'C'://marzo
                if((Integer.parseInt(codice.substring(9,11)) > 31 ) || (Integer.parseInt(codice.substring(9,11)) > 71)){
                    return false;
                }
                break;
            case 'D'://aprile
                if((Integer.parseInt(codice.substring(9,11)) > 30 ) || (Integer.parseInt(codice.substring(9,11)) > 70)){
                    return false;
                }
                break;
            case 'E'://maggio
                if((Integer.parseInt(codice.substring(9,11)) > 31 ) || (Integer.parseInt(codice.substring(9,11)) > 71)){
                    return false;
                }
                break;
            case 'H'://giugno
                if((Integer.parseInt(codice.substring(9,11)) > 30 ) || (Integer.parseInt(codice.substring(9,11)) > 70)){
                    return false;
                }
                break;
            case 'L'://luglio
                if((Integer.parseInt(codice.substring(9,11)) > 31 ) || (Integer.parseInt(codice.substring(9,11)) > 71)){
                    return false;
                }
                break;
            case 'M'://agosto
                if((Integer.parseInt(codice.substring(9,11)) > 31 ) || (Integer.parseInt(codice.substring(9,11)) > 71)){
                    return false;
                }
                break;
            case 'P'://settembre
                if((Integer.parseInt(codice.substring(9,11)) > 30 ) || (Integer.parseInt(codice.substring(9,11)) > 70)){
                    return false;
                }
                break;
            case 'R'://ottobre
                if((Integer.parseInt(codice.substring(9,11)) > 31 ) || (Integer.parseInt(codice.substring(9,11)) > 71)){
                    return false;
                }
                break;
            case 'S'://novembre
                if((Integer.parseInt(codice.substring(9,11)) > 30 ) || (Integer.parseInt(codice.substring(9,11)) > 70)){
                    return false;
                }
                break;
            case 'T'://dicembre
                if((Integer.parseInt(codice.substring(9,11)) > 31 ) || (Integer.parseInt(codice.substring(9,11)) > 71)){
                    return false;
                }
                break;
            default://mese non corretto
                    return false;
        }

        //controllo carattere di controllo
        if(codice.charAt(15) != getCarattereDiControllo(codice.substring(0,codice.length()-1))){
            return false;
        }


        return true;

    }


    /**
     *
     * Effettua la lettura del file inputPersone.xml per ottenere il numero di persone e per ogni persona:
     * id, nome, cognome, sesso, comune di nascita, data di nascita.<br>
     * Al termine di ogni elemento "persona" creo un nuovo oggetto di classe Persona e lo aggiungo ad ArrayList persone.
     *
     */
    public void importPersone(){

        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        boolean errore = false;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(NOME_FILE_PERSONE, new FileInputStream(NOME_FILE_PERSONE));
        } catch (Exception e) {
            errore = true;
            System.out.println(ERRORE_READER);
            System.out.println(e.getMessage());
        }

        if(!errore){
            try{

                while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione

                    switch (xmlr.getEventType()) { // switch sul tipo di evento

                        case XMLStreamConstants.START_ELEMENT: // inizio di un elemento

                            switch (xmlr.getLocalName()){

                                case "persone":
                                    setNumeroPersone(Integer.parseInt(xmlr.getAttributeValue(0))); //assegno a numeroPersone il valore dell'attributo dell'elemento <persone>
                                    break;

                                case "persona":
                                    setId(Integer.parseInt(xmlr.getAttributeValue(0))); //assegno a id il valore dell'attributo dell'elemento <persona>
                                    break;

                                case "nome":
                                    xmlr.next(); //passo al testo di <nome>
                                    setNome(xmlr.getText()); //assegno a nome il testo letto
                                    break;

                                case "cognome":
                                    xmlr.next(); //passo al testo di <cognome>
                                    setCognome(xmlr.getText()); //assegno a cognome il testo letto
                                    break;

                                case "sesso":
                                    xmlr.next(); //passo al testo di <sesso>
                                    setSesso(xmlr.getText().charAt(0)); //assegno a sesso il testo letto
                                    break;

                                case"comune_nascita":
                                    xmlr.next(); //passo al testo di <comune_nascita>
                                    setComuneNascita(xmlr.getText()); //assegno a comuneNascita il testo letto
                                    break;

                                case "data_nascita":
                                    xmlr.next(); //passo al testo di <data_nascita>
                                    setDataNascita(xmlr.getText()); //assegno a dataNascita il testo letto
                                    break;

                            }

                            break;

                        case XMLStreamConstants.END_ELEMENT: // fine di un elemento

                            //se termina elemento <persona> creo nuovo oggetto di classe Persona e lo aggiungo ad ArrayList persone
                            if(xmlr.getLocalName().equals("persona")){
                                nuovaPersona = new Persona(id, nome, cognome, sesso, comuneNascita, dataNascita);
                                persone.add(nuovaPersona);
                            }

                            break;

                    }

                    xmlr.next();

                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }


    /**
     *
     * Verifica se il codice fiscale di una persona è presente o assente confrontando il suo codice con i codici validi letti dal file codiciFiscali.xml
     *
     */
    public void verificaPresenza(){

        boolean trovato = false;

        for(int i=0; i < codiciCorretti.size(); i++){ //per ogni elemento di codiciCorretti
            for(int j = 0; (j < persone.size()) && (!trovato); j++){ //per ogni elemento di codici corretti finchè trovato = true o termina l'ArrayList
                if(persone.get(j).getCodiceFiscale().equals(codiciCorretti.get(i))){ //se i codici fiscali corrispondono imposto trovato = true e richiamo il metodo setAssente di classe Persona passando false per parametro
                    trovato = true;
                    persone.get(j).setAssente(false);
                }
            }
            if(!trovato){
                codiciSpaiati.add(codiciCorretti.get(i));
            }else{
                trovato = false;
            }
        }

    }


    /**
     *
     * Metodo che genera il file di output.
     *
     * @return true se il file viene generato correttamente.
     */
    public boolean generaFileOutput(){

        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;

        //inizializzazione del writer con controllo eccezioni
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(NOME_FILE_OUTPUT), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println(ERRORE_WRITER);
            System.out.println(e.getMessage());
            return false;
        }

        try { // blocco try per raccogliere eccezioni
            xmlw.writeStartElement( "output"); // scrittura del tag radice <output>

            xmlw.writeStartElement("persone"); // scrittura del tag <persone> ...
            xmlw.writeAttribute("numero" , Integer.toString(numeroPersone)); //... con attributo numero

                //per ogni elemento dell'ArrayList persone genero l'output richiamando il metodo generaElementiPersona
                for (int i=0; i < persone.size(); i++) {
                    generaElementiPersona(persone.get(i), xmlw);
                }

            xmlw.writeEndElement();//chiudo tag </persone>

            xmlw.writeStartElement("codici"); // scrittura del tag <codici>

                xmlw.writeStartElement("invalidi"); // scrittura del tag <invalidi> ...
                xmlw.writeAttribute("numero" , Integer.toString(codiciInvalidi.size())); // ... con attributo numero
                    //per ogni elemento dell'ArrayList codiciInvalidi genero un elemento codice con il valore dell'array come testo
                    for (int i=0; i < codiciInvalidi.size(); i++) {
                        xmlw.writeStartElement("codice"); // scrittura del tag <codice> ...
                        xmlw.writeCharacters(codiciInvalidi.get(i)); //scrittura codice
                        xmlw.writeEndElement(); // chiusura tag </nome>
                    }
                xmlw.writeEndElement();//chiudo tag </invalidi>

                xmlw.writeStartElement("spaiati"); // scrittura del tag <spaiati> ...
                xmlw.writeAttribute("numero" , Integer.toString(codiciSpaiati.size())); // ... con attributo numero
                    //per ogni elemento dell'ArrayList codiciSpaiati genero un elemento codice con il valore dell'array come testo
                    for (int i=0; i < codiciSpaiati.size(); i++) {
                        xmlw.writeStartElement("codice"); // scrittura del tag <codice> ...
                        xmlw.writeCharacters(codiciSpaiati.get(i)); //scrittura codice
                        xmlw.writeEndElement(); // chiusura tag </nome>
                    }
                xmlw.writeEndElement();//chiudo tag </spaiati>

            xmlw.writeEndElement();//chiudo tag </codici>

            xmlw.writeEndElement();//chiudo tag radice </output>

            xmlw.writeEndDocument();

            xmlw.flush(); // svuota il buffer e procede alla scrittura
            xmlw.close(); // chiusura del documento e delle risorse impiegate

            return true;

        } catch (Exception e) { // se c’è un errore viene eseguita questa parte
            System.out.println(ERRORE_SCRITTURA_FILE);
            System.out.println(e.getMessage());
            return false;
        }

    }


    /**
     * Metodo che genera gli elementi persona e sottoelementi: nome, cognome, sesso, comune di nascita, data di nascita e codice fiscale
     * da scrivere nel file di output.
     *
     * @param persona
     * @param xmlw
     */
    public void generaElementiPersona(Persona persona, XMLStreamWriter xmlw){

        try{

            xmlw.writeStartElement("persona"); // scrittura del tag <persona> ...
            xmlw.writeAttribute("id", Integer.toString(persona.getId())); // ...con attributo id

                xmlw.writeStartElement("nome"); //scrittura del tag <nome>
                xmlw.writeCharacters(persona.getNome());
                xmlw.writeEndElement(); // chiusura tag </nome>

                xmlw.writeStartElement("cognome"); //scrittura del tag <cognome>
                xmlw.writeCharacters(persona.getCognome());
                xmlw.writeEndElement(); // chiusura tag </cognome>

                xmlw.writeStartElement("sesso"); //scrittura del tag <sesso>
                xmlw.writeCharacters(Character.toString(persona.getSesso()));
                xmlw.writeEndElement(); // chiusura tag </sesso>

                xmlw.writeStartElement("comune_nascita"); //scrittura del tag <comune_nascita>
                xmlw.writeCharacters(persona.getComuneNascita());
                xmlw.writeEndElement(); // chiusura tag </comune_nascita>

                xmlw.writeStartElement("data_nascita"); //scrittura del tag <data_nascita>
                xmlw.writeCharacters(persona.getDataNascita());
                xmlw.writeEndElement(); // chiusura tag </data_nascita>

                xmlw.writeStartElement("codice_fiscale"); //scrittura del tag <codice_fiscale>
                //se assente=true scivo come testo di codice_fiscale la parola "ASSENTE" altrimenti inserisco il codice fiscle corrispondente
                if(persona.getAssente()){
                    xmlw.writeCharacters("ASSENTE");
                }else{
                    xmlw.writeCharacters(persona.getCodiceFiscale());
                }
                xmlw.writeEndElement(); // chiusura tag </codice_fiscale>

            xmlw.writeEndElement();// chiusura tag </persona>

        }catch (Exception e){
            System.out.println(ERRORE_SCRITTURA_FILE);
            System.out.println(e.getMessage());
        }

    }


    /**
     *
     * Genera il carattere di controllo (utile per la verifica validità)
     * a partire dalla prima parte di codice fiscale passata per parametro.
     *
     * @param primaParte
     * @return carattere generato
     */
    public char getCarattereDiControllo(String primaParte) {

        int pari=0,dispari=0;
        String caratteridispari = null, caratteripari = null,
                caratteri= "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                valoridispari="1-0-5-7-9-13-15-17-19-21-1-0-5-7-9-13-15-17-19-21-2-4-18-20-11-3-6-8-12-14-16-10-22-25-24-23",
                valoripari="0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-20-21-22-23-24-25",
                alfabeto="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char ccontrollo;
        StringTokenizer tokenizer;

        for(int i=0;i<15;i++)
        {
            if(i%2==0)
            {
                caratteridispari=caratteridispari+primaParte.charAt(i);
            }
            else
            {
                caratteripari=caratteripari+primaParte.charAt(i);
            }
        }
        for(int i=0;i<caratteridispari.length();i++)
        {
            boolean trovato=false;
            for (int j=0;j<36 && trovato==false;j++)
            {
                if (caratteridispari.charAt(i)==caratteri.charAt(j))
                {
                    tokenizer = new StringTokenizer( valoridispari , "-" );
                    for(int k=0;k<j;k++)
                    {
                        tokenizer.nextToken();
                    }
                    dispari=dispari+Integer.parseInt(tokenizer.nextToken());
                    trovato=true;
                }
            }
        }
        for(int i=0;i<caratteripari.length();i++)
        {
            boolean trovato=false;
            for (int j=0;j<36 && trovato==false;j++)
            {
                if (caratteripari.charAt(i)==caratteri.charAt(j))
                {
                    tokenizer = new StringTokenizer( valoripari , "-" );
                    for(int k=0;k<j;k++)
                    {
                        tokenizer.nextToken();
                    }
                    pari=pari+Integer.parseInt(tokenizer.nextToken());
                    trovato=true;
                }
            }
        }
        ccontrollo = alfabeto.charAt((pari+dispari)%26);
        return ccontrollo;
    }











    /**
     * stampa tutti gli elementi dell'ArrayList codiciCorretti
     */
    public void stampaCodiciCorretti(){

        System.out.println("Codici Corretti:");
        for(int i=0; i<codiciCorretti.size(); i++){
            System.out.println(codiciCorretti.get(i));
        }

    }

    /**
     * stampa tutti gli elementi dell'ArrayList codiciInvalidi
     */
    public void stampaCodiciInvalidi(){

        System.out.println("Codici Errati:");
        for(int i=0; i<codiciInvalidi.size(); i++){
            System.out.println(codiciInvalidi.get(i));
        }

    }

    /**
     * stampa tutti gli elementi dell'ArrayList codiciSpaiati
     */
    public void stampaCodiciSpaiati(){

        System.out.println("Codici Spaiati:");
        for(int i=0; i<codiciSpaiati.size(); i++){
            System.out.println(codiciSpaiati.get(i));
        }

    }

    /**
     * stampa tutti gli elementi dell'ArrayList persone
     */
    public void stampaPersone(){

        for(int i=0; i<persone.size(); i++){
            System.out.println(persone.get(i).getId() + " - " + persone.get(i).getNome() + " - " + persone.get(i).getCognome() + " - " + persone.get(i).getSesso() + " - " + persone.get(i).getComuneNascita() + " - " + persone.get(i).getDataNascita());
        }

    }

}
