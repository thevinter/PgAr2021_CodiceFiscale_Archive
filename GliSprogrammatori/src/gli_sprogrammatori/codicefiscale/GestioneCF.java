package gli_sprogrammatori.codicefiscale;

import java.util.HashMap;

/**
 * <p>
 * La classe <strong>GestioneCF</strong> mi permette di creare e validare vari
 * codici fiscali. Contiene dei metodi di utilità generale per i codici fiscali
 * </p>
 * 
 * @author Alessandro Muscio, Tommaso Bianchin, Gianmarco Gamo
 * @version 1.0
 */
public class GestioneCF {
  /**
   * Indica il <strong>valore da incrementare</strong> in caso la persona sia di
   * femmina
   */
  private static final int INCREMENTO_FEMMINE = 40;
  /**
   * Contiene tutti i nomi dei comuni, usati come chiave, e i rispettivi codici
   * identificativi, usati come valori
   */
  private static HashMap<String, String> comuni_codici;

  /**
   * Imposta la HashMap <strong>comuni_codici</strong>
   * 
   * @param comuni_codici Valore da assegnare a <strong>comuni_codici</strong>
   */
  public static void setComuniCodici(HashMap<String, String> comuni_codici) {
    GestioneCF.comuni_codici = comuni_codici;
  }

  /**
   * Dato il nome o il cognome lo codifica secondo il rispettivo algoritmo per
   * inserirlo nel codice fiscale
   * 
   * @param cognome_nome Rappresenta il cognome o il nome da codificare
   * @param is_cognome   Indica se cognome_nome rappresenta il cognome oppure no
   * @return Una <code>String</code> rappresentante cognome_nome codificato
   */
  public static String codificaCognomeNome(String cognome_nome, boolean is_cognome) {
    String stringa_codificata = "";
    // Modifica tutte le vocali con "" della stringa data
    String consonanti = cognome_nome.replaceAll("(?i)[aeiou]", "");
    // Modifica tutte le non vocali con "" della stringa data
    String vocali = cognome_nome.replaceAll("(?i)[^aeiou]", "");

    if (consonanti.length() < 3 || is_cognome) {
      stringa_codificata += consonanti;
      stringa_codificata += vocali;

      while (stringa_codificata.length() < 3)
        stringa_codificata += "X";
    } else {
      // Presumo prende il primo, il terzo e il quarto carattere della stringa data
      consonanti = consonanti.replaceAll("(?i)^(\\w).(\\w{2})(.?)", "$1$2");
      stringa_codificata += consonanti;
    }

    return stringa_codificata.substring(0, 3);
  }

  /**
   * Data la data di nascita e il sesso li codifica secondo il rispettivo
   * algoritmo per inserirli nel codice fiscale
   * 
   * @param data_nascita Rappresenta la data di nascita da codificare
   * @param sesso        Indica il sesso da codificare
   * @return Una <code>String</code> rappresentante la data di nascita e il sesso
   *         codificati
   */
  public static String codificaDataNascitaEsesso(String data_nascita, char sesso) {
    String stringa_codificata = data_nascita.substring(2, 4);
    int numero_mese_nascita = Integer.parseInt(data_nascita.substring(5, 7));
    int giorno_nascita = Integer.parseInt(data_nascita.substring(8));

    switch (numero_mese_nascita) {
      case 1:
        stringa_codificata += "A";
        break;
      case 2:
        stringa_codificata += "B";
        break;
      case 3:
        stringa_codificata += "C";
        break;
      case 4:
        stringa_codificata += "D";
        break;
      case 5:
        stringa_codificata += "E";
        break;
      case 6:
        stringa_codificata += "H";
        break;
      case 7:
        stringa_codificata += "L";
        break;
      case 8:
        stringa_codificata += "M";
        break;
      case 9:
        stringa_codificata += "P";
        break;
      case 10:
        stringa_codificata += "R";
        break;
      case 11:
        stringa_codificata += "S";
        break;
      case 12:
        stringa_codificata += "T";
        break;
    }

    if (sesso == 'F') {
      giorno_nascita += INCREMENTO_FEMMINE;
      stringa_codificata += String.valueOf(giorno_nascita);
    } else {
      if (giorno_nascita > 0 && giorno_nascita < 10)
        stringa_codificata += "0" + String.valueOf(giorno_nascita);
      else
        stringa_codificata += String.valueOf(giorno_nascita);
    }

    return stringa_codificata;
  }

