
    package it.unibs.Pa.CodiceFiscale;


    import javax.xml.stream.XMLStreamException;
    import java.util.ArrayList;

    public class Persona {

        private int id;
        private String nome;
        private String cognome;
        private String sesso;
        private String comuneNascita;
        private String data_nascita;
        private String codice_fiscale;

        public Persona() {

        }

        public Persona(String codice_fiscale) {
            this.codice_fiscale = codice_fiscale;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCognome() {
            return cognome;
        }

        public void setCognome(String cognome) {
            this.cognome = cognome;
        }

        public String getSesso() {
            return sesso;
        }

        public void setSesso(String sesso) {
            this.sesso = sesso;
        }

        public String getComuneNascita() {
            return comuneNascita;
        }

        public void setComuneNascita(String comuneNascita) {
            this.comuneNascita = comuneNascita;
        }

        public String getData_nascita() {
            return data_nascita;
        }

        public void setData_nascita(String data_nascita) {
            this.data_nascita = data_nascita;
        }

        public String getCodice_fiscale() {
            return codice_fiscale;
        }

        public void setCodice_fiscale(String codice_fiscale) {
            this.codice_fiscale = codice_fiscale;
        }

        @Override
        public String toString() {
            return "Persona{" +
                    "id=" + id +
                    ", nome='" + nome + '\'' +
                    ", cognome='" + cognome + '\'' +
                    ", sesso='" + sesso + '\'' +
                    ", comuneNascita='" + comuneNascita + '\'' +
                    ", data_nascita='" + data_nascita + '\'' +
                    '}';
        }



        public String creaParteCognome() {
            StringBuffer parteCognome = new StringBuffer();
            int cont = 0;
            /*caso cognome minore di 3 lettere*/
            if (cognome.length() < 3) {

                while (parteCognome.length() < 3) {

                    for (int i = 0; i < cognome.length() && parteCognome.length() <= cognome.length(); i++) {
                        parteCognome.append(String.format("%s", cognome.charAt(i)));
                    }

                    parteCognome.append("X");
                }
                cont = 3;
            }
            /*caso normale*/
            for (int i = 0; i < cognome.length(); i++) {
                if (cont == 3) break;
                if (cognome.charAt(i) != 'A' && cognome.charAt(i) != 'E' &&
                        cognome.charAt(i) != 'I' && cognome.charAt(i) != 'O' &&
                        cognome.charAt(i) != 'U') {
                    parteCognome.append(String.format("%s", cognome.charAt(i)));
                    cont++;
                }
            }
            /* nel casoci siano meno di 3 consonanti*/
            while (cont < 3) {
                for (int i = 0; i < cognome.length(); i++) {
                    if (cont == 3) break;
                    if (cognome.charAt(i) == 'A' || cognome.charAt(i) == 'E' ||
                            cognome.charAt(i) == 'I' || cognome.charAt(i) == 'O' ||
                            cognome.charAt(i) == 'U') {
                        parteCognome.append(String.format("%s", cognome.charAt(i)));
                        cont++;
                    }
                }
            }
            return new String(parteCognome);
        }


        public String creaParteNome() {
            StringBuffer parteNome = new StringBuffer();

            /*lettere nome*/
            int cont = 0;
            /*caso nome minore di 3 lettere*/
            if (nome.length() < 3) {

                while (parteNome.length() < 3) {

                    for (int i = 0; i < nome.length() && parteNome.length() <= nome.length(); i++) {
                        parteNome.append(String.format("%s", nome.charAt(i)));
                    }

                    parteNome.append("X");
                }
                cont = 3;
            }
            /*trovo il numero di consonanti*/
            int consonanti = 0;
            for (int i = 0; i < nome.length(); i++) {
                if (cont == 3) break;

                if (nome.charAt(i) != 'A' && nome.charAt(i) != 'E' &&
                        nome.charAt(i) != 'I' && nome.charAt(i) != 'O' &&
                        nome.charAt(i) != 'U') {

                    consonanti++;
                }
            }
            /*lo faccio solo se le consonanti sono almeno 4 e prendo la prima, la terza e la quarta*/
            if (consonanti >= 4) {
                int posizioneConsonante = 0;
                for (int i = 0; i < nome.length(); i++) {
                    if (cont == 3) break;

                    if (nome.charAt(i) != 'A' && nome.charAt(i) != 'E' &&
                            nome.charAt(i) != 'I' && nome.charAt(i) != 'O' &&
                            nome.charAt(i) != 'U') {

                        if (posizioneConsonante!= 1) {
                            parteNome.append(String.format("%s", nome.charAt(i)));
                            cont++;
                        }
                        posizioneConsonante++;
                    }

                }
                /*se le consonanti sono meno di 4*/
            } else if (consonanti<4){

                for (int i = 0; i < nome.length(); i++) {
                    if (cont == 3) break;
                    if (nome.charAt(i) != 'A' && nome.charAt(i) != 'E' &&
                            nome.charAt(i) != 'I' && nome.charAt(i) != 'O' &&
                            nome.charAt(i) != 'U') {
                        parteNome.append(String.format("%s", nome.charAt(i)));
                        cont++;
                    }
                }
                /* se ci sono meno di 3 consonanti*/
                while (cont < 3) {
                    for (int i = 0; i < nome.length(); i++) {
                        if (cont == 3) break;
                        if (nome.charAt(i) == 'A' || nome.charAt(i) == 'E' ||
                                nome.charAt(i) == 'I' || nome.charAt(i) == 'O' ||
                                nome.charAt(i) == 'U') {
                            parteNome.append(String.format("%s", nome.charAt(i)));
                            cont++;
                        }
                    }
                }

            }
            return new String(parteNome);

        }


        public String creaParteAnnoMese(){
            StringBuffer parteAnnoMese = new StringBuffer();

            /*parte anno di nascita*/
            parteAnnoMese.append(data_nascita.charAt(2));
            parteAnnoMese.append(data_nascita.charAt(3));

            /*parte mese di nascita*/
            int mese = 0;
            if (data_nascita.charAt(5)== '0') mese = Integer.parseInt(data_nascita.substring(6,7));
            else mese = Integer.parseInt(data_nascita.substring(5,7));
            switch (mese) {
                case 1:
                    parteAnnoMese.append("A");
                    break;
                case 2:
                    parteAnnoMese.append("B");
                    break;
                case 3:
                    parteAnnoMese.append("C");
                    break;
                case 4:
                    parteAnnoMese.append("D");
                    break;
                case 5:
                    parteAnnoMese.append("E");
                    break;
                case 6:
                    parteAnnoMese.append("H");
                    break;
                case 7:
                    parteAnnoMese.append("L");
                    break;
                case 8:
                    parteAnnoMese.append("M");
                    break;
                case 9:
                    parteAnnoMese.append("P");
                    break;
                case 10:
                    parteAnnoMese.append("R");
                    break;
                case 11:
                    parteAnnoMese.append("S");
                    break;
                case 12:
                    parteAnnoMese.append("T");
                    break;

            }

            return new String(parteAnnoMese);
        }



        public String creaParteGiornoSesso(){
            StringBuffer parteGiornoSesso = new StringBuffer();

            if (sesso.charAt(0) == 'M'){
                parteGiornoSesso.append(data_nascita.substring(8,10));
            } else {
                int giorno = Integer.parseInt(data_nascita.substring(8,10));
                giorno += 40;
                parteGiornoSesso.append(giorno);
            }

            return new String(parteGiornoSesso);
        }

        public char creaCarattereControllo(String radice){
            int sommaDeiDispari= 0;
            for (int i=0; i<radice.length(); i+=2){
                switch (radice.charAt(i)) {
                    case '0':
                        sommaDeiDispari += 1;
                        break;
                    case '1':
                        sommaDeiDispari += 0;
                        break;
                    case '2':
                        sommaDeiDispari += 5;
                        break;
                    case '3':
                        sommaDeiDispari += 7;
                        break;
                    case '4':
                        sommaDeiDispari += 9;
                        break;
                    case '5':
                        sommaDeiDispari += 13;
                        break;
                    case '6':
                        sommaDeiDispari += 15;
                        break;
                    case '7':
                        sommaDeiDispari += 17;
                        break;
                    case '8':
                        sommaDeiDispari += 19;
                        break;
                    case '9':
                        sommaDeiDispari += 21;
                        break;
                    case 'A':
                        sommaDeiDispari += 1;
                        break;
                    case 'B':
                        sommaDeiDispari += 0;
                        break;
                    case 'C':
                        sommaDeiDispari += 5;
                        break;
                    case 'D':
                        sommaDeiDispari += 7;
                        break;
                    case 'E':
                        sommaDeiDispari += 9;
                        break;
                    case 'F':
                        sommaDeiDispari += 13;
                        break;
                    case 'G':
                        sommaDeiDispari += 15;
                        break;
                    case 'H':
                        sommaDeiDispari += 17;
                        break;
                    case 'I':
                        sommaDeiDispari += 19;
                        break;
                    case 'J':
                        sommaDeiDispari += 21;
                        break;
                    case 'K':
                        sommaDeiDispari += 2;
                        break;
                    case 'L':
                        sommaDeiDispari += 4;
                        break;
                    case 'M':
                        sommaDeiDispari += 18;
                        break;
                    case 'N':
                        sommaDeiDispari += 20;
                        break;
                    case 'O':
                        sommaDeiDispari += 11;
                        break;
                    case 'P':
                        sommaDeiDispari += 3;
                        break;
                    case 'Q':
                        sommaDeiDispari += 6;
                        break;
                    case 'R':
                        sommaDeiDispari += 8;
                        break;
                    case 'S':
                        sommaDeiDispari += 12;
                        break;
                    case 'T':
                        sommaDeiDispari += 14;
                        break;
                    case 'U':
                        sommaDeiDispari += 16;
                        break;
                    case 'V':
                        sommaDeiDispari += 10;
                        break;
                    case 'W':
                        sommaDeiDispari += 22;
                        break;
                    case 'X':
                        sommaDeiDispari += 25;
                        break;
                    case 'Y':
                        sommaDeiDispari += 24;
                        break;
                    case 'Z':
                        sommaDeiDispari += 23;
                        break;
                }
            }



            int sommaDeiPari= 0;
            for (int i=1; i<radice.length(); i+=2) {
                switch (radice.charAt(i)) {
                    case '0':
                        sommaDeiPari += 0;
                        break;
                    case '1':
                        sommaDeiPari += 1;
                        break;
                    case '2':
                        sommaDeiPari += 2;
                        break;
                    case '3':
                        sommaDeiPari += 3;
                        break;
                    case '4':
                        sommaDeiPari += 4;
                        break;
                    case '5':
                        sommaDeiPari += 5;
                        break;
                    case '6':
                        sommaDeiPari += 6;
                        break;
                    case '7':
                        sommaDeiPari += 7;
                        break;
                    case '8':
                        sommaDeiPari += 8;
                        break;
                    case '9':
                        sommaDeiPari += 9;
                        break;
                    case 'A':
                        sommaDeiPari += 0;
                        break;
                    case 'B':
                        sommaDeiPari += 1;
                        break;
                    case 'C':
                        sommaDeiPari += 2;
                        break;
                    case 'D':
                        sommaDeiPari += 3;
                        break;
                    case 'E':
                        sommaDeiPari += 4;
                        break;
                    case 'F':
                        sommaDeiPari += 5;
                        break;
                    case 'G':
                        sommaDeiPari += 6;
                        break;
                    case 'H':
                        sommaDeiPari += 7;
                        break;
                    case 'I':
                        sommaDeiPari += 8;
                        break;
                    case 'J':
                        sommaDeiPari += 9;
                        break;
                    case 'K':
                        sommaDeiPari += 10;
                        break;
                    case 'L':
                        sommaDeiPari += 11;
                        break;
                    case 'M':
                        sommaDeiPari += 12;
                        break;
                    case 'N':
                        sommaDeiPari += 13;
                        break;
                    case 'O':
                        sommaDeiPari += 14;
                        break;
                    case 'P':
                        sommaDeiPari += 15;
                        break;
                    case 'Q':
                        sommaDeiPari += 16;
                        break;
                    case 'R':
                        sommaDeiPari += 17;
                        break;
                    case 'S':
                        sommaDeiPari += 18;
                        break;
                    case 'T':
                        sommaDeiPari += 19;
                        break;
                    case 'U':
                        sommaDeiPari += 20;
                        break;
                    case 'V':
                        sommaDeiPari += 21;
                        break;
                    case 'W':
                        sommaDeiPari += 22;
                        break;
                    case 'X':
                        sommaDeiPari += 23;
                        break;
                    case 'Y':
                        sommaDeiPari += 24;
                        break;
                    case 'Z':
                        sommaDeiPari += 25;
                        break;

                }
            }

            int sommaTotale = sommaDeiDispari+sommaDeiPari;
            int resto = sommaTotale % 26;

            switch (resto){
                case 0:
                    return 'A';

                case 1:
                    return 'B';

                case 2:
                    return 'C';

                case 3:
                    return 'D';

                case 4:
                    return 'E';

                case 5:
                    return 'F';

                case 6:
                    return 'G';

                case 7:
                    return 'H';

                case 8:
                    return 'I';

                case 9:
                    return 'J';

                case 10:
                    return 'K';

                case 11:
                    return 'L';

                case 12:
                    return 'M';

                case 13:
                    return 'N';

                case 14:
                    return 'O';

                case 15:
                    return 'P';

                case 16:
                    return 'Q';

                case 17:
                    return 'R';

                case 18:
                    return 'S';

                case 19:
                    return 'T';

                case 20:
                    return 'U';

                case 21:
                    return 'V';

                case 22:
                    return 'W';

                case 23:
                    return 'X';

                case 24:
                    return 'Y';

                default:
                    return 'Z';

            }
        }

        public String creaParteComune(){
            StringBuffer parteComune = new StringBuffer();
            ArrayList<Comune> Lista_Comuni = new ArrayList<Comune>();
            try {
                Lista_Comuni = LettoreXML.leggi_Comune();
            } catch (XMLStreamException e) {

            }
            for (int i=0; i<Lista_Comuni.size(); i++){
                if (this.comuneNascita.equals(Lista_Comuni.get(i).getNome())){
                    return Lista_Comuni.get(i).getCodice();
                }
            }
            //operazione di default in caso il comune non sia indicato
            return Lista_Comuni.get(0).getCodice();
        }


        //creaCodiceFiscaleFinale
        public String creaCodiceFiscaleFinale(ArrayList<String> lista_codici) {
            char carattere_controllo;

            StringBuffer codiceFiscale = new StringBuffer();
            codiceFiscale.append(creaParteCognome()+creaParteNome()+creaParteAnnoMese()+creaParteGiornoSesso()+ creaParteComune());
            String radice = new String(codiceFiscale);

            carattere_controllo = creaCarattereControllo(radice);

            codiceFiscale.append(carattere_controllo);


            String Codice = new String(codiceFiscale);

            for (int i=0; i<lista_codici.size(); i++) {
                if (Codice.equals(lista_codici.get(i))) {
                    return new String(codiceFiscale);
                }
            }
            return "ASSENTE";
        }




        //FUNZIONE CHE RICEVUTO UN CODICE FISCALE NE VERIFICA LA VALIDITA'
        public boolean VerificaCodice(String CodiceFiscale){
            StringBuffer Codice = new StringBuffer(CodiceFiscale);
            for (int i=0; i<CodiceFiscale.length(); i++){
                //controlla se le lettere sono in posizioni sbagliate
                if (!(Codice.charAt(i) >= 'A' && Codice.charAt(i) <= 'Z') && (i<6 || i==8 || i==11 || i==15))
                    return false;
                if (!(Codice.charAt(i) >= '0' && Codice.charAt(i) <= '9') && ((i>=6 && i<=7) || (i>=9 && i<=10) || (i>=12 && i<=14)))
                    return false;
            }

            //controlla la data di nascita
            int giorno = Integer.parseInt(Codice.substring(9, 11));
            if (Codice.charAt(8) == 'A' || Codice.charAt(8) == 'C' || Codice.charAt(8) == 'E' || Codice.charAt(8) == 'L'
                    || Codice.charAt(8) == 'M' || Codice.charAt(8) == 'R' || Codice.charAt(8) == 'T') {
                if (!((giorno >= 1 && giorno <= 31) || (giorno >= 41 && giorno <= 71)))
                    return false;
            }
            else if ((Codice.charAt(8) == 'S' || Codice.charAt(8) == 'D' || Codice.charAt(8) == 'H' || Codice.charAt(8) == 'P')) {
                if (!((giorno >= 1 && giorno <= 30) || (giorno >= 41 && giorno <= 70)))
                    return false;
            }
            else if (Codice.charAt(8) == 'B'){
                if (!((giorno >= 1 && giorno <= 28) || (giorno >= 41 && giorno <= 68)))
                    return false;
            }
            else
                return false;

            //crea il carattere di controllo lo confronta con quello presente nel codice
            String radice = new String(Codice.substring(0, 15));
            char carattere_controllo = creaCarattereControllo(radice);
            if (!(carattere_controllo == Codice.charAt(15)))
                return false;

            return true;
        }

}
