package it.unibs.arnaldo.cf;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamConstants;


/**
 * Classe che visualizza le informazioni personali delle persone
 * Estende GestoreXMLReader
 * @author toBdefined
 *
 */
public class XMLReaderPersone extends GestoreXMLReader {

	/**
	 * Richiama il costruttore della superclasse e gli passa il path
	 * @param path percorso del file
	 */
	public XMLReaderPersone(String path) {
		super(path);
	}
	
	
	
	/**
	 * Legge i dati delle persone e li inserisce nell'ArrayList
	 * @return l'ArrayList persone
	 */
	public ArrayList<Persona> read() {
		ArrayList<Persona> persone = new ArrayList<Persona>();

        //int nPersone=-1;
        int id = -1;
        String nome = "";
        String cognome = "";
        String sesso = null;
        String comune = "";
        String data = "";
		
	    try {
	    	while (xmlr.hasNext()) {
	    		switch (xmlr.getEventType()) {
	    		case XMLStreamConstants.START_DOCUMENT: break;
	    		case XMLStreamConstants.START_ELEMENT:
	    			String src = xmlr.getLocalName();
	    			if(src.equals("persona"))
	                    id = Integer.parseInt(xmlr.getAttributeValue(0));
		    		xmlr.next();
		    		switch (src) {
		    			case "nome": nome = xmlr.getText().trim().toUpperCase(); break;
		    			case "cognome": cognome = xmlr.getText().trim().toUpperCase(); break;
		    			case "sesso": sesso = xmlr.getText().toUpperCase().trim().toUpperCase(); break;
		    			case "comune_nascita": comune = xmlr.getText().trim().toUpperCase(); break;
		    			case "data_nascita": data = xmlr.getText().trim(); break;
		    		}break;

	    		case XMLStreamConstants.END_ELEMENT: 
	    			if(xmlr.getLocalName().equals("persona") && id != -1 && !nome.equals("") && !cognome.equals("") && !sesso.equals("") && !comune.equals("") && !data.equals("")) {
	    				persone.add(new Persona(id, cognome, nome, sesso, data, comune));
	    				id = -1;
	    				cognome = "";
	    				nome = "";
	    				sesso = "";
	    				data = "";
		        		comune = "";
		        	}
	    		case XMLStreamConstants.COMMENT: break;
	    		case XMLStreamConstants.CHARACTERS: break;
	    		}
	    		xmlr.next();
	    	}
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
	    return persone;
	}	
}
