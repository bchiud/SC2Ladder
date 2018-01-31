package com.bradychiu.sc2ladder.utils;

import com.bradychiu.sc2ladder.api.AdapterFactory;
import com.bradychiu.sc2ladder.model.Config;
import com.squareup.moshi.Moshi;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;
import java.net.URL;

public class RetrofitUtils {

    public static Retrofit getRetrofit(final Config config) {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();

                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("locale", config.locale())
                                .addQueryParameter("apikey", config.apiKey())
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
                .host(config.region() + ".api.battle.net")
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
