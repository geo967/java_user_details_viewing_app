package com.example.userdetailsviewingapp.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userdetailsviewingapp.Model.MainModel;
import com.example.userdetailsviewingapp.Network.MyApi;
import com.example.userdetailsviewingapp.Network.RetrofitCall;
import com.example.userdetailsviewingapp.Network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

public class MyViewModel extends ViewModel {


    private final MutableLiveData<List<MainModel>> list;

    public MyViewModel(){
        list=new MutableLiveData<>();
    }
    public MutableLiveData<List<MainModel>> getMainModelListObserver(){
        return list;
    }

    public void makeApiCall(){
        MyApi myApi= RetrofitCall.getRetrofitClient().create(MyApi.class);
        Call<List<MainModel>> call=myApi.getModels();
        call.enqueue(new Callback<List<MainModel>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<MainModel>> call, Response<List<MainModel>> response) {
                list.postValue(response.body());
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<MainModel>> call, Throwable t) {
                list.postValue(null);
            }
        });
    }



}
