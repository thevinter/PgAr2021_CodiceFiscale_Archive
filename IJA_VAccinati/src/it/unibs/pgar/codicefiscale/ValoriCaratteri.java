package it.unibs.pgar.codicefiscale;

/**
 * Enumerazione che contiene i possibili caratteri contenuti in un codice fiscale e i SIMPATICISSIMI
 * relativi valori, qualora il carattere considerato si trovi in un posto pari o dispari, per il  calcolo del carattere di controllo
 */
public enum ValoriCaratteri {
    N0('0', 1, 0),
    N1('1', 0, 1),
    N2('2', 5, 2),
    N3('3', 7, 3),
    N4('4', 9, 4),
    N5('5', 13, 5),
    N6('6', 15, 6),
    N7('7', 17, 7),
    N8('8', 19, 8),
    N9('9', 21, 9),
    A('A', 1, 0),
    B('B', 0, 1),
    C('C', 5, 2),
    D('D', 7, 3),
    E('E', 9, 4),
    F('F', 13, 5),
    G('G', 15, 6),
    H('H', 17, 7),
    I('I', 19, 8),
    J('J', 21, 9),
    K('K', 2, 10),
    L('L', 4, 11),
    M('M', 18, 12),
    N('N', 20, 13),
    O('O', 11, 14),
    P('P', 3, 15),
    Q('Q', 6, 16),
    R('R', 8, 17),
    S('S', 12, 18),
    T('T', 14, 19),
    U('U', 16, 20),
    V('V', 10, 21),
    W('W', 22, 22),
    X('X', 25, 23),
    Y('Y', 24, 24),
    Z('Z', 23, 25);

    private char cosaRappresentano;
    private int valoreDispari;
    private int valorePari;

    ValoriCaratteri(char cosaRappresentano, int valoreDispari, int valorePari) {
        this.cosaRappresentano = cosaRappresentano;
        this.valoreDispari = valoreDispari;
        this.valorePari = valorePari;
    }

    public char getCosaRappresentano() {
        return cosaRappresentano;
    }

    public void setCosaRappresentano(char cosaRappresentano) {
        this.cosaRappresentano = cosaRappresentano;
    }

    public int getValoreDispari() {
        return valoreDispari;
    }

    public void setValoreDispari(int valoreDispari) {
        this.valoreDispari = valoreDispari;
    }

    public int getValorePari() {
        return valorePari;
    }

    public void setValorePari(int valorePari) {
        this.valorePari = valorePari;
    }

}
