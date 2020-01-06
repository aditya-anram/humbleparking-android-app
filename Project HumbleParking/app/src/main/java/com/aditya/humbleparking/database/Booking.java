package com.aditya.humbleparking.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aditya.humbleparking.KebijakanPrivasi;
import com.aditya.humbleparking.MainActivity;
import com.aditya.humbleparking.R;
import com.aditya.humbleparking.Tentang;
import com.aditya.humbleparking.Versi;

import java.util.HashMap;

//public class Booking extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_booking);
//    }
//

public class Booking extends AppCompatActivity implements View.OnClickListener {
    EditText etNoPlat, etNama, etLama;
    Button btnSimpan, btnLihat, btnBatal;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        etNoPlat = (EditText)findViewById(R.id.et_noPlat);
        etNama = (EditText)findViewById(R.id.et_id);
        etLama = (EditText)findViewById(R.id.et_lihat);

        btnSimpan = (Button)findViewById(R.id.btn_simpan);
        //  btnLihat = (Button)findViewById(R.id.btn_riwayat);
//            btnBatal = (Button)findViewById(R.id.btn_batal);

        btnSimpan.setOnClickListener(this);
        // btnLihat.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private void addParkir(){
        final String noPlat = etNoPlat.getText().toString().trim();
        final String nama = etNama.getText().toString().trim();
        final String lama = etLama.getText().toString().trim();

        class AddParkir extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Booking.this,"Menambahkan data...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Booking.this,s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_PARKIR_NOPLAT,noPlat);
                params.put(Config.KEY_PARKIR_NAMA,nama);
                params.put(Config.KEY_PARKIR_LAMA,lama);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddParkir ae = new AddParkir();
        ae.execute();

    }

    @Override
    public void onClick(View v) {
        if(v == btnSimpan){
            addParkir();
        }
        if(v == btnLihat){
            startActivity(new Intent(this, TampilRiwayat.class));
        }

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
        Intent intent = new Intent(Booking.this, MainActivity.class);
        startActivity(intent);
    }

    public  void tentang(){
        Intent intent = new Intent(Booking.this, Tentang.class);
        startActivity(intent);
    }

    public  void versi(){
        Intent intent = new Intent(Booking.this, Versi.class);
        startActivity(intent);
    }

    public  void kebijakan(){
        Intent intent = new Intent(Booking.this, KebijakanPrivasi.class);
        startActivity(intent);
    }



}










