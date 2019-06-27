package com.example.bitmap;

public class elementIso8583 {
    private int Bit;
    private String Data_Element_Name;
    private String Attribute;
    private int Len;

    public elementIso8583()
    {
        Bit = -1;
        Data_Element_Name = "";
        Attribute = "";
        Len = 0;
    }

    public void addElement(int B, String Name, String A, int L)
    {
        Bit = B;
        Data_Element_Name = Name;
        Attribute = A;
        Len = L;
    }

    public int getBit()
    {
        return Bit;
    }

    public String getData_Element_Name()
    {
        return Data_Element_Name;
    }

    public String getAttribute()
    {
        return Attribute;
    }

    public int getLen()
    {
        return Len;
    }
}
