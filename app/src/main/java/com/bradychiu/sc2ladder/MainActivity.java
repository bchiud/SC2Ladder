package com.bradychiu.sc2ladder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.bradychiu.sc2ladder.api.MatchHistoryService;
import com.bradychiu.sc2ladder.api.SC2CommunityService;
import com.bradychiu.sc2ladder.api.SC2CommunityApi;
import com.bradychiu.sc2ladder.model.Config;
import com.bradychiu.sc2ladder.model.achievements.AchievementListModel;
import com.bradychiu.sc2ladder.model.achievements.AchievementModel;
import com.bradychiu.sc2ladder.model.ladder.LadderModel;
import com.bradychiu.sc2ladder.model.ladders.LaddersModel;
import com.bradychiu.sc2ladder.model.matchHistory.MatchHistoryModel;
import com.bradychiu.sc2ladder.model.profile.ProfileModel;
import com.bradychiu.sc2ladder.model.rewards.RewardsModel;
import com.bradychiu.sc2ladder.ui.NavigationDrawer;
import com.bradychiu.sc2ladder.utils.ConfigService;
import com.bradychiu.sc2ladder.utils.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvMain = (TextView) findViewById(R.id.tv_main);
        tvMain.setText("");

        NavigationDrawer.getNavDrawer(this);



        /*
        Populate MainActivity
        */

        Config config = ConfigService.ConfigService(getBaseContext());

        Retrofit retrofit = RetrofitUtils.getRetrofit(config);
        SC2CommunityApi sc2CommunityApi = retrofit.create(SC2CommunityApi.class);




        Call<ProfileModel> profileCall = sc2CommunityApi.getProfile(config.game(),
                config.profileNumber(),
                config.profileName(),
                config.realmNumber());

        SC2CommunityService<ProfileModel> profileService = new SC2CommunityService(tvMain, config, profileCall);
        profileService.execute();

        SC2CommunityService<LaddersModel> laddersService = new SC2CommunityService<>(tvMain, config,
                sc2CommunityApi.getLadders(config.game(),
                        config.profileNumber(),
                        config.realmNumber(),
                        config.profileName()));
        laddersService.execute();

        SC2CommunityService<MatchHistoryModel> matchHistoryService = new SC2CommunityService<>(tvMain, config,
                sc2CommunityApi.getMatchHistory(config.game(),
                        config.profileNumber(),
                        config.realmNumber(),
                        config.profileName()));
        matchHistoryService.execute();

        SC2CommunityService<LadderModel> ladderService = new SC2CommunityService<>(tvMain, config,
                sc2CommunityApi.getLadder(config.game(),
                        config.ladderNumber()));
        ladderService.execute();

        SC2CommunityService<AchievementListModel> achievementService = new SC2CommunityService<>(tvMain, config,
                sc2CommunityApi.getAchievementList(config.game()));
        achievementService.execute();

        SC2CommunityService<RewardsModel> rewardsService = new SC2CommunityService<>(tvMain, config,
                sc2CommunityApi.getRewards(config.game()));
        rewardsService.execute();

    }
}
