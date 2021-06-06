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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Tops_Activity extends AppCompatActivity {
    LinearLayout item_Tops;
    ImageView btn_back;

    DatabaseReference reference2;

    RecyclerView my_tops;
    ArrayList<MyTops> list;
    TopsAdapter topsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tops_);

        item_Tops = findViewById(R.id.item_Tops);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoHome = new Intent(Tops_Activity.this, HomeActivity.class);
                startActivity(gotoHome);
            }
        });

        my_tops = findViewById(R.id.my_tops);
        my_tops.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyTops>();

        reference2 = FirebaseDatabase.getInstance().getReference().child("Tops");
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    MyTops p = dataSnapshot1.getValue(MyTops.class);
                    list.add(p);
                    topsAdapter = new TopsAdapter(Tops_Activity.this, list);
                    my_tops.setAdapter(topsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}