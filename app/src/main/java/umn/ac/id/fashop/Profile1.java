package umn.ac.id.fashop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Profile1 extends AppCompatActivity {
    Button edit_profile, btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);

        edit_profile = findViewById(R.id.btn_editprofile);
        btn_continue = findViewById(R.id.btn_continue);
    }
}