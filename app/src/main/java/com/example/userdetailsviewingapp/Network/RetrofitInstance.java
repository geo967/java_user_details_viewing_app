package com.example.userdetailsviewingapp.Network;

import android.annotation.SuppressLint;

import com.example.userdetailsviewingapp.Model.Address;
import com.example.userdetailsviewingapp.Model.Company;
import com.example.userdetailsviewingapp.Model.Geo;
import com.example.userdetailsviewingapp.Model.MainModel;
import com.example.userdetailsviewingapp.Ui.FirstActivity;

import java.security.cert.CertificateException;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class RetrofitInstance {



    public static void getDataFromApi() {
        String url = "https://jsonplaceholder.typicode.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient().build())
                .build();

        MyApi myApi = retrofit.create(MyApi.class);
        Call<List<MainModel>> call = myApi.getModels();

        call.enqueue(new Callback<List<MainModel>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<MainModel>> call, Response<List<MainModel>> response) {
                if (response.code() != 200) {
                    String t = "Check connection";
                    FirstActivity.list.add(new MainModel(t, t, t, t, new Address(new Geo(t, t), "t", "t", "t"), new Company("t")));
                    return;
                }
                List<MainModel> data = response.body();
                for (int i = 0; i < data.size(); i++) {
                    FirstActivity.list.add(new MainModel(data.get(i).getName(), data.get(i).getUsername(), data.get(i).getEmail(), data.get(i).getPhone()
                            , new Address(new Geo(data.get(i).getAddress().getGeo().getLat(), data.get(i).getAddress().getGeo().getLng())
                            , data.get(i).getAddress().getStreet(), data.get(i).getAddress().getCity(), data.get(i).getAddress().getZipcode()), new Company(data.get(i).getCompany().getName())));
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<MainModel>> call, Throwable t) {
                //   list.add(new Model(t.getMessage(),t.getMessage(),t.getMessage(),t.getMessage()));
            }
        });
    }


    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
