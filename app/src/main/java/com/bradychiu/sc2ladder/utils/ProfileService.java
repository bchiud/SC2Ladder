package com.bradychiu.sc2ladder.utils;

import android.content.Context;
import android.util.Log;
import com.bradychiu.sc2ladder.api.CommunityOAuthProfileApi;
import com.bradychiu.sc2ladder.model.common.MessageEvent;
import com.bradychiu.sc2ladder.model.oauth.SC2OAuthProfileModel;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileService {

    private static ProfileService singletonInstance;
    private SC2OAuthProfileModel singletonProfile;
    private Context mContext;
    private CommunityOAuthProfileApi mCommunityOAuthProfileApi;
    private Retrofit mRetrofit;
    private SharedPrefsService mSharedPrefsService;
    private Call<SC2OAuthProfileModel> mSc2OAuthProfileModelCall;

    public ProfileService(Context context) {
        mContext = context;
        mRetrofit = RetrofitUtil.getRetrofit(mContext);
        mSharedPrefsService = SharedPrefsService.getInstance(mContext);
        mCommunityOAuthProfileApi = mRetrofit.create(CommunityOAuthProfileApi.class);
    }

    public static ProfileService getInstance(Context context) {
        if(singletonInstance == null) {
            singletonInstance = new ProfileService(context);
        }
        return singletonInstance;
    }

    public void requestProfile() {
        mSc2OAuthProfileModelCall = mCommunityOAuthProfileApi.getSC2OAuthProfile(mSharedPrefsService.getAccessToken());

        mSc2OAuthProfileModelCall.enqueue(new Callback<SC2OAuthProfileModel>() {

            @Override
            public void onResponse(Call<SC2OAuthProfileModel> call, Response<SC2OAuthProfileModel> response) {
                if(response.isSuccessful()) {
                    singletonProfile = response.body();
                    EventBus.getDefault().post(new MessageEvent("Profile Returned"));
                    System.out.println("event posted");
                } else {
                    //TODO: error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<SC2OAuthProfileModel> call, Throwable throwable) {
                //TODO: something went completely south (like no internet connection)
                Log.d("Error", throwable.getMessage());
            }
        });
    }

    public SC2OAuthProfileModel getProfile() {
        return singletonProfile;
    }

}
