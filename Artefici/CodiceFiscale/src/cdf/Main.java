package cdf;

public class Main {

	private static final String CODICI_FISCALI_XML = "codiciFiscali.xml";
	private static final String INPUTPERSONE_XML = "inputPersone.xml";
	private static final String COMUNI_XML = "comuni.xml";

	public static void main(String[] args) {
		
		
		Manager.prelievoDatiComuni(COMUNI_XML);					// lettura e prelievo dati dal file comuni.xml
		Manager.prelievoDatiPersone(INPUTPERSONE_XML);			// lettura e prelievo dati dal file inputPersone.xml
		Manager.prelievoDatiCodiciFiscali(CODICI_FISCALI_XML);	// lettura e prelievo dati dal file codiciFiscali.xml
		
		Manager.generaCodiciFiscaliPersone();                   //richiamo metodo che porta alla genereazione dei codici fiscali di ogni persona contenuta nel file inputPersone.xml
		Manager.verificaValiditaCodiciFiscali();                //richiamo metodo che porta alla verifica della validita' dei codici fiscali del file codiciFiscali.xml
		Manager.verificaPresenzaCodiceFiscaleInFile();			//richiamo metodo che porta alla verifica della presenza dei cod.fisc 
		
		Manager.scritturaFileXML();						        //scrittura del file codiciPersone.xml	
		
	}

}
