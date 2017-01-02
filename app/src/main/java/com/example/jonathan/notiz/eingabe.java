package com.example.jonathan.notiz;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class eingabe extends Activity {

    EditText textTitel;
    EditText textNotiz;
    ArrayList<String> titelArray = new ArrayList<>();
    ArrayList<String> notizenArray = new ArrayList<>();
    String FILENAME_titel = "Titel";
    String FILENAME_notiz = "Notiz";
    String trennung = "/.../";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eingabe);

        textTitel = (EditText) findViewById(R.id.titel);
        textNotiz = (EditText) findViewById(R.id.notiz);

        FloatingActionButton speichern = (FloatingActionButton) findViewById(R.id.save);
        speichern.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                titelArray.add(textTitel.getText().toString());
                System.out.println("titelArray =" + titelArray);
                try {
                    FileOutputStream speicher_titel = openFileOutput(FILENAME_titel, MODE_PRIVATE);
                    for (int i = 0; i < titelArray.size(); i++) {
                        String x = titelArray.get(i);
                        speicher_titel.write(x.getBytes());
                        speicher_titel.write(trennung.getBytes());
                    }
                    speicher_titel.close();

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                notizenArray.add(textNotiz.getText().toString());
                System.out.println("notizenArray =" + notizenArray);

                try {
                    FileOutputStream speicher_notiz = openFileOutput(FILENAME_notiz, MODE_PRIVATE);
                    for (int i = 0; i < notizenArray.size(); i++) {
                        String x = notizenArray.get(i);
                        speicher_notiz.write(x.getBytes());
                        speicher_notiz.write(trennung.getBytes());

                    }
                    speicher_notiz.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), "Notiz gespeichert", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(eingabe.this, startschirm.class);
                startActivity(intent);
            }
            });
    }

}
