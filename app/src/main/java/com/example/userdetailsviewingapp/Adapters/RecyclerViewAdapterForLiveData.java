package com.example.userdetailsviewingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userdetailsviewingapp.Model.MainModel;
import com.example.userdetailsviewingapp.R;

import java.util.List;

public class RecyclerViewAdapterForLiveData extends RecyclerView.Adapter<RecyclerViewAdapterForLiveData.MyHolder> {

    Context context;
    List<MainModel> list;

    public RecyclerViewAdapterForLiveData(Context context, List<MainModel> list) {
        this.context = context;
        this.list = list;
    }

    public void setModelList(List<MainModel> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapterForLiveData.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_design_for_recyclerview_in_first_activity,parent,false);
        return new RecyclerViewAdapterForLiveData.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterForLiveData.MyHolder holder, int position) {
        holder.name.setText(String.format("Name:%s", list.get(position).getName()));
        holder.username.setText(String.format("username:%s", list.get(position).getUsername()));
        holder.email.setText(String.format("email:%s", list.get(position).getEmail()));
        holder.phone.setText(String.format("phone:%s", list.get(position).getPhone()));
        holder.address.setText(String.format("city:%s \nstreet:%s \nzip:%s \nlat:%s  lng:%s", list.get(position).getAddress().getCity(), list.get(position).getAddress().getStreet(), list.get(position).getAddress().getZipcode(), list.get(position).getAddress().getGeo().getLat(), list.get(position).getAddress().getGeo().getLng()));
        holder.company.setText(String.format("company:%s", list.get(position).getCompany().getName()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{
        TextView name,username,email,phone,address,company;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name_id_in_card);
            username=itemView.findViewById(R.id.username_id_in_card);
            email=itemView.findViewById(R.id.email_id_in_card);
            phone=itemView.findViewById(R.id.phone_id_in_card);
            address=itemView.findViewById(R.id.address_id_in_card);
            company=itemView.findViewById(R.id.company_id_in_card);
        }
    }
}
