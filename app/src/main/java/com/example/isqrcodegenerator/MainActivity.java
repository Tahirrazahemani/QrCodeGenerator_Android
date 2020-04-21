package com.example.isqrcodegenerator;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidx.appcompat.app.AppCompatActivity;

import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
String TAG = "GenerateQrCode" , inputvalue;
EditText edttext;
ImageView qrImage;
Button generateQr;
Bitmap bitmap;
QRGEncoder qrgEncoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qrImage =(ImageView) findViewById(R.id.qrcode);
        edttext = (EditText) findViewById(R.id.editText);
        generateQr = (Button) findViewById(R.id.createQrButton);
        generateQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputvalue = edttext.getText().toString().trim();
                if(inputvalue.length()>0){
                    WindowManager manager= (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerdimension = width<height ? width:height;
                    smallerdimension = smallerdimension*3/4;
                    qrgEncoder = new QRGEncoder(inputvalue ,null,QRContents.Type.TEXT,smallerdimension);
                    try {
                        bitmap=qrgEncoder.encodeAsBitmap();
                        qrImage.setImageBitmap(bitmap);
                    }
                    catch (WriterException e){
                        Log.v(TAG , e.toString());
                    }
|
                }
                else{
                    edttext.setError("Required");
                }


            }
        });


    }
}
