package com.cub.librarymanagement;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.regex.Pattern;

public class CreateEventActivity extends AppCompatActivity {
    String need_time;
    private Button btnCreate,cancel;
    EditText name,place,capacity,budget,email,phone,detail;
    RadioGroup radioGroup;
    RadioButton type;
    TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_information_layout);

       Intent i =getIntent();
     String key = i.getStringExtra("EVENT_ID");
      if(key!=null && TextUtils.isEmpty(key)){
          String value = Util.getInstance().getValueByKey(this,key);
         String[] values = value.split("");
        }

        name = findViewById(R.id.etName);
        place = findViewById(R.id.etPlace);
        capacity = findViewById(R.id.etCapacity);
        budget = findViewById(R.id.etBudget);
        email = findViewById(R.id.etEmail);
        phone = findViewById(R.id.etPhone);
        detail = findViewById(R.id.etDescription);
        btnCreate = findViewById(R.id.btnSave);
        timePicker =findViewById(R.id.time);
        timePicker.setIs24HourView(true);
        radioGroup = findViewById(R.id.type);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                need_time = timePicker.getHour() + ":" + timePicker.getMinute();

                if (TextUtils.isEmpty(name.getText().toString())) {
                    name.setError("Name Required");

                    return;
                }
                if (TextUtils.isEmpty(place.getText().toString())) {
                    place.setError("place Required");

                    return;
                }
                if (TextUtils.isEmpty(capacity.getText().toString())) {
                    capacity.setError("Capacity Required");

                    return;
                }
                if (TextUtils.isEmpty(budget.getText().toString())) {
                    budget.setError("Budget Required");

                    return;
                }
                String email_1 = email.getText().toString().trim();
                if (TextUtils.isEmpty(email_1)) {
                    email.setError("Valid Email Required");

                    return;
                }
                if (isValid(email_1)){
                    email.setError("Email Required");
                    return;
                }
                if (TextUtils.isEmpty(phone.getText().toString())) {
                    phone.setError("Phone Required");

                    return;
                }
                if (phone.getText().toString().length() < 11) {
                    phone.setError("Enter 11 digit number");
                    return;
                }

                save(name.getText().toString(),need_time);

                    Log.d("Name = ", name.getText().toString());
                    Log.d("Place = ", place.getText().toString());
                    Log.d("Capacity = ", capacity.getText().toString());
                    Log.d("Budget = ", budget.getText().toString());
                    Log.d("Email = ", email.getText().toString());
                    Log.d("Phone = ", phone.getText().toString());
                    Log.d("Detail = ", detail.getText().toString());
                    Log.d("Time = ", need_time);




                Intent intent = new Intent(CreateEventActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
       cancel =findViewById(R.id.btnCancel) ;
       cancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(CreateEventActivity.this, MainActivity.class);
               startActivity(intent);
           }
       });
    }

    public boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    private void save(String a,String b){
        String key =a;
        String value =b;
        Util.getInstance().setKeyValue(this,key,value);
    }
}