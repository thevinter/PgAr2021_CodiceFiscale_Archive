package arnaldo.anno2021.triumvirato.codicefiscale;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.sound.midi.Synthesizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class InputOutputXML {
	
	private static XMLStreamReader getXmlReader(String filename) {
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
			
		} catch (Exception e){
			System.out.println(Constants.ERROR_MESSAGE_FILE_ASSENTI);
			DataProcessor.errorOccourrence();
			DataProcessor.setUnexecutable();
			//System.out.println(e.getMessage());
			
		}
		
		return xmlr;
	}
	
	
	
	public static ArrayList<Persona> prendiInInputPersone(String filename) {
		
		ArrayList<Persona> datiPersone=new ArrayList<Persona>();
		
		XMLStreamReader xmlr=getXmlReader(filename);
		
		if(xmlr!=null)
		try {
			String selezione="";
			boolean mustRemove=false;
			
			while (xmlr.hasNext()) {
				
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_ELEMENT: 
						 selezione=xmlr.getLocalName();
						 if(selezione.equals("persona")) {
							 datiPersone.add(new Persona(Integer.parseInt(xmlr.getAttributeValue(0))));
						 }
						 
					 break;
					 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
					 
						 
						 if (xmlr.getText().trim().length() > 0) {// controlla se il testo non contiene solo spazi
							 
							 
							 int n_persona=datiPersone.size()-1;
							 
							 if(selezione.equals("nome")) {
								 datiPersone.get(n_persona).setNome(xmlr.getText());
							 }else if(selezione.equals("cognome")) {
								 datiPersone.get(n_persona).setCognome(xmlr.getText());
							 }else if(selezione.equals("sesso")) {
								 boolean existingGender=false;
								 String gender=xmlr.getText();
								 for(int i=0;i<Constants.POSSIBLE_GENDER_NOTATION.length;i++) {
									 if(gender.equalsIgnoreCase(Constants.POSSIBLE_GENDER_NOTATION[i])) {
										 existingGender=true;
										 break;
									 }
								 }
								 
								 if(!existingGender) {
									 System.out.println(String.format(Constants.ERROR_MESSAGE_INPUT_PERSONA_ERRATO,"\"sesso\"",datiPersone.get(n_persona).getId()));
									 DataProcessor.errorOccourrence();
									 mustRemove=true;
								 }
								 
								 datiPersone.get(n_persona).setSesso(Character.toUpperCase(xmlr.getText().charAt(0)));//viene fatto il controllo nel metodo setSesso() della classe Persona, ma viene lanciato comunque un messaggio di avvertimento in 
							 
							 }else if(selezione.equals("comune_nascita")&&!mustRemove) {
								 datiPersone.get(n_persona).setComune_nascita(xmlr.getText()); //se il comune inserito non è presente in comuni.xml, il codice sarà riportato assente
							 }else if(selezione.equals("data_nascita")&&!mustRemove) {
								 
								 String data_inserita=xmlr.getText();
	                             boolean check_error=false;
	                             LocalDate data_nascita=null;
	                                 
	                             	 try {
	                                     data_nascita=LocalDate.parse(data_inserita);
	
	                                 }catch (Exception e) {
	                                     check_error=true;
	                                 }
	                                 
	                                 
	                                 if(check_error) {
	                                    try {
	                                         check_error=false;
	                                         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                                         data_nascita = LocalDate.parse(data_inserita, formatter);
	                                    } catch (Exception e) {
	                                        check_error=true;
	                                        
	                                    }
	                                 }
	                                 
	                                 
	                             if(!check_error) {
	                                  datiPersone.get(n_persona).setData_nascita(data_nascita);
	                             }else {
	                            	 
	                            	 mustRemove=true;
	                            	 System.out.println(String.format(Constants.ERROR_MESSAGE_INPUT_PERSONA_ERRATO,"\"data_nascita\"",datiPersone.get(n_persona).getId()));
									 
	                             }
								 
	                             
							 }//else: se trova un tag che non è previsto semplicemente lo salta
							 
						 } 

					 break;
					 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
						 if(xmlr.getLocalName().equals("persona")&&mustRemove&&datiPersone.size()>0) {
							 System.out.print(String.format(Constants.ERROR_MESSAGE_PERSONA_NON_ANALIZZABILE, datiPersone.get(datiPersone.size()-1).getNome(), datiPersone.get(datiPersone.size()-1).getCognome()));
							 DataProcessor.errorOccourrence();
							 datiPersone.remove(datiPersone.size()-1);
							 mustRemove=false;
						 };
					 break;
				 }
				 
				 
				 xmlr.next();
				
				 
			}
		
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return datiPersone;
		
	}
	
	
	public static ArrayList<String> prendiInInputCodiciFiscali(String filename) {
		
		ArrayList<String> datiCodiciFiscali=new ArrayList<String>();
		XMLStreamReader xmlr=getXmlReader(filename);
		
		if(xmlr!=null)
		try {
			String selezione="";
			
			while (xmlr.hasNext()) {
				
				switch (xmlr.getEventType()) {
					case XMLStreamConstants.START_ELEMENT: // inizio del documento: stampa che inizia il documento
						selezione=xmlr.getLocalName();
					 
					break;
					case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: prende il codice
							 
						if (xmlr.getText().trim().length() > 0  && selezione.equals("codice")) {// controlla se il testo non contiene solo spazi
							
							datiCodiciFiscali.add(new String(xmlr.getText()));
						}
		
					break;
				}
				 
				xmlr.next();
				 
			}
			
		
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return datiCodiciFiscali;
		
	}
	
	
	public static ArrayList<Comune> prendiInInputComuni(String filename) {
		ArrayList<Comune> datiComuni=new ArrayList<Comune>();
		
		XMLStreamReader xmlr=getXmlReader(filename);
		
		if(xmlr!=null)
		try {
			String selezione="";
			boolean mustRemove=false;
			
			while (xmlr.hasNext()) {
				
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_ELEMENT: 
						 
						 selezione=xmlr.getLocalName();
						 if(selezione.equals("comune")) {
							 datiComuni.add(new Comune());
						 }
						 
					 break;
					 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
						 
						 if (xmlr.getText().trim().length() > 0) {// controlla se il testo non contiene solo spazi
							 int n_comune=datiComuni.size()-1;
							 
							 if(selezione.equals("nome")) {
								 
								 datiComuni.get(n_comune).setNome(xmlr.getText());
								 
							 }else if(selezione.equals("codice")) {
								 
								 String codiceComune=xmlr.getText();
								 
								 boolean codiceValido=true;
								 
								 if(codiceComune.length()!=4) {
									 codiceValido=false;
								 }else {
									 
									 for(int i=0;i<4;i++) {
										 int posizione=Constants.POSIZIONE_CARATTERE_INIZIALE_COMUNE+i;
										 boolean controllo=DataProcessor.controlloRange(codiceComune.charAt(i), Constants.ESTREMI_INFERIORI_CARATTERI_CF[posizione], Constants.ESTREMI_SUPERIORI_CARATTERI_CF[posizione]);
										 codiceValido=codiceValido&&controllo;
										 if(!codiceValido) {
											 break;
										 }
									 }
								 }
								 
								 if(codiceValido) {
									 datiComuni.get(n_comune).setCodice(codiceComune);
								 }else{
									 mustRemove=true;
								 }
							 }
							 //else   se non è uno di questi tag, non fa assolutamente niente 
						}

					 break;
					 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
						 if(xmlr.getLocalName().equals("comune")&&mustRemove) {
							 System.out.println(String.format(Constants.ERROR_MESSAGE_INPUT_CODICE_COMUNE,datiComuni.get(datiComuni.size()-1).getNome()));
							 DataProcessor.errorOccourrence();
							 datiComuni.remove(datiComuni.get(datiComuni.size()-1));
							 mustRemove=false;
						 }
					 break;
				 }
				 
				 
				 xmlr.next();
				
				 
			}
		
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return datiComuni;
		
	}
	
	
	public static void OutputXML(ArrayList<Persona> dp,ArrayList<String> listaInvlidi,ArrayList<String> listaSpaiati) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db=null;
		db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();

		//elemento radice <output>
		Element output = doc.createElement("output");
		
		//CREAZIONE ELEMENTI DI PERSONE
		
		//elemento <persone>
		Element persone= doc.createElement("persone");
		//creo attributo numero
		Attr numero_persone=doc.createAttribute("numero");
		numero_persone.setValue(String.valueOf(dp.size()));
		//aggiungo numero a persone
		persone.setAttributeNode(numero_persone);
		//INIZIO FOR
		for(int i=0;i<dp.size();i++) {
			//creo persona
			Element persona=doc.createElement("persona");
			//creazione tutto per persona
			Attr id_persona=doc.createAttribute("id");
			Element nome=doc.createElement("nome");
			Element cognome=doc.createElement("cognome");
			Element sesso=doc.createElement("sesso");
			Element comune_nascita=doc.createElement("comune_nascita");
			Element data_nascita=doc.createElement("data_nascita");
			Element codice_fiscale=doc.createElement("codice_fiscale");
			
			//settaggio values
			id_persona.setValue(String.valueOf(i));
			nome.setTextContent(dp.get(i).getNome());
			cognome.setTextContent(dp.get(i).getCognome());
			sesso.setTextContent(String.valueOf(dp.get(i).getSesso()));
			comune_nascita.setTextContent(dp.get(i).getComune_nascita());
			data_nascita.setTextContent((dp.get(i).getData_nascita()).toString());
			
			if(dp.get(i).getCodiceAssente()) {
				codice_fiscale.setTextContent("ASSENTE");
			}else {
				codice_fiscale.setTextContent(dp.get(i).getCodiceFiscale());
			}
			//attaccare tutto a persona
			persona.setAttributeNode(id_persona);
			persona.appendChild(nome);
			persona.appendChild(cognome);
			persona.appendChild(sesso);
			persona.appendChild(comune_nascita);
			persona.appendChild(data_nascita);
			persona.appendChild(codice_fiscale);
			//attaccare persona a persone
			persone.appendChild(persona);
		}
		
		//elemento <codici>
		Element codici= doc.createElement("codici");
		//invalidi
		Element invalidi= doc.createElement("invalidi");
		Attr numero_invalidi=doc.createAttribute("numero");
		numero_invalidi.setValue(String.valueOf(listaInvlidi.size()));
		invalidi.setAttributeNode(numero_invalidi);
		//inizio for codice invalido
		for(int i=0;i<listaInvlidi.size();i++) {
			Element codice_invalido= doc.createElement("codice");
			//settaggio values
			codice_invalido.setTextContent(listaInvlidi.get(i));
			//attaccare un codice a codici invalidi
			invalidi.appendChild(codice_invalido);
			//fine for
		}
			
		//codici spaiati
		Element spaiati= doc.createElement("spaiati");
		Attr numero_spaiati=doc.createAttribute("numero");
		numero_spaiati.setValue(String.valueOf(listaSpaiati.size()));
		spaiati.setAttributeNode(numero_spaiati);
		//un codice spaiato, inizio for
		for(int i=0;i<listaSpaiati.size();i++) {
			Element codice_spaiato= doc.createElement("codice");
			//settaggio value
			codice_spaiato.setTextContent(listaSpaiati.get(i));
			//attaccare un codice ai codici spaiati
			spaiati.appendChild(codice_spaiato);
			//fine for
		}
		
			
		//attaccare tutto a codici
		codici.appendChild(invalidi);
		codici.appendChild(spaiati);
		
		//attaccare tutto ad output
		output.appendChild(persone);
		output.appendChild(codici);
		//attaccare l'output al documento principale
		doc.appendChild(output);
	

		// write the content into xml file
	    Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.INDENT,"yes");
		tf.setOutputProperty(OutputKeys.METHOD, "xml");
		tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(Constants.PATH_RELATIVO_OUTPUT));

		// Output to console for testing
		//StreamResult result = new StreamResult(System.out);

		tf.transform(source, result);

	}
	
	

}