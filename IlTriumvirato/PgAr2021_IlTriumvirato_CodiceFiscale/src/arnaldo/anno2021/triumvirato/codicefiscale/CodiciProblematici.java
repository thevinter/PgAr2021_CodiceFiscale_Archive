package arnaldo.anno2021.triumvirato.codicefiscale;

import java.util.ArrayList;

/**
 * Questa classe serve a contenere i una lista di codici anomali, suddivisi in codici invalidi per costruzione e codici spaiati, ossia che non corripondono al codice di nessuna persona.<br>
 * Viene utilizzata durante il controllo dei codici presi in input e viene passata all'output per fornire le informazioni sui codici anomali.
 */
public class CodiciProblematici {
	ArrayList<String> codiciInvalidi;
	ArrayList<String> codiciSpaiati;
	
	CodiciProblematici(){
		codiciInvalidi=new ArrayList<String>();
		codiciSpaiati=new ArrayList<String>();
	}
	

	public CodiciProblematici(ArrayList<String> codiciInvalidi, ArrayList<String> codiciSpaiati) {
		this.codiciInvalidi = codiciInvalidi;
		this.codiciSpaiati = codiciSpaiati;
	}

	public ArrayList<String> getCodiciInvalidi() {
		return codiciInvalidi;
	}

	public ArrayList<String> getCodiciSpaiati() {
		return codiciSpaiati;
	}

	public void setCodiciInvalidi(ArrayList<String> codiciInvalidi) {
		this.codiciInvalidi=codiciInvalidi;
	}
	
	public void setCodiciSpaiati(ArrayList<String> codiciSpaiati) {
		this.codiciSpaiati=codiciSpaiati;
	}
}
