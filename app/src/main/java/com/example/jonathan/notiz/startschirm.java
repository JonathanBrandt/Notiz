package com.example.jonathan.notiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class startschirm extends AppCompatActivity {

    public static int titelPlatz;
    String FILENAME_titel = "Titel";
    String titel_ganz;
    static ArrayList<String> titelArray= new ArrayList<String>();


   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_startschirm);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);   //Notiz hinzufügen Knopf
       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(startschirm.this, eingabe.class);
               startActivity(intent);                                              //wenn gedrückt öfnet sich neue Notiz
           }
       });

       einlesen();
       titelInArrayListSpeichern();

       ListView ll = (ListView) findViewById(R.id.listView1);

       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titelArray);
       ll.setAdapter(adapter);

       ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> arg0, View arg1, int arg3, long id) {
              /* String txt = arg1.toString();
               for(int i = 0; i < titelArray.size(); i++){
                   if(txt.equals(titelArray.get(i))){
                       titelPlatz = i;
                   }
               }*/
               titelPlatz = arg3;
               Intent lesen = new Intent(startschirm.this, lesen.class);
               startActivity(lesen);
           }
       });
   }





    public void einlesen() {            //Alle Notizentitel werden in einem String in Variable "titel_ganz" gespeichert
        try {
            String notiz = new String();
            FileInputStream fis = openFileInput(FILENAME_titel);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuffer stringBuffer = new StringBuffer();
            while ((notiz = bufferedReader.readLine()) != null) {
                stringBuffer.append(notiz);
            }
            titel_ganz = stringBuffer.toString();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void titelInArrayListSpeichern(){        //Der String mit allen Titeln wird in die einzelnen Titel geteilt in in die ArrayList "titelArray" gespeichert
        if(titel_ganz != null) {
            String[] titel = titel_ganz.split("/.../");
            for (int i = 0; i < titel.length; i++) {
                titelArray.add(titel[i]);
            }
        }
    }



        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_startschirm, menu);
            return true;
        }


        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
ydgyzyazysz