package com.keerthi77459.bankmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {

    private Context context;
    private ArrayList name_id,ano_id,email_id,amount;
    SharedPreferences sharedPreferences;

    public Myadapter(Context context, ArrayList name_id, ArrayList ano_id, ArrayList email_id , ArrayList amount) {
        this.context = context;
        this.name_id = name_id;
        this.ano_id = ano_id;
        this.email_id = email_id;
        this.amount = amount;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.viewName.setText(String.valueOf(name_id.get(position)));
            holder.viewAno.setText(String.valueOf(ano_id.get(position)));
            holder.viewEmail.setText(String.valueOf(email_id.get(position)));
            holder.viewBalance.setText(String.valueOf(amount.get(position)));
    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView viewName , viewAno , viewEmail,viewBalance;
        Button account;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            viewName = itemView.findViewById(R.id.viewName);
            viewAno = itemView.findViewById(R.id.viewAccount);
            viewEmail = itemView.findViewById(R.id.viewEmail);
            viewBalance = itemView.findViewById(R.id.viewBalance);
            viewBalance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPreferences = context.getSharedPreferences("VIEWDATA",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name",viewName.getText().toString());
                    editor.putString("account",viewAno.getText().toString());
                    editor.putString("email",viewEmail.getText().toString());
                    editor.putString("amount",viewBalance.getText().toString()) ;
                    editor.commit();
                    Intent intent = new Intent(itemView.getContext(),Profile.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
   }
}
