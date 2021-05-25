package umn.ac.id.fashop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DressesAdapter extends RecyclerView.Adapter<DressesAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyDresses> myDresses;

    public DressesAdapter(Context c, ArrayList<MyDresses> p){
        context = c;
        myDresses = p;
    }

    @NonNull
    @Override
    public DressesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DressesAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dresses, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DressesAdapter.MyViewHolder holder, int position) {
        holder.yDressNama.setText(myDresses.get(position).getNama_barang());
        holder.yDressUkuran.setText(myDresses.get(position).getUkuran());
        holder.yDressHarga.setText("IDR " + Integer.toString(myDresses.get(position).getHarga()));

        final String getNamaBarang = myDresses.get(position).getNama_barang();

        holder.itemView.setOnClickListener(v -> {
            Intent goToHistoryOrderDetails = new Intent(context, MyHistoryDetailActivity.class);
            goToHistoryOrderDetails.putExtra("nama_barang", getNamaBarang);
            context.startActivity(goToHistoryOrderDetails);
        });
    }

    @Override
    public int getItemCount() {
        return myDresses.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView yDressNama, yDressUkuran, yDressHarga;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            yDressNama = itemView.findViewById(R.id.yDressNama);
            yDressUkuran = itemView.findViewById(R.id.yDressUkuran);
            yDressHarga = itemView.findViewById(R.id.yDressHarga);
        }
    }
}
