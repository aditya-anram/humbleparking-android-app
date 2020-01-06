package com.aditya.humbleparking.database;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aditya.humbleparking.R;

import java.util.HashMap;


public class InputBooking extends AppCompatActivity implements View.OnClickListener {
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
      //  btnLihat = (Button)findViewById(R.id.btn_lihat);
     //   btnBatal = (Button)findViewById(R.id.btn_riwayat);

        btnSimpan.setOnClickListener(this);
        btnLihat.setOnClickListener(this);


    }

    private void addParkir(){
        final String noPlat = etNoPlat.getText().toString().trim();
        final String nama = etNama.getText().toString().trim();
        final String lama = etLama.getText().toString().trim();

        class AddParkir extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputBooking.this,"Menambahkan data...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputBooking.this,s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
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
}