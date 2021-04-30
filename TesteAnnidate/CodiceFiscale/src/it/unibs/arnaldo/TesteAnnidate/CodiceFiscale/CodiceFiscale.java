package it.unibs.arnaldo.TesteAnnidate.CodiceFiscale;

public class CodiceFiscale {

	private static final int QUARTA_CONSONANTE = 4;
	// private static final String CODICE_NON_VALIDO = "Codice non valido!";
	private static final int PENULTIMA_CIFRA_ANNO = 2;
	private static final int ULTIMA_CIFRA_ANNO = 3;
	private static final int LUNGHEZZA_CODICE = 16;

	private StringBuffer codiceCognome = new StringBuffer();
	private StringBuffer codiceNome = new StringBuffer();
	private StringBuffer codiceAnno = new StringBuffer();
	private char codiceMese;
	private StringBuffer codiceGiornoESesso = new StringBuffer();
	private StringBuffer codiceComune = new StringBuffer();
	private char carattereDiControllo;

	// Meotodo che ritorna il codice fiscale intero
	public String codiceFiscaleIntero() {
		StringBuffer cf = new StringBuffer();
		cf.append(codiceCognome);
		cf.append(codiceNome);
		cf.append(codiceAnno);
		cf.append(codiceMese);
		cf.append(codiceGiornoESesso);
		cf.append(codiceComune);
		cf.append(carattereDiControllo);
		return cf.toString();
	}

	// Costruttore data come parametro una persona
	public CodiceFiscale(Persona p) {
		generaCodiceCognome(p);
		generaCodiceNome(p);
		generaCodiceAnno(p);
		generaCodiceMese(p);
		generaCodiceGiornoESesso(p);
		generaCodiceComune(p);
		generaCarattereDiControllo();

	}

	public CodiceFiscale() {
		super();
	}

	public CodiceFiscale(CodiceFiscale cf) {
		super();
		cf.codiceCognome = codiceCognome;
		cf.codiceNome = codiceNome;
		cf.codiceAnno = codiceAnno;
		cf.codiceMese = codiceMese;
		cf.codiceGiornoESesso = codiceGiornoESesso;
		cf.codiceComune = codiceComune;
		cf.carattereDiControllo = carattereDiControllo;
	}

	// Costruttore che data un input una Stringa genera un oggetto di tipo
	// CodiceFiscale
	public CodiceFiscale(String codice) {
		for (int i = 0; i < codice.length(); i++) {
			if (i < 3)
				codiceCognome.append(codice.charAt(i));
			else if (i >= 3 && i < 6)
				codiceNome.append(codice.charAt(i));
			else if (i >= 6 && i < 8)
				codiceAnno.append(codice.charAt(i));
			else if (i == 8)
				codiceMese = codice.charAt(i);
			else if (i >= 9 && i < 11)
				codiceGiornoESesso.append(codice.charAt(i));
			else if (i >= 11 && i < 15)
				codiceComune.append(codice.charAt(i));
			else if (i == 15)
				carattereDiControllo = codice.charAt(i);
		}
	}

