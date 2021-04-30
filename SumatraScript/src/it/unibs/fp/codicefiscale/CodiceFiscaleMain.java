package it.unibs.fp.codicefiscale;

import java.util.ArrayList;

public class CodiceFiscaleMain {
	public static void main(String[] args) {
		ArrayList<Persona> persone = XmlReader.readerPersona();
		ArrayList<Comune> comuni = XmlReader.readerComuni();
		CodiceFiscaleUtil.creaCodiceFiscale(persone, comuni);
		ArrayList<String> codici_fiscali_prelevati = XmlReader.readerCodiciFiscali();
		XmlWriter.writerCodiciPersone(persone, codici_fiscali_prelevati, comuni);
	}

}
