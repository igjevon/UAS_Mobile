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

public class ProductDetailsOutersActivity extends AppCompatActivity {
    Button addToCart;
    TextView xnama_barang, xharga, xukuran, xbahan, xld, xlengan, xpanjang;
    ImageView xurl_product_image1;
    //            xurl_product_image2, xurl_product_image3,
//            xurl_product_image4, xurl_product_image5, xurl_product_image6, xurl_product_image7;
    LinearLayout button_back;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_outers);
        button_back = findViewById(R.id.button_back);

        xurl_product_image1 = findViewById(R.id.xurl_product_image1);
//        xurl_product_image2 = findViewById(R.id.xurl_product_image2);
//        xurl_product_image3 = findViewById(R.id.xurl_product_image3);
//        xurl_product_image4 = findViewById(R.id.xurl_product_image4);
//        xurl_product_image5 = findViewById(R.id.xurl_product_image5);
//        xurl_product_image6 = findViewById(R.id.xurl_product_image6);
//        xurl_product_image7 = findViewById(R.id.xurl_product_image7);

        xnama_barang = findViewById(R.id.xnama_barang);
        xharga = findViewById(R.id.xharga);
        xukuran = findViewById(R.id.xukuran);
        xbahan = findViewById(R.id.xbahan);
        xld = findViewById(R.id.xld);
        xlengan = findViewById(R.id.xlengan);
        xpanjang = findViewById(R.id.xpanjang);

        //Mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        String barang_baru = bundle.getString("nama_barang");

        //Mengambil data dari firebase berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child("Outers").child(barang_baru);
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
                            xld.setText(dataSnapshot.child("ld").getValue().toString());
                            xlengan.setText(dataSnapshot.child("lengan").getValue().toString());
                            xpanjang.setText(dataSnapshot.child("panjang").getValue().toString());
                            Picasso.get()
                                    .load(dataSnapshot.child("url_product_image1")
                                            .getValue().toString()).centerCrop().fit().into(xurl_product_image1);
                        } else {
                            Toast.makeText(ProductDetailsOutersActivity.this, "Data do not exists...", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ProductDetailsOutersActivity.this, "Does not exists..........", Toast.LENGTH_SHORT).show();
                    }
//                    Picasso.get()
//                            .load(dataSnapshot.child("url_product_image1")
//                                    .getValue().toString()).centerCrop().fit().into(xurl_product_image1);
                    //                Picasso.with(ProductDetailsActivity.this)
                    //                        .load(dataSnapshot.child("url_product_image2")
                    //                                .getValue().toString()).centerCrop().fit().into(xurl_product_image2);
                    //                Picasso.with(ProductDetailsActivity.this)
                    //                        .load(dataSnapshot.child("url_product_image3")
                    //                                .getValue().toString()).centerCrop().fit().into(xurl_product_image3);
                    //                Picasso.with(ProductDetailsActivity.this)
                    //                        .load(dataSnapshot.child("url_product_image4")
                    //                                .getValue().toString()).centerCrop().fit().into(xurl_product_image4);
                    //                Picasso.with(ProductDetailsActivity.this)
                    //                        .load(dataSnapshot.child("url_product_image5")
                    //                                .getValue().toString()).centerCrop().fit().into(xurl_product_image5);
                    //                Picasso.with(ProductDetailsActivity.this)
                    //                        .load(dataSnapshot.child("url_product_image6")
                    //                                .getValue().toString()).centerCrop().fit().into(xurl_product_image6);
                    //                Picasso.with(ProductDetailsActivity.this)
                    //                        .load(dataSnapshot.child("url_product_image7")
                    //                                .getValue().toString()).centerCrop().fit().into(xurl_product_image7);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(ProductDetailsOutersActivity.this, "No data found...", Toast.LENGTH_SHORT).show();
        }

        addToCart = findViewById(R.id.addToCart);
        addToCart.setOnClickListener(v -> {
            Intent toCheckout = new Intent(ProductDetailsOutersActivity.this, CheckoutActivity.class);
            toCheckout.putExtra("checkout_barang", barang_baru);
            startActivity(toCheckout);
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoback = new Intent(ProductDetailsOutersActivity.this, ListProductActivity.class);
                startActivity(gotoback);
            }
        });
    }
}