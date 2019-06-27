package com.example.bitmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private Button goButton;
    private EditText editBitmap;
    private TextView result;

    private final String rawOutputMsg = "60000000090110203801000E80001023000000012414480603220003393038313134343830363632313333333536303031303039313635380012303130332A42493032303247";
    private  ArrayList<DataConvert> dcl = new ArrayList<DataConvert>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.btnGo);
        editBitmap = (EditText) findViewById(R.id.editTextBitmap);
        final TextView result = (TextView) findViewById(R.id.txtViewResult);

        DefIso8583 ISO8583 = new DefIso8583();
        String s = "";

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // first example :
                // provided a bitmap, show fields
                /*
                result.setText("");
                String myString = getMsgElements(editBitmap.getText().toString());
                result.setText((myString));
                */

                // second example :
                result.setText("");
                String myString = generateISOMsg();
                result.setText((myString));
            }
        });
    }

    static String getMsgElements(String sbmp)
    {
        int i=0;
        int j=0;
        String S1="";
        uubyte ub1 = new uubyte();

        DefIso8583 ISO8583 = new DefIso8583();

        if (sbmp.length() != (8*2)) return S1;
        byte [] bmpArr = new byte[sbmp.length()];
        for (i=0 ; i < sbmp.length() / 2 ; i++) {
            String saux = sbmp.substring(i * 2, i * 2 + 2);
            char [] x = saux.toCharArray();
            ub1.setin1(x);
            //bmpArr[i] = (byte) ub1.cvtToX();
            bmpArr[i] = (byte) ub1.getin1();
        }
        for (i=0 ; i < 8 ; i++)
        {
            elementIso8583 eIso = new elementIso8583();
            //uubyte b = new uubyte();
            //b.setu1((byte) bmpArr[i]);
            ub1.setu1((byte) bmpArr[i]);
            for (j=0 ; j < 8 ; j++)
            {
                eIso = ISO8583.getElem((i * 8) + (j + 1));
                if (ub1.ubit(j) && eIso != null) S1 = S1 + eIso.getBit() + ": " + eIso.getData_Element_Name() + "\n";
            }
        }
        return S1;
    }

//    private void addToList2(int B, String S1, int i3, String S2)
    private void addToList2(int B, DataTypeIso DT, String S2)
    {
        //public void DataConvert(int b, DataTypeIso dti, int dl, String d)
        //elementIso8583 lm = new elementIso8583();
        //lm.addElement(B, S1, S2, i3);
        //elemnts.add(lm);
        DataConvert dc = new DataConvert();
        dc.DataConvert(B, DT.getTipo(), DT.getVar_fix(), DT.getNlen(), S2);
        dcl.add(dc);
    }

    public String generateISOMsg()
    {
        String ret = "";

        // Create data
        //-DataConvert dc = new DataConvert();
        //-ArrayList<DataConvert> dcl = new ArrayList<DataConvert>();

        // build Arraylist with fields
        // --------------------------------------------------------------------
        //dc.DataConvert(1,DataTypeIso.B_N, 8, "203801000E800010");
        //dcl.add(dc);
        //...
        //addToList2(1,DataTypeIso.B_N.getTipo(),DataTypeIso.B_N.getNlen(),"203801000E800010");
        addToList2(1, DataTypeIso.B_N,"203801000E800010");
        addToList2(3, DataTypeIso.N_N,"230000");
        addToList2(11, DataTypeIso.N_N,"000124");
        addToList2(12, DataTypeIso.N_N,"144806");
        addToList2(13, DataTypeIso.N_N,"0322");
        addToList2(24, DataTypeIso.N_N,"0003");
        addToList2(37, DataTypeIso.AN_N,"393038313134343830363632");
        addToList2(38, DataTypeIso.AN_N,"313333333536");
        addToList2(39, DataTypeIso.AN_N,"3030");
        addToList2(41, DataTypeIso.ANS_N,"3130303931363538");
        addToList2(60, DataTypeIso.ANS_3N,"303130332A42493032303247");


        // build ISO
        // --------------------------------------------------------------------
        /*
        for (int i=0;i<dcl.size();i++) {
            ret = dcl
        }
        */
        Iterator itr = dcl.iterator();
        while(itr.hasNext()) {
            DataConvert e = (DataConvert) itr.next();
            ret = ret + e.moveData();
        }

        return ret;
    }

}
