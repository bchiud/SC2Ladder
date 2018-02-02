package com.bradychiu.sc2ladder.api;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import com.bradychiu.sc2ladder.model.Config;
import java.io.IOException;
import retrofit2.Call;

public class SC2CommunityService<T> extends AsyncTask<Void, Void, T> {

    // TODO: refactor to handle memory leaks

    private TextView tv;
    private Config config;
    private Call<T> call;

    // todo: refactor this to builder?
    public SC2CommunityService(TextView tv, Config config, Call<T> call) {
        this.tv = tv;
        this.config = config;
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
        tv.append(t.toString().substring(0,50) + "\n");
    }

}
