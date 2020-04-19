package com.example.project;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button submit;
    RadioButton radlandlord,radtenant;
    TextView textView,textView2,regland,regten;
    EditText edemail,edpass;
    RadioGroup radioGroup;
    static int flag ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        submit=findViewById(R.id.button);
        regland=findViewById(R.id.textView8);
        regten=findViewById(R.id.textView9);
        // RadioButton for landlord
        radlandlord=findViewById(R.id.radioButton);
        // RadioButton for tanent
        radtenant=findViewById(R.id.radioButton2);
        edemail=findViewById(R.id.editText2);
        edpass=findViewById(R.id.editText3);
        radioGroup = findViewById(R.id.landtent);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               if(radlandlord.isChecked())
               {
                   flag =1;
               }
               else if (radtenant.isChecked())
               {
                   flag=2;
               }
            }
        });
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
              if(flag!=0)
              {
                 switch (flag) {
                     case 1:
                         Toast.makeText(MainActivity.this, "Landlord", Toast.LENGTH_SHORT).show();
                         flag = 0;
                         radioGroup.clearCheck();
                         break;
                     case 2:
                         Toast.makeText(MainActivity.this, "Tanent", Toast.LENGTH_SHORT).show();
                         flag = 0;
                         radioGroup.clearCheck();
                         break;
                 }
              }
              else
              {
                  Toast.makeText(MainActivity.this, "Kindly Select Role", Toast.LENGTH_SHORT).show();
              }

                //  Intent intent=new Intent(MainActivity.this,Nav_tenant.class);
              //  startActivity(intent);

            }
        });
    }
}
