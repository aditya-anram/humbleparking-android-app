package com.aditya.humbleparking.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aditya.humbleparking.Help;
import com.aditya.humbleparking.KebijakanPrivasi;
import com.aditya.humbleparking.R;
import com.aditya.humbleparking.login.api.ApiRequestData;
import com.aditya.humbleparking.login.api.Retroserver;
import com.aditya.humbleparking.login.model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarAkun extends AppCompatActivity {

    EditText user, pass, repass;
    Button daftar;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        user = (EditText)findViewById(R.id.buatUsername);
        pass = (EditText)findViewById(R.id.buatPsw);
        repass = (EditText)findViewById(R.id.buatRepassword);
        daftar = (Button)findViewById(R.id.signup);

        pd = new ProgressDialog(this);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(user.getText().toString()) || TextUtils.isEmpty(pass.getText().toString()) ||
                        TextUtils.isEmpty(repass.getText().toString())) {
                    Toast.makeText(DaftarAkun.this, "Mohon isi semua field", Toast.LENGTH_SHORT).show();
                } else{
                    pd.setMessage("Loading... ");
                pd.setCancelable(false);
                pd.show();

                String sUser = user.getText().toString();
                String sPass = pass.getText().toString();
                String sRepass = repass.getText().toString();

                ApiRequestData api = Retroserver.getClient().create(ApiRequestData.class);

                Call<ResponsModel> signup = api.signupAkun(sUser, sPass, sRepass);
                signup.enqueue(new Callback<ResponsModel>() {

                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();

                        if (kode.equals("1")) {
                            Toast.makeText(DaftarAkun.this, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(DaftarAkun.this, Login.class);
                            startActivity(i);
                        } else if (kode.equals("3")) {
                            Toast.makeText(DaftarAkun.this, "Username Sudah Ada", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DaftarAkun.this, "Data tidak berhasil disimpan", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                    }
                });
            }
            }
        });

    }

    public void keLogin(View view) {
        Intent intent = new Intent(DaftarAkun.this,Login.class);
        startActivity(intent);
    }
}
