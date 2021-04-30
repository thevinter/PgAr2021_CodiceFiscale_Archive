package theFightClub_Cod_Fiscali.unibs.it;

public class
Persona {

    private String COGNOME = null;
    private String NOME = null;
    private String ANNO = null;
    private String MESE = null;
    private String SESSO = null;
    private String GIORNO = null;
    private String COMUNE = null;
    private  String CF =  null;

public Persona(){

}


    public Persona(String COGNOME, String NOME, String ANNO, String MESE, String SESSO ,String GIORNO, String COMUNE) {
        this.COGNOME = COGNOME;
        this.NOME = NOME;
        this.ANNO = ANNO;
        this.MESE = MESE;
        this.SESSO = SESSO;
        this.GIORNO = GIORNO;
        this.COMUNE = COMUNE;

    }

public String getCOGNOME(){
        return COGNOME;
}

public void setCOGNOME(String cognome){
    this.COGNOME = cognome;
}
public void setNOME(String nome){
    this.NOME = nome;
}
public void setANNO(String anno){
    this.ANNO = anno;
}

public void setMESE(String mese){
    this.MESE = mese;
}

public void setGIORNO(String giorno){
    this.GIORNO = giorno;
}

public void setSESSO(String sesso){
    this.SESSO = sesso;
}

public void setCOMUNE(String comune){
    this.COMUNE = comune;
}

public String getNOME(){
        return NOME;
}

public String getANNO(){
        return ANNO;
}

public String getMESE(){
        return MESE;
}

public String getGIORNO(){
        return GIORNO;
}

public String getSESSO(){
        return SESSO;
}

public String getCOMUNE(){
        return COMUNE;
}

public String getCF(){
        return CF;
}

public void setCF(String CF){
        this.CF = CF;
}



    }

