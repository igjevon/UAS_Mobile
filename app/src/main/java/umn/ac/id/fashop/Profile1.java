package umn.ac.id.fashop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profile1 extends AppCompatActivity {
    Button edit_profile, btn_signout, btn_history, btn_location;
    ImageView back;
    TextView username,nama_lengkap, address, phone_number, email_address;
    ImageView photo_profile;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);

        getUsernameLocal();

        edit_profile = findViewById(R.id.btn_editprofile);
        btn_signout = findViewById(R.id.btn_signout);
        back = findViewById(R.id.back);
        btn_history = findViewById(R.id.btn_history);

        username = findViewById(R.id.username);
        address = findViewById(R.id.address);
        phone_number = findViewById(R.id.phone_number);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        photo_profile = findViewById(R.id.photo_profile);
        email_address = findViewById(R.id.email_address);
        btn_location = findViewById(R.id.btn_location);

        reference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                username.setText(dataSnapshot.child("username").getValue().toString());
                phone_number.setText(dataSnapshot.child("phone_number").getValue().toString());
                address.setText(dataSnapshot.child("address").getValue().toString());
                email_address.setText(dataSnapshot.child("email_address").getValue().toString());
                Picasso.get()
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit()
                        .into(photo_profile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoHistory = new Intent(Profile1.this, HistoryActivity.class);
                startActivity(gotoHistory);
            }
        });

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLocation = new Intent(Profile1.this, MapsActivity.class);
                startActivity(gotoLocation);
            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}