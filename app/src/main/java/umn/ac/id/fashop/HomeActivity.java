package umn.ac.id.fashop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import umn.ac.id.fashop.ProductDetailsActivity;
import umn.ac.id.fashop.R;

public class HomeActivity extends AppCompatActivity {
    LinearLayout man_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        man_view = findViewById(R.id.man_view);
        man_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manIntent = new Intent(HomeActivity.this, ProductDetailsActivity.class);
                startActivity(manIntent);
            }
        });
    }

}