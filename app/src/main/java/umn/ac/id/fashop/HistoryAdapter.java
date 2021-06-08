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
        //holder.xjumlah.setText(myHistory.get(position).getJumlah());
        holder.xjumlah.setText(Integer.toString(myHistory.get(position).getJumlah()));
        holder.xharga.setText("IDR " + Integer.toString(myHistory.get(position).getHarga()));
        //holder.xtanggal_order.setText(myHistory.get(position).getTanggal_order());
        holder.xukuran.setText(myHistory.get(position).getUkuran());

        final String getNamaBarang = myHistory.get(position).getNama_barang();
    }

    @Override
    public int getItemCount() {
        return myHistory.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView xnama_barang, xjumlah, xharga, xukuran;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            xnama_barang = itemView.findViewById(R.id.xnama_barang);
            xjumlah = itemView.findViewById(R.id.xjumlah);
            xharga = itemView.findViewById(R.id.xharga);
            //xtanggal_order = itemView.findViewById(R.id.xtanggal_order);
            xukuran = itemView.findViewById(R.id.xukuran);
        }
    }
}
