package cdf;

public enum RestoCaratteri {
	_A (0),
	_B (1),
	_C (2),
	_D (3),
	_E (4),
	_F (5),
	_G (6),
	_H (7),
	_I (8),
	_J (9),
	_K (10),
	_L (11),
	_M (12),
	_N (13),
	_O (14),
	_P (15),
	_Q (16),
	_R (17),
	_S (18),
	_T (19),
	_U (20),
	_V (21),
	_W (22),
	_X (23),
	_Y (24),
	_Z (25);

	private int valore; //valore necessario per generazione del carattere di controllo del codice fiscale
	
	RestoCaratteri(int intVal) {
		this.valore=intVal;
	}
	
	/**
	 * serve per trovare il nome dell'elemento Enum RestoCaratteri che occupa l'indice dato come parametro
	 * @param id	indice dell'elemento Enum RestoCaratteri
	 * @return	 elemento Enum RestoCaratteri presente all'indice dato
	 */
	public static RestoCaratteri getById(int id) {
		return RestoCaratteri.values()[id];
	}

	/**
	 * @return	valore dell'elemento Enum RestoCaratteri
	 */
	public int getValore() {
		return valore;
	}
	
	/**
	 * sapendo il carattere alfanumerico corrispondente ad un elemento Enum RestoCaratteri si ottiene il rispettivo valore
	 * @param carattere		
	 * @return valore dell'elemento Enum RestoCaratteri corrispondente se trovato, altrimenti restituisce -1
	 */	
	public static int getValoreDaNome(String carattere) {
		int val = -1;
		RestoCaratteri nodes[] = values();

		if(carattere.charAt(0)  == '_') {
			carattere=carattere.toUpperCase();
		}else {
			carattere="_"+carattere.toUpperCase();
		}		
		for(int i=0; i<nodes.length; i++) {
			if (nodes[i].name().equals(carattere)) {
				return nodes[i].getValore();
			}
		}
		return val;
	}
	
	/**
	 * ricerca dell'elemento Enum RestoCaratteri avente come valore l'argomento fornito
	 * @param valore
	 * @return elemento Enum RestoCaratteri che ha come valore il parametro passato
	 */	
	public static RestoCaratteri getNomeDaValore(int valore) {
		RestoCaratteri var = null;
		RestoCaratteri nodes[] = values();
	
		for(int i=0; i<nodes.length; i++) {
			if (nodes[i].getValore() == valore) {
				return getById(i);
			}
		}
		return var;
	}
	
	
}
