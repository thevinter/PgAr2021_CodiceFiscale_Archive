package it.unibs.fp.codicefiscale;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class XmlWriter {
	private static final String ASSENTE = "ASSENTE";

	public static void writerCodiciPersone(ArrayList<Persona> persone, ArrayList<String> codici_fiscali_prelevati, ArrayList<Comune> comuni) {
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;
		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("src/xml/codiciPersone.xml"), "utf-8");
			xmlw.writeStartDocument("utf-8", "1.0");
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del writer:");
			System.out.println(e.getMessage());
		}

		try { // blocco try per raccogliere eccezioni
			xmlw.writeCharacters("\n");
			xmlw.writeStartElement("output"); // scrittura del tag radice <output>

			xmlw.writeCharacters("\n\t");
			xmlw.writeStartElement("persone");
			xmlw.writeAttribute("numero", Integer.toString(persone.size()));
			for (int i = 0; i < persone.size(); i++) {
				xmlw.writeCharacters("\n\t\t");
				xmlw.writeStartElement("persona");
				xmlw.writeAttribute("id", Integer.toString(i));

				xmlw.writeCharacters("\n\t\t\t");
				xmlw.writeStartElement("nome");
				xmlw.writeCharacters(persone.get(i).getNome());
				xmlw.writeEndElement();

				xmlw.writeCharacters("\n\t\t\t");
				xmlw.writeStartElement("cognome");
				xmlw.writeCharacters(persone.get(i).getCognome());
				xmlw.writeEndElement();

				xmlw.writeCharacters("\n\t\t\t");
				xmlw.writeStartElement("sesso");
				xmlw.writeCharacters(persone.get(i).getSesso().toString());
				xmlw.writeEndElement();

				xmlw.writeCharacters("\n\t\t\t");
				xmlw.writeStartElement("comune_nascita");
				xmlw.writeCharacters(persone.get(i).getComune());
				xmlw.writeEndElement();

				xmlw.writeCharacters("\n\t\t\t");
				xmlw.writeStartElement("data_nascita");
				xmlw.writeCharacters(persone.get(i).getData_di_nascita());
				xmlw.writeEndElement();

				xmlw.writeCharacters("\n\t\t\t");
				xmlw.writeStartElement("codice_fiscale");
				if (isPresente(persone.get(i).getCodice_fiscale(), codici_fiscali_prelevati))
					xmlw.writeCharacters(persone.get(i).getCodice_fiscale());
				else
					xmlw.writeCharacters(ASSENTE);
				xmlw.writeEndElement();

				xmlw.writeCharacters("\n\t\t");
				xmlw.writeEndElement();
			}
			xmlw.writeCharacters("\n\t");
			xmlw.writeEndElement();

			ArrayList<String> invalidi = CodiceFiscaleUtil.cercaInvalidi(codici_fiscali_prelevati);
			ArrayList<String> spaiati = CodiceFiscaleUtil.cercaSpaiati(codici_fiscali_prelevati, persone, invalidi);

			xmlw.writeCharacters("\n\t");
			xmlw.writeStartElement("invalidi");
			xmlw.writeAttribute("numero", Integer.toString(invalidi.size()));
			for (int i = 0; i < invalidi.size(); i++) {
				xmlw.writeCharacters("\n\t\t");
				xmlw.writeStartElement("codice");
				xmlw.writeCharacters(invalidi.get(i));
				xmlw.writeEndElement();
			}
			xmlw.writeCharacters("\n\t");
			xmlw.writeEndElement();

			xmlw.writeCharacters("\n\t");
			xmlw.writeStartElement("spaiati");
			xmlw.writeAttribute("numero", Integer.toString(spaiati.size()));
			for (int i = 0; i < spaiati.size(); i++) {
				xmlw.writeCharacters("\n\t\t");
				xmlw.writeStartElement("codice");
				xmlw.writeCharacters(spaiati.get(i));
				xmlw.writeEndElement();
			}
			xmlw.writeCharacters("\n\t");
			xmlw.writeEndElement();

			xmlw.writeCharacters("\n");
			xmlw.writeEndElement(); // chiusura di </output>
			xmlw.writeEndDocument(); // scrittura della fine del documento
			xmlw.flush(); // svuota il buffer e procede alla scrittura
			xmlw.close(); // chiusura del documento e delle risorse impiegate
		} catch (Exception e) { // se c’è un errore viene eseguita questa parte
			System.out.println("Errore nella scrittura " + e.getMessage());
		}
	}
	
	public static boolean isPresente(String codice_fiscale_persona, ArrayList<String> codici_fiscali_prelevati) {
        for (int i = 0; i < codici_fiscali_prelevati.size(); i++)
            if (codice_fiscale_persona.equals(codici_fiscali_prelevati.get(i)))
                return true;
        return false;
    }
}
