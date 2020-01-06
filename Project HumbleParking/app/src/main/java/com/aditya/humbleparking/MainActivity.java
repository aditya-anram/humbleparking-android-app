package com.aditya.humbleparking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aditya.humbleparking.database.Booking;
import com.aditya.humbleparking.database.TampilDetail;
import com.aditya.humbleparking.database.TampilRiwayat;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    Button btncall, btnpesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_booking)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

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
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public  void tentang(){
        Intent intent = new Intent(MainActivity.this, Tentang.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public  void versi(){
        Intent intent = new Intent(MainActivity.this, Versi.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public  void kebijakan(){
        Intent intent = new Intent(MainActivity.this, Versi.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void keHelp(View view) {
        Intent intent = new Intent(MainActivity.this, Help.class);
        startActivity(intent);
    }

    public void keHubungi(View view) {
        Intent intent = new Intent(MainActivity.this, Hubungi.class);
        startActivity(intent);
    }


    public void keKebijakan(View view) {
        Intent intent = new Intent(MainActivity.this, KebijakanPrivasi.class);
        startActivity(intent);
    }



    public void keBooking(View view) {

        Intent intent = new Intent(MainActivity.this, Booking.class);
        startActivity(intent);

    }

    public void keRiwayat(View view) {
        Intent intent = new Intent(MainActivity.this, TampilRiwayat.class);
        startActivity(intent);
    }

    public void keLokasi(View view) {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void kePesan(View view) {
        Intent intent = new Intent(MainActivity.this, Pesan.class);
        startActivity(intent);
    }


    public void kePanggil(View view) {

        btncall = (Button)findViewById(R.id.btncall);
        btncall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent panggil = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:082279421996") );
                startActivity(panggil);
            }

        });

    }

    public void keDetail(View view) {
        Intent intent = new Intent(MainActivity.this, TampilDetail.class);
        startActivity(intent);
    }

}
