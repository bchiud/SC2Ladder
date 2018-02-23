package com.bradychiu.sc2ladder.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.utils.SharedPrefsService;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private Button mBTLogin;
    private Button mBTPlayer1;
    private Button mBTPlayer2;
    private Context mContext;
    private SharedPrefsService mSharedPrefsService;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        mContext = getActivity();
        mSharedPrefsService = SharedPrefsService.getInstance(mContext);

        mBTLogin = (Button) view.findViewById(R.id.bt_account_login);
        mBTPlayer1 = (Button) view.findViewById(R.id.bt_account_player_1);
        mBTPlayer2 = (Button) view.findViewById(R.id.bt_account_player_2);

        mBTLogin.setOnClickListener(this);
        mBTPlayer1.setOnClickListener(this);
        mBTPlayer2.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bt_account_login:
                getLoginFragment();
                break;
            case R.id.bt_account_player_1:
                setPlayer1();
                break;
            case R.id.bt_account_player_2:
                setPlayer2();
                break;
        }
    }

    public void getLoginFragment() {
        FragmentManager fragmentManager = ((Activity) mContext).getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        try {
            fragment = LoginFragment.class.newInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }
        fragmentTransaction.replace(R.id.fl_content, fragment);
        fragmentTransaction.commit();
    }

    public void setPlayer1() {
        mSharedPrefsService.setRegion(getResources().getString(R.string.player_1_region));
        mSharedPrefsService.setProfileName(getResources().getString(R.string.player_1_name));
        mSharedPrefsService.setProfileNumber(Integer.valueOf(getResources().getString(R.string.player_1_id)));
    }

    public void setPlayer2() {
        mSharedPrefsService.setRegion(getResources().getString(R.string.player_2_region));
        mSharedPrefsService.setProfileName(getResources().getString(R.string.player_2_name));
        mSharedPrefsService.setProfileNumber(Integer.valueOf(getResources().getString(R.string.player_2_id)));
    }
}
