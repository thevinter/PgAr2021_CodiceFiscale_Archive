package it.unibs.ing.arnaldo.CodiceFiscale;

public class Persona {

    private static final char []mesiCodiceFiscale = {'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T'};

    private String nome;
    private String cognome;
    private char sesso;
    private String dateOfBirth;
    private String luogoNascita;

    private char carattereControllo;

    private String CodiceFiscale; //codice fiscale


    public Persona(String []attributiPersona) {
        //assegno i valori dell'array attributipersona agli attributi della classe: nome, cognome, sesso, dateOfBirth e luogoNascita
        for(int i = 0; i < attributiPersona.length; i++) {
            if(i == 0 && attributiPersona[i] != null) {
                nome = attributiPersona[i];
            }else if(i == 1 && attributiPersona[i] != null) {
                cognome = attributiPersona[i];
            }else if(i == 2 && attributiPersona[i] != null) {
                sesso = attributiPersona[i].charAt(0);
            }else if(i == 3 && attributiPersona[i] != null) {
                dateOfBirth = attributiPersona[i];
            }else if(i == 4 && attributiPersona[i] != null){
                luogoNascita = attributiPersona[i];
            }
        }

    }





    /**
     * Estrae le prime tre lettere del nome.
     * Casi:
     * - se le consonanti sono 2 aggiungo la prima vocale in ordine;
     * - se la parola ha una lunghezza minore di tre aggiungo una X;
     * @param parola: nome/cognome dal quale verranno estratte le tre cifre per il nome/cognome
     */
    private String estraiCifrePseudonimo(String parola) {

        parola = parola.toUpperCase();

        /*
         * replace ritorna la stringa senza vocali.
         * La stringa "[AEIOU]" è una espressione regolare che significa "una qualsiasi lettera tra A, E, I, O e U".
         *
         * Esempio: SINGH ---> SNG
         * 			LUCA  ---> LCU
         * 			YU    ---> YUX
         * 			J	  ---> JXX
         *
         */
        String pseudonimoCF = parola.toUpperCase().replaceAll("[AEIOU]", "");

        //se le consonanti sono almeno tre la parola assume le prime tre consonanti
        if(pseudonimoCF.length() > 2) {
            parola = pseudonimoCF.substring(0, 3);
        }else {
            /*
             * se le consonanti sono meno di tre concateno le consonanti con le vocali in ordine
             * Nel replaceAll "[B-DF-HJ-NP-TV-Z]" sta ad indicare che cancello tutte le consonanti, dalla B alla D, dalla F alla H e così via...
             * Mi rimangono perciò le vocali che le accodo alle consonanti.
             */
            pseudonimoCF = pseudonimoCF.concat(parola.replaceAll("[B-DF-HJ-NP-TV-Z]", ""));
            if(pseudonimoCF.length() > 2) {
                parola = pseudonimoCF.substring(0, 3);
            }else {

                if(pseudonimoCF.length() == 2){
                    parola = pseudonimoCF.concat("X");
                }else{
                    parola = pseudonimoCF.concat("XX");
                }
            }
        }

        return parola;
    }

    /**
     * Anno di nascita (due cifre): si prendono le ultime due cifre dell'anno di nascita;
     */
    private String estraiCifreAnno() {

        String cifreAnno = dateOfBirth.substring(2, 4);

        return cifreAnno;
    }

    /**
     * Mese di nascita (una lettera): a ogni mese dell'anno viene associata una lettera in base alle lettere A,B ecc.
     */

    private int estraiMese() {
        String mese = dateOfBirth.substring(5,7);
        int month = Integer.parseInt(mese);
        return month;
    }

    /**
     * Ritorna il carattere associato ai vari mesi predefiniti per il codice fiscale
     * @return
     */
    public char monthCF() {

        char meseCF = ' ';
        int month = estraiMese();

        switch(month) {
            case 1:
                meseCF = mesiCodiceFiscale[0];
                break;
            case 2:
                meseCF = mesiCodiceFiscale[1];
                break;
            case 3:
                meseCF = mesiCodiceFiscale[2];
                break;
            case 4:
                meseCF = mesiCodiceFiscale[3];
                break;
            case 5:
                meseCF = mesiCodiceFiscale[4];
                break;
            case 6:
                meseCF = mesiCodiceFiscale[5];
                break;
            case 7:
                meseCF = mesiCodiceFiscale[6];
                break;
            case 8:
                meseCF = mesiCodiceFiscale[7];
                break;
            case 9:
                meseCF = mesiCodiceFiscale[8];
                break;
            case 10:
                meseCF = mesiCodiceFiscale[9];
                break;
            case 11:
                meseCF = mesiCodiceFiscale[10];
                break;
            case 12:
                meseCF = mesiCodiceFiscale[11];
                break;
        }

        return meseCF;
    }

    /**
     * estrae il giorno
     */
    private String estraiGiorno() {

        String dayCF = dateOfBirth.substring(8, 10);//estraggo il giorno
        int giorno = Integer.parseInt(dayCF);

        //per i soggetti di sesso femminile, a tale cifra va sommato il numero 40.
        if(sesso == 'F') {
            giorno = giorno + 40;
            dayCF = String.valueOf(giorno);//conversione int to String
        }

        return dayCF;
    }


    /**
     * Luogo di nascita: 1 lettera 3 cifre
     */
    private String estraiCodiceLuogoNascita() {

        String luogoNascitaCF = "";
        XMLReaderCF readComuniXML = new XMLReaderCF("comuni.xml");

        //verifico che il luogo di nascita sia presente nell'array di città
        for(Citta city: readComuniXML.getCities()) {
            if(luogoNascita.equalsIgnoreCase(city.getNomeCitta())) {
                luogoNascitaCF = city.getCodiceCitta();
            }
        }

        return luogoNascitaCF;
    }


    private char carattereDiControllo() {
        return 's';
    }



    public void generaCodiceFiscale() {
        //MANCA IL CARATTERE DI CONTROLLO
        CodiceFiscale =  estraiCifrePseudonimo(cognome)+ estraiCifrePseudonimo(nome) + estraiCifreAnno() + monthCF() + estraiGiorno() + estraiCodiceLuogoNascita();

    }

}
