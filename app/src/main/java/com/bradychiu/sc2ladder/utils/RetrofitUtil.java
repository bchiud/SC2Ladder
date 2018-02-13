package com.bradychiu.sc2ladder.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.bradychiu.sc2ladder.api.AdapterFactory;
import com.squareup.moshi.Moshi;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;
import java.net.URL;

public class RetrofitUtil {

    private static RetrofitUtil singletonRetrofitUtilInstance;
    private static Retrofit singletonRetrofitInstance;

    private RetrofitUtil(Context appContext) {

        final SharedPrefsService sharedPrefsService = SharedPrefsService.getInstance(appContext);

        final OkHttpClient httpClient = new OkHttpClient.Builder()
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

        final URL bnetUrl = new HttpUrl.Builder()
                .scheme("https")
                .host(sharedPrefsService.getRegion() + ".api.battle.net")
                .build()
                .url();

        final Moshi moshi = new Moshi.Builder()
                .add(AdapterFactory.create())
                .build();

        singletonRetrofitInstance = new Retrofit.Builder()
                .baseUrl(bnetUrl.toString())
                .client(httpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
    }

    public static Retrofit getRetrofit(Context context) {
        if(singletonRetrofitUtilInstance == null) singletonRetrofitUtilInstance = new RetrofitUtil(context);
        return singletonRetrofitInstance;
    }
}
