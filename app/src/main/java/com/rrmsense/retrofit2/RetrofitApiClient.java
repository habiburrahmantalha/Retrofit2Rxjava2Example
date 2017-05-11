package com.rrmsense.retrofit2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Talha on 5/11/2017.
 */

class RetrofitApiClient {

    private static final String BASE_URL = "http://www.rrmelectronics.com/";
    private static Retrofit retrofit = null;



    private Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
            new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create()).setLenient()
                    .create());


    private RetrofitApiClient() {} // So that nobody can create an object with constructor

    static synchronized Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(gsonConverterFactory)
                    .build();
        }
        return retrofit;
    }

}