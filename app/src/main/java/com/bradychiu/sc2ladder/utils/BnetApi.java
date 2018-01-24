package com.bradychiu.sc2ladder.utils;

import android.os.AsyncTask;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.bradychiu.sc2ladder.model.Config;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.net.URL;


public class BnetApi<T> extends AsyncTask<Void, Void, T> {

    private Class<T> clazz;
    private Config config;
    private T finalResult;

    public BnetApi(Class<T> clazz, Config config) {
        this.clazz = clazz;
        this.config = config;
    }

    @Override
    public void onPreExecute() {}

    @Override
    public T doInBackground(Void... params) {

        /*
        okhttp to make connect
        retrofit to turn into object
        moshi to turn json into pojo; used by retrofit
        */

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host(config.region() + ".api.battle.net")
                .addPathSegment(config.game());

        // System.out.println(clazz.getName().replaceAll("^.*model\\.(\\w*\\.)?", ""));

        switch(clazz.getName().replaceAll("^.*model\\.(\\w*\\.)?", "")) {
            case "Profile": // https://us.api.battle.net/sc2/profile/4014615/1/LieZ/?locale=en_US&apikey=
                urlBuilder.addPathSegment("profile")
                        .addPathSegment(config.profileNumber().toString())
                        .addPathSegment(config.realmNumber().toString())
                        .addPathSegment(config.profileName())
                        .addPathSegment("");
                break;
            case "Ladders": // https://us.api.battle.net/sc2/profile/4014615/1/LieZ/ladders?locale=en_US&apikey=
                urlBuilder.addPathSegment("profile")
                        .addPathSegment(config.profileNumber().toString())
                        .addPathSegment(config.realmNumber().toString())
                        .addPathSegment(config.profileName())
                        .addPathSegment("ladders");
                break;
            case "MatchHistory": // https://us.api.battle.net/sc2/profile/4014615/1/LieZ/matches?locale=en_US&apikey=
                urlBuilder.addPathSegment("profile")
                        .addPathSegment(config.profileNumber().toString())
                        .addPathSegment(config.realmNumber().toString())
                        .addPathSegment(config.profileName())
                        .addPathSegment("matches");
                break;
            case "Ladder": // https://us.api.battle.net/sc2/ladder/264387?locale=en_US&apikey=
                urlBuilder.addPathSegment("ladder")
                        .addPathSegment(config.ladderNumber().toString());
                break;
            case "AchievementList": // https://us.api.battle.net/sc2/data/achievements?locale=en_US&apikey=
                urlBuilder.addPathSegments("data/achievements");
                break;
            case "Rewards": // https://us.api.battle.net/sc2/data/rewards?locale=en_US&apikey=
                urlBuilder.addPathSegments("data/rewards");
                break;
        }

        URL url = urlBuilder.addQueryParameter("locale", config.locale())
                .addQueryParameter("apikey", config.apiKey())
                .build()
                .url();

        // System.out.println(url.toString());

        // https://dev.battle.net/io-docs
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        String results = null;
        try {
            response = client.newCall(request).execute();
            results = response.body().string();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        // System.out.println(results);

        Moshi moshi = new Moshi.Builder()
                .add(AdapterFactory.create())
                .build();
        JsonAdapter<T> jsonAdapter = moshi.adapter(clazz);

        try {
            finalResult = jsonAdapter.fromJson(results);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return finalResult;

    }

    @Override
    public void onPostExecute(T t) { }

}
