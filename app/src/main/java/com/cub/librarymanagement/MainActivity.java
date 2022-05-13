package com.cub.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnCreate;
    EditText  name,place,capacity,budget,email,phone,detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
        System.out.println("@MainActivity-onCreate()");

        String key = "hh";
        if(key!=null && TextUtils.isEmpty(key)){
            String value = Util.getInstance().getValueByKey(this,key);
            String[] values = value.split("");
            Log.d("SQL", value);
        }

        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateEventActivity.class);
                startActivity(intent);

            }
        });
    }
    public void onStart(){
        super.onStart();
        String key = "hh";
        if(key!=null && TextUtils.isEmpty(key)){
            String value = Util.getInstance().getValueByKey(this,key);
            String[] values = value.split("");
            Log.d("SQL", value);
        }
    }
}