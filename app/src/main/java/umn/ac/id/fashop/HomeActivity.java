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
    LinearLayout go_to_tops;
    LinearLayout go_to_bottoms;
    LinearLayout go_to_dresses;
    LinearLayout go_to_loungewears;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        go_to_tops = findViewById(R.id.go_to_tops);
        go_to_bottoms = findViewById(R.id.go_to_bottoms);
        go_to_dresses = findViewById(R.id.go_to_dresses);
        go_to_loungewears = findViewById(R.id.go_to_loungewears);
        go_to_tops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoTopsIntent = new Intent(HomeActivity.this, Tops_Activity.class);
                startActivity(gotoTopsIntent);
            }
        });
        go_to_bottoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoBottomsIntent = new Intent(HomeActivity.this, Bottoms_Activity.class);
                startActivity(gotoBottomsIntent);
            }
        });
        go_to_dresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDressesIntent = new Intent(HomeActivity.this, Dresses_Activity.class);
                startActivity(gotoDressesIntent);
            }
        });
        go_to_loungewears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLoungeWearsIntent = new Intent(HomeActivity.this, LoungeWears_Activity.class);
                startActivity(gotoLoungeWearsIntent);
            }
        });
    }

}