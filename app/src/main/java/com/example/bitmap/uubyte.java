package com.example.bitmap;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class uubyte {
    private byte u1;
    private int in1;

    uubyte()
    {
        u1=0x00;
        in1 = 0;
    }

    uubyte( byte u )
    {
        u1 = u;
        in1 = (int) u;
    }

    uubyte( int in )
    {
        u1 = (byte) in;
        in1 = in;
    }

    byte getu1()
    {
        return u1;
    }

    void setu1( byte u)
    {
        u1 = u;
    }

    int getin1()
    {
        return in1;
    }

    void setin1( int i)
    {
        in1 = i;
    }

    void setin1( char [] c)
    {
        byte c1=0;
        byte c2=0;

        if (c.length == 1) {
            c1=0;
        } else {
            c1 = cv2h((byte) c[0]);
        }
        c2 = cv2h((byte) c[1]);
        in1 = (16 * c1) + c2;
    }

    // hexvalue case 1:
    // ascii    case 2:
    // as int   case 3:
    void setu1( byte u, int fmt)
    {
        u1 =0;
        switch(fmt)
        {
            // hexvalue
            case 1:
                u1 = u;
                break;
            // ascii
            case 2:
                if ( (u >= 0x30) && (u <= 0x39) ) {
                    u1 = (byte) (u - 0x30);
                }
                else if ( (u >= 0x41) && (u <= 0x46) ) {
                    u1 = (byte) (0x0A + u - 0x41);
                }
                else if ( (u >= 0x61) && (u <= 0x66) ) {
                    u1 = (byte) (0x0A + u - 0x61);
                }
                else u1 =0;
                break;
            // as int
            case 3:
                if ( (u >= 0) && (u <= 15) ) {
                    u1 = (byte) u;
                }
                else u1 =0;
                break;
        }

    }

    public boolean ubit(int iPos)
    {
        boolean b = false;
        int ul1 = (int) u1;
        int ul2 = 0;
        //byte u2 = 0;
        switch (iPos) {

            case 0:  ul2 = (ul1 & 0x80);
                     if (ul2 != 0) b = true; break;
            case 1:  ul2 = (ul1 & 0x40);
                     if (ul2 != 0) b = true; break;
            case 2:  ul2 = (ul1 & 0x20);
                     if (ul2 != 0) b = true; break;
            case 3:  ul2 = (ul1 & 0x10);
                     if (ul2 != 0) b = true; break;
            case 4:  ul2 = (ul1 & 0x08);
                     if (ul2 != 0) b = true; break;
            case 5:  ul2 = (ul1 & 0x04);
                     if (ul2 != 0) b = true; break;
            case 6:  ul2 = (ul1 & 0x02);
                     if (ul2 != 0) b = true; break;
            case 7:  ul2 = (ul1 & 0x01);
                     if (ul2 != 0) b = true; break;
        }

        return b;
    }

    // Take 2 byte and compact them to one single byte. Ex:
    // b1='3', b2='A', Result=\x3A
    public byte compactByte(byte b1, byte b2)
    {
        byte result = 0x00;
        byte aux = 0x00;
        int  iaux  = b1 << 8;;
        int  iaux2 = b2;
        iaux = iaux | iaux2;
        result = (byte) iaux;
        return result;
    }
    public byte compactByte(String s)
    {
        char [] a1 = s.toCharArray();
        if ( s.length() == 0 ) return 0;
        if ( s.length() == 1 )
            return compactByte((byte) a1[0], (byte) 0x00);
        else
            return compactByte((byte) a1[0], (byte) a1[1]);
    }

    public String compactStr(String Src)
    {
        String Res = "";
        int len = Src.length() / 2;
        for (int i=0 ; i<len ; i++) {
            compactByte(Src.substring(i * 2, 2));
        }
        int msnib = in1 / 10;
        int lsnib = in1 % 10;
        return String.format("%01X", ( nibble(msnib) << 4) | nibble(lsnib));
    }

    public int nibble(int input)
    {
        int res=0;
        int mask=1;
        for (int i = 0; i < 4; i++) {
            if ( (input % 2) == 1 ) res = res | mask;
            input = input / 2;
            mask = mask * 2;
        }
        return res;
    }

    byte cv2h(byte b1)
    {
        byte result=0;
        if ( (b1 >= 0x30) && (b1 <= 0x39) ) {
            result = (byte) (b1 - 0x30);
        }
        else if ( (b1 >= 0x41) && (b1 <= 0x46) ) {
            result = (byte) (10 + b1 - 0x41);
        }
        else if ( (b1 >= 0x61) && (b1 <= 0x66) ) {
            result = (byte) (10 + b1 - 0x61);
        }
        return result;
    }

    byte cvtToX()
    {
        int bResult = 0;
        int i = 0;
        int aux = in1;
        int mask1 = 0x80;

        for(i=0 ; i < 8 ; i++ ) {
            if (aux >= mask1) {
                aux = aux - mask1;
                bResult = bResult | mask1;
            }
            mask1 = mask1 / 2;
        }
        return (byte) bResult;
    };
}

