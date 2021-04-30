package theFightClub_Cod_Fiscali.unibs.it;

public class CodiceFiscale {

    public static String generaCodice(Persona persona) {
        String cognome = generaCognome(String.valueOf(persona.getCOGNOME()));
        String nome = generaNome(String.valueOf(persona.getNOME()));
        String anno = generaAnno(String.valueOf(persona.getANNO()));
        String mese = generaMese(String.valueOf(persona.getMESE()));
        String giorno = generaGiorno(String.valueOf(persona.getGIORNO()), String.valueOf(persona.getSESSO()));
        String comune = String.valueOf(persona.getCOMUNE());

        String codiceTemp = cognome+nome+anno+mese+giorno+comune;

        String carFinale = generaCarSpeciale(codiceTemp);


        return codiceTemp+carFinale;


    }

    public static String generaCognome(String cognome) {

        //da Wikipedia: se il cognome ha solo 2 lettere allora aggiungo una X;
        if (cognome.length() == 2) {
            cognome = cognome + 'X';
            return cognome;
        }

        String new_Cognome; //sara il mio cognome senza vocali;
        new_Cognome = cognome.replaceAll("[aeiouAEIOU]",
                "");
        //se il cognome senza vocali ha esattamente 3 lettere lo mantengo cosi' com'è;
        if (new_Cognome.length() == 3) {
            return new_Cognome;

            //se il cognome senza vocali ha meno di 3 lettere allora aggiungo la prima vocale del cognome originale;
        } else if (new_Cognome.length() < 3) {
            for (int i = 0; i < cognome.length(); i++) {
                char x;
                x = cognome.charAt(i);
                if (cognome.charAt(i) == 'A' || cognome.charAt(i) == 'I' || cognome.charAt(i) == 'E'
                        || cognome.charAt(i) == 'O' || cognome.charAt(i) == 'U'){
                    new_Cognome = new_Cognome+x;
                    break;}


            }
        }

        return new_Cognome;
    }

    public static String generaNome(String nome) {
        //se il nome è fatto di sole due lettere aggiungo una X. es. Fo -> FOX; Po -> POX;
        if (nome.length() == 2) {
            return nome + 'X';
        }

        String new_Nome; //sarà il nome senza vocali;
        new_Nome = nome.replaceAll("[aeiouAEIOU]",
                "");
        if (new_Nome.length() == 3) {
            return new_Nome;
        } else if (new_Nome.length() < 3) {
            for (int i = 0; i < nome.length(); i++) {
                char x;
                x = nome.charAt(i);
                if (nome.charAt(i) == 'A' || nome.charAt(i) == 'I' || nome.charAt(i) == 'E' ||
                        nome.charAt(i) == 'O' || nome.charAt(i) == 'U'){
                    new_Nome = new_Nome+x;
                    break;
                }
            }
            return new_Nome;
        }

        return new_Nome.substring(0, 3);
    }

    public static String generaAnno(String anno){
        anno = anno.substring(2,4);
        return anno;
    }

    public static String generaMese(String mese) {
        if (mese.equals("01")) {
            return "A";
        }
        if (mese.equals("02")) {
            return "B";
        }
        if (mese.equals("03")) {
            return "C";
        }
        if (mese.equals("04")) {
            return "D";
        }
        if (mese.equals("05")) {
            return "E";
        }
        if (mese.equals("06")) {
            return "H";
        }
        if (mese.equals("07")) {
            return "L";
        }
        if (mese.equals("08")) {
            return "M";
        }
        if (mese.equals("09")) {
            return "P";
        }
        if (mese.equals("10")) {
            return "R";
        }
        if (mese.equals("11")) {
            return "S";
        }
        if (mese.equals("12")) {
            return "T";
        }
        return null;
    }

    public static String generaGiorno (String giorno, String sesso) {

         if (sesso.equals("F")){
            int g = Integer.parseInt(giorno);
           giorno = String.valueOf(40 + g);
           return giorno;
       }
         else if(sesso.equals("M")){
           return giorno;
        }
           else return null;
           //return sesso.equals("F") ? String.valueOf(Integer.parseInt(giorno) + 40) : giorno;
    }

