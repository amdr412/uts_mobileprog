package com.example.astimen.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.astimen.myapplication.fragment.frg_entry;
import com.example.astimen.myapplication.fragment.frg_search;
import com.example.astimen.myapplication.fragment.frg_welcome;
import com.example.astimen.myapplication.fragment.frg_edit;
import com.example.astimen.myapplication.fragment.frg_upload_image;
import com.example.astimen.myapplication.model.FullscreenActivity;

public class MainActivity extends AppCompatActivity
        implements frg_entry.OnFragmentInteractionListener, frg_welcome.OnFragmentInteractionListener, frg_search.OnFragmentInteractionListener , frg_upload_image.OnFragmentInteractionListener , frg_edit.OnFragmentInteractionListener ,NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frm_utama, frg_welcome.newInstance("0","0"))
                .commit();
        getSupportActionBar().setTitle("Welcome AflowZ");

    }

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //return true;
            pesan_toast("Hasil Option Setting");
        }else if (id == R.id.action_aplikasi){
            pesan_toast("Hasil Option Aplikasi");
            Intent i = new Intent(this, FullscreenActivity.class);
            i.putExtra("PersonID", "123");
            startActivity(i);


        }else if (id == R.id.action_bantuan){
            pesan_toast("Hasil Option Bantuan");
        }else if (id == R.id.action_keluar){
            //pesan_toast("Hasil Option Keluar");
            keluar();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            pesan_toast("Fragment frg_welcome ditampilkan ...");

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frm_utama, frg_welcome.newInstance("0","0"))
                    .commit();
            getSupportActionBar().setTitle("Welcome AflowZ");

        } else if (id == R.id.nav_gallery) {
            pesan_toast("Fragment frg_search ditampilkan ...");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frm_utama, frg_search.newInstance("0","0"))
                    .commit();

            getSupportActionBar().setTitle("Search");

        } else if (id == R.id.nav_slideshow) {
            pesan_toast("Fragment frg_entry ditampilkan ...");
            getSupportFragmentManager()
                    .beginTransaction()
                    //.remove(getSupportFragmentManager().findFragmentById(R.id.content_main))
                    .replace(R.id.frm_utama, frg_entry.newInstance("0","0"))
                    .commit();

            getSupportActionBar().setTitle("Entry Data");
        } else if (id == R.id.nav_manage) {
            getSupportFragmentManager()
                    .beginTransaction()
                    //.remove(getSupportFragmentManager().findFragmentById(R.id.content_main))
                    .replace(R.id.frm_utama, frg_entry.newInstance("0","0"))
                    .commit();

            getSupportActionBar().setTitle("Entry Data");
            pesan_toast("Hasil akses menu manage");
        } else if (id == R.id.nav_share) {
            getSupportFragmentManager()
                    .beginTransaction()
                    //.remove(getSupportFragmentManager().findFragmentById(R.id.content_main))
                    .replace(R.id.frm_utama, frg_upload_image.newInstance("0","0"))
                    .commit();

            getSupportActionBar().setTitle("Upload Data");
            pesan_toast("Hasil akses menu share");
        } else if (id == R.id.nav_send) {
            pesan_toast("Hasil akses menu send");

        } else if (id == R.id.nav_monitor) {
            pesan_toast("Hasil akses menu monitor");
        } else if (id == R.id.nav_bookmark) {
            pesan_toast("Hasil akses menu bookmark");

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void pesan_toast ( final String pesan){

        Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_LONG).show();

    }
    public void keluar () {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;

                }
            }

        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah anda akan keluar aplikasi ?").setPositiveButton("Ya",dialogClickListener)
                .setNegativeButton("Tidak",dialogClickListener).show();
    }

}

