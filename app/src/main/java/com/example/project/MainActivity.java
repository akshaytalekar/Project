package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;


import com.example.project.R;
import com.example.project.constants.Constant;
import com.example.project.extras.AppPreference;
import com.example.project.fragment.LandRegistrationFragment;
import com.example.project.fragment.LoginFragment;
import com.example.project.fragment.ProfileFragment;
import com.example.project.fragment.TenRegistrationFragment;
import com.example.project.services.MyInterface;
import com.example.project.services.RetrofitClient;
import com.example.project.services.ServiceApiL;
import com.example.project.services.ServiceApiT;
import com.example.project.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements MyInterface {
    public static AppPreference appPreference;
    public static String c_date;
    FrameLayout container_layout;
    public static ServiceApiL serviceApiL;
    public static ServiceApiT serviceApiT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container_layout = findViewById(R.id.fragment_container);
        appPreference = new AppPreference(this);

        serviceApiL = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApiL.class);
        serviceApiT = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApiT.class);


        if (container_layout != null) {
            if (savedInstanceState != null) {
                return;
            }
            //check login status from sharedPreference
            if (appPreference.getLoginStatus()) {
                //when true
                /*getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new ProfileFragment())
                        .commit();*/
                Intent intent=new Intent(getApplicationContext(), Nav_tenant.class);
                startActivity(intent);
            } else {
                // when get false
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new LoginFragment())
                        .commit();
            }
        }
    }

    @Override
    public void register() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new TenRegistrationFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void registerL() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LandRegistrationFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void login(String name, String email, String created_at) {
        appPreference.setDisplayName(name);
        appPreference.setDisplayEmail(email);
        appPreference.setCreDate(created_at);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();

    }

    @Override
    public void logout() {
        appPreference.setLoginStatus(false);
        appPreference.setDisplayName("Name");
        appPreference.setDisplayEmail("Email");
        appPreference.setCreDate("DATE");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();

    }
}
