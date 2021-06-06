package umn.ac.id.fashop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopsAdapter extends RecyclerView.Adapter<TopsAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyTops> topsList;

    public TopsAdapter(Context c, ArrayList<MyTops> p){
        context = c;
        topsList = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopsAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tops, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(topsList.get(position).getGambar_barang()).into(holder.yTopImages);
//        Picasso.with(context).load(topsList.get(position).getGambar_barang()).into(holder.yTopImages);
        holder.yTopNama.setText(topsList.get(position).getNama_barang());
        holder.yTopUkuran.setText(topsList.get(position).getUkuran());
        holder.yTopHarga.setText("IDR " + Integer.toString(topsList.get(position).getHarga()));

        final String getNamaBarang = topsList.get(position).getNama_barang();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToProductDetail = new Intent(context, ProductDetailsActivity.class);
                goToProductDetail.putExtra("barang", getNamaBarang);
                context.startActivity(goToProductDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView yTopNama, yTopUkuran, yTopHarga;
        ImageView yTopImages;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            yTopImages = itemView.findViewById(R.id.yTopImages);
            yTopNama = itemView.findViewById(R.id.yTopNama);
            yTopUkuran = itemView.findViewById(R.id.yTopUkuran);
            yTopHarga = itemView.findViewById(R.id.yTopHarga);
        }
    }
}
