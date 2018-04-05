package com.example.asepr.crudretro.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by ASEPR on 3/22/2018.
 */

public class ApiClient {
    public static final String BASE_URL = "http://io.nowdb.net/v2/";
    public static final String BASE_URL_IMG = "http://io.nowdb.net/files/";
    private static Retrofit retrofit = null;
    private static Retrofit retrofit2 = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientImg() {
        if (retrofit2==null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL_IMG)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }
}