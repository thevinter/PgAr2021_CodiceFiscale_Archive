public class Main {

     public static void main(String[] args){

          GestionePersone start = new GestionePersone(); //creo nuovo oggetto di classe GestionePersone

          start.importPersone(); //richiamo metodo importPersone della classe GestionePersone
          start.importCodici(); //richiamo metodo importCodici della classe GestionePersone
          start.verificaPresenza(); //richiamo metodo verificaPresenza della classe GestionePersone

          //controllo funzionamento fileOutput
          if(start.generaFileOutput())
               System.out.println("File di output generato correttamente!");

     }

}
