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

public class SuccessBuyActivity extends AppCompatActivity {
    Animation app_splash, btt, ttb;
    Button btnContinueShopping, btnViewOrder;
    TextView tvSuccess, tvOrderMessage;
    ImageView success_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_buy);

        btnViewOrder = findViewById(R.id.btnViewOrder);
        btnContinueShopping = findViewById(R.id.btnContinueShopping);
        tvSuccess = findViewById(R.id.tvSuccess);
        tvOrderMessage = findViewById(R.id.tvOrderMessage);
        success_buy = findViewById(R.id.success_buy);

        //Load animation
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);

        //Run Animation
        success_buy.startAnimation(app_splash);

        tvSuccess.startAnimation(ttb);
        tvOrderMessage.startAnimation(ttb);

        btnViewOrder.startAnimation(btt);
        btnContinueShopping.startAnimation(btt);

        btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoprofile = new Intent(SuccessBuyActivity.this, Profile1.class);
                startActivity(gotoprofile);
            }
        });

        btnContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(SuccessBuyActivity.this, HomeActivity.class);
                startActivity(gotohome);
            }
        });
    }
}