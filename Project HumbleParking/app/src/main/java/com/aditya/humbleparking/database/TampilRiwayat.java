package com.aditya.humbleparking.database;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.aditya.humbleparking.KebijakanPrivasi;
import com.aditya.humbleparking.MainActivity;
import com.aditya.humbleparking.R;
import com.aditya.humbleparking.Tentang;
import com.aditya.humbleparking.Versi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class TampilRiwayat extends AppCompatActivity implements ListView.OnItemClickListener {


    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_riwayat);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }


    private void showParkir(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String nama = jo.getString(Config.TAG_NAMA);
                String plat = jo.getString(Config.TAG_NOPLAT);

                HashMap<String,String> parkir = new HashMap<>();
                parkir.put(Config.TAG_ID,id);
                parkir.put(Config.TAG_NAMA,nama);
                parkir.put(Config.TAG_NOPLAT,plat);
                list.add(parkir);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TampilRiwayat.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID,Config.TAG_NAMA, Config.TAG_NOPLAT},
                new int[]{R.id.id, R.id.nama, R.id.noPlat});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilRiwayat.this,"Mengambil Data","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showParkir();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TampilDetail.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String idparkir = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.PARKIR_ID,idparkir);
        startActivity(intent);
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
        Intent intent = new Intent(TampilRiwayat.this, MainActivity.class);
        startActivity(intent);
    }

    public  void tentang(){
        Intent intent = new Intent(TampilRiwayat.this, Tentang.class);
        startActivity(intent);
    }

    public  void versi(){
        Intent intent = new Intent(TampilRiwayat.this, Versi.class);
        startActivity(intent);
    }

    public  void kebijakan(){
        Intent intent = new Intent(TampilRiwayat.this, KebijakanPrivasi.class);
        startActivity(intent);
    }
}

