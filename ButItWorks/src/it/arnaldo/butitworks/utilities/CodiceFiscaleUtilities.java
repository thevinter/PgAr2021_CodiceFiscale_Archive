package it.arnaldo.butitworks.utilities;
import it.arnaldo.butitworks.model.Comune;
import it.arnaldo.butitworks.model.Persona;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CodiceFiscaleUtilities {
    //COSTANTI
    private static final String VOCALI = "AEIOU";
    private static final String CODICI_MESE = "ABCDEHLMPRST";

    //METODI BOOLEANI GENERALI
    private static Boolean isVocale(char lettera) {
        return VOCALI.indexOf(lettera) != -1;
    }

    private static Boolean isConsonanteDiversaDaX(char lettera) {
        return (!isVocale(lettera) && lettera != 'X');
    }

    //METODI PUBBLICI CLASSE
    /**
     * Genera il codice fiscale a partire da una persona e da una lista comuni
     * @param p persona di cui generare il codice fiscale
     * @param comuni ArrayList comuni per la generazione della parte del codice fiscale relativa al comune
     * @return ritorna una stringa di 16 caratteri corrispondente al codice fiscale della persona in input
     * @throws ParseException
     */
    public static String generaCodiceFiscale(Persona p, ArrayList<Comune> comuni) throws ParseException {
        StringBuilder codiceFiscale = new StringBuilder();
        codiceFiscale.append(generaCognomeCf(p.getCognome()));
        codiceFiscale.append(generaNomeCf(p.getNome()));
        codiceFiscale.append(generaDataCf(p.getDataNascita()));
        codiceFiscale.append(generaGiornoSessoCf(p.getDataNascita().getDayOfMonth(), p.getSesso()));
        codiceFiscale.append(generaComuneCf(p.getComuneNascita(), comuni));
        codiceFiscale.append(generaCarattereControlloCf(codiceFiscale.toString()));
        return new String(codiceFiscale);
    }


    /**
     * Controlla se il codice fiscale dato in input sia corretto
     * @param codiceFiscale Una stringa corrispondente ad un codice fiscale
     * @param comuni ArrayList comuni per il controllo della parte del codice fiscale relativa al comune
     * @return ritorna true se la stringa inserita corrisonde a una corretta formattazione del codice fiscale, false altrimenti
     * @throws XMLStreamException
     * @throws FileNotFoundException
     */
    public static Boolean controlloCodiceFiscale(String codiceFiscale, ArrayList<Comune> comuni) throws FileNotFoundException, XMLStreamException {
        return codiceFiscale.length() == 16 && analizzaCognome(codiceFiscale) && analizzaNome(codiceFiscale) && analizzaAnno(codiceFiscale) && analizzaMese(codiceFiscale)
                && analizzaGiornoSesso(codiceFiscale) && analizzaComune(codiceFiscale, comuni) && analizzaCarattereDiControllo(codiceFiscale);
    }


    //==================================================
    //METODI PRIVATI PER GENERARE IL CODICE FISCALE
    //==================================================

    /**
     * Genera la parte di codice fiscale relativa al cognome
     * @param cognome stringa relativa al cognome della persona
     * @return stringa contenente il codice relativo al cognome
     */
    private static String generaCognomeCf(String cognome) {
        StringBuilder codiceCognome = new StringBuilder();

        //INSERIRE CONSONANTI IN ORDINE
        for (int i = 0; i < cognome.length() && codiceCognome.length() < 3; i++) {
            char lettera = cognome.charAt(i);
            if (!isVocale(lettera)) {
                codiceCognome.append(lettera);
            }
        }

        //INSERIRE VOCALI SE CONSONANTI MINORI DI 3
        for (int i = 0; i < cognome.length() && codiceCognome.length() < 3; i++) {
            char lettera = cognome.charAt(i);
            if(isVocale(lettera)) {
                codiceCognome.append(lettera);
            }
        }

        //AGGIUNGERE X SE LETTERE MINORI DI 3
        while(codiceCognome.length() < 3) {
            codiceCognome.append("X");
        }

        return new String(codiceCognome);
    }


    /**
     * Genera la parte di codice fiscale relativa al nome
     * @param nome stringa relativa al nome della persona
     * @return stringa contenente il codice relativo al nome
     */
    private static String generaNomeCf(String nome) {
        StringBuilder consonanti = new StringBuilder();
        StringBuilder codiceNome = new StringBuilder();

        //INSERIRE CONSONANTI IN ORDINE
        for (int i = 0; i < nome.length() && codiceNome.length() < 3; i++) {
            char lettera = nome.charAt(i);
            if (!isVocale(lettera)) {
                consonanti.append(lettera);
            }
        }

        //SE CONSONANTI MAGGIORI DI 3 METTO LA PRIMA LA TERZA E LA QUARTA
        if(consonanti.length() > 3) {
            codiceNome.append(consonanti.charAt(0));
            codiceNome.append(consonanti.charAt(2));
            codiceNome.append(consonanti.charAt(3));
        } else {
            codiceNome.append(consonanti);
        }

        //INSERIRE VOCALI SE CONSONANTI MINORI DI 3
        for (int i = 0; i < nome.length() && codiceNome.length() < 3; i++) {
            char lettera = nome.charAt(i);
            if(isVocale(lettera)) {
                codiceNome.append(lettera);
            }
        }

        //AGGIUNGERE X SE LETTERE MINORI DI 3
        while(codiceNome.length() < 3) {
            codiceNome.append("X");
        }

        return new String(codiceNome);
    }

    /**
     * Genera la parte di codice fiscale relativa all'anno e al mese di nascita
     * @param data data in formato <b>LocalDate</b> relativa alla data di nascita della persona
     * @return stringa contenente il codice relativo dell'anno e del mese di nascita
     */
    private static String generaDataCf(LocalDate data) throws ParseException {
        StringBuilder codiceData = new StringBuilder();
        String anno = Integer.toString(data.getYear());
        int mese = data.getMonthValue();

        codiceData.append(anno.charAt(anno.length()-2));
        codiceData.append(anno.charAt(anno.length()-1));

        switch (mese) {
            case 1: {codiceData.append("A"); break;}
            case 2: {codiceData.append("B"); break;}
            case 3: {codiceData.append("C"); break;}
            case 4: {codiceData.append("D"); break;}
            case 5: {codiceData.append("E"); break;}
            case 6: {codiceData.append("H"); break;}
            case 7: {codiceData.append("L"); break;}
            case 8: {codiceData.append("M"); break;}
            case 9: {codiceData.append("P"); break;}
            case 10: {codiceData.append("R"); break;}
            case 11: {codiceData.append("S"); break;}
            case 12: {codiceData.append("T"); break;}
        }

        return new String(codiceData);
    }

    /**
     * Genera la parte di codice fiscale relativa al giorno di nascita e al sesso
     * @param giorno int relativo al gitono di nascita della persona
     * @param sesso char relativo al sesso (M, F) della persona
     * @return stringa contenente il codice relativo al girno di nascita e al sesso della persona
     */
    private static String generaGiornoSessoCf(int giorno, char sesso) throws ParseException {
        StringBuilder codiceGiornoSesso = new StringBuilder();

        if(sesso == 'M') {
            if(giorno >= 1 && giorno <= 9) {
                codiceGiornoSesso.append("0");
            }
            codiceGiornoSesso.append(Integer.toString(giorno));
        } else {
            codiceGiornoSesso.append(Integer.toString(giorno + 40));
        }

        return new String(codiceGiornoSesso);
    }

    /**
     * Genera la parte di codice fiscale relativa al comune di nascita
     * @param comuneNascita stringa relativa al comune di nascita della persona
     * @param comuni ArrayList comuni per il controllo della parte del codice fiscale relativa al comune
     * @return stringa contenente il codice relativo al comune di nascita della persona
     */
    private static String generaComuneCf(String comuneNascita , ArrayList<Comune> comuni) {
        StringBuilder comuneLettere = new StringBuilder();

        for (Comune c : comuni) {
            if(comuneNascita.equals(c.getNome())) {
                comuneLettere.append(c.getCodice());
                break;
            }
        }

        return new String(comuneLettere);
    }

    /**
     * Genera la parte di codice fiscale relativa al giorno di nascita e al sesso
     * @param codiceFiscaleAttuale stringa contenente il codice fiscale relativo a una persona con l'ultimo carattere mancante
     * @return stringa contenente il carattere di controllo
     */
    private static String generaCarattereControlloCf(String codiceFiscaleAttuale) {
        StringBuilder lettereCarattereControllo = new StringBuilder();
        int carattereControllo = 0;

        for (int i = 0; i < codiceFiscaleAttuale.length(); i++) {
            //CARATTERI ALFANUMERICI PARI
            if(i % 2 != 0) {
                switch (codiceFiscaleAttuale.charAt(i)) {
                    case '1': {carattereControllo+=1;break;}
                    case '2': {carattereControllo+=2;break;}
                    case '3': {carattereControllo+=3;break;}
                    case '4': {carattereControllo+=4;break;}
                    case '5': {carattereControllo+=5;break;}
                    case '6': {carattereControllo+=6;break;}
                    case '7': {carattereControllo+=7;break;}
                    case '8': {carattereControllo+=8;break;}
                    case '9': {carattereControllo+=9;break;}
                    case 'B': {carattereControllo+=1;break;}
                    case 'C': {carattereControllo+=2;break;}
                    case 'D': {carattereControllo+=3;break;}
                    case 'E': {carattereControllo+=4;break;}
                    case 'F': {carattereControllo+=5;break;}
                    case 'G': {carattereControllo+=6;break;}
                    case 'H': {carattereControllo+=7;break;}
                    case 'I': {carattereControllo+=8;break;}
                    case 'J': {carattereControllo+=9;break;}
                    case 'K': {carattereControllo+=10;break;}
                    case 'L': {carattereControllo+=11;break;}
                    case 'M': {carattereControllo+=12;break;}
                    case 'N': {carattereControllo+=13;break;}
                    case 'O': {carattereControllo+=14;break;}
                    case 'P': {carattereControllo+=15;break;}
                    case 'Q': {carattereControllo+=16;break;}
                    case 'R': {carattereControllo+=17;break;}
                    case 'S': {carattereControllo+=18;break;}
                    case 'T': {carattereControllo+=19;break;}
                    case 'U': {carattereControllo+=20;break;}
                    case 'V': {carattereControllo+=21;break;}
                    case 'W': {carattereControllo+=22;break;}
                    case 'X': {carattereControllo+=23;break;}
                    case 'Y': {carattereControllo+=24;break;}
                    case 'Z': {carattereControllo+=25;break;}
                }
            }
            //CARATTERI ALFANUMERICI DISPARI
            else {
                switch (codiceFiscaleAttuale.charAt(i)) {
                    case '0': {carattereControllo+=1;break;}
                    case '2': {carattereControllo+=5;break;}
                    case '3': {carattereControllo+=7;break;}
                    case '4': {carattereControllo+=9;break;}
                    case '5': {carattereControllo+=13;break;}
                    case '6': {carattereControllo+=15;break;}
                    case '7': {carattereControllo+=17;break;}
                    case '8': {carattereControllo+=19;break;}
                    case '9': {carattereControllo+=21;break;}
                    case 'A': {carattereControllo+=1;break;}
                    case 'C': {carattereControllo+=5;break;}
                    case 'D': {carattereControllo+=7;break;}
                    case 'E': {carattereControllo+=9;break;}
                    case 'F': {carattereControllo+=13;break;}
                    case 'G': {carattereControllo+=15;break;}
                    case 'H': {carattereControllo+=17;break;}
                    case 'I': {carattereControllo+=19;break;}
                    case 'J': {carattereControllo+=21;break;}
                    case 'K': {carattereControllo+=2;break;}
                    case 'L': {carattereControllo+=4;break;}
                    case 'M': {carattereControllo+=18;break;}
                    case 'N': {carattereControllo+=20;break;}
                    case 'O': {carattereControllo+=11;break;}
                    case 'P': {carattereControllo+=3;break;}
                    case 'Q': {carattereControllo+=6;break;}
                    case 'R': {carattereControllo+=8;break;}
                    case 'S': {carattereControllo+=12;break;}
                    case 'T': {carattereControllo+=14;break;}
                    case 'U': {carattereControllo+=16;break;}
                    case 'V': {carattereControllo+=10;break;}
                    case 'W': {carattereControllo+=22;break;}
                    case 'X': {carattereControllo+=25;break;}
                    case 'Y': {carattereControllo+=24;break;}
                    case 'Z': {carattereControllo+=23;break;}
                }
            }
        }

        carattereControllo = carattereControllo % 26;

        switch (carattereControllo) {
            case 0:{lettereCarattereControllo.append("A");break;}
            case 1:{lettereCarattereControllo.append("B");break;}
            case 2:{lettereCarattereControllo.append("C");break;}
            case 3:{lettereCarattereControllo.append("D");break;}
            case 4:{lettereCarattereControllo.append("E");break;}
            case 5:{lettereCarattereControllo.append("F");break;}
            case 6:{lettereCarattereControllo.append("G");break;}
            case 7:{lettereCarattereControllo.append("H");break;}
            case 8:{lettereCarattereControllo.append("I");break;}
            case 9:{lettereCarattereControllo.append("J");break;}
            case 10:{lettereCarattereControllo.append("K");break;}
            case 11:{lettereCarattereControllo.append("L");break;}
            case 12:{lettereCarattereControllo.append("M");break;}
            case 13:{lettereCarattereControllo.append("N");break;}
            case 14:{lettereCarattereControllo.append("O");break;}
            case 15:{lettereCarattereControllo.append("P");break;}
            case 16:{lettereCarattereControllo.append("Q");break;}
            case 17:{lettereCarattereControllo.append("R");break;}
            case 18:{lettereCarattereControllo.append("S");break;}
            case 19:{lettereCarattereControllo.append("T");break;}
            case 20:{lettereCarattereControllo.append("U");break;}
            case 21:{lettereCarattereControllo.append("V");break;}
            case 22:{lettereCarattereControllo.append("W");break;}
            case 23:{lettereCarattereControllo.append("X");break;}
            case 24:{lettereCarattereControllo.append("Y");break;}
            case 25:{lettereCarattereControllo.append("Z");break;}
        }

        return new String(lettereCarattereControllo);
    }

    //==================================================
    //METODI PRIVATI CONTROLLO CODICE FISCALE
    //==================================================

    /**
     * Restituisce true se il contenuto del codice rispetta le regole imposte dalla correttezza di un codice fiscale generico relative a nome/cognome
     * @param codiceNomeCognome stringa contenente la parte del codice fiscale relativa al nome o al cognome
     * @return Boolean relativo alla correttezza del codice (true se corretto, false altrimenti)
     */
    private static Boolean isNomeCognomeValido(String codiceNomeCognome) {
        char c1 = codiceNomeCognome.charAt(0);
        char c2 = codiceNomeCognome.charAt(1);
        char c3 = codiceNomeCognome.charAt(2);

        if (isConsonanteDiversaDaX(c3)) {
            return isConsonanteDiversaDaX(c2) && isConsonanteDiversaDaX(c1);
        } else {
            if (isConsonanteDiversaDaX(c2)) {
                return isConsonanteDiversaDaX(c1);
            } else {
                return true;
            }
        }

    }

    /**
     * Restituisce true se il contenuto del codice rispetta le regole imposte dalla correttezza di un codice fiscale generico relative al cognome
     * @param codiceFiscale Stringa relativa al codiceFiscale
     * @return Boolean relativo alla correttezza del codice (true se corretto, false altrimenti)
     */

    private static Boolean analizzaCognome(String codiceFiscale){
        //compongo stringa cognome
        String codCognome = codiceFiscale.substring(0,3);

        //controllo che ci siano solo lettere
        if (codCognome.matches("^[0-9]+$")) {
            return false;
        }

        return isNomeCognomeValido(codCognome);
    }

    /**
     * Restituisce true se il contenuto del codice rispetta le regole imposte dalla correttezza di un codice fiscale generico relative al nome
     * @param codiceFiscale Stringa relativa al codiceFiscale
     * @return Boolean relativo alla correttezza del codice (true se corretto, false altrimenti)
     */
    private static Boolean analizzaNome(String codiceFiscale) {
        //compongo stringa nome
        String codNome = codiceFiscale.substring(3,6);

        //controllo che ci siano solo lettere
        if (codNome.matches("^[0-9]+$")) {
            return false;
        }

        return isNomeCognomeValido(codNome);
    }

    /**
     * Restituisce true se il contenuto del codice rispetta le regole imposte dalla correttezza di un codice fiscale generico relative al anno di nascita
     * @param codiceFiscale Stringa relativa al codiceFiscale
     * @return Boolean relativo alla correttezza del codice (true se corretto, false altrimenti)
     */
    private static Boolean analizzaAnno(String codiceFiscale) {
        //estrapolo anno
        String annoEx = codiceFiscale.substring(6,8);

        //controllo non ci siano lettere
        if(annoEx.matches("^[A-Z]+$"))
            return false;

        return true;
    }

    /**
     * Restituisce true se il contenuto del codice rispetta le regole imposte dalla correttezza di un codice fiscale generico relative al mese di nascita
     * @param codiceFiscale Stringa relativa al codiceFiscale
     * @return Boolean relativo alla correttezza del codice (true se corretto, false altrimenti)
     */
    private static Boolean analizzaMese(String codiceFiscale) {
        String meseEx = codiceFiscale.substring(8,9);

        //controllo non ci siano numeri
        if(meseEx.matches("^[0-9]+$")){
            return false;
        }
        //controllo se il caratttere e' corretto
        else if(CODICI_MESE.contains(meseEx)) {
            return true;
        }

        return false;
    }

    /**
     * Restituisce true se il contenuto del codice rispetta le regole imposte dalla correttezza di un codice fiscale generico relative al gitono di nascita e al sesso
     * @param codiceFiscale Stringa relativa al codiceFiscale
     * @return Boolean relativo alla correttezza del codice (true se corretto, false altrimenti)
     */
    private static Boolean analizzaGiornoSesso(String codiceFiscale){
        String giornoEx = codiceFiscale.substring(9,11);
        int num = 0;

        //controllo non ci siano lettere
        if(giornoEx.matches("^[A-Z]+$")){
            return false;
        } else {
            num = Integer.parseInt(giornoEx);
        }

        // controllo validità giorno
        String meseEx = codiceFiscale.substring(8,9);
        String caratteriControlloMese = "SDHP";

        if(meseEx.equals("B")) {
            return (num >= 1 && num <= 28) || (num >= 41 && num <= 68);
        }
        else if(caratteriControlloMese.contains(meseEx)) {
            return (num >= 1 && num <= 30) || (num >= 41 && num <= 70);
        }
        else
        {
            return (num >= 1 && num <= 31) || (num >= 41 && num <= 71);
        }
    }

    /**
     * Restituisce true se il contenuto del codice rispetta le regole imposte dalla correttezza di un codice fiscale generico relative al comune di nascita
     * @param codiceFiscale Stringa relativa al codiceFiscale
     * @return Boolean relativo alla correttezza del codice (true se corretto, false altrimenti)
     */
    private static Boolean analizzaComune(String codiceFiscale, ArrayList<Comune> comuni) throws XMLStreamException, FileNotFoundException {
        String comEx = codiceFiscale.substring(11, 15);

        for(Comune c : comuni) {
            if(c.getCodice().equals(comEx)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Restituisce true se il contenuto del codice rispetta le regole imposte dalla correttezza di un codice fiscale generico relative al carattere di controllo
     * @param codiceFiscale Stringa relativa al codiceFiscale
     * @return Boolean relativo alla correttezza del codice (true se corretto, false altrimenti)
     */
    private static Boolean analizzaCarattereDiControllo(String codiceFiscale) {
        String codEx = codiceFiscale.substring(0,15);
        String carattereControlloRelativo = codiceFiscale.substring(15);
        String carattereControlloEffettivo = generaCarattereControlloCf(codEx);

        return carattereControlloRelativo.equals(carattereControlloEffettivo);
    }

}
