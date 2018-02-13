package com.bradychiu.sc2ladder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.bradychiu.sc2ladder.ui.*;
import com.bradychiu.sc2ladder.utils.SharedPrefsService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPrefsService sharedPrefsService = SharedPrefsService.getInstance(getApplicationContext());

        new NavigationDrawer(this);

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

    }
}
