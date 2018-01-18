package com.bradychiu.sc2ladder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("asdf");
        TextView tvMain = (TextView) findViewById(R.id.tv_main);
        tvMain.setText("");

        // model.Config config = utils.ConfigService.ConfigService();
        //
        // utils.BnetApi<model.profile.Profile> profile = new utils.BnetApi<model.profile.Profile>(model.profile.Profile.class, config);
        // tvMain.append(profile.callApi().toString());
        //
        // utils.BnetApi<model.ladders.Ladders> ladders = new utils.BnetApi<model.ladders.Ladders>(model.ladders.Ladders.class, config);
        // tvMain.append(ladders.callApi().toString());
        //
        // utils.BnetApi<model.matchHistory.MatchHistory> matchHistory = new utils.BnetApi<model.matchHistory.MatchHistory>(model.matchHistory.MatchHistory.class, config);
        // tvMain.append(matchHistory.callApi().toString());
        //
        // utils.BnetApi<model.ladder.Ladder> ladder = new utils.BnetApi<model.ladder.Ladder>(model.ladder.Ladder.class, config);
        // tvMain.append(ladder.callApi().toString());
        //
        // utils.BnetApi<model.achievements.AchievementList> achievements = new utils.BnetApi<model.achievements.AchievementList>(model.achievements.AchievementList.class, config);
        // tvMain.append(achievements.callApi().toString());
        //
        // utils.BnetApi<model.rewards.Rewards> rewards = new utils.BnetApi<model.rewards.Rewards>(model.rewards.Rewards.class, config);
        // tvMain.append(rewards.callApi().toString());
    }
}
