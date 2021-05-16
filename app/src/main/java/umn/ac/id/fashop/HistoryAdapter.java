package umn.ac.id.fashop;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyHistory> myHistory;
    public HistoryAdapter(Context c, ArrayList<MyHistory> p){
        context = c;
        myHistory = p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.xnama_barang.setText(myHistory.get(position).getNama_barang());
        holder.xjumlah.setText(myHistory.get(position).getJumlah());
        holder.xharga.setText(myHistory.get(position).getHarga());
        holder.xtanggal_order.setText(myHistory.get(position).getTanggal_order());

        final String getNamaBarang = myHistory.get(position).getNama_barang();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHistoryOrderDetails = new Intent(context, MyHistoryDetailActivity.class);
                goToHistoryOrderDetails.putExtra("nama_barang", getNamaBarang);
                context.startActivity(goToHistoryOrderDetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myHistory.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView xnama_barang, xjumlah, xharga, xtanggal_order;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            xnama_barang = itemView.findViewById(R.id.xnama_barang);
            xjumlah = itemView.findViewById(R.id.xjumlah);
            xharga = itemView.findViewById(R.id.xharga);
            xtanggal_order = itemView.findViewById(R.id.xtanggal_order);
        }
    }
}