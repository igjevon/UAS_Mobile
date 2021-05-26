package umn.ac.id.fashop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Outers_Activity extends AppCompatActivity {
    LinearLayout item_Outers;
    ImageView btn_back;

    DatabaseReference reference2;

    RecyclerView my_outers;
    ArrayList<MyOuters> list;
    OutersAdapter outersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outers_);
        item_Outers = findViewById(R.id.item_Outers);
        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoHome = new Intent(Outers_Activity.this, HomeActivity.class);
                startActivity(gotoHome);
            }
        });

        my_outers = findViewById(R.id.my_outers);
        my_outers.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyOuters>();

        reference2 = FirebaseDatabase.getInstance().getReference().child("Outers");

        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    MyOuters p = dataSnapshot1.getValue(MyOuters.class);
                    list.add(p);
                    outersAdapter = new OutersAdapter(Outers_Activity.this, list);
                    my_outers.setAdapter(outersAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}