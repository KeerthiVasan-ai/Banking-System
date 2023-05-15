package com.keerthi77459.bankmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Profile extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences1;
    TextView nameView,accountView,emailView,amount;
    Button transfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = getSharedPreferences("VIEWDATA",MODE_PRIVATE);
        sharedPreferences1 = getSharedPreferences("TRANSFERDATA",MODE_PRIVATE);
        nameView = findViewById(R.id.nameView);
        accountView = findViewById(R.id.accountView);
        emailView = findViewById(R.id.emailView);
        amount = findViewById(R.id.amount);
        transfer = findViewById(R.id.transfer);

        String name = sharedPreferences.getString("name",null);
        String account = sharedPreferences.getString("account",null);
        String email = sharedPreferences.getString("email",null);
        String balance = sharedPreferences.getString("amount",null);

        nameView.setText(name);
        accountView.setText("Account No:"+account);
        emailView.setText(email);
        amount.setText(balance);

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("sender",name);
                editor.putString("account",account);
                editor.putString("balance",balance);
                editor.commit();
                Intent intent = new Intent(Profile.this,transfer.class);
                startActivity(intent);
            }
        });
    }
}