	// Metodo che verifica la correttezza di un codice fiscale
	public static boolean isCorret(CodiceFiscale cf) {
		// il codice deve avere lunghezza 16
		if (cf.codiceFiscaleIntero().length() != LUNGHEZZA_CODICE)
			return false;
		// i primi 3 caratteri devono essere lettere
		for (int i = 0; i < cf.getCodiceCognome().length(); i++)
			if (cf.getCodiceCognome().charAt(i) < 'A' || cf.getCodiceCognome().charAt(i) > 'Z')
				return false;
		// i caratteri dal 4 al 6 devono essere lettere
		for (int i = 0; i < cf.getCodiceNome().length(); i++)
			if (cf.getCodiceNome().charAt(i) < 'A' || cf.getCodiceNome().charAt(i) > 'Z')
				return false;
		// i caratteri 7 e 8 devono essere numeri
		for (int i = 0; i < cf.getCodiceAnno().length(); i++)
			if (cf.getCodiceAnno().charAt(i) < '0' || cf.getCodiceAnno().charAt(i) > '9')
				return false;
		// il carattere 9 deve essere una lettera compresa tra A e T
		if (cf.getCodiceMese() < 'A' || cf.getCodiceMese() > 'T')
			return false;
		// il carattere 10 deve essere compreso tra 0 e 7
		if (cf.getCodiceGiornoESesso().charAt(0) < '0' || cf.getCodiceGiornoESesso().charAt(0) > '7')
			return false;
		// il carattere 11 deve essere un numero
		if (cf.getCodiceGiornoESesso().charAt(1) < '0' || cf.getCodiceGiornoESesso().charAt(1) > '9')
			return false;
		for (int i = 0; i < cf.getCodiceComune().length(); i++) {
			// il carattere 12 deve essere una lettera
			if (i == 0){
				if(cf.getCodiceComune().charAt(i) < 'A' || cf.getCodiceComune().charAt(i) > 'Z')
					return false;
			}

			// i caratteri 13 14 15 devono essere dei numeri
			else if (cf.getCodiceComune().charAt(i) < '0' || cf.getCodiceComune().charAt(i) > '9')
				return false;
		}
		// il carattere 16 deve essere una lettera
		if (cf.getCarattereDiControllo() < 'A' || cf.getCarattereDiControllo() > 'Z')
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carattereDiControllo;
		result = prime * result + ((codiceAnno == null) ? 0 : codiceAnno.hashCode());
		result = prime * result + ((codiceCognome == null) ? 0 : codiceCognome.hashCode());
		result = prime * result + ((codiceComune == null) ? 0 : codiceComune.hashCode());
		result = prime * result + ((codiceGiornoESesso == null) ? 0 : codiceGiornoESesso.hashCode());
		result = prime * result + codiceMese;
		result = prime * result + ((codiceNome == null) ? 0 : codiceNome.hashCode());
		return result;
	}

