package com.example.bitmap;

public enum DataTypeIso {
    B_N    ("B",   "F", 0),
    B_3N   ("B",   "V", 3),
    N_N    ("N",   "F", 0),
    N_2N   ("N",   "V", 2),
    AN_N   ("AN",  "F", 0),
    AN_3N  ("AN",  "V", 3),
    ANS_N  ("ANS", "F", 0),
    ANS_2N ("ANS", "V", 2),
    ANS_3N ("ANS", "V", 3),
    Z_2N   ("Z",   "V", 2);

    private final String tipo;
    private final String var_fix;
    private final int nlen;

    DataTypeIso(String tipo, String var_fix, int nlen)
    {
        this.tipo = tipo;
        this.var_fix =var_fix;
        this.nlen = nlen;
    }

    public String getTipo() { return tipo; }
    public String getVar_fix() { return var_fix; }
    public int getNlen() { return nlen; }
}
