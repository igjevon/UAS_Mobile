package umn.ac.id.fashop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    LinearLayout go_to_tops;
    LinearLayout go_to_bottoms;
    LinearLayout go_to_dresses;
    LinearLayout go_to_outers;

    ImageView pic_photo_home_user_circled;
    TextView nama_lengkap;
    TextView go_to_profile;

    RecyclerView my_promo;
    ArrayList<MyPromo> list;
    PromoAdapter promoAdapter;


    DatabaseReference reference, reference2;

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
        go_to_profile = findViewById(R.id.go_to_profile);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
//
//        RecyclerView my_promo = (RecyclerView)findViewById(R.id.my_promo);
//        my_promo.setLayoutManager(layoutManager);

        reference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                Picasso.get()
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

        go_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomyProfile = new Intent(HomeActivity.this, Profile1.class);
                startActivity(gotomyProfile);
            }
        });
        my_promo = findViewById(R.id.my_promo);
        my_promo.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyPromo>();

        reference2 = FirebaseDatabase.getInstance().getReference().child("MyPromo");

        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    MyPromo p = dataSnapshot1.getValue(MyPromo.class);
                    list.add(p);
                    promoAdapter = new PromoAdapter(HomeActivity.this, list);
                    my_promo.setAdapter(promoAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}