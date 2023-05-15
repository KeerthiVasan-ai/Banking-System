package com.keerthi77459.bankmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insertData extends AppCompatActivity {

    EditText name,ano,email;
    Button insertBtn;
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        DB = new DBhelper(this);

        insertBtn = findViewById(R.id.insertBtn);

        name = findViewById(R.id.insertName);
        ano = findViewById(R.id.insertAccount);
        email = findViewById(R.id.insertEmail);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String anoTXT = ano.getText().toString();
                String emailTXT = email.getText().toString();

                if (nameTXT.isEmpty() || anoTXT.isEmpty() || emailTXT.isEmpty()) {
                    Toast.makeText(insertData.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {

                    Boolean Checkdata = DB.insertdata(nameTXT, anoTXT, emailTXT);

                    if (Checkdata == true) {
                        Toast.makeText(insertData.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(insertData.this,customer.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(insertData.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                 }
            }
        });
    }
}