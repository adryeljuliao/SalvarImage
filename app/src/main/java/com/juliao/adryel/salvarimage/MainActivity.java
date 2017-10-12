package com.juliao.adryel.salvarimage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button botSaveInterno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botSaveInterno = (Button) findViewById(R.id.idBotSaveInternoMain);

        botSaveInterno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SaveIntenalActivity.class);
                startActivity(i);
            }
        });




    }



}
