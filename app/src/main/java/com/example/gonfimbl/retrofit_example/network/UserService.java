package com.example.gonfimbl.retrofit_example.network;

import com.example.gonfimbl.retrofit_example.models.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("/get-user")
    Call<User> fetchUser();
}