	// Metodo equals per verificare la presenza di un codice all'interno di una
	// lista
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodiceFiscale other = (CodiceFiscale) obj;
		return this.codiceFiscaleIntero().equals(other.codiceFiscaleIntero());
		/*if (carattereDiControllo != other.carattereDiControllo)
			return false;
		if (codiceAnno == null) {
			if (other.codiceAnno != null)
				return false;
		} else if (!codiceAnno.equals(other.codiceAnno))
			return false;
		if (codiceCognome == null) {
			if (other.codiceCognome != null)
				return false;
		} else if (!codiceCognome.equals(other.codiceCognome))
			return false;
		if (codiceComune == null) {
			if (other.codiceComune != null)
				return false;
		} else if (!codiceComune.equals(other.codiceComune))
			return false;
		if (codiceGiornoESesso == null) {
			if (other.codiceGiornoESesso != null)
				return false;
		} else if (!codiceGiornoESesso.equals(other.codiceGiornoESesso))
			return false;
		if (codiceMese != other.codiceMese)
			return false;
		if (codiceNome == null) {
			if (other.codiceNome != null)
				return false;
		} else if (!codiceNome.equals(other.codiceNome))
			return false;
		return true;*/
	}

	public void generaCarattereDiControllo() {
		StringBuffer codiceParziale = new StringBuffer();
		codiceParziale.append(codiceCognome);
		codiceParziale.append(codiceNome);
		codiceParziale.append(codiceAnno);
		codiceParziale.append(codiceMese);
		codiceParziale.append(codiceGiornoESesso);
		codiceParziale.append(codiceComune);
		StringBuffer pari = new StringBuffer();
		StringBuffer dispari = new StringBuffer();
		for (int i = 1; i <= codiceParziale.length(); i++) {
			if (i % 2 == 0)
				pari.append(codiceParziale.charAt(i - 1));
			else
				dispari.append(codiceParziale.charAt(i - 1));
		}
		int valoreTot = tabellaDispari(dispari) + tabellaPari(pari);
		int resto = valoreTot % 26;
		carattereDiControllo = tabellaCarattereControllo(resto);
	}

	public char tabellaCarattereControllo(int resto) {
		char carattere = ' ';
		switch (resto) {
		case 0:
			carattere = 'A';
			break;
		case 1:
			carattere = 'B';
			break;
		case 2:
			carattere = 'C';
			break;
		case 3:
			carattere = 'D';
			break;
		case 4:
			carattere = 'E';
			break;
		case 5:
			carattere = 'F';
			break;
		case 6:
			carattere = 'G';
			break;
		case 7:
			carattere = 'H';
			break;
		case 8:
			carattere = 'I';
			break;
		case 9:
			carattere = 'J';
			break;
		case 10:
			carattere = 'K';
			break;
		case 11:
			carattere = 'L';
			break;
		case 12:
			carattere = 'M';
			break;
		case 13:
			carattere = 'N';
			break;
		case 14:
			carattere = 'O';
			break;
		case 15:
			carattere = 'P';
			break;
		case 16:
			carattere = 'Q';
			break;
		case 17:
			carattere = 'R';
			break;
		case 18:
			carattere = 'S';
			break;
		case 19:
			carattere = 'T';
			break;
		case 20:
			carattere = 'U';
			break;
		case 21:
			carattere = 'V';
			break;
		case 22:
			carattere = 'W';
			break;
		case 23:
			carattere = 'X';
			break;
		case 24:
			carattere = 'Y';
			break;
		case 25:
			carattere = 'Z';
			break;
		}
		return carattere;
	}

	public int tabellaPari(StringBuffer pari) {
		int valoreTot = 0;
		for (int i = 0; i < pari.length(); i++) {

			switch (pari.charAt(i)) {
			case '0':
				valoreTot += 0;
				break;
			case '1':
				valoreTot += 1;
				break;
			case '2':
				valoreTot += 2;
				break;
			case '3':
				valoreTot += 3;
				break;
			case '4':
				valoreTot += 4;
				break;
			case '5':
				valoreTot += 5;
				break;
			case '6':
				valoreTot += 6;
				break;
			case '7':
				valoreTot += 7;
				break;
			case '8':
				valoreTot += 8;
				break;
			case '9':
				valoreTot += 9;
				break;
			case 'A':
				valoreTot += 0;
				break;
			case 'B':
				valoreTot += 1;
				break;
			case 'C':
				valoreTot += 2;
				break;
			case 'D':
				valoreTot += 3;
				break;
			case 'E':
				valoreTot += 4;
				break;
			case 'F':
				valoreTot += 5;
				break;
			case 'G':
				valoreTot += 6;
				break;
			case 'H':
				valoreTot += 7;
				break;
			case 'I':
				valoreTot += 8;
				break;
			case 'J':
				valoreTot += 9;
				break;
			case 'K':
				valoreTot += 10;
				break;
			case 'L':
				valoreTot += 11;
				break;
			case 'M':
				valoreTot += 12;
				break;
			case 'N':
				valoreTot += 13;
				break;
			case 'O':
				valoreTot += 14;
				break;
			case 'P':
				valoreTot += 15;
				break;
			case 'Q':
				valoreTot += 16;
				break;
			case 'R':
				valoreTot += 17;
				break;
			case 'S':
				valoreTot += 18;
				break;
			case 'T':
				valoreTot += 19;
				break;
			case 'U':
				valoreTot += 20;
				break;
			case 'V':
				valoreTot += 21;
				break;
			case 'W':
				valoreTot += 22;
				break;
			case 'X':
				valoreTot += 23;
				break;
			case 'Y':
				valoreTot += 24;
				break;
			case 'Z':
				valoreTot += 25;
				break;

			}
		}
		return valoreTot;
	}

	public int tabellaDispari(StringBuffer dispari) {
		int valoreTot = 0;
		for (int i = 0; i < dispari.length(); i++) {
			switch (dispari.charAt(i)) {
			case '0':
				valoreTot += 1;
				break;
			case '1':
				valoreTot += 0;
				break;
			case '2':
				valoreTot += 5;
				break;
			case '3':
				valoreTot += 7;
				break;
			case '4':
				valoreTot += 9;
				break;
			case '5':
				valoreTot += 13;
				break;
			case '6':
				valoreTot += 15;
				break;
			case '7':
				valoreTot += 17;
				break;
			case '8':
				valoreTot += 19;
				break;
			case '9':
				valoreTot += 21;
				break;
			case 'A':
				valoreTot += 1;
				break;
			case 'B':
				valoreTot += 0;
				break;
			case 'C':
				valoreTot += 5;
				break;
			case 'D':
				valoreTot += 7;
				break;
			case 'E':
				valoreTot += 9;
				break;
			case 'F':
				valoreTot += 13;
				break;
			case 'G':
				valoreTot += 15;
				break;
			case 'H':
				valoreTot += 17;
				break;
			case 'I':
				valoreTot += 19;
				break;
			case 'J':
				valoreTot += 21;
				break;
			case 'K':
				valoreTot += 2;
				break;
			case 'L':
				valoreTot += 4;
				break;
			case 'M':
				valoreTot += 18;
				break;
			case 'N':
				valoreTot += 20;
				break;
			case 'O':
				valoreTot += 11;
				break;
			case 'P':
				valoreTot += 3;
				break;
			case 'Q':
				valoreTot += 6;
				break;
			case 'R':
				valoreTot += 8;
				break;
			case 'S':
				valoreTot += 12;
				break;
			case 'T':
				valoreTot += 14;
				break;
			case 'U':
				valoreTot += 16;
				break;
			case 'V':
				valoreTot += 10;
				break;
			case 'W':
				valoreTot += 22;
				break;
			case 'X':
				valoreTot += 25;
				break;
			case 'Y':
				valoreTot += 24;
				break;
			case 'Z':
				valoreTot += 23;
				break;

			}
		}
		return valoreTot;
	}

	public void generaCodiceComune(Persona p) {

		// da aggiungere questione stranieri nati all'estero,
		// italiani nati all'estero, stranieri nati in italia.

		codiceComune.append(p.getLuogoDiNascita().getCodice());
	}

	public void generaCodiceGiornoESesso(Persona p) {
		if (p.getDataDiNascita().getGiorno() < 10) {
			if (p.getSesso() == 'F')
				codiceGiornoESesso.append(p.getDataDiNascita().getGiorno() + 40);
			else {
				codiceGiornoESesso.append(0);
				codiceGiornoESesso.append(p.getDataDiNascita().getGiorno());
			}
		} else {
			if (p.getSesso() == 'F')
				codiceGiornoESesso.append(p.getDataDiNascita().getGiorno() + 40);
			else
				codiceGiornoESesso.append(p.getDataDiNascita().getGiorno());
		}
	}

	public void generaCodiceMese(Persona p) {
		codiceMese = p.getDataDiNascita().ritornaCarattereMese();
	}

	public void generaCodiceAnno(Persona p) {
		codiceAnno.append(p.getDataDiNascita().getAnno().charAt(PENULTIMA_CIFRA_ANNO));
		codiceAnno.append(p.getDataDiNascita().getAnno().charAt(ULTIMA_CIFRA_ANNO));
	}

	public void generaCodiceNome(Persona p) {
		int consonanti = 0;
		for (int i = 0; i < p.getNome().length(); i++) {
			if (p.getNome().charAt(i) != 'A' && p.getNome().charAt(i) != 'E' && p.getNome().charAt(i) != 'I'
					&& p.getNome().charAt(i) != 'O' && p.getNome().charAt(i) != 'U')
				consonanti++;
		}
		if (consonanti <= 3) {
			for (int i = 0; i < p.getNome().length(); i++) {
				if (p.getNome().charAt(i) != 'A' && p.getNome().charAt(i) != 'E' && p.getNome().charAt(i) != 'I'
						&& p.getNome().charAt(i) != 'O' && p.getNome().charAt(i) != 'U') {
					codiceNome.append(p.getNome().charAt(i));
				}
				if (codiceNome.length() == 3)
					break;
			}
			if (codiceNome.length() < 3) {
				for (int i = 0; i < p.getNome().length(); i++) {
					if (p.getNome().charAt(i) == 'A' || p.getNome().charAt(i) == 'E' || p.getNome().charAt(i) == 'I'
							|| p.getNome().charAt(i) == 'O' || p.getNome().charAt(i) == 'U') {
						codiceNome.append(p.getCognome().charAt(i));

					}
					if (codiceNome.length() == 3)
						break;
				}
				while (codiceNome.length() < 3) {
					codiceNome.append('X');
				}
			}
		}
		if (consonanti > 3) {
			int cont = 0;
			for (int i = 0; i < p.getNome().length(); i++) {
				if (p.getNome().charAt(i) != 'A' && p.getNome().charAt(i) != 'E' && p.getNome().charAt(i) != 'I'
						&& p.getNome().charAt(i) != 'O' && p.getNome().charAt(i) != 'U') {
					cont++;
					if (cont != 2)
						codiceNome.append(p.getNome().charAt(i));
				}
				if (codiceNome.length() == 3)
					break;
			}

		}
	}

	// Metodo che genera il codice del Cognome
	public void generaCodiceCognome(Persona p) {
		for (int i = 0; i < p.getCognome().length(); i++) {
			if (p.getCognome().charAt(i) != 'A' && p.getCognome().charAt(i) != 'E' && p.getCognome().charAt(i) != 'I'
					&& p.getCognome().charAt(i) != 'O' && p.getCognome().charAt(i) != 'U') {
				codiceCognome.append(p.getCognome().charAt(i));
			}
			if (codiceCognome.length() == 3)
				break;
		}
		if (codiceCognome.length() < 3) {
			for (int i = 0; i < p.getCognome().length(); i++) {
				if (p.getCognome().charAt(i) == 'A' || p.getCognome().charAt(i) == 'E'
						|| p.getCognome().charAt(i) == 'I' || p.getCognome().charAt(i) == 'O'
						|| p.getCognome().charAt(i) == 'U') {
					codiceCognome.append(p.getCognome().charAt(i));

				}
				if (codiceCognome.length() == 3)
					break;
			}
			while (codiceCognome.length() < 3) {
				codiceCognome.append('X');
			}
		}
	}

	// Getters e setters

	public StringBuffer getCodiceCognome() {
		return codiceCognome;
	}

	public void setCodiceCognome(StringBuffer codiceCognome) {
		this.codiceCognome = codiceCognome;
	}

	public StringBuffer getCodiceNome() {
		return codiceNome;
	}

	public void setCodiceNome(StringBuffer codiceNome) {
		this.codiceNome = codiceNome;
	}

	public StringBuffer getCodiceAnno() {
		return codiceAnno;
	}

	public void setCodiceAnno(StringBuffer codiceAnno) {
		this.codiceAnno = codiceAnno;
	}

	public char getCodiceMese() {
		return codiceMese;
	}

	public void setCodiceMese(char codiceMese) {
		this.codiceMese = codiceMese;
	}

	public StringBuffer getCodiceGiornoESesso() {
		return codiceGiornoESesso;
	}

	public void setCodiceGiornoESesso(StringBuffer codiceGiornoESesso) {
		this.codiceGiornoESesso = codiceGiornoESesso;
	}

	public StringBuffer getCodiceComune() {
		return codiceComune;
	}

	public void setCodiceComune(StringBuffer codiceComune) {
		this.codiceComune = codiceComune;
	}

	public char getCarattereDiControllo() {
		return carattereDiControllo;
	}

	public void setCarattereDiControllo(char carattereDiControllo) {
		this.carattereDiControllo = carattereDiControllo;
	}

}
