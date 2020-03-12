package com.example.project;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button submit;
    RadioButton radlandlord,radtenant;
    TextView textView,textView2,regland,regten;
    EditText edemail,edpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        submit=findViewById(R.id.button);
        regland=findViewById(R.id.textView8);
        regten=findViewById(R.id.textView9);
        radlandlord=findViewById(R.id.radioButton);
        radtenant=findViewById(R.id.radioButton2);
        edemail=findViewById(R.id.editText2);
        edpass=findViewById(R.id.editText3);

        regland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LandlordReg.class);
                startActivity(intent);
            }
        });
        regten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TenantReg.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Nav_tenant.class);
                startActivity(intent);

            }
        });
    }
}
