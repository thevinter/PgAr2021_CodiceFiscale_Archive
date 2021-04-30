package gli_sprogrammatori.codicefiscale;

import java.util.ArrayList;

/**
 * <p>
 * La classe <strong>App</strong> è la classe principale dove è racchiuso il
 * main e da cui parte il programma
 * </p>
 * 
 * @author Alessandro Muscio, Tommaso Bianchin, Gianmarco Gamo
 * @version 1.0
 */
public class App {
  public static void main(String[] args) throws Exception {
    LeggiScriviXML leggi_scrivi_xml = new LeggiScriviXML("XMLfiles/inputPersone.xml", "XMLfiles/codiciFiscali.xml",
        "XMLfiles/comuni.xml", "codiciPersone.xml", "utf-8");

    GestioneCF.setComuniCodici(leggi_scrivi_xml.leggiCodiciComuni());

    ArrayList<Persona> persone = leggi_scrivi_xml.leggiPersone();
    ArrayList<String> codici_fiscali = leggi_scrivi_xml.leggiCodiciFiscali();

    ArrayList<String> codici_fiscali_invalidi = new ArrayList<String>();
    String codice_fiscale_attuale;
    boolean not_found;

    for (Persona persona : persone) {
      not_found = true;

      for (int i = 0; i < codici_fiscali.size() && not_found; i++) {
        codice_fiscale_attuale = codici_fiscali.get(i);

        if (GestioneCF.isCodiceFiscaleValido(codice_fiscale_attuale)) {
          if (persona.getCodiceFiscale().equals(codice_fiscale_attuale)) {
            not_found = false;
            codici_fiscali.remove(i);
          }
        } else {
          codici_fiscali_invalidi.add(codice_fiscale_attuale);
          codici_fiscali.remove(i);
        }
      }

      if (not_found)
        persona.setCodiceFiscale("ASSENTE");
    }

    leggi_scrivi_xml.scriviOutput(persone, codici_fiscali_invalidi, codici_fiscali);
  }
}