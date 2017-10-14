package com.juliao.adryel.salvarimage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveIntenalActivity extends AppCompatActivity {
    Button botTakePhotoInternal;
    ImageView imageViewCam;
    private String FILENAME = "image.jpg";
    Button botReadPhotoInternal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_intenal);

        botTakePhotoInternal = (Button) findViewById(R.id.botTakePhotoInternal);
        botReadPhotoInternal = (Button) findViewById(R.id.botReadPhotoInternal);
        imageViewCam = (ImageView) findViewById(R.id.imageViewInterno);

        botReadPhotoInternal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FileInputStream fin ;
                try {
                    //abre o arquivo chamado FILENAME para LEITURA
                    fin = openFileInput(FILENAME);

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                    Bitmap imagem = BitmapFactory.decodeStream(fin,null, options);

                    imageViewCam.setImageBitmap(imagem);

                    fin.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
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
                Log.i("RESULTADO", "ENTROU AQUI KK");
                if(data!=null){

                    //Cria um bundle e recupera o valor dos dados, vulgo a imagem tirada
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    Log.i("DATA", imageBitmap.toString());
                    //FileOutputStream serve para escrever um arquivo, que no caso seria uma imagem
                    FileOutputStream outputStream = null;

                    try {
                        //se o arquivo ja existe ele irá sobrescrever-lo
                        outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //bitmap seja reduzido em formato JPEG
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 85, outputStream);

                    try {
                        //metodo que garante o envio do último lote de bytes enviados para gravação
                       outputStream.flush();
                        //metodo que fecha a stram de leitura ou gravação
                        outputStream.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(this, "Imagem salva com sucesso!", Toast.LENGTH_SHORT).show();


                }
            }
        }

    }
}
