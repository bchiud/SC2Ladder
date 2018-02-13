package com.bradychiu.sc2ladder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.bradychiu.sc2ladder.api.SC2CommunityService;
import com.bradychiu.sc2ladder.api.SC2CommunityApi;
import com.bradychiu.sc2ladder.model.profile.ProfileModel;
import com.bradychiu.sc2ladder.ui.*;
import com.bradychiu.sc2ladder.utils.RetrofitUtil;
import com.bradychiu.sc2ladder.utils.SharedPrefsService;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    LocalBroadcastManager mLocalBroadcastManager;
    TextView mMainTextView;
    ProfileModel profile;
    final String PROFILE_INTENT_KEY = "profile";
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPrefsService sharedPrefsService = SharedPrefsService.getInstance(getApplicationContext());

        mMainTextView = (TextView) findViewById(R.id.tv_main);

        new NavigationDrawer(this);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mContext = getApplicationContext();

        /*
        Populate MainActivity
        */

        Retrofit retrofit = RetrofitUtil.getRetrofit(this);
        SC2CommunityApi sc2CommunityApi = retrofit.create(SC2CommunityApi.class);

        Call<ProfileModel> profileCall = sc2CommunityApi.getProfile(sharedPrefsService.getGame(),
                sharedPrefsService.getProfileNumber(),
                sharedPrefsService.getProfileName(),
                sharedPrefsService.getRealmNumber());

        SC2CommunityService<ProfileModel> profileService = new SC2CommunityService(getApplicationContext(),PROFILE_INTENT_KEY, profileCall);
        profileService.execute();
        mLocalBroadcastManager.registerReceiver(mProfileBroadcastReceiver, new IntentFilter(PROFILE_INTENT_KEY));

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        try {
            fragment = ProfileFragment.class.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragmentTransaction.replace(R.id.fl_content, fragment);
        fragmentTransaction.commit();


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
            profile = intent.getParcelableExtra(PROFILE_INTENT_KEY);
            mMainTextView.setText("");

            StringBuilder sbPlayerName = new StringBuilder();
            if(!TextUtils.isEmpty(profile.clanTag())) sbPlayerName.append("[" + profile.clanTag() + "]");
            sbPlayerName.append(profile.displayName());
            mMainTextView.append("Player: " + sbPlayerName.toString() + "\n");

            mMainTextView.append("Highest 1v1 Rank: " + profile.career().highest1v1Rank() + "\n");

            mMainTextView.append("Highest Team Rank: " + profile.career().highestTeamRank() + "\n");

            mMainTextView.append("Season Games: " + profile.career().seasonTotalGames() + "\n");

            mMainTextView.append("Career Games: " + profile.career().careerTotalGames() + "\n");
        }
    };
}
