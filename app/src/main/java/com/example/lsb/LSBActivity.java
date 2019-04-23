package com.example.lsb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lsb.stego.Stegonographia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

public class LSBActivity extends AppCompatActivity {
    private Button buttonChooseImage;
    private EditText editTextCrypto;
    private ImageView imageView;
    private Bitmap copyImage;
    private static int indexImage = 0;
    private Uri oUri;
    File directory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsb);
        buttonChooseImage = findViewById(R.id.buttonChooseImage);
        imageView = findViewById(R.id.imageViewImage);
        editTextCrypto = findViewById(R.id.editTextCryptoText);
    }

    public void chooseImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (resultCode == RESULT_OK) {
            try {

                //Получаем URI изображения, преобразуем его в Bitmap
                //объект и отображаем в элементе ImageView нашего интерфейса:
                final Uri imageUri = imageReturnedIntent.getData();
                oUri = imageUri;
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                copyImage = selectedImage;
                imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendImage(View view) {

    }


    public void saveImage(View view) throws IOException {
        try {
            File dest = new File(getGalleryPath() + "TikTakToe");
            dest.mkdirs();
            dest = new File(getGalleryPath() + "TikTakToe/" + indexImage + System.currentTimeMillis() / 1000 + ".jpg");
            FileOutputStream out = new FileOutputStream(dest);
            copyImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(this, "Сохранил " + dest.getPath().toString(),
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка при сохранении. Повторите попытку.",
                    Toast.LENGTH_SHORT).show();
            Log.d("MyIlnarLog2", e.toString());
        }


    }


    private static String getGalleryPath() {
        return Environment.getExternalStorageDirectory() + "/";
    }

}
