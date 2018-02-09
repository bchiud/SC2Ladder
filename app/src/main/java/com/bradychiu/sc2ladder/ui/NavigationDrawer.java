package com.bradychiu.sc2ladder.ui;

import android.app.Activity;
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

    private Toast mToast;
    private Context mContext;
    private Activity mActivity;

    public NavigationDrawer(Context context, Activity activity) {
        mContext = context;
        mActivity = activity;
    }

    public void getNavDrawer() {

        /*
        Navigation Drawer
        http://blog.technoguff.com/2015/07/navigation-drawer-using-recyclerview.html
        */

        ArrayList<DrawerItemModel> mDrawerItemList = new ArrayList<>();
        mDrawerItemList.add(DrawerItemModel.builder()
                .setIcon(R.mipmap.profle)
                .setTitle("Profile")
                .setFragmentId(R.layout.fragment_profile)
                .build());
        mDrawerItemList.add(DrawerItemModel.builder()
                .setIcon(R.mipmap.ladders)
                .setTitle("Ladders")
                .setFragmentId(R.layout.fragment_ladders)
                .build());
        mDrawerItemList.add(DrawerItemModel.builder()
                .setIcon(R.mipmap.match_history)
                .setTitle("Match History")
                .setFragmentId(R.layout.fragment_match_history)
                .build());
        mDrawerItemList.add(DrawerItemModel.builder()
                .setIcon(R.mipmap.settings)
                .setTitle("Settings")
                .setFragmentId(R.layout.fragment_settings)
                .build());

        RecyclerView mRecyclerView = (RecyclerView) mActivity.findViewById(R.id.rv_drawer);
        DrawerAdapter drawerAdapter = new DrawerAdapter(mDrawerItemList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(drawerAdapter);

    }

    @Override
    public void onDrawerItemClick(int clickedPosition) {
        if (mToast != null) {
            mToast.cancel();
        }

        String toastMessage = "Item #" + clickedPosition + " clicked.";
        mToast = Toast.makeText(mContext, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }
}
