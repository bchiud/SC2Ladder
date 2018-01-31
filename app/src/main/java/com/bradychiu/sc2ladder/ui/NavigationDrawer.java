package com.bradychiu.sc2ladder.ui;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.model.drawer.DrawerItemModel;
import com.bradychiu.sc2ladder.utils.DrawerAdapter;
import java.util.ArrayList;

public class NavigationDrawer {

    public static void getNavDrawer(Activity activity) {

        /*
        Navigation Drawer
        http://blog.technoguff.com/2015/07/navigation-drawer-using-recyclerview.html
        */

        ArrayList<DrawerItemModel> mDrawerItemList = new ArrayList<>();
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

        RecyclerView mRecyclerView = (RecyclerView) activity.findViewById(R.id.rv_drawer);
        DrawerAdapter drawerAdapter = new DrawerAdapter(mDrawerItemList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mRecyclerView.setAdapter(drawerAdapter);

    }

}
