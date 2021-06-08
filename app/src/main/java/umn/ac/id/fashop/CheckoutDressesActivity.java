package umn.ac.id.fashop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class CheckoutDressesActivity extends AppCompatActivity {
    Button payNow, btn_plus, btn_minus;
    TextView xnama_barang, xukuran, xharga;
    TextView texttotalharga, textjumlahproduk;
    ImageView xurl_product_image1;
    LinearLayout button_back;

    Integer valuejumlahproduk = 1;
    Integer valuetotalharga = 0;
    Integer valuehargaproduk = 0;

    DatabaseReference reference, reference3;

    Calendar calendar;

    String date;
    SimpleDateFormat dateFormat;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    //Generate nomor integer secara random
    //Karena kita ingin membuat transaksi secara unik
    Integer nomor_transaksi = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_dresses);

        getUsernameLocal();

        //Mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String barang_baru = bundle.getString("nama_barang");

        button_back = findViewById(R.id.button_back);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        payNow = findViewById(R.id.payNow);

        xnama_barang = findViewById(R.id.xnama_barang);
        xukuran = findViewById(R.id.xukuran);
        xharga = findViewById(R.id.xharga);
        xurl_product_image1 = findViewById(R.id.xurl_product_image1);

        texttotalharga = findViewById(R.id.texttotalharga);
        textjumlahproduk = findViewById(R.id.textjumlahproduk);

        //Setting value baru untuk beberapa komponen
        textjumlahproduk.setText(valuejumlahproduk.toString());
        valuetotalharga = valuehargaproduk * valuejumlahproduk;
        texttotalharga.setText("Rp " + valuetotalharga+"");

        //Secara default, kita hide btn_minus
        btn_minus.animate().alpha(0).setDuration(300).start();
        btn_minus.setEnabled(false);

        //Mengambil data dari firebase berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child("Dresses").child(barang_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Menimpa data yang ada dengan data yang baru
                xnama_barang.setText(dataSnapshot.child("nama_barang").getValue().toString());
                xukuran.setText(dataSnapshot.child("ukuran").getValue().toString());
                xharga.setText("IDR " + dataSnapshot.child("harga").getValue().toString());
                Picasso.get()
                        .load(dataSnapshot.child("url_product_image1")
                                .getValue().toString()).centerCrop().fit().into(xurl_product_image1);
                valuehargaproduk = Integer.valueOf(dataSnapshot.child("harga").getValue().toString());
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
            }
        });

        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Menyimpan data user kepada firebase dan membuat table baru "MyHistory"
                calendar = Calendar.getInstance();

                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                date = dateFormat.format(calendar.getTime());

                reference3 = FirebaseDatabase.getInstance()
                        .getReference().child("MyHistory")
                        .child(username_key_new).child(xnama_barang.getText().toString() + nomor_transaksi);
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference3.getRef().child("id_barang").setValue(xnama_barang.getText().toString() + nomor_transaksi);
                        reference3.getRef().child("nama_barang").setValue(xnama_barang.getText().toString());
                        reference3.getRef().child("ukuran").setValue(xukuran.getText().toString());
                        reference3.getRef().child("jumlah").setValue(valuejumlahproduk);
                        reference3.getRef().child("harga").setValue(valuetotalharga);
                        reference3.getRef().child("tanggal_order").setValue(date);

                        Intent gotopayment = new Intent(CheckoutDressesActivity.this, PaymentActivity.class);
                        startActivity(gotopayment);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoback = new Intent(CheckoutDressesActivity.this, HomeActivity.class);
                startActivity(gotoback);
            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}