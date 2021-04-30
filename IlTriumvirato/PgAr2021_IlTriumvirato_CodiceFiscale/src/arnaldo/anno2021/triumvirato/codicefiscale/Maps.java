package arnaldo.anno2021.triumvirato.codicefiscale;
//arnaldo.anno2021.triumvirato.codicefiscale
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Maps { //this class is used for more complex generated data that is used as constants
	private static Map<String,String> mappaCodiciComuniDalNome;
	private static Map<Character,Integer> mappaGiorniMesi;
	private static Map<Character,Integer> mappaPari;
	private static Map<Character,Integer> mappaDispari;
	
	/**
	 * inizializza le mappe
	 * @param datiComuni vengono passati i dati dei comuni
	 */
	public static void inizializzaMappe(ArrayList<Comune> datiComuni) {
		mappaCodiciComuniDalNome=inizializzaMappaCodiciComuniDalNome(datiComuni);
		mappaGiorniMesi=inizializzaMappaGiorniMesi();
		mappaPari=inizializzaMappaPari();
		mappaDispari=inizializzaMappaDispari();
	}
	
	/**
	 * inizializza le mappe dato in input il nome del comune
	 * @param datiComuni vengono passati i nomi dei comuni dai quali viene inizializzata la mappa
	 * @return ritorna la mappa che associa a ogni nome del comune il codice che gli corrisponde
	 */
	public static Map<String,String> inizializzaMappaCodiciComuniDalNome(ArrayList<Comune> datiComuni) {
		Map<String,String> mappa=new HashMap<String,String>();
		
		for(int i=0;i<datiComuni.size();i++) {
			mappa.put(datiComuni.get(i).getNome(), datiComuni.get(i).getCodice());
		}
		
		return mappa;
	}

	/**
	 * inizializza le mappa dei giorni e dei mesi
	 * @return ritorna la mappa che assoccia a ogni carattere i giorni dei mesi
	 */
	public static Map<Character,Integer> inizializzaMappaGiorniMesi(){
		Map <Character, Integer> mappaGiorni=new HashMap<Character,Integer>();
		
		for(int i=0;i<12;i++) {
			mappaGiorni.put(Constants.MONTH_CHARS[i], Constants.GIORNI_PER_MESE[i]);
		}
		
		return mappaGiorni;
	}
	
	/**
	 * inizializza la mappa per i codici pari del codice fiscale
	 * @return ritorna la mappa che associa a ogni carattere del codice un numero che serve per calcolare il carattere di controllo
	 */
	public static Map<Character,Integer> inizializzaMappaPari() {
		Map <Character, Integer> mappaPari = new HashMap <Character, Integer>( );
		
		
		for(int i=0;i<=9;i++) {
			mappaPari.put((char)(i+'0'), i);
		}
		for(int i=0;i<26;i++) {
			mappaPari.put((char)(i+'A'),i);
		}
		
		//l'ho inizializzata così pronta in caso di cambiamenti
		/*
		mappaPari.put('0', 0);
		mappaPari.put('1', 1);
		mappaPari.put('2', 2);
		mappaPari.put('3', 3);
		mappaPari.put('4', 4);
		mappaPari.put('5', 5);
		mappaPari.put('6', 6);
		mappaPari.put('7', 7);
		mappaPari.put('8', 8);
		mappaPari.put('9', 9);
		mappaPari.put('A', 0);
		mappaPari.put('B', 1);
		mappaPari.put('C', 2);
		mappaPari.put('D', 3);
		mappaPari.put('E', 4);
		mappaPari.put('F', 5);
		mappaPari.put('G', 6);
		mappaPari.put('H', 7);
		mappaPari.put('I', 8);
		mappaPari.put('J', 9);
		mappaPari.put('K', 10);
		mappaPari.put('L', 11);
		mappaPari.put('M', 12);
		mappaPari.put('N', 13);
		mappaPari.put('O', 14);
		mappaPari.put('P', 15);
		mappaPari.put('Q', 16);
		mappaPari.put('R', 17);
		mappaPari.put('S', 18);
		mappaPari.put('T', 19);
		mappaPari.put('U', 20);
		mappaPari.put('V', 21);
		mappaPari.put('W', 22);
		mappaPari.put('X', 23);
		mappaPari.put('Y', 24);
		mappaPari.put('Z', 25);*/
		
		
		return mappaPari;
	}
	
	/**
	 * inizializza la mappa per i codici dispari del codice fiscale
	 * @return ritorna la mappa che associa a ogni carattere del codice un numero che serve per calcolare il carattere di controllo
	 */
	public static Map<Character,Integer> inizializzaMappaDispari() {
		Map <Character, Integer> mappaDispari = new HashMap <Character, Integer>( );
		
		mappaDispari.put('0', 1);
		mappaDispari.put('1', 0);
		mappaDispari.put('2', 5);
		mappaDispari.put('3', 7);
		mappaDispari.put('4', 9);
		mappaDispari.put('5', 13);
		mappaDispari.put('6', 15);
		mappaDispari.put('7', 17);
		mappaDispari.put('8', 19);
		mappaDispari.put('9', 21);
		mappaDispari.put('A', 1);
		mappaDispari.put('B', 0);
		mappaDispari.put('C', 5);
		mappaDispari.put('D', 7);
		mappaDispari.put('E', 9);
		mappaDispari.put('F', 13);
		mappaDispari.put('G', 15);
		mappaDispari.put('H', 17);
		mappaDispari.put('I', 19);
		mappaDispari.put('J', 21);
		mappaDispari.put('K', 2);
		mappaDispari.put('L', 4);
		mappaDispari.put('M', 18);
		mappaDispari.put('N', 20);
		mappaDispari.put('O', 11);
		mappaDispari.put('P', 3);
		mappaDispari.put('Q', 6);
		mappaDispari.put('R', 8);
		mappaDispari.put('S', 12);
		mappaDispari.put('T', 14);
		mappaDispari.put('U', 16);
		mappaDispari.put('V', 10);
		mappaDispari.put('W', 22);
		mappaDispari.put('X', 25);
		mappaDispari.put('Y', 24);
		mappaDispari.put('Z', 23);
		
		return mappaDispari;
	}

	public static Map<String, String> getMappaCodiciComuniDalNome() {
		return mappaCodiciComuniDalNome;
	}
	
	public static Map<Character, Integer> getMappaGiorniMesi() {
		return mappaGiorniMesi;
	}
	
	public static Map<Character, Integer> getMappaPari() {
		return mappaPari;
	}

	public static Map<Character, Integer> getMappaDispari() {
		return mappaDispari;
	}

}
