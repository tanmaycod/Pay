package com.example.paymentgate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button b1;
    String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    int GOOGLE_PAY_REQUEST_CODE = 123;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==123 && resultCode== RESULT_OK && data.getData() != null)
        {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.bt1);
        e1=findViewById(R.id.et1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amt=e1.getText().toString();
                if (amt.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }
                else {
                    Uri uri =
                            new Uri.Builder()
                                    .scheme("upi")
                                    .authority("pay")
                                    .appendQueryParameter("pa", "98")//enter upi id
                                    .appendQueryParameter("pn", "san")
                                    .appendQueryParameter("mc", "")
                                    .appendQueryParameter("tr", "23677")
                                    .appendQueryParameter("tn", "your-transaction-note")
                                    .appendQueryParameter("am", amt)
                                    .appendQueryParameter("cu", "INR")
//                                    .appendQueryParameter("url", "your-transaction-url")
                                    .build();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(uri);
                    Intent chooser=Intent.createChooser(intent,"Pay With");
                    startActivityForResult(chooser,GOOGLE_PAY_REQUEST_CODE);
                }
            }
        });
    }
}