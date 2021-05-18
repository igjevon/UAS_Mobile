package umn.ac.id.fashop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class ListProductActivity extends AppCompatActivity {
    LinearLayout button_back;
    TextView nama_produk, size, harga;
    ImageView image_produk;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        nama_produk = findViewById(R.id.nama_produk);
        size = findViewById(R.id.size);
        harga = findViewById(R.id.harga);
        image_produk = findViewById(R.id.image_produk);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoback = new Intent(ListProductActivity.this, HomeActivity.class);
                startActivity(gotoback);
            }
        });
    }
}