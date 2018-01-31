package com.bradychiu.sc2ladder.api;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import com.bradychiu.sc2ladder.model.profile.ProfileModel;
import com.bradychiu.sc2ladder.model.Config;
import com.bradychiu.sc2ladder.utils.RetrofitUtils;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Retrofit;


public class ProfileService extends AsyncTask<Void, Void, ProfileModel> {

    // TODO: refactor to handle memory leaks

    private TextView tv;
    private Config config;

    public ProfileService(TextView tv, Config config) {
        this.tv = tv;
        this.config = config;
    }

    @Override
    public void onPreExecute() {}

    @Override
    public ProfileModel doInBackground(Void... params) {

        Retrofit retrofit = RetrofitUtils.getRetrofit(config);

        SC2CommunityService SC2CommunityService = retrofit.create(SC2CommunityService.class);

        Call<ProfileModel> call = SC2CommunityService.getProfile(config.game(),
                config.profileNumber(),
                config.profileName(),
                config.realmNumber());

        ProfileModel profile = null;

        try {
            profile = call.execute().body();
        } catch (IOException e) {
            // TODO: toast for user, and log.d for dev
            Log.d("", "API call failed");
            e.printStackTrace();
        } finally {
            // TODO: make - profile is already null?
        }

        return profile;

    }

    @Override
    public void onPostExecute(ProfileModel profile) {
        tv.append(profile.toString().substring(0,50) + "\n");
    }

}
