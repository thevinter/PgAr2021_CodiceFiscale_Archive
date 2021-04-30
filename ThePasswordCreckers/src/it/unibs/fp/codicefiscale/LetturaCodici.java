package it.unibs.fp.codicefiscale;

import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class LetturaCodici {

	private static String NOMEFILE="codiciFiscali.xml";
	private static ArrayList<String>codiciCorretti=new ArrayList();
	private static ArrayList<String>codiciSbagliati=new ArrayList();
	
	public static void main(String[] args) throws XMLStreamException {
		
		//System.out.println(letturaCodicixml(true).size());
		
		//System.out.println(letturaCodicixml(false));
	}
	
	public static XMLStreamReader creazioneReader()
	{
		XMLInputFactory xmlif=null;
		XMLStreamReader xmlr=null;

		try{
			xmlif=XMLInputFactory.newInstance();
			xmlr=xmlif.createXMLStreamReader("inputPersone.xml",new FileInputStream(NOMEFILE));
		}
		
		catch (Exception e){
			System.out.println("Errore inizializzazione del reader:");
			System.out.println(e.getMessage());
		}
		return xmlr;
	}
	
	/**
	 * 
	 * legge tutti i codici ed in base al valore in input restituisce un arraylist di codici giusti o sbagliati
	 * inserisci treu per avere quelli corretti false per avere quelli sbagliati
	 * @return
	 * @throws XMLStreamException
	 */
	public static ArrayList<String> letturaCodicixml(boolean type) throws XMLStreamException 
	{		
		if(codiciCorretti.size()==0 && codiciSbagliati.size()==0)
		{
			System.out.println("entra nel calcolo");
			XMLStreamReader xmlr=creazioneReader();
			
			String cod=null;
			
			try {
				while(xmlr.hasNext()){
					switch (xmlr.getEventType()){
			
						case  XMLStreamConstants.END_ELEMENT:
							
							if(xmlr.getLocalName().equals("codice"))
							{
								
								if(ControlloCodiceFiscale.controllaCodice(cod)) {
									codiciCorretti.add(cod)	;	
								}
								else {
									codiciSbagliati.add(cod);
										
								}
								
							}
							break;
							
						case XMLStreamConstants.CHARACTERS:
							if(xmlr.getText().trim().length() >0)
								cod=xmlr.getText();
								
							break;
					}
					xmlr.next();
				}
			} 
			
			catch (XMLStreamException e) {
				e.printStackTrace();
			}
		}
			
		
		if(type)
		{
			return codiciCorretti;
		}
		
		return codiciSbagliati;
		
	}
	
	
	
	

}
