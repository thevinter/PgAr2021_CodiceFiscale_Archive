package cdf;

public enum Mese {
	GENNAIO ("A", 31), 
	FEBBRAIO ("B", 28),
	MARZO ("C", 31),
	APRILE ("D", 30),
	MAGGIO ("E", 31),
	GIUGNO ("H", 30),
	LUGLIO ("L", 31),
	AGOSTO ("M", 31),
	SETTEMBRE ("P", 30),
	OTTOBRE ("R", 31),
	NOVEMBRE ("S", 30),
	DICEMBRE ("T", 31);
	
	String carattere_mese;//lettera del mese per generazione del codice fiscale
	int giorni;
	
	Mese(String string, int i) {
		this.carattere_mese=string;
		this.giorni=i;
	}
	
	/**
	 * serve per trovare il nome dell'elemento Enum Mese che occupa l'indice dato come parametro
	 * @param id	indice dell'elemento Enum Mese
	 * @return	 elemento Enum presente all'indice dato
	 */
	public static Mese getById(int id) {
		return Mese.values()[id];
	}
	
	/**
	 * @return	carattere corrispondente all' elemento Enum Mese
	 */
	public String getValore() {
		return carattere_mese;
	}
	
	/**
	 * @return numero di giorni del mese
	 */
	public int getGiorni() {
		return giorni;
	}
	
	/**
	 * verifica corrispondenza del carattere all'interno della classe Enum Mese
	 * @param carattere
	 * @return true se il carattere passato come argomento è stato trovato nella classe Enum Mese
	 */
	public static boolean isPresente(String carattere) {
		Mese nodes[] = values();
		
		for(int i=0; i<nodes.length; i++) {
			if(nodes[i].getValore().equalsIgnoreCase(carattere)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ricerca dell'elemento Enum Mese il cui valore corrisponde a quello passato come argomento
	 * @param valore
	 * @return oggetto Enum Mese
	 */
	public static Mese getNomeDaValore(String valore) {
		Mese var = null;
		Mese nodes[] = values();
	
		for(int i=0; i<nodes.length; i++) {
			if (nodes[i].getValore().equalsIgnoreCase(valore)) {
				return getById(i);
			}
		}
		return var;
	}
	
	
}
