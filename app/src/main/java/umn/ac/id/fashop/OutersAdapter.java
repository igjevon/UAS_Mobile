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

public class OutersAdapter extends RecyclerView.Adapter<OutersAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyOuters> myOuters;

    public OutersAdapter(Context c, ArrayList<MyOuters> p){
        context = c;
        myOuters = p;
    }

    @NonNull
    @Override
    public OutersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OutersAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_outers, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OutersAdapter.MyViewHolder holder, int position) {
        holder.yOuterNama.setText(myOuters.get(position).getNama_barang());
        holder.yOuterUkuran.setText(myOuters.get(position).getUkuran());
        holder.yOuterHarga.setText("IDR " + Integer.toString(myOuters.get(position).getHarga()));

        final String getNamaBarang = myOuters.get(position).getNama_barang();

        holder.itemView.setOnClickListener(v -> {
            Intent goToHistoryOrderDetails = new Intent(context, MyHistoryDetailActivity.class);
            goToHistoryOrderDetails.putExtra("nama_barang", getNamaBarang);
            context.startActivity(goToHistoryOrderDetails);
        });
    }

    @Override
    public int getItemCount() {
        return myOuters.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView yOuterNama, yOuterUkuran, yOuterHarga;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            yOuterNama = itemView.findViewById(R.id.yOuterNama);
            yOuterUkuran = itemView.findViewById(R.id.yOuterUkuran);
            yOuterHarga = itemView.findViewById(R.id.yOuterHarga);
        }
    }
}
