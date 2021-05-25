package umn.ac.id.fashop;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BottomsAdapter extends RecyclerView.Adapter<BottomsAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyBottoms> myBottoms;

    public BottomsAdapter(Context c, ArrayList<MyBottoms> p){
        context = c;
        myBottoms = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BottomsAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bottoms, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.yBottomNama.setText(myBottoms.get(position).getNama_barang());
            holder.yBottomUkuran.setText(myBottoms.get(position).getUkuran());
            holder.yBottomHarga.setText("IDR " + Integer.toString(myBottoms.get(position).getHarga()));

    final String getNamaBarang = myBottoms.get(position).getNama_barang();

            holder.itemView.setOnClickListener(v -> {
                Intent goToHistoryOrderDetails = new Intent(context, MyHistoryDetailActivity.class);
                goToHistoryOrderDetails.putExtra("nama_barang", getNamaBarang);
                context.startActivity(goToHistoryOrderDetails);
                });
            }

    @Override
    public int getItemCount() {
        return myBottoms.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView yBottomNama, yBottomUkuran, yBottomHarga;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            yBottomNama = itemView.findViewById(R.id.yBottomNama);
            yBottomUkuran = itemView.findViewById(R.id.yBottomUkuran);
            yBottomHarga = itemView.findViewById(R.id.yBottomHarga);
        }
    }
}
