import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class Persona {

    private final static String NOME_FILE_COMUNI = "Codice_Fiscale/file/comuni.xml";
    private final static String ERRORE_READER = "Errore nell'inizializzazione del reader:";

    private int id;
    private String nome, cognome, comuneNascita, dataNascita, codiceFiscale, codiceComune;

    private char sesso;
    private boolean assente;

    // abbiamo utilizzato i javadoc "nonostante non li abbiate spiegati" cit.
    public Persona(int id, String nome, String cognome, char sesso, String comuneNascita, String dataNascita) {
        this.id = id;
        this.nome = nome.toUpperCase();
        this.cognome = cognome.toUpperCase();
        this.sesso = sesso;
        this.comuneNascita = comuneNascita.toUpperCase();
        this.dataNascita = dataNascita;
        this.assente = true;
        setCodiceFiscale(generaCodice());
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

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public char getSesso() {
        return sesso;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public boolean getAssente(){
        return assente;
    }

    public void setAssente(boolean assente){
        this.assente = assente;
    }

    public String getCodiceComune() {
        return codiceComune;
    }

    public void setCodiceComune(String codiceComune) {
        this.codiceComune = codiceComune;
    }


    private int lunghezzaCognome() {
        return this.cognome.length();
    }

    /** (ciao lange, abbiamo utilizzato i javadoc senza che qualcuno li spiegasse) restituisce le 3 lettere del cognome
     *
     *
     * @return String
     */
    public String cognomeCodice() {
        int contatore = 0;
        String primaparte = new String();
        //se cognome ha lunghezza >=3 entro
        if (lunghezzaCognome() >= 3) {

            contatore = 0;
            for (int i = 0; i < lunghezzaCognome() && contatore < 3; i++) {
                //controllo che non sia una vocale
                if (!((cognome.charAt(i) == 'A') || (cognome.charAt(i) == 'E') || (cognome.charAt(i) == 'I') || (cognome.charAt(i) == 'O') || (cognome.charAt(i) == 'U'))) {
                    primaparte = primaparte + cognome.charAt(i);
                    contatore++;
                }
            }
            // se è stato generato un codice con meno di 3 cifre allora entro ed aggiungo tante x quante mancano
            if (contatore < 3) {
                for (int i = 0; i < lunghezzaCognome() && contatore < 3; i++) {
                    if ((cognome.charAt(i) == 'A') || (cognome.charAt(i) == 'E') || (cognome.charAt(i) == 'I') || (cognome.charAt(i) == 'O') || (cognome.charAt(i) == 'U')) {
                        primaparte = primaparte + cognome.charAt(i);
                        contatore++;
                    }
                }
            }

        } //se cognome non ha almeno 3 lettere entro
            else {

            for (int i = 0; i < lunghezzaCognome(); i++) {
                contatore = 0;
                //se non è una vocale entro
                if (cognome.charAt(i) != 'A' && cognome.charAt(i) != 'E' && cognome.charAt(i) != 'I' && cognome.charAt(i) != 'O' && cognome.charAt(i) != 'U') {
                    primaparte = primaparte + cognome.charAt(i);
                    contatore++;
                }
            }
            //se il codice generato non ha almeno 3 caratteri entro a prendere le vocali
            if (contatore < 3) {
                for (int i = 0; i < lunghezzaCognome() && contatore < 3; i++) {
                    if ((cognome.charAt(i) == 'A') || (cognome.charAt(i) == 'E') || (cognome.charAt(i) == 'I') || (cognome.charAt(i) == 'O') || (cognome.charAt(i) == 'U')) {
                        primaparte = primaparte + cognome.charAt(i);
                        contatore++;
                    }
                }
            }
            if (contatore < 3) {
                for (int i = 0; i < lunghezzaCognome() - contatore; i++) {
                    primaparte = primaparte + 'X';
                }
            }
        }
        return primaparte;
    }

    /** CIAOO LANGE QUESTI SONO I FAMOSI JAVADOC CHE MI AVEVI CHIESTO, ORA, TI PREGO, RIVOGLIO IL MIO GUBBINO
     * restituisce una stringa di 3 caratteri corrispondente al nome
     *
     * @return String
     */
    public String codiceNome() {
        int contatore = 0, consonanti = 0;
        String nome_consonanti = new String(), secondaparte = new String();

        if (nome.length() >= 3) {

            for (int i = 0; i < nome.length(); i++) {
                //se non sono vocali entro
                if (!((nome.charAt(i) == 'A') || (nome.charAt(i) == 'E') || (nome.charAt(i) == 'I') || (nome.charAt(i) == 'O') || (nome.charAt(i) == 'U'))) {
                    consonanti++;
                    nome_consonanti = nome_consonanti + nome.charAt(i);
                }
            }
            if (consonanti >= 4) {
                secondaparte=""+nome_consonanti.charAt(0)+nome_consonanti.charAt(2)+nome_consonanti.charAt(3);
            }
            else{
                    secondaparte=secondaparte+nome_consonanti;
                    contatore=nome_consonanti.length();
                    for(int i=0;i<nome.length() && contatore<3;i++)
                    {
                        if((nome.charAt(i) == 'A') || (nome.charAt(i) == 'E') || (nome.charAt(i) == 'I') || (nome.charAt(i) == 'O') || (nome.charAt(i) == 'U'))
                        {
                            secondaparte=secondaparte+nome.charAt(i);
                        }
                    }

            }

        } else {
                for (int i = 0; i < nome.length() && contatore<3; i++) {
                    if (!((nome.charAt(i) == 'A') || (nome.charAt(i) == 'E') || (nome.charAt(i) == 'I') || (nome.charAt(i) == 'O') || (nome.charAt(i) == 'U'))) {
                        contatore++;
                       secondaparte = secondaparte + nome.charAt(i);
                    }
                }
                for (int i = 0; i < nome.length() && contatore<3; i++) {
                    if ((nome.charAt(i) == 'A') || (nome.charAt(i) == 'E') || (nome.charAt(i) == 'I') || (nome.charAt(i) == 'O') || (nome.charAt(i) == 'U')) {
                        contatore++;
                        secondaparte = secondaparte + nome.charAt(i);
                    }
                }
                if(contatore<3)
                {
                    for(int i=contatore; i<3;i++)
                    {
                        secondaparte=secondaparte+"X";
                    }
                }


        }
        return secondaparte;
    }

    /** CIAO LANGE
     *  restituisce una stringa corrispondente al anno e mese
     *
     * @return String
     */
    public String codiceAnnoMese()
    {
        String terzaparte= new String();
        int mese;
        mese = Integer.parseInt(""+dataNascita.charAt(5)+dataNascita.charAt(6));
        terzaparte=""+dataNascita.charAt(2)+dataNascita.charAt(3);
        if(mese==1) {
            terzaparte+="A";
        }
        if(mese==2) {
            terzaparte+="B";
        }
        if(mese==3) {
            terzaparte+="C";
        }
        if(mese==4) {
            terzaparte+="D";
        }
        if(mese==5) {
            terzaparte+="E";
        }
        if(mese==6) {
            terzaparte+="H";
        }
        if(mese==7) {
            terzaparte+="L";
        }
        if(mese==8) {
            terzaparte+="M";
        }
        if(mese==9) {
            terzaparte+="P";
        }
        if(mese==10) {
            terzaparte+="R";
        }
        if(mese==11) {
            terzaparte+="S";
        }
        if(mese==12) {
            terzaparte+="T";
        }
        return terzaparte;
    }

    /**Ciao Lange, ma quando fai la prima lezione sul programma arnaldo?
     * restituisce una stringa corrispondente al giorno di nascita in base al sesso del individuo
     * @return String
     */
    public String codiceGiorno()
    {
        String quartaparte= new String();
        int giorno;
        giorno= Integer.parseInt(""+dataNascita.charAt(8)+dataNascita.charAt(9));
        if(sesso=='F')
        {
            quartaparte=""+(40+giorno);
        }
        else
        {
            if(giorno<10)
            {
                quartaparte="0"+giorno;
            }
            else
                quartaparte=""+giorno;
        }
        return quartaparte;
    }


    /**estrae dal file il codice relativo al comune di nascità e assegna
     * alla variabile di classe
     *
     */
    public void generaCodiceComune(){

        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        boolean errore = false;
        String comune = null;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(NOME_FILE_COMUNI, new FileInputStream(NOME_FILE_COMUNI));
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

                                case "nome":
                                    xmlr.next();
                                    comune = xmlr.getText().toUpperCase();
                                    break;

                                case "codice":
                                    if(comune.equals(getComuneNascita())){
                                        xmlr.next();
                                        setCodiceComune(xmlr.getText());
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

    /**genera il carattere di controllo sulla base del codice generato in precedenza
     *
     * @return String
     */
        public String getCarattereDiControllo()
        {
            int pari=0,dispari=0;
            String caratteridispari = new String (),ccontrollo = new String (),caratteripari = new String (),
                    codiceincompleto = generaCodiceincompleto(),caratteri= "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                    valoridispari="1-0-5-7-9-13-15-17-19-21-1-0-5-7-9-13-15-17-19-21-2-4-18-20-11-3-6-8-12-14-16-10-22-25-24-23",
                    valoripari="0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-20-21-22-23-24-25",
                    alfabeto="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringTokenizer tokenizer;
            for(int i=0;i<15;i++)
            {
                //se sei in posizione dispari, cioè indice pari, entra
                if(i%2==0)
                {
                    caratteridispari=caratteridispari+codiceincompleto.charAt(i);
                }
                else
                {
                    caratteripari=caratteripari+codiceincompleto.charAt(i);
                }
            }
            for(int i=0;i<caratteridispari.length();i++)
            {
                boolean trovato=false;
                for (int j=0;j<36 && trovato==false;j++)
                {
                    //se corrispondono i caratteri entro
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
            ccontrollo=""+(alfabeto.charAt((pari+dispari)%26));
            return ccontrollo;
        }

    /**aggrega e restituisce tutti gli output dei metodi relativi alla creazione del codice fiscale senza il carattere di controllo
     *
     * @return String
     */
    public String generaCodiceincompleto() {

        String codice;

        generaCodiceComune();
        codice = cognomeCodice() + codiceNome() + codiceAnnoMese() + codiceGiorno() + getCodiceComune();

        return codice;

    }

    /**restituisce il codice fiscale
     *
     * @return String
     */
    public String generaCodice()
    {

        return generaCodiceincompleto()+getCarattereDiControllo();
    }
}
