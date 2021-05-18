package umn.ac.id.fashop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class CheckoutActivity extends AppCompatActivity {
    Button payNow, btn_plus, btn_minus;
    TextView nama_produk, size, harga;
    TextView texttotalharga, textmybalance, textjumlahproduk;
    ImageView notice_uang, image_produk;

    Integer valuejumlahproduk = 1;
    Integer mybalance = 800;
    Integer valuetotalharga = 0;
    Integer valuehargaproduk = 0;
    Integer sisa_balance = 0;

    DatabaseReference reference, reference2, reference3, reference4;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    //Generate nomor integer secara random
    //Karena kirta ingin membuat transaksi secara unik
    Integer nomor_transaksi = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getUsernameLocal();

        //Mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String produk_detail = bundle.getString("produk_detail");

        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
//        notice_uang = findViewById(R.id.notice_uang);
        payNow = findViewById(R.id.payNow);

        nama_produk = findViewById(R.id.nama_produk);
        size = findViewById(R.id.size);
        harga = findViewById(R.id.harga);
        image_produk = findViewById(R.id.image_produk);

        texttotalharga = findViewById(R.id.texttotalharga);
        textmybalance = findViewById(R.id.textmybalance);

        //Setting value baru untuk beberapa komponen
        textjumlahproduk.setText(valuejumlahproduk.toString());
        textmybalance.setText("Rp " + mybalance+"");
        valuetotalharga = valuehargaproduk * valuejumlahproduk;
        texttotalharga.setText("Rp " + valuetotalharga+"");

        //Secara default, kita hide btn_minus
        btn_minus.animate().alpha(0).setDuration(300).start();
        btn_minus.setEnabled(false);
        notice_uang.setVisibility(View.GONE);

        //Mengambil data user dari firebase
        reference2 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mybalance = Integer.valueOf(dataSnapshot.child("user_balance").getValue().toString());
                textmybalance.setText("Rp " + mybalance+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Mengambil data dari firebase berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child("Produk").child(produk_detail);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Menimpa data yang ada dengan data yang baru
                nama_produk.setText(dataSnapshot.child("nama_produk").getValue().toString());
                size.setText(dataSnapshot.child("size").getValue().toString());
                valuehargaproduk = Integer.valueOf(dataSnapshot.child("harga").getValue().toString());
                Picasso.with(CheckoutActivity.this)
                        .load(dataSnapshot.child("image1")
                                .getValue().toString()).centerCrop().fit()
                        .into(image_produk);
                valuetotalharga = valuehargaproduk * valuejumlahproduk;
                texttotalharga.setText("Rp " + valuetotalharga+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuejumlahproduk += 1;
                textjumlahproduk.setText(valuejumlahproduk.toString());
                if (valuejumlahproduk > 1) {
                    btn_minus.animate().alpha(1).setDuration(300).start();
                    btn_minus.setEnabled(true);
                }
                valuetotalharga = valuehargaproduk * valuejumlahproduk;
                texttotalharga.setText("Rp " + valuetotalharga +"");
                if (valuetotalharga > mybalance) {
                    payNow.animate().translationY(250)
                            .alpha(0).setDuration(350).start();
                    payNow.setEnabled(false);
                    textmybalance.setTextColor(Color.parseColor("#D12068"));
//                    notice_uang.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuejumlahproduk-=1;
                textjumlahproduk.setText(valuejumlahproduk.toString());
                if(valuejumlahproduk < 2) {
                    btn_minus.animate().alpha(0).setDuration(300).start();
                    btn_minus.setEnabled(false);
                }
                valuetotalharga = valuehargaproduk * valuejumlahproduk;
                texttotalharga.setText("Rp " + valuetotalharga+"");
                if (valuetotalharga < mybalance){
                    payNow.animate().translationY(0)
                            .alpha(1).setDuration(350).start();
                    payNow.setEnabled(true);
                    textmybalance.setTextColor(Color.parseColor("#203DD1"));
//                    notice_uang.setVisibility(View.GONE);
                }
            }
        });

        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Menyimpan data user kepada firebase dan membuat table baru "MyProducts"
                reference3 = FirebaseDatabase.getInstance()
                        .getReference().child("MyProducts")
                        .child(username_key_new).child(nama_produk.getText().toString() + nomor_transaksi);
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference3.getRef().child("id_produk").setValue(nama_produk.getText().toString() + nomor_transaksi);
                        reference3.getRef().child("nama_produk").setValue(nama_produk.getText().toString());
                        reference3.getRef().child("size").setValue(size);
                        reference3.getRef().child("jumlah_produk").setValue(valuejumlahproduk);

                        Intent successBuyIntent = new Intent(CheckoutActivity.this, SuccessBuyActivity.class);
                        startActivity(successBuyIntent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                //Update data balance kepada users (yang saat ini login)
                //Mengambil data user dari firebase
                reference4 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
                reference4.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        sisa_balance = mybalance - valuetotalharga;
                        reference4.getRef().child("user_balance").setValue(sisa_balance);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}