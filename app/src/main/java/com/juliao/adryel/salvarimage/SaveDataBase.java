package com.juliao.adryel.salvarimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.orm.SugarContext;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SaveDataBase extends AppCompatActivity {
    Button botTakePhotoDB;
    ImageView imageViewCam;
    Button botReadPhotoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data_base);
    Log.i("DATABASE", "ENTROU NO DATA");
        SugarContext.init(this);
//
        botTakePhotoDB = (Button) findViewById(R.id.botTakePhotoDB);
        botReadPhotoDB = (Button) findViewById(R.id.botReadPhotoDB);
        imageViewCam = (ImageView) findViewById(R.id.imageViewDB);
//
        botReadPhotoDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageRecord image = ImageRecord.last(ImageRecord.class);
                //fanbrica de bitmap para decodificar o array de bytes
                Bitmap imagem = BitmapFactory.decodeByteArray(image.getImage(),0, image.getImage().length);
                imageViewCam.setImageBitmap(imagem);
            }
        });

        botTakePhotoDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //abrir a intent da camera
                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                Log.i("ABRIR", "ABRIU A INTENT DA CAMERA");
                startActivityForResult(i, 24);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 24) {
            if (resultCode == RESULT_OK) {

                if (data != null) {
                    //recupera os dados (imagem)
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    //arquivo q escreve um array de bytes pasa salvar no banco
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, arrayOutputStream);

                    byte[] image = arrayOutputStream.toByteArray();

                    ImageRecord imageBanco = new ImageRecord(image);

                    imageBanco.save();

                    Toast.makeText(this, "Imagem salva com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //fecha a conexão
        SugarContext.terminate();
    }
}
