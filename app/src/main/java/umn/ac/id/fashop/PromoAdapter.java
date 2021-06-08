package umn.ac.id.fashop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyPromo> myPromo;

    public PromoAdapter(Context c, ArrayList<MyPromo> p){
        context = c;
        myPromo = p;
    }

    @NonNull
    @Override
    public PromoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PromoAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_promo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PromoAdapter.MyViewHolder holder, int position) {
        holder.zPromoNama.setText(myPromo.get(position).getNama_barang());
        holder.zPromoHarga.setText("IDR " + Integer.toString(myPromo.get(position).getHarga()));
        Picasso.get().load(myPromo.get(position).getUrl_product_image1()).fit().centerCrop().into(holder.zPromoImages);
        final String getNamaBarang = myPromo.get(position).getNama_barang();

        holder.itemView.setOnClickListener(v -> {
            Intent goToProductDetails = new Intent(context, ProductDetailsActivity.class);
            goToProductDetails.putExtra("nama_barang", getNamaBarang);
            context.startActivity(goToProductDetails);
        });
    }

    @Override
    public int getItemCount() {
        return myPromo.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView zPromoNama, zPromoHarga;
        ImageView zPromoImages;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            zPromoNama = itemView.findViewById(R.id.zPromoNama);
            zPromoHarga = itemView.findViewById(R.id.zPromoHarga);
            zPromoImages = itemView.findViewById(R.id.zPromoImages);
        }
    }
}
