package com.aditya.humbleparking.database;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aditya.humbleparking.AdminActivity;
import com.aditya.humbleparking.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class TampilDetail extends AppCompatActivity implements View.OnClickListener{

    private EditText etId, etNoPlat, etNama, etLama;
    private Button btnUpdate;
    private Button btnHapus;
    private Button nextScan;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_detail);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.PARKIR_ID);

        etId = (EditText) findViewById(R.id.et_id);
        etNoPlat = (EditText) findViewById(R.id.et_noPlat);
        etNama = (EditText) findViewById(R.id.et_nama);
        etLama = (EditText) findViewById(R.id.et_lihat);

        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnHapus = (Button) findViewById(R.id.btn_hapus);

        btnUpdate.setOnClickListener(this);
        btnHapus.setOnClickListener(this);


        etId.setText(id);

        getParkir();
    }

    private void getParkir(){

        class GetParkir extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilDetail.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showParkir(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_PARKIR,id);
                return s;
            }
        }
        GetParkir ge = new GetParkir();
        ge.execute();
    }

    private void showParkir(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String noPlat = c.getString(Config.TAG_NOPLAT);
            String nama = c.getString(Config.TAG_NAMA);
            String lama = c.getString(Config.TAG_LAMA);

            etNoPlat.setText(noPlat);
            etNama.setText(nama);
            etLama.setText(lama);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateData(){

        final String noPlat = etNoPlat.getText().toString().trim();
        final String nama = etNama.getText().toString().trim();
        final String lama = etLama.getText().toString().trim();

        class UpdateParkir extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilDetail.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilDetail.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_PARKIR_ID,id);
                hashMap.put(Config.KEY_PARKIR_NOPLAT,noPlat);
                hashMap.put(Config.KEY_PARKIR_NAMA,nama);
                hashMap.put(Config.KEY_PARKIR_LAMA,lama);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_PARKIR,hashMap);

                return s;
            }
        }

        UpdateParkir ue = new UpdateParkir();
        ue.execute();
    }

    private void deleteParkir(){

        class DeleteParkir extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilDetail.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilDetail.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_PARKIR, id);
                return s;
            }
        }

        DeleteParkir de = new DeleteParkir();
        de.execute();
    }

    private void konfirmasiHapus(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah anda yakin akan menghapus data tersebut ?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteParkir();
                        startActivity(new Intent(TampilDetail.this, TampilRiwayat.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnUpdate){
            updateData();
        }

        if(v == btnHapus){
            konfirmasiHapus();
        }
    }
}
