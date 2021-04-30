package arnaldo.anno2021.triumvirato.codicefiscale;

public class Constants {
	public static final int CF_SIZE=16; //CCCNNN01L23G009C
	public static final int DIVISORE_CARATTERE_CONTROLLO=26;
	public static final int GENDER_NUMBER_TO_ADD=40; //must be greater than 31
	public static final int POSIZIONE_CARATTERE_MESE=8;
	public static final int POSIZIONE_CARATTERE_INIZIALE_COMUNE=11; //must differ from POSIZIONE_CARATTERE_MESE
	public static final char FILLER_CHAR='X';
	public static final String[] POSSIBLE_GENDER_NOTATION= {"Maschio","Femmina","Uomo","Donna","Maschile","Femminile","M","F","U","D"};
	
	public static final int[] GIORNI_PER_MESE= {31,28,31,30,31,30,31,31,30,31,30,31}; //30 in [10][3][5][8] 28 in [1] else 31
	public static final char[] MONTH_CHARS= {'A','B','C','D','E','H','L','M','P','R','S','T'};
	public static final char[] ESTREMI_INFERIORI_CARATTERI_CF= {'A','A','A','A','A','A','0','0','A','0','0','A','0','0','0','A'};
	public static final char[] ESTREMI_SUPERIORI_CARATTERI_CF= {'Z','Z','Z','Z','Z','Z','9','9','T','7','9','Z','9','9','9','Z'};
	
	
	public static final String PATH_RELATIVO_INPUT_FOLDER="xmlInputFiles/";
	public static final String NOME_FILE_XML_PERSONE="inputPersone.xml";
	public static final String NOME_FILE_XML_COMUNI="comuni.xml";
	public static final String NOME_FILE_XML_CODICI_FISCALI="codiciFiscali.xml";
	
	public static final String PATH_RELATIVO_PERSONE=PATH_RELATIVO_INPUT_FOLDER+NOME_FILE_XML_PERSONE;
	public static final String PATH_RELATIVO_COMUNI=PATH_RELATIVO_INPUT_FOLDER+NOME_FILE_XML_COMUNI;
	public static final String PATH_RELATIVO_CODICI_FISCALI=PATH_RELATIVO_INPUT_FOLDER+NOME_FILE_XML_CODICI_FISCALI;
	public static final String PATH_RELATIVO_OUTPUT="codiciPersone.xml";

	public static final String ALERT_MESSAGE_SUCCESSFUL_EXECUTION="L'esecuzione del programma è avvenuta con successo.";
	public static final String ERROR_MESSAGE_EXECUTION_STOPPED="ERRORE: a causa di uno o più errori(sopra indicati se è stato possibile individuarli) il programma ha dovuto terminare la sua esecuzione anzitempo, si raccomanda di ricontrollare i file di input per evitare che questo si verifichi di nuovo";
	public static final String ERROR_MESSAGE_PERSONA_NON_ANALIZZABILE="ERRORE: a causa di un errore di lettura non sarà possibile analizzare l'individuo %s %s";
	public static final String ERROR_MESSAGE_FILE_ASSENTI=String.format("ERRORE: il file cercato risulta assente, si prega di controllare di averlo inserito nella cartella corretta e con il nome previsto, si ricordano di seguito i path previsti: \n1-xml coi dati sulle persone in %s \n2-xml coi dati sui comuni in %s \n3-xml coi dati sui codici fiscali in %s", PATH_RELATIVO_PERSONE,PATH_RELATIVO_COMUNI,PATH_RELATIVO_CODICI_FISCALI);
	public static final String ERROR_MESSAGE_INPUT_CODICE_COMUNE="ERRORE: Il codice del comune %s non è consono ai parametri definiti(quattro simboli, il primo dei quali è una lettera maiuscola, gli altri tre caratteri numerici), il comune in questione non sarà quindi preso in analisi";
	public static final String ERROR_MESSAGE_INPUT_PERSONA_ERRATO="ERRORE: non è stato possibile interpretare il dato %s inserito per la persona di id %d nel file "+NOME_FILE_XML_PERSONE+" si prega di rivedere l'inserimento in caso si voglia ottenere l'esecuzione corretta";
	public static final String ERROR_MESSAGE_FILE_INVALIDI="ERRORE: si è verificato un errore nalla lettura del file %s";
	public static final String INFORMATION_MESSAGE=String.format("Benvenuto nel nostro programma, per ottenere le informazioni sui codici fiscali di un gruppo di persone, inserire le seguenti informazioni nei file indicati, i quali si trovano tutti nella cartella %s all'interno della directory principale:"
	+"\n1-dati delle persone in %s\n2-dati dei comuni in %s\n3-codici fiscali di partenza(che verranno verificati) in %s"
	+"\n\nI dati da lei forniti verranno elaborati e restituiti in formato xml nel file %s presente nella directory principale del programma."
	+"\nIl file di output conterrà i dati delle persone con i relativi codici fiscali da lei forniti in %s, sarà effettuato però un controllo correttezza e scritto \"ASSENTE\" per i codici fiscali errati o assenti."
	+"\nSaranno inoltre riportati nel tratto successivo i codici fiscali errati o non corrispondenti a nessuna persona.\n",
	PATH_RELATIVO_INPUT_FOLDER, NOME_FILE_XML_PERSONE, NOME_FILE_XML_COMUNI, NOME_FILE_XML_CODICI_FISCALI, PATH_RELATIVO_OUTPUT, NOME_FILE_XML_CODICI_FISCALI);
}
