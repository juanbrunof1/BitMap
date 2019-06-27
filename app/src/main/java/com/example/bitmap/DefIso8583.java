package com.example.bitmap;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import static android.content.ContentValues.TAG;

public class DefIso8583 {
    private ArrayList<elementIso8583> elemnts = new ArrayList<elementIso8583>();

    public DefIso8583()
    {
        elementIso8583 lm = new elementIso8583();

        Log.d(TAG, "DefIso8583: ");

        addToList(1, "Bit Map", "B_N", 64);
        addToList(2, "Primary Acct. Num.", "N_2N", 19);
        addToList(3, "Processing Code", "N_N", 6);
        addToList(4, "Amount, Trans.", "N_N", 12);
        addToList(11, "Systems Trace No", "N_N", 6);
        addToList(12, "Time, Local Trans.", "N_N", 6);
        addToList(13, "Date, Local Trans.", "N_N", 4);
        addToList(14, "Date, Expiration", "N_N", 4);
        addToList(22, "POS Entry Mode", "N_N", 3);
        addToList(24, "NII", "N_N", 3);
        addToList(25, "POS Condition Code", "N_N", 2);
        addToList(35, "Track 2 Data", "Z_2N", 37);
        addToList(37, "Retrieval Ref. No.", "AN_N", 12);
        addToList(38, "Auth. Id. Response", "AN_N", 6);
        addToList(39, "Response Code", "AN_N", 2);
        addToList(41, "Terminal Id", "ANS_N", 8);
        addToList(42, "Card Acq. Id", "ANS_N", 15);
        addToList(43, "Card Acq. Name", "ANS_N", 40);
        addToList(45, "Track 1 Data", "ANS_2N", 76);
        addToList(48, "Add. Data - Private", "ANS_3N", 999);
        addToList(52, "PIN Data", "B_N", 64);
        addToList(53, "Security Control Info", "N_N", 16);
        addToList(54, "Additional Amounts", "AN_3N", 120);
        addToList(55, "ICC Sys Related Data", "B_3N", 255);
        addToList(60, "Private Use", "ANS_3N", 999);
        addToList(61, "Private Use", "ANS_3N", 999);
        addToList(62, "Private Use", "ANS_3N", 999);
        addToList(63, "Private Use", "ANS_3N", 999);
        addToList(64, "Message Auth. Code", "B_N", 64);
    }

    private void addToList(int B, String S1, String S2, int i3)
    {
        elementIso8583 lm = new elementIso8583();
        lm.addElement(B, S1, S2, i3);
        elemnts.add(lm);
    }

    public elementIso8583 getElem(int i)
    {
        Iterator itr = elemnts.iterator();
        while(itr.hasNext()) {
            elementIso8583 e = (elementIso8583) itr.next();
            if (i == e.getBit()) return e;
        }
        return null;
    }

    public int length()
    {
        return elemnts.size();
    }
}
