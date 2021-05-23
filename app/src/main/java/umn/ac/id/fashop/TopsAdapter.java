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

public class TopsAdapter extends RecyclerView.Adapter<TopsAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyTops> myTops;

    public TopsAdapter(Context c, ArrayList<MyTops> p){
        context = c;
        myTops = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopsAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tops, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.yTopNama.setText(myTops.get(position).getNama_barang());
        holder.yTopUkuran.setText(myTops.get(position).getUkuran());
        holder.yTopHarga.setText("IDR " + Integer.toString(myTops.get(position).getHarga()));

        final String getNamaBarang = myTops.get(position).getNama_barang();

        holder.itemView.setOnClickListener(v -> {
            Intent goToHistoryOrderDetails = new Intent(context, MyHistoryDetailActivity.class);
            goToHistoryOrderDetails.putExtra("nama_barang", getNamaBarang);
            context.startActivity(goToHistoryOrderDetails);
        });
    }

    @Override
    public int getItemCount() {
        return myTops.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView yTopNama, yTopUkuran, yTopHarga;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            yTopNama = itemView.findViewById(R.id.yTopNama);
            yTopUkuran = itemView.findViewById(R.id.yTopUkuran);
            yTopHarga = itemView.findViewById(R.id.yTopHarga);
        }
    }
}
