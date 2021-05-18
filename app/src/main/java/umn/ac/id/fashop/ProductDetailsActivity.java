package umn.ac.id.fashop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class ProductDetailsActivity extends AppCompatActivity {
    Button addToCart;
    TextView nama_produk, harga, size, material, bust, length, model, instruction;
    ImageView imageViewDetail1, imageView3;
    LinearLayout button_back;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        button_back = findViewById(R.id.button_back);

        imageViewDetail1 = findViewById(R.id.imageViewDetail1);
        imageView3 = findViewById(R.id.imageView3);

        nama_produk = findViewById(R.id.nama_produk);
        harga = findViewById(R.id.harga);
        size = findViewById(R.id.size);
        material = findViewById(R.id.material);
        bust = findViewById(R.id.bust);
        length = findViewById(R.id.length);
        model = findViewById(R.id.model);
        instruction = findViewById(R.id.instruction);

        //Mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        String list_produk = bundle.getString("list_produk");

        //Mengambil data dari firebase berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child("Produk").child(list_produk);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Menimpa data yang ada dengan data yang baru
                nama_produk.setText(dataSnapshot.child("nama_produk").getValue().toString());
                harga.setText("Rp " + dataSnapshot.child("harga").getValue().toString());
                size.setText(dataSnapshot.child("size").getValue().toString());
                material.setText(dataSnapshot.child("material").getValue().toString());
                bust.setText(dataSnapshot.child("bust").getValue().toString());
                length.setText(dataSnapshot.child("length").getValue().toString());
                model.setText(dataSnapshot.child("model").getValue().toString());
                instruction.setText(dataSnapshot.child("instruction").getValue().toString());
                Picasso.with(ProductDetailsActivity.this)
                        .load(dataSnapshot.child("image1")
                                .getValue().toString()).centerCrop().fit()
                        .into(imageViewDetail1);
                Picasso.with(ProductDetailsActivity.this)
                        .load(dataSnapshot.child("image2")
                                .getValue().toString()).centerCrop().fit()
                        .into(imageView3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addToCart = findViewById(R.id.addToCart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCheckout = new Intent(ProductDetailsActivity.this, CheckoutActivity.class);
                toCheckout.putExtra("list_produk", list_produk);
                startActivity(toCheckout);
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoback = new Intent(ProductDetailsActivity.this, ListProductActivity.class);
                startActivity(gotoback);
            }
        });
    }
}