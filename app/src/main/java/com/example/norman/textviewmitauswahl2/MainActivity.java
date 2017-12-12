package com.example.norman.textviewmitauswahl2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.norman.textviewmitauswahl2.R;
import com.example.norman.textviewmitauswahl2.TapText;

/**
 * Das ist eine App, die kurz zeigen soll, wie man einzelene Wörter aus einem Textview löschen könnte.
 * Es ist sehr improvisiert um müsste überarbeitet werden, wenn man es wirklich verwenden will.
 * Aber es zeigt ungefähr, wie es funktionieren könnte...
 * Im EditText ist ein Text(Anfang von Franz Kafka, die Verwandlung) als Beispiel.
 * Man kann den Text beliebig austauschen
 * In der 2. Activity taptext kann man nun durch aktivieren des delete buttons und antippen
 * einzelne Wörter löschen. Ist der Button nicht aktiv, gibt die app einen Toast mit dem Wort, das berührt wurde.
 *
 * Hauptsächlich relevant sind dabei Spannable und Breakiterator. Die App soll kurz darstellen, wie
 * man diese eventuell verwenden könnte.
 */



public class MainActivity extends AppCompatActivity {
    Button btnGo;
    EditText etxt;

    /*hat ein Edit Text, und einen button. Button wechselt aktivity und übergibt den Text aus dem EditText als String*/
    public void changeActivity(String pass){
        Intent intent = new Intent(this, TapText.class);
        intent.putExtra("pass", pass);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxt = (EditText) findViewById(R.id.editText);
        btnGo = (Button) findViewById(R.id.btnGo);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass;
                pass = etxt.getText().toString();
                changeActivity(pass);
            }
        });
    }
}
