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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductDetailsBottomsActivity extends AppCompatActivity {
    Button addToCart;
    TextView xnama_barang, xharga, xukuran, xbahan, xpinggang, xpinggul, xpanjang;
    ImageView xurl_product_image1,
            xurl_product_image2, xurl_product_image3,
            xurl_product_image4, xurl_product_image5, xurl_product_image6, xurl_product_image7;
    LinearLayout button_back;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_bottoms);
        button_back = findViewById(R.id.button_back);

        xurl_product_image1 = findViewById(R.id.xurl_product_image1);
        xurl_product_image2 = findViewById(R.id.xurl_product_image2);
        xurl_product_image3 = findViewById(R.id.xurl_product_image3);
        xurl_product_image4 = findViewById(R.id.xurl_product_image4);
        xurl_product_image5 = findViewById(R.id.xurl_product_image5);
        xurl_product_image6 = findViewById(R.id.xurl_product_image6);
        xurl_product_image7 = findViewById(R.id.xurl_product_image7);

        xnama_barang = findViewById(R.id.xnama_barang);
        xharga = findViewById(R.id.xharga);
        xukuran = findViewById(R.id.xukuran);
        xbahan = findViewById(R.id.xbahan);
        xpinggang = findViewById(R.id.xpinggang);
        xpinggul = findViewById(R.id.xpinggul);
        xpanjang = findViewById(R.id.xpanjang);

        //Mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        String barang_baru = bundle.getString("nama_barang");

        //Mengambil data dari firebase berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child("Bottoms").child(barang_baru);
        if (reference != null){
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        if (dataSnapshot.hasChild("nama_barang")) {
                            xnama_barang.setText(dataSnapshot.child("nama_barang").getValue().toString());
                            xharga.setText("IDR " + dataSnapshot.child("harga").getValue().toString());
                            xukuran.setText(dataSnapshot.child("ukuran").getValue().toString());
                            xbahan.setText(dataSnapshot.child("bahan").getValue().toString());
                            xpinggang.setText(dataSnapshot.child("pinggang").getValue().toString());
                            xpinggul.setText(dataSnapshot.child("pinggul").getValue().toString());
                            xpanjang.setText(dataSnapshot.child("panjang").getValue().toString());
                            Picasso.get()
                                    .load(dataSnapshot.child("url_product_image1")
                                            .getValue().toString()).centerCrop().fit().into(xurl_product_image1);
                            Picasso.get()
                                    .load(dataSnapshot.child("url_product_image2")
                                            .getValue().toString()).centerCrop().fit().into(xurl_product_image2);
                            Picasso.get()
                                    .load(dataSnapshot.child("url_product_image3")
                                            .getValue().toString()).centerCrop().fit().into(xurl_product_image3);
                            Picasso.get()
                                    .load(dataSnapshot.child("url_product_image4")
                                            .getValue().toString()).centerCrop().fit().into(xurl_product_image4);
                            Picasso.get()
                                    .load(dataSnapshot.child("url_product_image5")
                                            .getValue().toString()).centerCrop().fit().into(xurl_product_image5);
                            Picasso.get()
                                    .load(dataSnapshot.child("url_product_image6")
                                            .getValue().toString()).centerCrop().fit().into(xurl_product_image6);
                            Picasso.get()
                                    .load(dataSnapshot.child("url_product_image7")
                                            .getValue().toString()).centerCrop().fit().into(xurl_product_image7);
                        } else {
                            Toast.makeText(ProductDetailsBottomsActivity.this, "Data do not exists...", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ProductDetailsBottomsActivity.this, "Does not exists..........", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(ProductDetailsBottomsActivity.this, "No data found...", Toast.LENGTH_SHORT).show();
        }

        addToCart = findViewById(R.id.addToCart);
        addToCart.setOnClickListener(v -> {
            Intent toCheckout = new Intent(ProductDetailsBottomsActivity.this, CheckoutActivity.class);
            toCheckout.putExtra("checkout_barang", barang_baru);
            startActivity(toCheckout);
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoback = new Intent(ProductDetailsBottomsActivity.this, Bottoms_Activity.class);
                startActivity(gotoback);
            }
        });
    }
}