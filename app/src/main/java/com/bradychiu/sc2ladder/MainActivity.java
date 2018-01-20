package com.bradychiu.sc2ladder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.bradychiu.sc2ladder.model.achievements.AchievementList;
import com.bradychiu.sc2ladder.model.ladder.Ladder;
import com.bradychiu.sc2ladder.model.ladders.Ladders;
import com.bradychiu.sc2ladder.model.matchHistory.MatchHistory;
import com.bradychiu.sc2ladder.model.profile.Profile;
import com.bradychiu.sc2ladder.model.rewards.Rewards;
import com.bradychiu.sc2ladder.utils.BnetApi;
import com.bradychiu.sc2ladder.utils.ConfigService;
import model.Config;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvMain = (TextView) findViewById(R.id.tv_main);
        tvMain.setText("");

        Config config = ConfigService.ConfigService(getBaseContext());

        BnetApi<Profile> profile = new BnetApi<Profile>(Profile.class, config);
        tvMain.append(profile.callApi().toString());
        //
        // BnetApi<Ladders> ladders = new BnetApi<Ladders>(Ladders.class, config);
        // tvMain.append(ladders.callApi().toString());
        //
        // BnetApi<MatchHistory> matchHistory = new BnetApi<MatchHistory>(MatchHistory.class, config);
        // tvMain.append(matchHistory.callApi().toString());
        //
        // BnetApi<Ladder> ladder = new BnetApi<Ladder>(Ladder.class, config);
        // tvMain.append(ladder.callApi().toString());
        //
        // BnetApi<AchievementList> achievements = new BnetApi<AchievementList>(AchievementList.class, config);
        // tvMain.append(achievements.callApi().toString());
        //
        // BnetApi<Rewards> rewards = new BnetApi<Rewards>(Rewards.class, config);
        // tvMain.append(rewards.callApi().toString());
    }
}
