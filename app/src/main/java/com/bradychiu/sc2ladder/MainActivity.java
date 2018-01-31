package com.bradychiu.sc2ladder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.bradychiu.sc2ladder.api.MatchHistoryService;
import com.bradychiu.sc2ladder.api.ProfileService;
import com.bradychiu.sc2ladder.model.Config;
import com.bradychiu.sc2ladder.model.drawer.DrawerItemModel;
import com.bradychiu.sc2ladder.ui.NavigationDrawer;
import com.bradychiu.sc2ladder.utils.ConfigService;
import com.bradychiu.sc2ladder.utils.DrawerAdapter;
import java.util.ArrayList;

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

        ProfileService profileService = new ProfileService(tvMain, config);
        profileService.execute();

        MatchHistoryService matchHistoryService = new MatchHistoryService(tvMain, config);
        matchHistoryService.execute();



        // MatchHistoryService<LaddersModel> ladders = new MatchHistoryService<LaddersModel>(LaddersModel.class, config);
        // ladders.execute();
        // try {
        //     tvMain.append(ladders.get().toString().substring(0,50));
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // tvMain.append("\n");
        //
        // MatchHistoryService<MatchHistoryModel> matchHistory = new MatchHistoryService<MatchHistoryModel>(MatchHistoryModel.class, config);
        // matchHistory.execute();
        // try {
        //     tvMain.append(matchHistory.get().toString().substring(0,50));
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // tvMain.append("\n");
        //
        // MatchHistoryService<LadderModel> ladder = new MatchHistoryService<LadderModel>(LadderModel.class, config);
        // ladder.execute();
        // try {
        //     tvMain.append(ladder.get().toString().substring(0,50));
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // tvMain.append("\n");
        //
        // MatchHistoryService<AchievementListModel> achievements = new MatchHistoryService<AchievementListModel>(AchievementListModel.class, config);
        // achievements.execute();
        // try {
        //     tvMain.append(achievements.get().toString().substring(0,50));
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // tvMain.append("\n");
        //
        // MatchHistoryService<RewardsModel> rewards = new MatchHistoryService<RewardsModel>(RewardsModel.class, config);
        // rewards.execute();
        // try {
        //     tvMain.append(rewards.get().toString().substring(0,50));
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // tvMain.append("\n");

    }
}
