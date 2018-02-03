package com.bradychiu.sc2ladder.api;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;
import retrofit2.Call;

public class SC2CommunityService<T> extends AsyncTask<Void, Void, T> {

    // TODO: is there a way to enforce T is parcelable generic type?
    // TODO: refactor to handle memory leaks - http://blog.nimbledroid.com/2016/09/06/stop-memory-leaks.html

    private Context appContext;
    String intentKey;
    private Call<T> call;

    public SC2CommunityService(Context appContext, String intentKey, Call<T> call) {
        this.appContext = appContext;
        this.intentKey = intentKey;
        this.call = call;
    }

    @Override
    public void onPreExecute() {}

    @Override
    public T doInBackground(Void... params) {

        T profile = null;

        try {
            profile = call.execute().body();
        } catch (IOException e) {
            // TODO: toast for user, and log.d for dev, do not stack trace in prod
            Log.d("", "API call failed");
            e.printStackTrace();
        } finally {
            // TODO: make - profile is already null?
        }

        return profile;

    }

    @Override
    public void onPostExecute(T t) {
        Intent intent = new Intent(intentKey);
        intent.putExtra(intentKey, (Parcelable) t);
        LocalBroadcastManager.getInstance(appContext).sendBroadcast(intent);
    }

}
