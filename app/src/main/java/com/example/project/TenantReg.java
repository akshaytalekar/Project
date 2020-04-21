package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class TenantReg extends AppCompatActivity {
    EditText edname,edmob,edemail,edpass,edconpass;
    Button submit;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_reg);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        edname=findViewById(R.id.tenenteditName);
        edmob=findViewById(R.id.tenenteditMobile);
        edemail=findViewById(R.id.tenenteditEmail);
        edpass=findViewById(R.id.tenenteditpassword);
        edconpass=findViewById(R.id.tenenteditConfirm);
        submit=findViewById(R.id.tenentSubmit);

        awesomeValidation.addValidation(this, R.id.tenenteditName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",
                R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.tenenteditEmail, Patterns.EMAIL_ADDRESS,
                R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.tenenteditpassword, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.tenenteditMobile, "^[2-9]{2}[0-9]{8}$",
                R.string.mobileerror);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
                /*Intent intent=new Intent(getApplicationContext(),MainActivity.class);
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
