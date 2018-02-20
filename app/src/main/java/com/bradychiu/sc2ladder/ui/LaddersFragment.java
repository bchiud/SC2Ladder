package com.bradychiu.sc2ladder.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.api.SC2CommunityApi;
import com.bradychiu.sc2ladder.model.ladders.LaddersModel;
import com.bradychiu.sc2ladder.utils.RetrofitUtil;
import com.bradychiu.sc2ladder.utils.SharedPrefsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LaddersFragment extends Fragment {

    private Context mContext;
    private LaddersModel mLadders;
    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ladders, container, false);
        mContext = getActivity();
        mTextView = (TextView) view.findViewById(R.id.tv_ladders);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPrefsService sharedPrefsService = SharedPrefsService.getInstance(mContext);
        Retrofit retrofit = RetrofitUtil.getRetrofit(mContext);
        SC2CommunityApi sc2CommunityApi = retrofit.create(SC2CommunityApi.class);

        Call<LaddersModel> laddersModelCall = sc2CommunityApi.getLadders(sharedPrefsService.getGame(),
                sharedPrefsService.getProfileNumber(),
                sharedPrefsService.getRealmNumber(),
                sharedPrefsService.getProfileName(),
                sharedPrefsService.getLocale(),
                sharedPrefsService.getApiKey());

        laddersModelCall.enqueue(new Callback<LaddersModel>() {
            @Override
            public void onResponse(Call<LaddersModel> call, Response<LaddersModel> response) {
                if(response.isSuccessful()) {
                    mLadders = response.body();
                    mTextView.setText(mLadders.toString());
                } else {
                    //TODO: error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<LaddersModel> call, Throwable t) {
                //TODO: something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });
    }
}
