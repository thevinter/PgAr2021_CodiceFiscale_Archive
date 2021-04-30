package cdf;

public enum CaratteriPari {
	_0 (0),
	_1 (1),
	_2 (2),
	_3 (3),
	_4 (4),
	_5 (5),
	_6 (6),
	_7 (7),
	_8 (8),
	_9 (9),
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
	
	CaratteriPari(int intVal) {
		this.valore=intVal;
	}

	/**
	 * serve per trovare il nome dell'elemento Enum CaratteriPari che occupa l'indice dato come argomento
	 * @param id	indice dell'elemento Enum CaratteriPari
	 * @return	 elemento Enum CaratteriPari presente all'indice dato
	 */
	public static CaratteriPari getById(int id) {
		return CaratteriPari.values()[id];
	}
	
	/**
	 * @return	valore dell'elemento Enum CaratteriPari
	 */
	public int getValore() {
		return valore;
	}
	
	/**
	 * sapendo il carattere alfanumerico corrispondente ad un elemento Enum CaratteriPari si ottiene il rispettivo valore
	 * @param carattere		 
	 * @return valore dell'elemento Enum CaratteriPari corrispondente se trovato, altrimenti restituisce -1
	 */	
	public static int getValoreDaNome(String carattere) {
		int val = -1;
		CaratteriPari nodes[] = values();

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
}
