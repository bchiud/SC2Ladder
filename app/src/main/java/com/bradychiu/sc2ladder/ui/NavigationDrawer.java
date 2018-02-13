package com.bradychiu.sc2ladder.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.model.drawer.DrawerItemModel;
import com.bradychiu.sc2ladder.utils.DrawerAdapter;

import java.util.ArrayList;

public class NavigationDrawer implements DrawerAdapter.DrawerItemClickListener {

    private Activity mActivity;
    private ArrayList<DrawerItemModel> mDrawerItemList;

    public NavigationDrawer(Activity activity) {
        mActivity = activity;

        /*
        Navigation Drawer
        http://blog.technoguff.com/2015/07/navigation-drawer-using-recyclerview.html
        */

        mDrawerItemList = new ArrayList<>();
        mDrawerItemList.add(DrawerItemModel.builder()
                .setIcon(R.mipmap.profle)
                .setTitle("Profile")
                .build());
        mDrawerItemList.add(DrawerItemModel.builder()
                .setIcon(R.mipmap.ladders)
                .setTitle("Ladders")
                .build());
        mDrawerItemList.add(DrawerItemModel.builder()
                .setIcon(R.mipmap.match_history)
                .setTitle("Match History")
                .build());
        mDrawerItemList.add(DrawerItemModel.builder()
                .setIcon(R.mipmap.settings)
                .setTitle("Settings")
                .build());

        RecyclerView mRecyclerView = (RecyclerView) mActivity.findViewById(R.id.rv_drawer);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(new DrawerAdapter(mDrawerItemList, this));
    }

    @Override
    public void onDrawerItemClick(String buttonTitle) {
        FragmentManager fragmentManager = mActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        try {
            switch(buttonTitle) {
                case "Profile":
                    fragment = ProfileFragment.class.newInstance();
                    break;
                case "Ladders":
                    fragment = LaddersFragment.class.newInstance();
                    break;
                case "Match History":
                    fragment = MatchHistoryFragment.class.newInstance();
                    break;
                case "Settings":
                    fragment = SettingsFragment.class.newInstance();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragmentTransaction.replace(R.id.fl_content, fragment);
        fragmentTransaction.commit();
    }
}
