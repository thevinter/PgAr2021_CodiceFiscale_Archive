package arnaldo.anno2021.triumvirato.codicefiscale;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

public class CodiceFiscaleMain {
	
	/** fornisce una breve spiegazione del funzionamento del programma per consentire all'eventuale utente ignaro di utilizzarlo
	 */
	public static void provideProgramInformation() {
		System.out.println(Constants.INFORMATION_MESSAGE);
	}
	
	/** prende in input i dati relativi a persone, comuni e codici fiscali usando l'apposita classe InputOutputXML, dopodiché li passa all'elaboratore dei dati
	 */
	public static void dataInput() {
		ArrayList<Comune> datiComuni=InputOutputXML.prendiInInputComuni(Constants.PATH_RELATIVO_COMUNI);//importante che i comuni vengano presi prima delle persone
		ArrayList<Persona> datiPersone=InputOutputXML.prendiInInputPersone(Constants.PATH_RELATIVO_PERSONE);
		ArrayList<String> datiCodiciFiscali=InputOutputXML.prendiInInputCodiciFiscali(Constants.PATH_RELATIVO_CODICI_FISCALI);
		
		
		DataProcessor.setInitialData(datiPersone, datiCodiciFiscali, datiComuni);
	}
	
	/** esegue l'output dei dati elaborati da DataProcessor, chiamando la classe InputOutputXML
	 */
	private static void dataOutput() {
		if(DataProcessor.isExecutable()) {
			try {
				InputOutputXML.OutputXML(DataProcessor.getListaPersone(), DataProcessor.getListaErrati().getCodiciInvalidi(),DataProcessor.getListaErrati().getCodiciSpaiati());
			}catch (Exception e) {
				
			}
			
		}
	}
	
	/**
	 * Notifica l'utente se il programma è stato eseguito senza incontrare errori
	 */
	private static void errorCheck() {
		if(DataProcessor.isErrorLess()&&DataProcessor.isExecutable()) {
			System.out.println(Constants.ALERT_MESSAGE_SUCCESSFUL_EXECUTION);
		}
	}
	
	/**
	 * Metodo main, fa eseguire il programma avviando le quattro fasi di informazione per l'utente, input, elaborazione e output,  c'è poi un'ultima fase di chiusura che informa l'utente che il programma è stato eseguito con successo qualora non si sia verificato nessun errore
	 * @param args
	 * @throws XMLStreamException
	 */
	public static void main(String[] args) throws XMLStreamException {
		
		provideProgramInformation();
		
		dataInput();
				
		DataProcessor.elaboraDati();
		
		dataOutput();
		
		errorCheck();

	}


}
