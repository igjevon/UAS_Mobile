package umn.ac.id.fashop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class GetStartedAct extends AppCompatActivity {
    Button btn_sign_in;
    Button btn_new_account_create;
    Animation ttb, btt;
    ImageView emblem_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        //load animation
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);


        emblem_app = findViewById(R.id.emblem_app);
        btn_sign_in=findViewById(R.id.btn_sign_in);
        btn_new_account_create=findViewById(R.id.btn_new_account_create);

        //run animation
        emblem_app.startAnimation(ttb);
        btn_sign_in.startAnimation(btt);
        btn_new_account_create.startAnimation(btt);

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosign = new Intent(GetStartedAct.this, SigninAct.class);
                startActivity(gotosign);
            }
        });

        btn_new_account_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoregisterone = new Intent(GetStartedAct.this, RegisterOneAct.class);
                startActivity(gotoregisterone);
            }
        });
    }
}