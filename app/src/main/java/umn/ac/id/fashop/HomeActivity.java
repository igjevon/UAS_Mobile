package umn.ac.id.fashop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.PointerIcon;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import umn.ac.id.fashop.ProductDetailsActivity;
import umn.ac.id.fashop.R;

public class HomeActivity extends AppCompatActivity {
    LinearLayout go_to_tops;
    LinearLayout go_to_bottoms;
    LinearLayout go_to_dresses;
    LinearLayout go_to_outers;

    ImageView pic_photo_home_user_circled, chat;
    TextView nama_lengkap, user_balance;
    TextView go_to_profile;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getUsernameLocal();

        pic_photo_home_user_circled = findViewById(R.id.pic_photo_home_user_circled);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        user_balance = findViewById(R.id.user_balance);
        chat = findViewById(R.id.chat);
        go_to_profile = findViewById(R.id.go_to_profile);

        reference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                user_balance.setText("Rp " + dataSnapshot.child("user_balance").getValue().toString());
                Picasso.with(HomeActivity.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit()
                        .into(pic_photo_home_user_circled);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        go_to_tops = findViewById(R.id.go_to_tops);
        go_to_bottoms = findViewById(R.id.go_to_bottoms);
        go_to_dresses = findViewById(R.id.go_to_dresses);
        go_to_outers = findViewById(R.id.go_to_outers);

        go_to_tops.setOnClickListener(v -> {
            Intent gotoTopsIntent = new Intent(HomeActivity.this, Tops_Activity.class);
            startActivity(gotoTopsIntent);
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
        go_to_outers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoOutersIntent = new Intent(HomeActivity.this, Outers_Activity.class);
                startActivity(gotoOutersIntent);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotochat = new Intent(HomeActivity.this, ChatActivity.class);
                startActivity(gotochat);
            }
        });

        go_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomyProfile = new Intent(HomeActivity.this, Profile1.class);
                startActivity(gotomyProfile);
            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}