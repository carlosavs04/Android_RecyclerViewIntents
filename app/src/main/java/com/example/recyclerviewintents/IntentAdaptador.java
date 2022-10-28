package com.example.recyclerviewintents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recyclerviewintents.Modelos.Intent;
import java.util.List;

public class IntentAdaptador extends RecyclerView.Adapter<IntentAdaptador.viewholder> {
    List<Intent> LI;

    public IntentAdaptador(List<Intent> LI) {
        this.LI = LI;
    }

    @NonNull
    @Override
    public IntentAdaptador.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IntentAdaptador.viewholder holder, int position) {
        holder.setData(LI.get(position));
    }

    @Override
    public int getItemCount() {
        return LI.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtIntent;
        Intent item;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtIntent = itemView.findViewById(R.id.txtIntent);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            view.getContext().startActivity(item.getIntento());
        }

        public void setData(Intent item) {
            this.item = item;
            txtIntent.setText(item.getNombreIntent());
        }
    }
}
