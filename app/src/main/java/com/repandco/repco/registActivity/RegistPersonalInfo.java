package com.repandco.repco.registActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.repandco.repco.R;
import com.repandco.repco.constants.Keys;
import com.repandco.repco.constants.Toasts;
import com.repandco.repco.constants.Values;

public class RegistPersonalInfo extends AppCompatActivity {

    private EditText phone;
    private EditText birthdate;
    private RadioGroup radioGroup;
    private Intent intent;
    private Toolbar postTolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_personal_info);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        postTolbar = (Toolbar) findViewById(R.id.postTolbar);
        postTolbar.setTitle("Register auth info:");
        setSupportActionBar(postTolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        phone = (EditText) findViewById(R.id.phone);
        birthdate = (EditText) findViewById(R.id.birthdate);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        intent = getIntent();
    }

    public void next(View view) {
        String birthdateStr = birthdate.getText().toString();
        String phoneStr = phone.getText().toString();
        if(TextUtils.isEmpty(phoneStr)){
            phone.setError("Phone is empty!");
            phone.requestFocus();
            return;
        }
        else {
            if(phoneStr.length() < 4){
                phone.setError("Phone is wrong!");
                phone.requestFocus();
                return;
            }
        }
        if(TextUtils.isEmpty(birthdateStr)){
            birthdate.setError("Birthrate is empty!");
            birthdate.requestFocus();
            return;
        }
        if(intent!=null)
        {
            long date = Integer.valueOf(birthdateStr);
            intent.putExtra(Keys.PHONE, phoneStr);
            intent.putExtra(Keys.BIRTHDAY,date);
            switch (radioGroup.getCheckedRadioButtonId())
            {
                case R.id.male:
                    intent.putExtra(Keys.GENDER, Values.GENDERS.MALE);
                    break;
                case R.id.female:
                    intent.putExtra(Keys.GENDER, Values.GENDERS.FEMALE);
                    break;
                default:
                    Toast.makeText(this, Toasts.ERR_GENDER, Toast.LENGTH_SHORT).show();
            }
            intent.setClass(this,RegistAuthInfo.class);
        }
        else intent = new Intent(this, RegistUserInfo.class);

        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
