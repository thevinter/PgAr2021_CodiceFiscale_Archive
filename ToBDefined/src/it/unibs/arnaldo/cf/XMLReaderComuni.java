package it.unibs.arnaldo.cf;

import java.util.HashMap;

import javax.xml.stream.XMLStreamConstants;


/**
 * Classe che legge i comuni (nomi e codice)
 * Eredita i metodi della classe astratta GestoreXMLReader
 * @author toBdefined
 */
public class XMLReaderComuni extends GestoreXMLReader {

	/**
	 * Richiama il costruttore della superclasse e gli passa il path
	 * @param path percorso del file
	 */
	public XMLReaderComuni(String path) {
		super(path);
	}
	
	
	/**
	 * Legge il file XML e aggiunge i comuni all'interno dell'HashMap comuni
	 * In caso di eccezione, quest'ultima viene stampata
	 * @return i comuni
	 */
	public HashMap<String,String> read() {
		HashMap<String,String> comuni = new HashMap<String, String>();
		String nome = "";
		String codice = "";
		
	    try {
	    	while (xmlr.hasNext()) {
	    		switch (xmlr.getEventType()) {
	    		case XMLStreamConstants.START_DOCUMENT: break;
	    		case XMLStreamConstants.START_ELEMENT:
	    			String src = xmlr.getLocalName();
		    		xmlr.next();
		    		switch (src) {
                    	case "nome": nome = xmlr.getText(); break;
                    	case "codice": codice = xmlr.getText(); break;
		    		}
		    		break;

	    		case XMLStreamConstants.END_ELEMENT: 
	    			if(!nome.equals("") && !codice.equals("")) {
		        		comuni.put(nome, codice);
		        		nome = "";
		        		codice = "";
		        	}
	    			break;
	    		case XMLStreamConstants.COMMENT: break;
	    		case XMLStreamConstants.CHARACTERS: break;
	    		}
	    		xmlr.next();
	    	}
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
	    return comuni;
	}
}
