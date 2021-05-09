package umn.ac.id.fashop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckoutActivity extends AppCompatActivity {
    Button payNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        payNow = findViewById(R.id.payNow);
        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent successBuyIntent = new Intent(CheckoutActivity.this, SuccessBuyActivity.class);
                startActivity(successBuyIntent);
            }
        });
    }
}