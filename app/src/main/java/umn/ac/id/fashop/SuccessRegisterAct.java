package umn.ac.id.fashop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessRegisterAct extends AppCompatActivity {
    Button btn_explore, btn_viewoder;
    Animation app_splash, btt, ttb;
    ImageView icon;
    TextView textSuccess, textViewLet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);

        icon = findViewById(R.id.emblem_app);
        textSuccess = findViewById(R.id.textSuccess);
        textViewLet = findViewById(R.id.textViewLet);
        btn_explore = findViewById(R.id.btn_explore);
        btn_viewoder = findViewById(R.id.btn_vieworder);

        //load animation
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);

        //run animation
        btn_explore.startAnimation(btt);
        btn_viewoder.startAnimation(btt);
        icon.startAnimation(app_splash);
        textSuccess.startAnimation(ttb);
        textViewLet.startAnimation(ttb);

        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoexplore = new Intent(SuccessRegisterAct.this, HomeActivity.class);
                startActivity(gotoexplore);
            }
        });

        btn_viewoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotovieworder = new Intent(SuccessRegisterAct.this, MyProfileAct.class);
                startActivity(gotovieworder);
            }
        });
    }
}