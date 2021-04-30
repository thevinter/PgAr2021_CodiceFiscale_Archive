package it.unibs.pgar.codicefiscale;

/**
 * Una classe che definisce l'oggetto data, scomposto in anno, mese e giorno.
 * In questo modo i dati risultano meglio accessibili.
 */
public class Data {

    private int anno;
    private int mese;
    private int giorno;

    public Data(int anno, int mese, int giorno) {
        this.anno = anno;
        this.mese = mese;
        this.giorno = giorno;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getMese() {
        return mese;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public int getGiorno() {
        return giorno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    /**
     * Crea un array di caratteri a partire da un numero intero
     *
     * @param numero: int
     * @return numeroInCaratteri: char[]
     */
    public char[] creaArray(int numero) {
        Integer temp = numero;
        return temp.toString().toCharArray();
    }

    /**
     * Partendo da una stringa che rappresenta la data di nascita
     * crea un oggetto Data
     *
     * @param dataData: String
     * @return dataGenerata: Data
     */
    public static Data generaData(String dataData /*XD loL lMaO*/) {
        char[] dataDataArray = dataData.toCharArray();

        int annoData = 0;
        int meseData = 0;
        int giornoData = 0;

        for (int i = 0; dataDataArray[i] != '-'; i++) {
            annoData *= 10;
            annoData += dataDataArray[i] - '0';
        }

        for (int i = 5; dataDataArray[i] != '-'; i++) {
            meseData *= 10;
            meseData += dataDataArray[i] - '0';
        }

        for (int i = 8; i < dataDataArray.length; i++) {
            giornoData *= 10;
            giornoData += dataDataArray[i] - '0';
        }

        Data data = new Data(annoData, meseData, giornoData);

        return data;
    }

    @Override
    /**
     * Esprime gli attributi dell'oggetto Data sottoforma di stringa
     */
    public String toString() {
        return anno + "-" + (mese < 10 ? "0" + mese : mese) + "-" + (giorno < 10 ? "0" + giorno : giorno);
    }

}
