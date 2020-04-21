package com.example.project.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.project.MainActivity;
import com.example.project.Nav_tenant;
import com.example.project.R;
import com.example.project.model.User;
import com.example.project.model.UserTenent;
import com.example.project.services.MyInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private MyInterface loginFromActivityListener;
    private TextView registerTV,registerTV2;
    RadioButton radlandlord,radtenant;
    RadioGroup radioGroup;
    private EditText emailInput, passwordInput;
    private Button loginBtn;
    static int flag ;

    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        // for login
        emailInput = view.findViewById(R.id.editText2);
        passwordInput = view.findViewById(R.id.editText3);

        // RadioButton for landlord
        radlandlord=view.findViewById(R.id.radioButton);
        // RadioButton for tanent
        radtenant=view.findViewById(R.id.radioButton2);

        radioGroup = view.findViewById(R.id.landtent);

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

        loginBtn = view.findViewById(R.id.button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag!=0)
                {
                    switch (flag) {
                        case 1:
                            Toast.makeText(getContext(), "Landlord", Toast.LENGTH_SHORT).show();
                            loginUserL();
                            flag = 0;
                            radioGroup.clearCheck();
                            break;
                        case 2:
                            Toast.makeText(getContext(), "Tanent", Toast.LENGTH_SHORT).show();
                            loginUserT();
                            flag = 0;
                            radioGroup.clearCheck();
                            break;
                    }
                }
                else
                {
                    Toast.makeText(getContext(), "Kindly Select Role", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerTV = view.findViewById(R.id.textView9);
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFromActivityListener.register();
            }
        });

        registerTV2 = view.findViewById(R.id.textView8);
        registerTV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFromActivityListener.registerL();

            }
        });
        return view;
    } //ending onCreateView

    private void loginUserT() {
        String Email = emailInput.getText().toString();
        String Password = passwordInput.getText().toString();

        if (TextUtils.isEmpty(Email)){
            MainActivity.appPreference.showToast("Your email is required.");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            MainActivity.appPreference.showToast("Invalid email");
        } else if (TextUtils.isEmpty(Password)){
            MainActivity.appPreference.showToast("Password required");
        } else if (Password.length() < 6){
            MainActivity.appPreference.showToast("Password  may be at least 6 characters long.");
        } else {
            Call<UserTenent> userCall = MainActivity.serviceApiT.doLogin(Email, Password);
            userCall.enqueue(new Callback<UserTenent>() {
                @Override
                public void onResponse(Call<UserTenent> call, Response<UserTenent> response) {
                    if (response.body().getResponse().equals("data")){
                        MainActivity.appPreference.setLoginStatus(true); // set login status in sharedPreference
                        loginFromActivityListener.login(
                                response.body().getName(),
                                response.body().getEmail(),
                                response.body().getCreatedAt());
                        Intent intent=new Intent(getContext(), Nav_tenant.class);
                        startActivity(intent);
                    } else if (response.body().getResponse().equals("login_failed")){
                        MainActivity.appPreference.showToast("Error. Login Failed");
                        emailInput.setText("");
                        passwordInput.setText("");
                    }
                }
                @Override
                public void onFailure(Call<UserTenent> call, Throwable t) {
                }
            });
        }
    }

    private void loginUserL() {
        String Email = emailInput.getText().toString();
        String Password = passwordInput.getText().toString();

        if (TextUtils.isEmpty(Email)){
            MainActivity.appPreference.showToast("Your email is required.");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            MainActivity.appPreference.showToast("Invalid email");
        } else if (TextUtils.isEmpty(Password)){
            MainActivity.appPreference.showToast("Password required");
        } else if (Password.length() < 6){
            MainActivity.appPreference.showToast("Password  may be at least 6 characters long.");
        } else {
            Call<User> userCall = MainActivity.serviceApiL.doLogin(Email, Password);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body().getResponse().equals("data")){
                        MainActivity.appPreference.setLoginStatus(true); // set login status in sharedPreference
                        loginFromActivityListener.login(
                                response.body().getName(),
                                response.body().getEmail(),
                                response.body().getCreatedAt());
                        Intent intent=new Intent(getContext(), Nav_tenant.class);
                        startActivity(intent);
                    } else if (response.body().getResponse().equals("login_failed")){
                        MainActivity.appPreference.showToast("Error. Login Failed");
                        emailInput.setText("");
                        passwordInput.setText("");
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                }
            });
        }
    } //ending loginUser()

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFromActivityListener = (MyInterface) activity;
    }

}

