package com.juliao.adryel.salvarimage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveIntenalActivity extends AppCompatActivity {
    Button botTakePhotoInternal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_intenal);

        botTakePhotoInternal = (Button) findViewById(R.id.botTakePhotoInternal);
        botTakePhotoInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                Log.i("ABRIR", "ABRIU A INTENT DA CAMERA");
                startActivityForResult(i, 24);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 24){
            if(resultCode == RESULT_OK){

                if(data!=null){
                    Log.i("RESULTADO", "ENTROU AQUI KK");



                }
            }
        }

    }
}
