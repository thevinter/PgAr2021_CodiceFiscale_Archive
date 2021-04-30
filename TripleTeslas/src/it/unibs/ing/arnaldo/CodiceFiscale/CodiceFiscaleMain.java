package it.unibs.ing.arnaldo.CodiceFiscale;

public class CodiceFiscaleMain {

    public static void main(String[] args) {

        String filename = ("inputPersone.xml");
        XMLReaderCF fileDaLeggere = new XMLReaderCF(filename);
        fileDaLeggere.readFile();

    }

}
