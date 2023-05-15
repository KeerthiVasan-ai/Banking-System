package com.keerthi77459.bankmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class customer extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton insert;
    ArrayList<String> name,account,email,amount;
    DBhelper DB;
    Myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

//        insert = findViewById(R.id.insert);

        DB = new DBhelper(this);

        name = new ArrayList<>();
        account = new ArrayList<>();
        email = new ArrayList<>();
        amount = new ArrayList<>();

        recyclerView = findViewById(R.id.recycle);
        myadapter = new Myadapter(this,name,account,email,amount);

        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();

//        insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(customer.this, insertData.class);
//                startActivity(intent);
//            }
//        });
    }


    private void displayData() {
        Cursor cursor = DB.getdata();
        if(cursor.getCount() == 0){
            Toast.makeText(customer.this,"No data Present",Toast.LENGTH_SHORT).show();
            return;
        } else {
            while(cursor.moveToNext()){
                name.add(cursor.getString(0));
                account.add(cursor.getString(1));
                email.add(cursor.getString(2));
                amount.add(cursor.getString(3));
            }
        }
    }
}