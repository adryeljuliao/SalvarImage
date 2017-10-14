package com.juliao.adryel.salvarimage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button botSaveInterno;
    Button botSaveBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botSaveInterno = (Button) findViewById(R.id.idBotSaveInternoMain);
        botSaveBD = (Button) findViewById(R.id.idBotSaveBDMain);

        botSaveInterno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DATABASEE", "ENTROU NO DATA");
                Intent i = new Intent(MainActivity.this, SaveIntenalActivity.class);
                startActivity(i);

            }
        });

        botSaveBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("CLICK", "METODO CLICK DEU CERTO");
                Intent intent = new Intent(MainActivity.this, SaveDataBase.class);
                startActivity(intent);
            }
        });




    }



}
