package com.bradychiu.sc2ladder.ui;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.api.SC2CommunityApi;
import com.bradychiu.sc2ladder.api.SC2CommunityService;
import com.bradychiu.sc2ladder.model.profile.ProfileModel;
import com.bradychiu.sc2ladder.utils.RetrofitUtil;
import com.bradychiu.sc2ladder.utils.SharedPrefsService;
import kotlin.reflect.jvm.internal.impl.descriptors.EffectiveVisibility;
import retrofit2.Call;
import retrofit2.Retrofit;

public class ProfileFragment extends Fragment {

    // TODO: what part of lifecycle is network call supposed to go?
    // TODO: getContext is limited to api23+?

    private Context mContext;
    private LocalBroadcastManager mLocalBroadcastManager;
    private ProfileModel profile;
    private Retrofit mRetrofit;
    private SC2CommunityApi mSC2CommunityApi;
    private final String PROFILE_INTENT_KEY = "profile";
    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mContext = getActivity();
        mTextView = (TextView) view.findViewById(R.id.tv_profile);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPrefsService sharedPrefsService = SharedPrefsService.getInstance(mContext);
        Retrofit retrofit = RetrofitUtil.getRetrofit(mContext);
        SC2CommunityApi sc2CommunityApi = retrofit.create(SC2CommunityApi.class);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(mContext);

        Call<ProfileModel> profileCall = sc2CommunityApi.getProfile(sharedPrefsService.getGame(),
                sharedPrefsService.getProfileNumber(),
                sharedPrefsService.getProfileName(),
                sharedPrefsService.getRealmNumber());

        SC2CommunityService<ProfileModel> profileService = new SC2CommunityService(mContext,PROFILE_INTENT_KEY, profileCall);
        profileService.execute();
        mLocalBroadcastManager.registerReceiver(mProfileBroadcastReceiver, new IntentFilter(PROFILE_INTENT_KEY));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mTextView.setText("Profile Frag Started");
    }

    @Override
    public void onDestroy() {
        mLocalBroadcastManager.unregisterReceiver(mProfileBroadcastReceiver);
    }

    private BroadcastReceiver mProfileBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            profile = intent.getParcelableExtra(PROFILE_INTENT_KEY);
            mTextView.setText("");

            StringBuilder sbPlayerName = new StringBuilder();
            if(!TextUtils.isEmpty(profile.clanTag())) sbPlayerName.append("[" + profile.clanTag() + "]");
            sbPlayerName.append(profile.displayName());
            mTextView.append("Player: " + sbPlayerName.toString() + "\n");

            mTextView.append("Highest 1v1 Rank: " + profile.career().highest1v1Rank() + "\n");

            mTextView.append("Highest Team Rank: " + profile.career().highestTeamRank() + "\n");

            mTextView.append("Season Games: " + profile.career().seasonTotalGames() + "\n");

            mTextView.append("Career Games: " + profile.career().careerTotalGames() + "\n");
        }
    };

    public void setText(String text) {
        mTextView.setText(text);
    }

}
