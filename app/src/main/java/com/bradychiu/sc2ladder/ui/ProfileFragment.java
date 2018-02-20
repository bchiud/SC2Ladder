package com.bradychiu.sc2ladder.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.api.CommunityOAuthProfileApi;
import com.bradychiu.sc2ladder.api.SC2CommunityApi;
import com.bradychiu.sc2ladder.model.oauth.SC2OAuthProfileModel;
import com.bradychiu.sc2ladder.model.profile.ProfileModel;
import com.bradychiu.sc2ladder.utils.RetrofitUtil;
import com.bradychiu.sc2ladder.utils.SharedPrefsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileFragment extends Fragment {

    // TODO: what part of lifecycle is network call supposed to go?

    private Context mContext;
    private ProfileModel mProfile;
    private Retrofit mRetrofit;
    private SharedPrefsService mSharedPrefsService;
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
        mSharedPrefsService = SharedPrefsService.getInstance(mContext);
        mRetrofit = RetrofitUtil.getRetrofit(mContext);

        getProfile();
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    private void getProfile() {
        SC2CommunityApi sc2CommunityApi = mRetrofit.create(SC2CommunityApi.class);

        Call<ProfileModel> profileCall = sc2CommunityApi.getProfile(
                mSharedPrefsService.getGame(),
                mSharedPrefsService.getProfileNumber(),
                mSharedPrefsService.getProfileName(),
                mSharedPrefsService.getRealmNumber(),
                mSharedPrefsService.getLocale(),
                mSharedPrefsService.getApiKey());

        profileCall.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if (response.isSuccessful()) {
                    mProfile = response.body();

                    StringBuilder sbPlayerName = new StringBuilder();
                    if(!TextUtils.isEmpty(mProfile.clanTag())) sbPlayerName.append("[" + mProfile.clanTag() + "]");
                    sbPlayerName.append(mProfile.displayName());
                    mTextView.append("Player: " + sbPlayerName.toString() + "\n");

                    mTextView.append("Highest 1v1 Rank: " + mProfile.career().highest1v1Rank() + "\n");

                    mTextView.append("Highest Team Rank: " + mProfile.career().highestTeamRank() + "\n");

                    mTextView.append("Season Games: " + mProfile.career().seasonTotalGames() + "\n");

                    mTextView.append("Career Games: " + mProfile.career().careerTotalGames() + "\n");

                } else {
                    //TODO: error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                //TODO: something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });
    }

    // private void getOAuthSC2Profile() {
    //     CommunityOAuthProfileApi communityOAuthProfileApi = mRetrofit.create(CommunityOAuthProfileApi.class);
    //
    //     Call<SC2OAuthProfileModel> sc2OAuthProfileModelCall = communityOAuthProfileApi.getSC2OAuthProfile(
    //             mSharedPrefsService.getAccessToken());
    //
    //     sc2OAuthProfileModelCall.enqueue(new Callback<SC2OAuthProfileModel>() {
    //
    //         @Override
    //         public void onResponse(Call<SC2OAuthProfileModel> call, Response<SC2OAuthProfileModel> response) {
    //             if(response.isSuccessful()) {
    //                 mTextView.setText(response.body().toString());
    //             } else {
    //                 //TODO: error response, no access to resource?
    //             }
    //         }
    //
    //         @Override
    //         public void onFailure(Call<SC2OAuthProfileModel> call, Throwable t) {
    //             //TODO: something went completely south (like no internet connection)
    //             Log.d("Error", t.getMessage());
    //         }
    //     });
    // }

}