  /**
   * Dato il comune di nascita restituisce il rispettivo codice associativo
   * 
   * @param comune_nascita Rappresenta il comune di nascita di cui si cerca il
   *                       codice associativo
   * @return Una <code>String</code> rappresentante il codice identificativo
   */
  public static String getCodiceComune(String comune_nascita) {
    return comuni_codici.get(comune_nascita);
  }

  /**
   * Tramite l'apposito algoritmo genera il carattere di controllo del codice
   * fiscale
   * 
   * @param cf_incompleto Rappresenta i caratteri fin'ora trovati del codice
   *                      fiscale
   * @return Una <code>String</code> rappresentante il carattere di controllo
   */
  public static String generazioneCarattereControllo(String cf_incompleto) {
    int sommatoria = 0;

    for (int i = 0; i < cf_incompleto.length(); i++) {
      if (i % 2 == 0) {
        switch (cf_incompleto.charAt(i)) {
          case '0':
          case 'A':
            sommatoria += 1;
            break;

          case '2':
          case 'C':
            sommatoria += 5;
            break;

          case '3':
          case 'D':
            sommatoria += 7;
            break;

          case '4':
          case 'E':
            sommatoria += 9;
            break;

          case '5':
          case 'F':
            sommatoria += 13;
            break;

          case '6':
          case 'G':
            sommatoria += 15;
            break;

          case '7':
          case 'H':
            sommatoria += 17;
            break;

          case '8':
          case 'I':
            sommatoria += 19;
            break;

          case '9':
          case 'J':
            sommatoria += 21;
            break;

          case 'K':
            sommatoria += 2;
            break;

          case 'L':
            sommatoria += 4;
            break;

          case 'M':
            sommatoria += 18;
            break;

          case 'N':
            sommatoria += 20;
            break;

          case 'O':
            sommatoria += 11;
            break;

          case 'P':
            sommatoria += 3;
            break;

          case 'Q':
            sommatoria += 6;
            break;

          case 'R':
            sommatoria += 8;
            break;

          case 'S':
            sommatoria += 12;
            break;

          case 'T':
            sommatoria += 14;
            break;

          case 'U':
            sommatoria += 16;
            break;

          case 'V':
            sommatoria += 10;
            break;

          case 'W':
            sommatoria += 22;
            break;

          case 'X':
            sommatoria += 25;
            break;

          case 'Y':
            sommatoria += 24;
            break;

          case 'Z':
            sommatoria += 23;
            break;
        }
      } else {
        switch (cf_incompleto.charAt(i)) {
          case '1':
          case 'B':
            sommatoria += 1;
            break;

          case '2':
          case 'C':
            sommatoria += 2;
            break;

          case '3':
          case 'D':
            sommatoria += 3;
            break;

          case '4':
          case 'E':
            sommatoria += 4;
            break;

          case '5':
          case 'F':
            sommatoria += 5;
            break;

          case '6':
          case 'G':
            sommatoria += 6;
            break;

          case '7':
          case 'H':
            sommatoria += 7;
            break;

          case '8':
          case 'I':
            sommatoria += 8;
            break;

          case '9':
          case 'J':
            sommatoria += 9;
            break;

          case 'K':
            sommatoria += 10;
            break;

          case 'L':
            sommatoria += 11;
            break;

          case 'M':
            sommatoria += 12;
            break;

          case 'N':
            sommatoria += 13;
            break;

          case 'O':
            sommatoria += 14;
            break;

          case 'P':
            sommatoria += 15;
            break;

          case 'Q':
            sommatoria += 16;
            break;

          case 'R':
            sommatoria += 17;
            break;

          case 'S':
            sommatoria += 18;
            break;

          case 'T':
            sommatoria += 19;
            break;

          case 'U':
            sommatoria += 20;
            break;

          case 'V':
            sommatoria += 21;
            break;

          case 'W':
            sommatoria += 22;
            break;

          case 'X':
            sommatoria += 23;
            break;

          case 'Y':
            sommatoria += 24;
            break;

          case 'Z':
            sommatoria += 25;
            break;
        }
      }
    }

    switch (sommatoria % 26) {
      case 0:
        return "A";

      case 1:
        return "B";

      case 2:
        return "C";

      case 3:
        return "D";

      case 4:
        return "E";

      case 5:
        return "F";

      case 6:
        return "G";

      case 7:
        return "H";

      case 8:
        return "I";

      case 9:
        return "J";

      case 10:
        return "K";

      case 11:
        return "L";

      case 12:
        return "M";

      case 13:
        return "N";

      case 14:
        return "O";

      case 15:
        return "P";

      case 16:
        return "Q";

      case 17:
        return "R";

      case 18:
        return "S";

      case 19:
        return "T";

      case 20:
        return "U";

      case 21:
        return "V";

      case 22:
        return "W";

      case 23:
        return "X";

      case 24:
        return "Y";

      case 25:
        return "Z";
    }

    return "";
  }

