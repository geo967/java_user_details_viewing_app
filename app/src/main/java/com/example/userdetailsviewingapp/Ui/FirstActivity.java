package com.example.userdetailsviewingapp.Ui;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userdetailsviewingapp.Adapters.RecyclerViewAdapter;
import com.example.userdetailsviewingapp.Adapters.RecyclerViewAdapterForLiveData;
import com.example.userdetailsviewingapp.Model.Address;
import com.example.userdetailsviewingapp.Model.Company;
import com.example.userdetailsviewingapp.Model.Geo;
import com.example.userdetailsviewingapp.Model.MainModel;
import com.example.userdetailsviewingapp.Network.RetrofitInstance;
import com.example.userdetailsviewingapp.R;
import com.example.userdetailsviewingapp.ViewModel.MyViewModel;

import java.util.ArrayList;
import java.util.List;


public class FirstActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerViewAdapterForLiveData recyclerViewAdapterForLiveData;
    boolean horizontalClick;
    boolean verticalClick;

    public static List<MainModel> list=new ArrayList<>();

    //connecting main activity with view model
    MyViewModel myViewModel;


    Button horizontalButton,verticalButton;
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        horizontalClick=savedInstanceState.getBoolean("horizontal_click");
        verticalClick=savedInstanceState.getBoolean("vertical_click");

        if(horizontalClick && !verticalClick){
             recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(FirstActivity.this);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            recyclerViewAdapterForLiveData = new RecyclerViewAdapterForLiveData(FirstActivity.this, list);
            recyclerView.setAdapter(recyclerViewAdapterForLiveData);

            myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
            myViewModel.getMainModelListObserver().observe(this, new Observer<List<MainModel>>() {
                @Override
                public void onChanged(List<MainModel> mainModels) {
                    if(mainModels!=null){
                        list=mainModels;
                        recyclerViewAdapterForLiveData.setModelList(mainModels);
                    }
                }
            });
            myViewModel.makeApiCall();
        }


        if(!horizontalClick && verticalClick){
             recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(FirstActivity.this);
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);

            recyclerViewAdapterForLiveData = new RecyclerViewAdapterForLiveData(FirstActivity.this, list);
            recyclerView.setAdapter(recyclerViewAdapterForLiveData);

            myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
            myViewModel.getMainModelListObserver().observe(this, new Observer<List<MainModel>>() {
                @Override
                public void onChanged(List<MainModel> mainModels) {
                    if(mainModels!=null){
                        list=mainModels;
                        recyclerViewAdapterForLiveData.setModelList(mainModels);
                    }
                }
            });
            myViewModel.makeApiCall();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity_layout);
        recyclerView=findViewById(R.id.recycler_view_id);
        horizontalButton=findViewById(R.id.button_id_for_horizontal_view);
        verticalButton=findViewById(R.id.button_id_for_vertical_view);

      // RetrofitInstance.getDataFromApi();
      // myViewModel.makeApiCall();

      /* recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(FirstActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerViewAdapterForLiveData = new RecyclerViewAdapterForLiveData(FirstActivity.this, list);
        recyclerView.setAdapter(recyclerViewAdapterForLiveData);

        myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.getMainModelListObserver().observe(this, new Observer<List<MainModel>>() {
            @Override
            public void onChanged(List<MainModel> mainModels) {
                if(mainModels!=null){
                    list=mainModels;
                    recyclerViewAdapterForLiveData.setModelList(mainModels);
                }
            }
        });
        myViewModel.makeApiCall();*/



     /*   if(horizontalClick && !verticalClick){
            String t = "Check connection";
            list.add(new MainModel(t, t, t, t, new Address(new Geo(t, t), "t", "t", "t"), new Company("t")));
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(FirstActivity.this);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            recyclerViewAdapterForLiveData = new RecyclerViewAdapterForLiveData(FirstActivity.this, list);
            recyclerView.setAdapter(recyclerViewAdapterForLiveData);

            myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
            myViewModel.getMainModelListObserver().observe(this, new Observer<List<MainModel>>() {
                @Override
                public void onChanged(List<MainModel> mainModels) {
                    if(mainModels!=null){
                        list=mainModels;
                        recyclerViewAdapterForLiveData.setModelList(mainModels);
                    }
                }
            });
            myViewModel.makeApiCall();
        }*/


        handleHorizontalButtonClick();

        handleVerticalButtonClick();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean("horizontal_click",horizontalClick);
        outState.putBoolean("vertical_click",verticalClick);
        super.onSaveInstanceState(outState);
    }



    private void handleVerticalButtonClick() {
        verticalButton.setOnClickListener(v -> {
            if(!horizontalClick) {
                horizontalClick=true;
                verticalClick=false;
               /* recyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(FirstActivity.this);
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);

                recyclerViewAdapter=new RecyclerViewAdapter(FirstActivity.this,list);
                recyclerView.setAdapter(recyclerViewAdapter);

                recyclerViewAdapterForLiveData = new RecyclerViewAdapterForLiveData(FirstActivity.this, list);
                recyclerView.setAdapter(recyclerViewAdapterForLiveData);*/

                recyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(FirstActivity.this);
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);

                recyclerViewAdapterForLiveData = new RecyclerViewAdapterForLiveData(FirstActivity.this, list);
                recyclerView.setAdapter(recyclerViewAdapterForLiveData);

                myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
                myViewModel.getMainModelListObserver().observe(this, new Observer<List<MainModel>>() {
                    @Override
                    public void onChanged(List<MainModel> mainModels) {
                        if(mainModels!=null){
                            list=mainModels;
                            recyclerViewAdapterForLiveData.setModelList(mainModels);
                        }
                    }
                });
                myViewModel.makeApiCall();


            }
        });

    }

    private void handleHorizontalButtonClick() {
        horizontalButton.setOnClickListener(v -> {
            if(!verticalClick){
                verticalClick=true;
                horizontalClick=false;
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager=new LinearLayoutManager(FirstActivity.this);
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);

            /*recyclerViewAdapter=new RecyclerViewAdapter(FirstActivity.this,list);
            recyclerView.setAdapter(recyclerViewAdapter);*/

                recyclerViewAdapterForLiveData = new RecyclerViewAdapterForLiveData(FirstActivity.this, list);
                recyclerView.setAdapter(recyclerViewAdapterForLiveData);
            }
        });
    }




}