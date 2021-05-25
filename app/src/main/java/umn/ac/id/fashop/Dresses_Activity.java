package umn.ac.id.fashop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dresses_Activity extends AppCompatActivity {
    LinearLayout item_Dresses;

    DatabaseReference reference2;

    RecyclerView my_dresses;
    ArrayList<MyDresses> list;
    DressesAdapter dressesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dresses_);
        item_Dresses = findViewById(R.id.item_Dresses);

        my_dresses = findViewById(R.id.my_dresses);
        my_dresses.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyDresses>();

        reference2 = FirebaseDatabase.getInstance().getReference().child("Dresses");

        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    MyDresses p = dataSnapshot1.getValue(MyDresses.class);
                    list.add(p);
                    dressesAdapter = new DressesAdapter(Dresses_Activity.this, list);
                    my_dresses.setAdapter(dressesAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}