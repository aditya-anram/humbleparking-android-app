package com.aditya.humbleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

public class KebijakanPrivasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kebijakan_privasi);
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

            case R.id.kebijakan:
                kebijakan();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public  void home(){
        Intent intent = new Intent(KebijakanPrivasi.this, MainActivity.class);
        startActivity(intent);
    }

    public  void tentang(){
        Intent intent = new Intent(KebijakanPrivasi.this, Tentang.class);
        startActivity(intent);
    }

    public  void versi(){
        Intent intent = new Intent(KebijakanPrivasi.this, Versi.class);
        startActivity(intent);
    }

    public  void kebijakan(){
        Intent intent = new Intent(KebijakanPrivasi.this, KebijakanPrivasi.class);
        startActivity(intent);
    }

}
