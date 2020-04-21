package com.example.project;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class LandlordReg extends AppCompatActivity {
    EditText edname,edmob,edemail,edpass,edconpass;
    Button submit;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlord_reg);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        edname=findViewById(R.id.landeditName);
        edmob=findViewById(R.id.landeditMobile);
        edemail=findViewById(R.id.landeditEmail);
        edpass=findViewById(R.id.landeditpassword);
        edconpass=findViewById(R.id.landeditConfirm);
        submit=findViewById(R.id.landlordSubmit);

        awesomeValidation.addValidation(this, R.id.landeditName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",
                R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.landeditEmail, Patterns.EMAIL_ADDRESS,
                R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.landeditpassword, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.landeditMobile, "^[2-9]{2}[0-9]{8}$",
                R.string.mobileerror);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    submitForm();

               /* Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);*/
            }
        });
    }
    private void submitForm() {
        if (awesomeValidation.validate()) {
            Toast.makeText(this, "Registration Successfull", Toast.LENGTH_LONG).show();
//process the data further
        }
    }
}
