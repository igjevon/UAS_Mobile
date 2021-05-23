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
    TextView nama_barang, harga, ukuran, bahan, ld, lengan, panjang, instruction;
    ImageView url_product_image1, url_product_image2, url_product_image3,
            url_product_image4, url_product_image5, url_product_image6, url_product_image7;
    LinearLayout button_back;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        button_back = findViewById(R.id.button_back);

        url_product_image1 = findViewById(R.id.url_product_image1);
        url_product_image2 = findViewById(R.id.url_product_image2);
        url_product_image3 = findViewById(R.id.url_product_image3);
        url_product_image4 = findViewById(R.id.url_product_image4);
        url_product_image5 = findViewById(R.id.url_product_image5);
        url_product_image6 = findViewById(R.id.url_product_image6);
        url_product_image7 = findViewById(R.id.url_product_image7);

        nama_barang = findViewById(R.id.nama_barang);
        harga = findViewById(R.id.harga);
        ukuran = findViewById(R.id.ukuran);
        bahan = findViewById(R.id.bahan);
        ld = findViewById(R.id.ld);
        lengan = findViewById(R.id.lengan);
        panjang = findViewById(R.id.panjang);
        instruction = findViewById(R.id.instruction);

        //Mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        String list_produk = bundle.getString("list_produk");

        //Mengambil data dari firebase berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child("Products").child(list_produk);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Menimpa data yang ada dengan data yang baru
                nama_barang.setText(dataSnapshot.child("nama_barang").getValue().toString());
                harga.setText("Rp " + dataSnapshot.child("harga").getValue().toString());
                ukuran.setText(dataSnapshot.child("ukuran").getValue().toString());
                bahan.setText(dataSnapshot.child("bahan").getValue().toString());
                ld.setText(dataSnapshot.child("ld").getValue().toString());
                lengan.setText(dataSnapshot.child("lengan").getValue().toString());
                panjang.setText(dataSnapshot.child("panjang").getValue().toString());
                instruction.setText(dataSnapshot.child("instruction").getValue().toString());
                Picasso.with(ProductDetailsActivity.this)
                        .load(dataSnapshot.child("url_product_image1")
                                .getValue().toString()).centerCrop().fit().into(url_product_image1);
                Picasso.with(ProductDetailsActivity.this)
                        .load(dataSnapshot.child("url_product_image2")
                                .getValue().toString()).centerCrop().fit().into(url_product_image2);
                Picasso.with(ProductDetailsActivity.this)
                        .load(dataSnapshot.child("url_product_image3")
                                .getValue().toString()).centerCrop().fit().into(url_product_image3);
                Picasso.with(ProductDetailsActivity.this)
                        .load(dataSnapshot.child("url_product_image4")
                                .getValue().toString()).centerCrop().fit().into(url_product_image4);
                Picasso.with(ProductDetailsActivity.this)
                        .load(dataSnapshot.child("url_product_image5")
                                .getValue().toString()).centerCrop().fit().into(url_product_image5);
                Picasso.with(ProductDetailsActivity.this)
                        .load(dataSnapshot.child("url_product_image6")
                                .getValue().toString()).centerCrop().fit().into(url_product_image6);
                Picasso.with(ProductDetailsActivity.this)
                        .load(dataSnapshot.child("url_product_image7")
                                .getValue().toString()).centerCrop().fit().into(url_product_image7);
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