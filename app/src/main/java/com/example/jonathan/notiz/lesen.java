package com.example.jonathan.notiz;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class lesen extends AppCompatActivity {

    TextView titel;
    TextView notiz;
    String FILENAME_notiz = "Notiz";
    String notiz_ganz;
    ArrayList<String> notizenArray= new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesen);

        titel = (TextView) findViewById(R.id.ViewTitel);
        notiz = (TextView) findViewById(R.id.ViewNotiz);

        //startschirm.titelPlatz++;
        titel.setText(startschirm.titelArray.get(startschirm.titelPlatz));

        FileInputStream fis = null;
        try {
            fis = openFileInput(FILENAME_notiz);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuffer stringBuffer_notizen = new StringBuffer();
            String x = new String();
            while ((x = bufferedReader.readLine()) != null) {
                stringBuffer_notizen.append(x);
            }
            notiz_ganz = stringBuffer_notizen.toString();

            }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if(notiz_ganz != null) {
            String[] titel = notiz_ganz.split("/.../");
            for (int i = 0; i < titel.length; i++) {
                notizenArray.add(titel[i]);
            }
        }
        notiz.setText(notizenArray.get(startschirm.titelPlatz));

    }
}
