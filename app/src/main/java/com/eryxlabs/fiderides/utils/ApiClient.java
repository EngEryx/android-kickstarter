package com.eryxlabs.fiderides.utils;

import android.content.Context;

import com.eryxlabs.fiderides.BuildConfig;
import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private Context ctx;

    private ApiClient(Context ctx) {
        this.ctx = ctx;
    }

    public static ApiClient with(Context ctx){
        return new ApiClient(ctx);
    }

    Retrofit getRetrofitClient(){

        return new Retrofit.Builder()
                .baseUrl("http://192.168.100.225/sims/two/shuleyetu/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    private OkHttpClient getOkHttpClient(){
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

            if (BuildConfig.DEBUG){
                httpClientBuilder.addInterceptor(new HttpLoggingInterceptor());
                if(ctx != null){
                    httpClientBuilder.addInterceptor(new ChuckInterceptor(ctx));
                }
            }
            httpClientBuilder.addNetworkInterceptor(new AuthServiceInterceptor(ctx));
         return httpClientBuilder.build();
    }

    public ApiService getApiService(){
       return  getRetrofitClient()
               .create(ApiService.class);
    }
}
