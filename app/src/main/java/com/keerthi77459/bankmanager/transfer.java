package com.keerthi77459.bankmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class transfer extends AppCompatActivity {

    TextView sender,accountNumber,amount,receiver;
    Button proceed;
    DBhelper DB;
    SharedPreferences sharedPreferences1;
    ArrayList<String> names;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

//        receiver = findViewById(R.id.receiver);

        DB = new DBhelper(this);
        names = new ArrayList<>();
        displayNames();

        sharedPreferences1 = getSharedPreferences("TRANSFERDATA", MODE_PRIVATE);

//        adapter = new ArrayAdapter<String>(this,R.layout.text,names);
//        autoCompleteTextView.setAdapter(adapter);

//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                SharedPreferences.Editor editor = sharedPreferences1.edit();
//                editor.putString("item",item);
//                editor.commit();
//            }
//        });

        sender = findViewById(R.id.sender);
        String Sender = sharedPreferences1.getString("sender", null);
        String senderText = Sender + "'s Money Transfer";
        sender.setText(senderText);

        receiver = findViewById(R.id.receiver);

        accountNumber = findViewById(R.id.accountNumber);
        String account = sharedPreferences1.getString("account", null);
        String accountText = "AC : " + account;
        accountNumber.setText(accountText);


        amount = findViewById(R.id.amountValue);
        String senderBalance = sharedPreferences1.getString("balance", null);

        proceed = findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Receiver = receiver.getText().toString();
                receiver(Receiver);
                String receiverBalance = sharedPreferences1.getString("receiverAmount", null);
                if (Sender != Receiver) {
                    String transactionAmount = amount.getText().toString();
                    if (!transactionAmount.isEmpty()) {
                        Boolean message1 = DB.transactions(Sender, Receiver, transactionAmount);

                        long senderbalance = Long.parseLong(senderBalance);
                        long receiverbalance = Long.parseLong(receiverBalance);
                        System.out.println(receiverbalance);
                        long transferamount = Long.parseLong(transactionAmount);
                        System.out.println(transferamount);
                        long updateamount1 = senderbalance - transferamount;
                        long updateamount2 = receiverbalance + transferamount;
                        System.out.println(updateamount2);
                        Boolean message2 = DB.updatedata(Sender, String.valueOf(updateamount1));
                        Boolean message3 = DB.updatedata(Receiver, String.valueOf(updateamount2));

                        if (message1 && message2 && message3) {
                            Toast.makeText(transfer.this, "Money Transfered", Toast.LENGTH_SHORT).show();
                            startactivitiy();
                        } else {
                            Toast.makeText(transfer.this, "Transfer Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(transfer.this,"Payment Must be At least 1",Toast.LENGTH_SHORT).show();

                    }
                } else{
                    Toast.makeText(transfer.this,"Receiver Cannot Be Same as Sender",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void startactivitiy() {
        Intent intent = new Intent(transfer.this,customer.class);
        startActivity(intent);
    }

    private void displayNames() {
        Cursor cursor = DB.returnName();
        if(cursor.getCount() == 0){
            Toast.makeText(transfer.this,"No data Present",Toast.LENGTH_SHORT).show();
            return;
        } else {
            while(cursor.moveToNext()){
                names.add(cursor.getString(0));
            }
        }
    }

    private void receiver(String Receiver) {
        String condition = "name = '"+Receiver+"'";
        Cursor cursor = DB.getName(condition);
        if (cursor.moveToFirst()){
            do{
                String value = cursor.getString(cursor.getColumnIndexOrThrow("amount"));
                System.out.println(value);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("receiverAmount",value);
                editor.commit();
            } while (cursor.moveToNext());
        }
        cursor.close();
        DB.close();
    }
}
