package com.example.astimen.myapplication.model;
/**
 * Created by fujitsu1 on 9/23/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.astimen.myapplication.MainActivity;
import com.example.astimen.myapplication.R;

public class SplashScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splashscreen);
        /*menjalankan splash screen dan menu menggunakan delayed thread*/
        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                Intent mainMenu= new Intent(SplashScreen.this,MainActivity.class);
                startActivity(mainMenu);
                finish();
                overridePendingTransition(R.layout.fadein,R.layout.fadeout);
            }
        }, 3000);


    }

}