  /**
   * Verifica la validità del codice fiscale
   * 
   * @param codice_fiscale Rappresenta il codice fiscale di cui si vuole
   *                       verificare la validità
   * @return Un <code>boolean</code> rappresentante se il codice fiscale è valido
   *         o no
   */
  public static boolean isCodiceFiscaleValido(String codice_fiscale) {
    int giorno_nascita;
    String mese_nascita, carattere_di_controllo;

    // controllo caratteri e cifre nelle posizioni corrette
    if (!codice_fiscale.matches("(?i)^[a-z]{6}\\d{2}[a-z]{1}\\d{2}[a-z]{1}\\d{3}[a-z]"))
      return false;

    giorno_nascita = Integer.parseInt(codice_fiscale.substring(9, 11));
    mese_nascita = codice_fiscale.substring(8, 9);
    carattere_di_controllo = codice_fiscale.substring(15);

    // validitá giorno - piccolo controllo per vedere se é da 1-31 o 41-71 (per le
    // femmine)
    if (giorno_nascita == 0 || giorno_nascita > 71 || (giorno_nascita > 31 && giorno_nascita < 41))
      return false;

    // validitá mese (esistenza)
    if (!codice_fiscale.matches("(?i)^.{8}[abcdehlmprst].{7}"))
      return false;

    // numero giorni del mese
    if (giorno_nascita > INCREMENTO_FEMMINE)
      giorno_nascita -= INCREMENTO_FEMMINE;

    if (giorno_nascita < 1)
      return false;

    switch (mese_nascita) {
      case "D":
      case "H":
      case "P":
      case "S":
        if (giorno_nascita > 30)
          return false;
        break;

      case "B":
        if (giorno_nascita > 28)
          return false;
        break;

      default:
        if (giorno_nascita > 31)
          return false;
        break;
    }

    // corretteza carattere di controllo (usando lo stesso algoritmo)
    if (!carattere_di_controllo.equals(generazioneCarattereControllo(codice_fiscale.substring(0, 15))))
      return false;

    // validitá nome
    if (!GestioneCF.validitaNomeCognome(codice_fiscale.substring(0, 3)))
      return false;

    // validitá cognome
    if (!GestioneCF.validitaNomeCognome(codice_fiscale.substring(3, 6)))
      return false;

    return true;

  }

  /**
   * Verifica la validità dei caratteri del nome e del cognome del codice fiscale
   * 
   * @param str I caratteri da verificare
   * @return Un <code>boolean</code> rappresentante se i caratteri sono validi
   *         oppure no
   */
  private static boolean validitaNomeCognome(String str) {
    char ultimaLettera = str.charAt(str.length() - 1);
    String consonanti = str.replaceAll("(?i)[aeiou]", "");
    String vocali = str.replaceAll("(?i)[^aeiou]", "");

    if (!str.equals(consonanti + vocali) && ultimaLettera != 'X')
      return false;

    return true;
  }
}
