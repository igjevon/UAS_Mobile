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

public class Bottoms_Activity extends AppCompatActivity {

    LinearLayout item_Bottoms;

    DatabaseReference reference2;

    RecyclerView my_bottoms;
    ArrayList<MyBottoms> list;
    BottomsAdapter bottomsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottoms_);

        item_Bottoms = findViewById(R.id.item_Bottoms);

        my_bottoms = findViewById(R.id.my_bottoms);
        my_bottoms.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyBottoms>();

        reference2 = FirebaseDatabase.getInstance().getReference().child("Bottoms");

        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    MyBottoms p = dataSnapshot1.getValue(MyBottoms.class);
                    list.add(p);
                    bottomsAdapter = new BottomsAdapter(Bottoms_Activity.this, list);
                    my_bottoms.setAdapter(bottomsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}