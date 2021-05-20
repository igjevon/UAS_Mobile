package umn.ac.id.fashop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;

public class Profile1 extends AppCompatActivity {
    Button edit_profile, btn_signout;
    ImageView back;

    DatabaseReference reference, reference2;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);

        edit_profile = findViewById(R.id.btn_editprofile);
        btn_signout = findViewById(R.id.btn_signout);
        back = findViewById(R.id.back);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoeditprofile = new Intent(Profile1.this, EditProfileAct.class);
                startActivity(gotoeditprofile);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoback = new Intent(Profile1.this, HomeActivity.class);
                startActivity(gotoback);
            }
        });

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //menghapus isi/nilai/value dari username login
                //menghapus data ke localstorage -handphone
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key, null);
                editor.apply();

                //berpindah activity
                Intent gotosignout = new Intent(Profile1.this, SigninAct.class);
                startActivity(gotosignout);
                finish(); //agar tidak bisa kembali ke myprofile
            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}