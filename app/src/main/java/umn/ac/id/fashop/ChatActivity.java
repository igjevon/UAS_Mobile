package umn.ac.id.fashop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {
    ImageView back,send;
    EditText textinput;

    StorageReference storage;
    DatabaseReference reference;
    RecyclerView recyclerView;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getUsernameLocal();

        back = findViewById(R.id.back);
        send = findViewById(R.id.send);
        textinput = findViewById(R.id.textinput);
        recyclerView = findViewById(R.id.recyclerView);

        reference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(username_key_new);
        storage = FirebaseStorage.getInstance().getReference().child("Photousers").child(username_key_new);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoback = new Intent(ChatActivity.this, HomeActivity.class);
                startActivity(gotoback);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}