package com.bradychiu.sc2ladder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.bradychiu.sc2ladder.model.Config;
import com.bradychiu.sc2ladder.utils.BnetApi;
import com.bradychiu.sc2ladder.utils.ConfigService;
import com.bradychiu.sc2ladder.model.achievements.AchievementList;
import com.bradychiu.sc2ladder.model.ladder.Ladder;
import com.bradychiu.sc2ladder.model.ladders.Ladders;
import com.bradychiu.sc2ladder.model.matchHistory.MatchHistory;
import com.bradychiu.sc2ladder.model.profile.Profile;
import com.bradychiu.sc2ladder.model.rewards.Rewards;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvMain = (TextView) findViewById(R.id.tv_main);
        tvMain.setText("");

        Config config = ConfigService.ConfigService(getBaseContext());

        BnetApi<Profile> profile = new BnetApi<Profile>(Profile.class, config);
        profile.execute();
        try {
            tvMain.append(profile.get().toString().substring(0,25));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvMain.append("\n");

        BnetApi<Ladders> ladders = new BnetApi<Ladders>(Ladders.class, config);
        ladders.execute();
        try {
            tvMain.append(ladders.get().toString().substring(0,25));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvMain.append("\n");

        BnetApi<MatchHistory> matchHistory = new BnetApi<MatchHistory>(MatchHistory.class, config);
        matchHistory.execute();
        try {
            tvMain.append(matchHistory.get().toString().substring(0,25));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvMain.append("\n");

        BnetApi<Ladder> ladder = new BnetApi<Ladder>(Ladder.class, config);
        ladder.execute();
        try {
            tvMain.append(ladder.get().toString().substring(0,25));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvMain.append("\n");

        BnetApi<AchievementList> achievements = new BnetApi<AchievementList>(AchievementList.class, config);
        achievements.execute();
        try {
            tvMain.append(achievements.get().toString().substring(0,25));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvMain.append("\n");

        BnetApi<Rewards> rewards = new BnetApi<Rewards>(Rewards.class, config);
        rewards.execute();
        try {
            tvMain.append(rewards.get().toString().substring(0,25));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvMain.append("\n");

    }
}
