package com.example.userdetailsviewingapp.Network;

import com.example.userdetailsviewingapp.Model.MainModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {
    @GET("users")
    Call<List<MainModel>> getModels();
}
