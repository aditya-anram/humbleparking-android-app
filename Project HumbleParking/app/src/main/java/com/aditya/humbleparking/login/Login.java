package com.aditya.humbleparking.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aditya.humbleparking.AdminActivity;
import com.aditya.humbleparking.MainActivity;
import com.aditya.humbleparking.R;
import com.aditya.humbleparking.login.api.ApiRequestData;
import com.aditya.humbleparking.login.api.Retroserver;
import com.aditya.humbleparking.login.model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText user, pass;
    Button masuk;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText)findViewById(R.id.Username);
        pass = (EditText)findViewById(R.id.Psw);
        masuk = (Button)findViewById(R.id.Masuk);

        pd = new ProgressDialog(this);

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage(" loading... ");
                pd.setCancelable(false);
                pd.show();

                final String sUser = user.getText().toString();
                String sPass = pass.getText().toString();

                ApiRequestData api = Retroserver.getClient().create(ApiRequestData.class);

                Call<ResponsModel> login = api.loginAkun(sUser,sPass);
                login.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();

                        if(kode.equals("77")) {
                            Toast.makeText(Login.this, "Selamat Datang Admin", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Login.this, AdminActivity.class);
                            startActivity(i);
                            finish();
                        }else if(kode.equals("99"))
                        {
                            Toast.makeText(Login.this, "Selamat Datang "+sUser, Toast.LENGTH_LONG).show();

                            Intent i = new Intent(Login.this, MainActivity.class);
                            finish();

                            //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            startActivity(i);

                        }else
                        {
                            Toast.makeText(Login.this, "Gagal Login", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Falure : " + "Gagal Untuk Mengirim Request");
                    }
                });
            }
        });

    }
    public void SignUp(View v){
        Intent sp = new Intent(Login.this, DaftarAkun.class);
        startActivity(sp);
    }
}
