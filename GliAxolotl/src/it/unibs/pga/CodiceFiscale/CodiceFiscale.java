package it.unibs.pga.CodiceFiscale;

public class CodiceFiscale {

    private String codice_fiscale;

    public CodiceFiscale(String codice_fiscale) {
        this.codice_fiscale = codice_fiscale;
    }

    public String getCodice_fiscale() {
        return codice_fiscale;
    }

    public void setCodice_fiscale(String codice_fiscale) {
        this.codice_fiscale = codice_fiscale;
    }


    /**
     * Metodo per il controllo della validità di un codice fiscale
     * @param codice_fiscale
     * @return verdicità
     */
    public boolean codiceValido(String codice_fiscale){ //CTTFNC01D60B157A
        boolean valido = true;
        //se ha meno di 16 caratteri è sicuramente sbagliato
        if (codice_fiscale.length() != 16){
            valido = false;
            return valido;
        }
        /*
        Il for mi controlla che i caratteri del codice fiscale passato siano al posto corretto.
        Quindi lettere e numero al posto giusto
         */
        for (int i = 0; i < 16; i++){
            if(i < 6){
                if(codice_fiscale.charAt(i) < 'A' || codice_fiscale.charAt(i) > 'Z'){
                    valido = false;
                    return valido;
                }
            }else if(i >= 6 && i < 8){
                if(codice_fiscale.charAt(i) < '0' || codice_fiscale.charAt(i) >'9'){
                    valido = false;
                    return valido;
                }
            }else if(!controlloMese(codice_fiscale.charAt(8))){  //controlla
                valido = false;
                return valido;
            }else if(i == 9){
                i += 1;
                if(!controlloGiornoNascita(codice_fiscale)){
                    valido = false;
                    return valido;
                }
            }else if(i == 11){
                if(codice_fiscale.charAt(i) < 'A' || codice_fiscale.charAt(i) > 'Z'){
                    valido = false;
                    return valido;
                }
            }else if(i >= 12 && i < 15){
                if(codice_fiscale.charAt(i) < '0' || codice_fiscale.charAt(i) >'9'){
                    valido = false;
                    return valido;
                }
            }else if(i == 15){
                GeneratoreCodiceFiscale gcf = new GeneratoreCodiceFiscale();
                if(codice_fiscale.charAt(i) >= 'A' && codice_fiscale.charAt(i) <= 'Z'){
                    if(!gcf.letteraDiControllo(codice_fiscale).equals(String.valueOf(codice_fiscale.charAt(15)))){
                        valido = false;
                        return valido;
                    }

                }else{
                    valido = false;
                    return valido;
                }
            }
        }
        return valido;
    }

    /**
     * controlla che il giorno esista e controlla
     * che appartenga al mese indicato in precendeza
     * Si presuppone, come indicato dalla consegna, che febbraio abbia sempre 28 giorni
     *
     * @param codice_fiscale
     * @return valido
     */
    private boolean controlloGiornoNascita(String codice_fiscale) {
        String giorno = String.valueOf(codice_fiscale.charAt(9)) + String.valueOf(codice_fiscale.charAt(10));
        Integer gg= Integer.valueOf(giorno); //trasformiamo il giorno, che viene preso come una stringa in un numero
        boolean valido = false;
        /*
        controllo che nel mese di febbraio ci siano 28 giorni; nei mesi di
        settembre, novembre, aprile, giugno ce ne siano 30; nei restanti 31
         */
        if(codice_fiscale.charAt(8) == 'B'){
            if(gg >= 1 && gg <= 28 || gg >= 41 && gg <= 68){
                valido = true;
            }
        }else if(codice_fiscale.charAt(8) == 'D'||codice_fiscale.charAt(8) == 'P'||
                codice_fiscale.charAt(8) == 'H' ||codice_fiscale.charAt(8) == 'S'){
            if(gg >= 1 && gg <= 30 || gg >= 41 && gg <= 70){
                valido = true;
            }
        }else{
            if(gg >= 1 && gg <= 31 || gg >= 41 && gg <= 71){
                valido = true;
            }
        }
        return valido;
    }

    /**
     * Metodo che controlla che il carattere del mese sia una lettera valida
     * @param lettera
     * @return veridicità
     */
    public boolean controlloMese(char lettera){
        boolean valido = true;
        if(lettera == 'A' || lettera == 'B' || lettera == 'C' || lettera == 'D'
            || lettera == 'E' || lettera == 'H' || lettera == 'L' || lettera == 'M'
            || lettera == 'P' || lettera == 'R' || lettera =='S' || lettera == 'T'){
            return valido;
        }else{
            valido = false;
            return valido;
        }
    }

}
