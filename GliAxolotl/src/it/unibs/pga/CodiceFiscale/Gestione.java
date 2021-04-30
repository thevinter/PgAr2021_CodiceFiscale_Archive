package it.unibs.pga.CodiceFiscale;

import com.sun.jdi.IntegerValue;

public class Gestione{

    /**
     * una volta preso in input il nome o cognome per creare il codice fiscale
     * bisogna prendere le prime tre consonati. Quindi dato il nome mi ciclo su di esso
     * e se la lettera presa è una consonante ti ritorna vero quindi poi bisognerà tenerne
     * conto per la formazione del codice fiscale
     *
     * Controlla quindi che il carattere passato sia una consonante
     * @param lettera da controllare
     * @return validità
     */
    public boolean controlloConsonanti(char lettera){
        boolean corretto = true;
        if (lettera == 'A' || lettera == 'E' || lettera == 'I' || lettera == 'O' || lettera == 'U' || lettera == ' '){
            corretto = false;
        }
        return corretto;
    }

    /**
     * Metodo richiamato per vedere se il carattere passato sia una vocale
     *
     * @param lettera da controllare
     * @return validità
     */
    public boolean controlloVocali(char lettera){
        boolean corretto = false;
        if (lettera == 'A' || lettera == 'E' || lettera == 'I' || lettera == 'O' || lettera == 'U'){
            corretto = true;
        }
        return corretto;
    }

    /**
     * Metodo che ci viene in soccorso quando creiamo la lettera di controllo
     * I caratteri del codice fiscale in posizione dispari assumeranno un valore
     * secondo il seguente switch
     *
     * @param lettera
     * @return valore della lettera
     */
    public int tabellaDispari(char lettera){
        int valore_dispari;
        switch(lettera){
            case('0'):
            case('A'):
                return valore_dispari = 1;
            case('1'):
            case('B'):
                return valore_dispari = 0;
            case('2'):
            case('C'):
                return valore_dispari = 5;
            case('3'):
            case('D'):
                return valore_dispari = 7;
            case('4'):
            case('E'):
                return valore_dispari = 9;
            case('5'):
            case('F'):
                return valore_dispari = 13;
            case('6'):
            case('G'):
                return valore_dispari = 15;
            case('7'):
            case('H'):
                return valore_dispari = 17;
            case('8'):
            case('I'):
                return valore_dispari = 19;
            case('9'):
            case('J'):
                return valore_dispari= 21;
            case('K'):
                return valore_dispari = 2;
            case('L'):
                return valore_dispari = 4;
            case('M'):
                return valore_dispari = 18;
            case('N'):
                return valore_dispari = 20;
            case('O'):
                return valore_dispari = 11;
            case('P'):
                return valore_dispari = 3;
            case('Q'):
                return valore_dispari = 6;
            case('R'):
                return valore_dispari = 8;
            case('S'):
                return valore_dispari = 12;
            case('T'):
                return valore_dispari = 14;
            case('U'):
                return valore_dispari = 16;
            case('V'):
                return valore_dispari = 10;
            case('W'):
                return valore_dispari = 22;
            case('X'):
                return valore_dispari = 25;
            case('Y'):
                return valore_dispari = 24;
            default:
                return valore_dispari = 23;
        }
    }

    /**
     * Metodo che ci viene in soccorso quando creiamo la lettera di controllo
     * I caratteri del codice fiscale in posizione pari assumeranno un valore
     * secondo il seguente switch
     *
     * @param lettera
     * @return valore della lettera
     */
    public int tabellaPari(char lettera){
        int valore_pari;
        switch(lettera){
            case('0'):
            case('A'):
                return valore_pari = 0;
            case('1'):
            case('B'):
                return valore_pari = 1;
            case('2'):
            case('C'):
                return valore_pari = 2;
            case('3'):
            case('D'):
                return valore_pari = 3;
            case('4'):
            case('E'):
                return valore_pari = 4;
            case('5'):
            case('F'):
                return valore_pari = 5;
            case('6'):
            case('G'):
                return valore_pari = 6;
            case('7'):
            case('H'):
                return valore_pari = 7;
            case('8'):
            case('I'):
                return valore_pari = 8;
            case('9'):
            case('J'):
                return valore_pari= 9;
            case('K'):
                return valore_pari = 10;
            case('L'):
                return valore_pari = 11;
            case('M'):
                return valore_pari = 12;
            case('N'):
                return valore_pari = 13;
            case('O'):
                return valore_pari = 14;
            case('P'):
                return valore_pari = 15;
            case('Q'):
                return valore_pari = 16;
            case('R'):
                return valore_pari = 17;
            case('S'):
                return valore_pari = 18;
            case('T'):
                return valore_pari = 19;
            case('U'):
                return valore_pari = 20;
            case('V'):
                return valore_pari = 21;
            case('W'):
                return valore_pari = 22;
            case('X'):
                return valore_pari = 23;
            case('Y'):
                return valore_pari = 24;
            default:
                return valore_pari = 25;
        }
    }

    /**
     * Metodo che mi serve per la lettera di controllo.
     * Una volta presi e sommati tutti i valori che assumono i quindici caratteri del codice fiscale,
     * dividiamo per 26 e il resto viene convertito da un numero a un carattere che mi rappresenterà
     * la lettera del carattere di controllo
     * @param valore
     * @return lettera di controllo
     */
    public char tabellaConversione(int valore){
        char caratterino;
        switch(valore){
            case(0):
                return caratterino = 'A';
            case(1):
                return caratterino = 'B';
            case(2):
                return caratterino = 'C';
            case(3):
                return caratterino = 'D';
            case(4):
                return caratterino = 'E';
            case(5):
                return caratterino = 'F';
            case(6):
                return caratterino = 'G';
            case(7):
                return caratterino = 'H';
            case(8):
                return caratterino = 'I';
            case(9):
                return caratterino = 'J';
            case(10):
                return caratterino = 'K';
            case(11):
                return caratterino = 'L';
            case(12):
                return caratterino = 'M';
            case(13):
                return caratterino = 'N';
            case(14):
                return caratterino = 'O';
            case(15):
                return caratterino = 'P';
            case(16):
                return caratterino = 'Q';
            case(17):
                return caratterino = 'R';
            case(18):
                return caratterino = 'S';
            case(19):
                return caratterino = 'T';
            case(20):
                return caratterino = 'U';
            case(21):
                return caratterino = 'V';
            case(22):
                return caratterino = 'W';
            case(23):
                return caratterino = 'X';
            case(24):
                return caratterino = 'Y';
            default:
                return caratterino = 'Z';
        }

    }

    /**
     * Metodo che mi converte il mese, dato in numero, nel suo carattere (lettera) corrispondente
     * @param numero_mese
     * @return lettera corrispondente al mese
     */
    public char conversioneMese(int numero_mese){
        char mese;
        switch (numero_mese) {
            case (1):
                return mese = 'A';
            case (2):
                return mese = 'B';
            case (3):
                return mese = 'C';
            case (4):
                return mese = 'D';
            case (5):
                return mese = 'E';
            case (6):
                return mese = 'H';
            case (7):
                return mese = 'L';
            case (8):
                return mese = 'M';
            case (9):
                return mese = 'P';
            case (10):
                return mese = 'R';
            case (11):
                return mese = 'S';
            default:
                return mese = 'T';
        }
    }
}



