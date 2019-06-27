package com.example.bitmap;

import java.util.ArrayList;

//import static com.example.bitmap.DataTypeIso.AN_3N;

public class DataConvert {
    private int bit;
    private String dataType;
    private String fixx;
    private int dataMaxLen;
    private String data;
    //private DefIso8583 tablaISO;

    DataConvert () {
        bit = 1;
        dataType = "N_N";
        fixx = "F";
        dataMaxLen = 0;
        data = "";
        //isoTable();
    }
    public void DataConvert(int b, String dti, String fx, int dl, String d) {
        bit = b;
        dataType = dti;
        fixx =fx;
        dataMaxLen = dl;
        data = d;
        //isoTable();
    }

    public void setBit(int bit) {
        this.bit = bit;
    }

    public int getBit() {
        return bit;
    }

    public void setDataType(String dataType) {
        dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setFixx(String fixx) {
        this.fixx = fixx;
    }

    public String getFixx() {
        return fixx;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setdataMaxLen(int dataMaxLen) {
        this.dataMaxLen = dataMaxLen;
    }

    public int getdataMaxLen() {
        return dataMaxLen;
    }

    /*
    public void isoTable()
    {
        tablaISO = new DefIso8583();
    }
    */

    // parameters: bit, data
    /*
    public String buildISOelement(int I, String D) {
        String ret = "";

        //elementIso8583 bit = tablaISO.getElem(I);

        switch (bit.getAttribute()) {
            case "AN_3N":
                DataTypeIso elementType=AN_3N;
                ret = moveData(D, elementType);
                break;
        }

        return ret;
    }
    */
    public String buildISOelement()
    {
        String ret = "";
        return moveData();
    }

    String fillWithC(int n, char c) {
        char[] sch = new char[n];
        for (int i=0 ; i<n ; i++) sch[i] = c;
        return sch.toString();
    }

    String moveData()
    //String moveData(String src, DataTypeIso dt)
    {
        String sRet;
        int dLen = data.length();

        if ( ( dataType == "AN" ) || ( dataType == "ANS" ) || ( dataType == "B" )) {
            if (fixx == "F") {
                sRet = data + fillWithC(dLen,' ');
                return sRet.substring(0,dLen);
            }
            else {
                int ln = dataMaxLen;
                if (ln == 2) {
                    // includes len in the first byte
                    uubyte ub = new uubyte(ln);
                    sRet = String.format("%02d%s", ub.cvtToX(), data + fillWithC(dLen,' '));
                    return sRet.substring(0,dLen);
                }
                else if (ln == 3) {
                    // start  with len in two positions
                    uubyte ub = new uubyte(ln);
                    sRet = String.format("%04d%s", ub.cvtToX(), data + fillWithC(dLen,' '));
                    return sRet.substring(0,dLen);
                }
            }
        }
        else if ( ( dataType == "N" ) || ( dataType == "Z" ) ) {
            int ln = dataMaxLen;
            uubyte ub = new uubyte(ln);
            if (fixx == "F") {
                sRet = data + fillWithC(dLen,' ');
                return sRet.substring(0,dLen);
            }
            else {
                if (ln == 2) {
                    // includes len in the first byte
                    sRet = String.format("%02d%s", ub.cvtToX(), ub.compactStr(data) + fillWithC(dLen,' '));
                    return sRet.substring(0,dLen);
                }
                else if (ln == 3) {
                    // start  with len in two positions
                    sRet = String.format("%04d%s", ub.cvtToX(), ub.compactStr(data) + fillWithC(dLen,' '));
                    return sRet.substring(0,dLen);
                }
            }
        }
        return "";
    }

}
