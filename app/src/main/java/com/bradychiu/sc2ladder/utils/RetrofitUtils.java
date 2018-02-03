package com.bradychiu.sc2ladder.utils;

import android.content.Context;
import com.bradychiu.sc2ladder.api.AdapterFactory;
import com.squareup.moshi.Moshi;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;
import java.net.URL;

public class RetrofitUtils {

    public static Retrofit getRetrofit(Context appContext) {

        final SharedPrefsService sharedPrefsService = SharedPrefsService.getInstance(appContext);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();

                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("locale", sharedPrefsService.getLocale())
                                .addQueryParameter("apikey", sharedPrefsService.getApiKey())
                                .build();

                        Request.Builder requestBuilder = original.newBuilder()
                                .url(url);

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();

        URL bnetUrl = new HttpUrl.Builder()
                .scheme("https")
                .host(sharedPrefsService.getRegion() + ".api.battle.net")
                .build()
                .url();

        Moshi moshi = new Moshi.Builder()
                .add(AdapterFactory.create())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(bnetUrl.toString())
                .client(httpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        return retrofit;
    }
}
