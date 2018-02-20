package com.bradychiu.sc2ladder.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.bradychiu.sc2ladder.api.AdapterFactory;
import com.squareup.moshi.Moshi;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.Nullable;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class RetrofitUtil {

    // TODO: correct way to do nullable params?
    // TODO: why use okhttp

    public static Retrofit getRetrofit(Context context) {
        return getRetrofit(context, null);
    }

    public static Retrofit getRetrofit(Context context, URL baseUrl) {

        SharedPrefsService sharedPrefsService = SharedPrefsService.getInstance(context);
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();

        baseUrl = baseUrl != null ? baseUrl : new HttpUrl.Builder()
                .scheme("https")
                .host(sharedPrefsService.getRegion() + ".api.battle.net")
                .build()
                .url();

        final Moshi moshi = new Moshi.Builder()
                .add(AdapterFactory.create())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl.toString())
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        return retrofit;

    }
}
