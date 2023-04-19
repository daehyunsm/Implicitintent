package com.example.implicitintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] PERMISSIONS = {Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA};

        if((ContextCompat.checkSelfPermission(getApplicationContext(), PERMISSIONS[0])
                != PackageManager.PERMISSION_GRANTED)
            || (ContextCompat.checkSelfPermission(getApplicationContext(), PERMISSIONS[1])
                != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }

    }

    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnCall:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0436491269"));
                break;
            case R.id.btnDial:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0436491269"));
                break;
            case R.id.btnSms:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:01054144014"));
                intent.putExtra("sms_body", "스마트모바일프로그래밍 리포트 다 하셨나요?");
                break;
            case R.id.btnMap:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.30, 127.2?z=10"));
                break;
            case R.id.btnWeb:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.semyung.ac.kr"));
                break;
            case R.id.btnContact:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("contant://contacts/people/"));
                break;
            case R.id.btnCamera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                break;
        }

        if(intent != null) {
            if(v.getId() == R.id.btnCamera)
                startActivityForResult(intent, 1);

            else
                startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode == 1) && (resultCode == RESULT_OK)) {
            Bitmap bitmap = data.getParcelableExtra("data");
            ImageView iVCamera = findViewById(R.id.iVCamera);
            iVCamera.setImageBitmap(bitmap);
        }

    }
}