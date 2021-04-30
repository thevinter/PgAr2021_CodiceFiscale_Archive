package it.unibs.arnaldo.geriatricpark.codicefiscale;

import javax.xml.stream.XMLStreamReader;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamConstants;

public class TestN {

	public static void main(String[] args) throws Exception {
		/*
		XMLStreamReader pippo = null;
		int beforeElementType = 0;
		String beforeElementName = "";
		
		pippo = GestoreXML.impostaReader("./xml/codiciFiscali.xml");
		
		while(pippo.getEventType() != XMLStreamConstants.CHARACTERS) {
			beforeElementType = pippo.getEventType();
			if(pippo.getEventType() == XMLStreamConstants.START_ELEMENT)
				beforeElementName = pippo.getLocalName();
			pippo.next();
		}//
		
		while(pippo.hasNext()) {
			
			if(beforeElementType == XMLStreamConstants.START_ELEMENT && beforeElementName.equals("codice"))
				System.out.println(pippo.getText());
			
			beforeElementType = pippo.getEventType();
			if(pippo.getEventType() == XMLStreamConstants.START_ELEMENT)
				beforeElementName = pippo.getLocalName();
			pippo.next();
		}
		/*
		
		ArrayList<Persona> persone = new ArrayList<Persona>();
		
		persone = GestoreXML.creaElencoPersone();
		
		
		for( Persona persona : persone) {
			System.out.println(persona.toString());
		}
		
		CodiceFiscale cf = new CodiceFiscale("");
		System.out.println(cf.correttezzaSintassi());
		
		ArrayList<CodiceFiscale> codici = new ArrayList<CodiceFiscale>();
		codici = GestoreXML.estraiCF();
		
		System.out.println(codici.get(1).getValore());
		for( CodiceFiscale codice : codici) {
			System.out.println(codice.getValore());
		}*/
		
		//System.out.println(GestoreXML.estraiCodiceComune("CAVALLINO-TREPORTI"));
		
		Persona persona = new Persona(0, "MOHAMED", "NASSAR", 'M', "CAMISANO", 1997, 07, 19);
		
		CodiceFiscale cf = new CodiceFiscale(persona);
		
		System.out.println(cf.getValore());

	}

}
