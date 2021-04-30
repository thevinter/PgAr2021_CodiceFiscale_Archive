package it.unibs.arnaldo.TesteAnnidate.CodiceFiscale;

import javax.xml.stream.XMLStreamException;

import it.unibs.arnaldo.TesteAnnidate.CodiceFiscale.Data.Mesi;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws XMLStreamException {


		ArrayList<Persona> lista_persone = new ArrayList<Persona>();
		ArrayList<CodiceFiscale> lista_codici = new ArrayList<CodiceFiscale>();
		ArrayList<CodiceFiscale> lista_codici_da_persone = new ArrayList<CodiceFiscale>();
		ArrayList<Comune> lista_comuni = new ArrayList<Comune>();



		//Creo gli array lista_persone e lista_comuni con i metodi che leggono gli xml
		lista_comuni = Input.leggiComuni();
		lista_persone = Input.leggiPersone();
		lista_codici = Input.leggiCodiciFiscali();

		//confronto il nome del comune di ogni persona per settarne il codice corrispondente
		for(Persona persona: lista_persone){  //faccio passare tutte le persone dell'array appena creato
			for(Comune comune_e_codice: lista_comuni){
				if(persona.getLuogoDiNascita().getNome_comune().equals(comune_e_codice.getNome_comune())){
					//quando ho creato le persone non ho settato il codice corrispondente al loro comune di nascita
					//ora, una volta che il comune della persona risulta uguale ad uno dei comuni dell'array, setto
					//il codice in persona
					persona.getLuogoDiNascita().setCodice(comune_e_codice.getCodice());
				}
			}
		}


		//ora che ho creato tutte le persone, con anche i codici corrispondenti ai vari comuni, posso settare
		//la variabile cf (codice fiscale) di persona.

		CodiceFiscale codice_generato;

		for(Persona persona: lista_persone){
			codice_generato = new CodiceFiscale(persona);
			persona.setCf(codice_generato);
			lista_codici_da_persone.add(codice_generato);
		}

		/*for(Persona persona: lista_persone){
			System.out.println(persona.getCf().codiceFiscaleIntero());
		}*/

		System.out.println("Creazione xml...");
		//creazione file di output
		OutPut.creaXml(lista_persone, lista_codici);
		System.out.println("XML creato!!");
	}

}
