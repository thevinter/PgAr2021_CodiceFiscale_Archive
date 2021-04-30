package it.unibs.arnaldo.cf;

public class Main {
	public static void main(String args[]) {
		/*ORACOLO PROTETTORE DEL CODICE, NON CANCELLARE*/
		System.out.println("Se non va mi uccido");
		/*ORACOLO PROTETTORE DEL CODICE, NON CANCELLARE*/
		
		ElencoComuni.init();
		
		Database db = new Database();
		db.genAll();
		db.readCfs();
		db.checkCfsValidi();
		db.checkCfsSpaiati();
		db.writeCfs();
		
		
		/*
		//PER IL DEBUG
		CodiceFiscale cf = new CodiceFiscale("ALBIERI", "LUCA", Sesso.M, "2001-07-05", "Desenzano del garda");
		System.out.println(cf);
		
		//ElencoComuni.printAll();
		System.out.println(ElencoComuni.getCodice("Padenghe sul Garda"));
		
		//db.printAllPersone();
		//db.printAllPresenti();
		//db.printAllCFs();
		//db.printAllCFsSpaiati();
		*/		
	}
}
