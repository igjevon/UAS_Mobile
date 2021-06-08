package umn.ac.id.fashop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class Splash extends AppCompatActivity {
    Animation app_splash;
    ImageView app_logo;
    View bg1,bg2;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //load animation
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);

        //load element
        app_logo = findViewById(R.id.app_logo);
        bg1 = findViewById(R.id.bg1);
        bg2 = findViewById(R.id.bg2);

        //run animation
        app_logo.startAnimation(app_splash);

        getUsernameLocal();
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");

        if(username_key_new.isEmpty()){
            //menjalankan timer untuk 2 detik //usernamenya kosong
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent gogetstarted = new Intent(Splash.this,GetStartedAct.class);
                    startActivity(gogetstarted);
                    finish();
                }
            },2000); //2000 ms = 2s
        }
        else {
            //kalau tidak kosong
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent gogethome = new Intent(Splash.this,HomeActivity.class);
                    startActivity(gogethome);
                    finish();
                }
            },2000); //2000 ms = 2s
        }
    }
}