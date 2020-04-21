package com.example.project.services;




import com.example.project.model.UserTenent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApiT {
    @GET("register-ten.php")
    Call<UserTenent> doRegistration(@Query("name") String name, @Query("email") String email, @Query("phone") String phone, @Query("password") String password);


    @GET("login-tenent.php")
    Call<UserTenent> doLogin(@Query("email") String email, @Query("password") String password);


    /*@GET("register.php")
    Call<User> doRegistrationT(@Query("name") String name, @Query("email") String email, @Query("phone") String phone, @Query("password") String password);*/
    }
