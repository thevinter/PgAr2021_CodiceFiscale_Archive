package it.unibs.prgarnaldo.cuorisolitari.codicefiscale;

public class MainClass {

    public static void main(String[] args) {
        String prova = "FRMPLA01D26D434N";

        int day;
        day = 30;
        int year;
        year = 2020;
        int month;
        month = 3;


        boolean b = Data.VerificaData(day,month,year);
        if ( b == false )
            System.out.println("data non valida");
        else
            System.out.println("data corretta");
        if ( month==2 && day > 29 )
            System.out.println("il mese di febbraio ha al massimo 29 giorni");


        boolean a = CodiceFiscale.verificaCF(prova);
        if (a==false) System.out.println("ciao");
        else System.out.println("bella");

        Data data = new Data( day, month, year);
        System.out.println(data.getCarattere_mese());
    }
}