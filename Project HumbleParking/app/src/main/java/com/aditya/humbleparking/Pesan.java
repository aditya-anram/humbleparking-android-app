package com.aditya.humbleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Pesan extends AppCompatActivity {


    Button btnkirim;
    EditText isipesan;
            //, nomor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        Button btnkirim = (Button) findViewById(R.id.btnkirim);

        final EditText isipesan = (EditText) findViewById(R.id.isipesan);
//        final EditText nomor = (EditText) findViewById(R.id.nomor);

        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = isipesan.getText().toString();
//                String phoneNo = nomor.getText().toString();
                if (!TextUtils.isEmpty(message)) {
//                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo));
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:082279421996"));
                    smsIntent.putExtra("sms_body", message);
                    startActivity(smsIntent);
                }

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.home:
                home();
                return true;

            case R.id.tentang:
                tentang();
                return true;

            case R.id.versi:
                versi();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public  void home(){
        Intent intent = new Intent(Pesan.this, MainActivity.class);
        startActivity(intent);
    }

    public  void tentang(){
        Intent intent = new Intent(Pesan.this, Tentang.class);
        startActivity(intent);
    }

    public  void versi(){
        Intent intent = new Intent(Pesan.this, Versi.class);
        startActivity(intent);
    }

    public  void kebijakan(){
        Intent intent = new Intent(Pesan.this, KebijakanPrivasi.class);
        startActivity(intent);
    }
}