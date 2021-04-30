package it.unibs.arnaldo.geriatricpark.codicefiscale;

import java.util.ArrayList;

/**
 * La classe contenente il metodo main.
 * @author Geriatric Park (Wiam Nasr Allah, Nicol Stocchetti, Mohamed Nassar).
 *
 */
public class CodiceFiscaleMain {

	/**
	 * Il metodo main, esegue tutte le operazioni necessarie alla creazione del file XML codiciPersone richiesto.
	 * @param args (non utilizzati in questo programma), String[].
	 */
	public static void main(String[] args) {
		ArrayList<Persona> persone = new ArrayList<Persona>();
		ArrayList<CodiceFiscale> codiciPersone = new ArrayList<CodiceFiscale>();
		ArrayList<CodiceFiscale> codiciEstratti = new ArrayList<CodiceFiscale>();
		ArrayList<CodiceFiscale> codiciInvalidi = new ArrayList<CodiceFiscale>();
		ArrayList<CodiceFiscale> codiciValidi = new ArrayList<CodiceFiscale>();
		ArrayList<CodiceFiscale> codiciSpaiati = new ArrayList<CodiceFiscale>();
		
		//Leggere tutti i dati di tutte le persone dal file inputPersone.xml
		persone = GestoreXML.creaArrayListPersone();
		
		//Generare i codici fiscali di tutte le persone, appoggiandosi al file comuni.xml per quanto riguarda il codice del comune
		for( Persona persona : persone) {
			codiciPersone.add(new CodiceFiscale(persona));
		}
		
		//Verificare la validità dei codici fiscali nel file codiciFiscali.xml.
		codiciEstratti = GestoreXML.estraiCF();
		
		for( CodiceFiscale codice : codiciEstratti) {
			if(codice.correttezzaSintassi())
				codiciValidi.add(codice);
			else
				codiciInvalidi.add(codice);
		}
		
		//Verificare se il codice fiscale di ogni persona risulta presente nel file codiciFiscali.xml. Il risultato di questo
		//passo influenzerà la scrittura del file di output.
		
		System.out.println("Il codice della persona corrisponde a un codice nell'elenco fornito?\n");
		
		for(CodiceFiscale codicePersona : codiciPersone) {
			
			boolean trovato = false;
			System.out.print(codicePersona.getValore()+" ");
			
			for( CodiceFiscale codiceEstratto : codiciEstratti) {
				if(codiceEstratto.getValore().equals(codicePersona.getValore())) {
					trovato = true;
					break;
				}
			}
			System.out.println(trovato);
		}

		
		//Scrivere un file XML (codiciPersone.xml), contenente le informazioni necessarie (presentate nella prossima slide)
		
		for(CodiceFiscale codiceValido : codiciValidi) {
			boolean trovato = false;
			
			for( CodiceFiscale codicePersona : codiciPersone) {
				if(codiceValido.getValore().equals(codicePersona.getValore())) {
					trovato = true;
					break;
				}
			}
			if(!trovato)
				codiciSpaiati.add(codiceValido);
		}
		
		GestoreXML.scriviDocumento(codiciPersone, codiciEstratti, codiciInvalidi, codiciSpaiati);
	}

}
