package com.example.norman.textviewmitauswahl2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class TapText extends AppCompatActivity {

    /*
    *globale Objekte, Textview, Button, Spannable, BreakIterator
    */
    TextView txtView;
    Button btnDelete;
    Spannable span;
    BreakIterator iterator;

    /* globaler String pass, erhält Wert aus main activity, ist das, was im Textview steht*/
    String pass;

    /*globale boolean variable für Button, falls true, btnDelete ist aktive usw.*/
    boolean globBool = false;

    /*
    * Tut eigentlich alles, setzt textview, spans etc... call getClickableSpan, übergibt position(start,end) und das Wort*/
    private void settxt() {
        txtView.setMovementMethod(LinkMovementMethod.getInstance());
        txtView.setText(pass, TextView.BufferType.SPANNABLE);
        span = (Spannable) txtView.getText();
        iterator = BreakIterator.getWordInstance();
        iterator.setText(pass);


        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {

            String possibleWord = pass.substring(start, end);
            if (Character.isLetterOrDigit(possibleWord.charAt(0))) {


                ClickableSpan clickSpan = getClickableSpan(possibleWord, start, end);

                span.setSpan(clickSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    /*funktion zum löschen des wortes. wenn button aktiv, dann wird wort durch "____" ersetzt*/
    private void delete(int start, int end) {
        char[] myNameChars = pass.toCharArray();
        for(int i=start; i<=end-1; i++) {
            myNameChars[i] = '_';
        }
        pass = String.valueOf(myNameChars);
    }

    private ClickableSpan getClickableSpan(final String word,final int start,final int end) {
        return new ClickableSpan() {

            final String mWord;
            {
                mWord = word;
            }
            @Override
            public void onClick(View widget) {
                if(globBool){
                    delete(start, end);
                    settxt();
                }else{
                    Toast.makeText(widget.getContext(), mWord, Toast.LENGTH_SHORT)
                            .show();
                }
            }
            public void updateDrawState(TextPaint ds) {
                /*Setzt Span auf farbe der app, damit man ihn nicht sieht. Keine ahnung, wie man das sonst sinvoller tun kann*/
                super.updateDrawState(ds);
                ds.setColor(0xff000000);
                ds.bgColor = 0xffffffff;
                ds.setUnderlineText(false);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_text);

        /*bekommt pass aus intent von Mainactivity*/
        Intent intentpass =  getIntent();
        pass = intentpass.getStringExtra("pass");

        /*txtview und button*/
        txtView = (TextView)findViewById(R.id.txtView);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        /*OnClickListener für Button*/
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(globBool){
                    btnDelete.setBackgroundResource(android.R.drawable.btn_default);
                    btnDelete.setTextColor(0xFF000000);
                    globBool=false;
                }else {
                    btnDelete.setBackgroundColor(0xFFFF0000);
                    btnDelete.setTextColor(0xFFFFFFFF);
                    globBool=true;
                }
            }
        });
        /*settext funktion*/
        settxt();
    }
}
