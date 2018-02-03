package com.bradychiu.sc2ladder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.bradychiu.sc2ladder.api.SC2CommunityService;
import com.bradychiu.sc2ladder.api.SC2CommunityApi;
import com.bradychiu.sc2ladder.model.profile.ProfileModel;
import com.bradychiu.sc2ladder.ui.NavigationDrawer;
import com.bradychiu.sc2ladder.utils.RetrofitUtils;
import com.bradychiu.sc2ladder.utils.SharedPrefsService;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    LocalBroadcastManager mLocalBroadcastManager;
    TextView mMainTextView;
    final String PROFILE_INTENT_KEY = "profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPrefsService sharedPrefsService = SharedPrefsService.getInstance(getApplicationContext());

        mMainTextView = (TextView) findViewById(R.id.tv_main);

        NavigationDrawer.getNavDrawer(this);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);

        /*
        Populate MainActivity
        */

        Retrofit retrofit = RetrofitUtils.getRetrofit(this);
        SC2CommunityApi sc2CommunityApi = retrofit.create(SC2CommunityApi.class);

        Call<ProfileModel> profileCall = sc2CommunityApi.getProfile(sharedPrefsService.getGame(),
                sharedPrefsService.getProfileNumber(),
                sharedPrefsService.getProfileName(),
                sharedPrefsService.getRealmNumber());

        SC2CommunityService<ProfileModel> profileService = new SC2CommunityService(getApplicationContext(),PROFILE_INTENT_KEY, profileCall);
        profileService.execute();
        mLocalBroadcastManager.registerReceiver(mProfileBroadcastReceiver, new IntentFilter(PROFILE_INTENT_KEY));

        // SC2CommunityService<LaddersModel> laddersService = new SC2CommunityService<>(tvMain, config,
        //         sc2CommunityApi.getLadders(config.game(),
        //                 config.profileNumber(),
        //                 config.realmNumber(),
        //                 config.profileName()));
        // laddersService.execute();
        //
        // SC2CommunityService<MatchHistoryModel> matchHistoryService = new SC2CommunityService<>(tvMain, config,
        //         sc2CommunityApi.getMatchHistory(config.game(),
        //                 config.profileNumber(),
        //                 config.realmNumber(),
        //                 config.profileName()));
        // matchHistoryService.execute();
        //
        // SC2CommunityService<LadderModel> ladderService = new SC2CommunityService<>(tvMain, config,
        //         sc2CommunityApi.getLadder(config.game(),
        //                 config.ladderNumber()));
        // ladderService.execute();
        //
        // SC2CommunityService<AchievementListModel> achievementService = new SC2CommunityService<>(tvMain, config,
        //         sc2CommunityApi.getAchievementList(config.game()));
        // achievementService.execute();
        //
        // SC2CommunityService<RewardsModel> rewardsService = new SC2CommunityService<>(tvMain, config,
        //         sc2CommunityApi.getRewards(config.game()));
        // rewardsService.execute();

    }

    @Override
    public void onDestroy() {
        mLocalBroadcastManager.unregisterReceiver(mProfileBroadcastReceiver);
    }

    private BroadcastReceiver mProfileBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            ProfileModel profile = intent.getParcelableExtra(PROFILE_INTENT_KEY);
            mMainTextView.append(profile.toString().substring(0,50) + "\n");
        }
    };
}
