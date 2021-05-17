package umn.ac.id.fashop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    LinearLayout item_MyHistory;
    TextView nama_pengguna;
    ImageView profile_photo;

    DatabaseReference reference, reference2;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    RecyclerView my_belanjaan;
    ArrayList<MyHistory> list;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getUsernameLocal();

        item_MyHistory = findViewById(R.id.item_MyHistory);
        nama_pengguna = findViewById(R.id.tvNamaPengguna);
        profile_photo = findViewById(R.id.profile_photo);

        my_belanjaan = findViewById(R.id.my_belanjaan);
        my_belanjaan.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyHistory>();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nama_pengguna.setText(snapshot.child("nama_lengkap").getValue().toString());
                Picasso.with(HistoryActivity.this)
                        .load(snapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit()
                        .into(profile_photo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference2 = FirebaseDatabase.getInstance().getReference().child("MyHistory").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    MyHistory p = dataSnapshot1.getValue(MyHistory.class);
                    list.add(p);
                    historyAdapter = new HistoryAdapter(HistoryActivity.this, list);
                    my_belanjaan.setAdapter(historyAdapter);
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