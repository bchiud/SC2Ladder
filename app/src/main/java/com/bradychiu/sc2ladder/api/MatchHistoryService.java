package com.bradychiu.sc2ladder.api;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import com.bradychiu.sc2ladder.model.Config;
import com.bradychiu.sc2ladder.model.matchHistory.MatchHistoryModel;
import com.bradychiu.sc2ladder.utils.RetrofitUtils;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MatchHistoryService extends AsyncTask<Void, Void, MatchHistoryModel> {

    // TODO: refactor to handle memory leaks

    private TextView tv;
    private Config config;

    public MatchHistoryService(TextView tv, Config config) {
        this.tv = tv;
        this.config = config;
    }

    @Override
    public void onPreExecute() {}

    @Override
    public MatchHistoryModel doInBackground(Void... params) {

        Retrofit retrofit = RetrofitUtils.getRetrofit(config);

        SC2CommunityApi SC2CommunityApi = retrofit.create(SC2CommunityApi.class);

        Call<MatchHistoryModel> call = SC2CommunityApi.getMatchHistory(
                config.game(),
                config.profileNumber(),
                config.realmNumber(),
                config.profileName());

        MatchHistoryModel matchHistory = null;

        try {
            matchHistory = call.execute().body();
        } catch (IOException e) {
            // TODO: toast for user, and log.d for dev
            Log.d("", "API call failed");
            e.printStackTrace();
        } finally {
            // TODO: make - profile is already null?
        }

        return matchHistory;

    }

    @Override
    public void onPostExecute(MatchHistoryModel matchHistoryModel) {
        tv.append(matchHistoryModel.toString().substring(0,50) + "\n");
    }

}