    /*a partire dai 15 caratteri alfanumerici ricavati, determiniamo il carattere di controllo in base all'algoritmo
    che opera nel seguente modo:
    1. mettiamo da una parte i caratteri alfanumerici che si trovano in posizione dispari e da un'altra
    quelli che si trovano in posizione pari.
    2. fatto questo, i carattteri vengono convertiti secondo speciali regolo.
    (per dettagli vedi wikipedia);
     */

    public static String generaCarSpeciale(String codTemp) {
        int codice_finale;
        int totale;
        int totale_pari=0;
        int totale_dispari=0;


        /* le stringhe in cui salvo rispettivamente i caratteri con indice pari o dispari*/
        StringBuffer pari = new StringBuffer();
        StringBuffer dispari = new StringBuffer();
        for (int j = 0; j < codTemp.length(); j++) {
            if (j % 2 == 0) {
                pari.append(codTemp.charAt(j));
            } else {
                dispari.append(codTemp.charAt(j));
            }
        }

        //per i caratteri con indice pari procedo nel seguente modo:
        final String alphabet = "ABCDEFGHIJKLMNOPQRST";
        for(int i = 0; i < pari.length(); i++){
            /*se il carattere è un intero lo sommo al totale dei pari*/
            if(Character.isDigit(pari.charAt(i)))
                totale_pari+=pari.charAt(i);
                /*se il carattere è un char allora li assegno il numero in base all'ordine che occupa nell'alfabeto*/
            else
                totale_pari+=(alphabet.indexOf(pari.charAt(i)));
        }

        //per i caratteri con indice DISPARI procedo nel seguente modo:
        for(int i=0; i < dispari.length(); i++){
            //verifico se in quella posizione c'è un intero, 0 o 1;
            if(Character.isDigit(dispari.charAt(i)) && dispari.charAt(i) != 0 && dispari.charAt(i) != 1){
                if((dispari.charAt(i))%2==0)
                    totale_dispari += dispari.charAt(i)+1;
            }
            else if(dispari.charAt(i)=='1')
                totale_dispari+=0;
            else if(dispari.charAt(i) == '0')
                totale_dispari+=1;
            else if(dispari.charAt(i)=='A')
                totale_dispari+=1;
            else if(dispari.charAt(i)=='B')
                totale_dispari+=0;
            else if(dispari.charAt(i)=='C')
                totale_dispari+=5;
            else if(dispari.charAt(i)=='D')
                totale_dispari+=7;
            else if(dispari.charAt(i)=='E')
                totale_dispari+=9;
            else if(dispari.charAt(i)=='F')
                totale_dispari+=13;
            else if(dispari.charAt(i)=='G')
                totale_dispari+=15;
            else if(dispari.charAt(i)=='H')
                totale_dispari+=17;
            else if(dispari.charAt(i)=='I')
                totale_dispari+=19;
            else if(dispari.charAt(i)=='J')
                totale_dispari+=21;
            else if(dispari.charAt(i)=='K')
                totale_dispari+=2;
            else if(dispari.charAt(i)=='L')
                totale_dispari+=4;
            else if(dispari.charAt(i)=='M')
                totale_dispari+=18;
            else if(dispari.charAt(i)=='N')
                totale_dispari+=20;
            else if(dispari.charAt(i)=='O')
                totale_dispari+=11;
            else if(dispari.charAt(i)=='P')
                totale_dispari+=3;
            else if(dispari.charAt(i)=='Q')
                totale_dispari+=6;
            else if(dispari.charAt(i)=='R')
                totale_dispari+=8;
            else if(dispari.charAt(i)=='S')
                totale_dispari+=12;
            else if(dispari.charAt(i)=='T')
                totale_dispari+=14;
            else if(dispari.charAt(i)=='U')
                totale_dispari+=16;
            else if(dispari.charAt(i)=='V')
                totale_dispari+=10;
            else if(dispari.charAt(i)=='W')
                totale_dispari+=22;
            else if(dispari.charAt(i)=='X')
                totale_dispari+=25;
            else if(dispari.charAt(i)=='Y')
                totale_dispari+=24;
            else if(dispari.charAt(i)=='Z')
                totale_dispari+=23;
        }

        totale = totale_dispari+totale_pari;
        codice_finale=totale%26;

        String alphabet1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String carFinale = Character.toString(alphabet1.charAt(codice_finale));
        return carFinale;

    }
}