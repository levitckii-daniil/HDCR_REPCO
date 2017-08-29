package com.repandco.repco.registActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.repandco.repco.R;
import com.repandco.repco.constants.Keys;

public class RegistBusinessContacts extends AppCompatActivity {

    private EditText address;
    private EditText emailaddress;
    private EditText phone;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_business_contacts);

        intent = getIntent();

        address = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.phone);
        emailaddress = (EditText) findViewById(R.id.emailaddress);
    }

    public void next(View view) {
        if(intent!=null)
        {
            intent.putExtra(Keys.ADRESS, address.getText().toString());
            intent.putExtra(Keys.PHONE,phone.getText().toString());
            intent.putExtra(Keys.EMAIL, emailaddress.getText().toString());
            intent.setClass(this,RegistAuthInfo.class);
        }
        else intent = new Intent(this, RegistBusinessInfo.class);
        startActivity(intent);
    }
}
