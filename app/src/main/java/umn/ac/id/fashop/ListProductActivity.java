package umn.ac.id.fashop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListProductActivity extends AppCompatActivity {
    LinearLayout button_back;
    TextView nama_barang, ukuran, harga;
    ImageView url_product_image1;

    DatabaseReference reference;
    RecyclerView viewListProduct;
    //ArrayList<ListProduct> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

//        nama_barang = findViewById(R.id.nama_barang);
//        ukuran = findViewById(R.id.ukuran);
//        harga = findViewById(R.id.harga);
//        url_product_image1 = findViewById(R.id.url_product_image1);
//
//        //viewListProduct = findViewById(R.id.viewListProduct);
//
//        //reference = FirebaseDatabase.getInstance().getReference().child("MyHistory").child(username_key_new);
//        //reference.addListenerForSingleValueEvent(new ValueEventListener() {
//
//        button_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent gotoback = new Intent(ListProductActivity.this, HomeActivity.class);
//                startActivity(gotoback);
//            }
//        });
    }
}