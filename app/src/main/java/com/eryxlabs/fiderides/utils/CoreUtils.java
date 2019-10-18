package com.eryxlabs.fiderides.utils;


import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;

import com.eryxlabs.fiderides.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoreUtils {


    private static Retrofit retrofit = null;
    private static Retrofit auth_retrofit = null;

    public static String base_url= BuildConfig.BASE_URL;

    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(CoreUtils.base_url)
                    .addConverterFactory(GsonConverterFactory.create(CoreUtils.gson()))
                    .callbackExecutor(Executors.newSingleThreadExecutor());
                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                    NoCacheInterceptor noCacheInterceptor = new NoCacheInterceptor();
                    httpClient.addInterceptor(logging);
                    httpClient.addInterceptor(noCacheInterceptor);
                    builder.client(httpClient.build());
                    retrofit = builder.build();
        }
        return retrofit;
    }


    public static Gson gson(){

        return  new GsonBuilder()
                .serializeNulls()
                .create();
    }

    public static Retrofit getAuthRetrofitClient(String token) {
        if (auth_retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            Retrofit.Builder builder = new Retrofit.Builder()
                            .baseUrl(CoreUtils.base_url)
                            .addConverterFactory(GsonConverterFactory.create(CoreUtils.gson()))
                            .callbackExecutor(Executors.newSingleThreadExecutor());
                             //build http interceptor with tokens
                            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(token);
                            NoCacheInterceptor noCacheInterceptor = new NoCacheInterceptor();
                            httpClient.addInterceptor(interceptor);
                            httpClient.addInterceptor(logging);
                            httpClient.addInterceptor(noCacheInterceptor);
                            builder.client(httpClient.build());

            auth_retrofit=builder.build();
        }
        return auth_retrofit;
    }


    public static String dateTimeFormatter(String enteredDateTime){
        String formattedDate="";

        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);

        SimpleDateFormat formatter5=new SimpleDateFormat("E, MMM d yyyy HH:mm a");

        try{

            Date dateTime=formatter2.parse(enteredDateTime);

            formattedDate=formatter5.format(dateTime);

        }catch (ParseException e){

            e.printStackTrace();
        }


        return  formattedDate;
    }

}
