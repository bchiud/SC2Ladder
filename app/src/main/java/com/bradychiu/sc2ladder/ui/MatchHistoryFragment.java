package com.bradychiu.sc2ladder.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.api.SC2CommunityApi;
import com.bradychiu.sc2ladder.model.matchHistory.MatchHistoryModel;
import com.bradychiu.sc2ladder.model.matchHistory.MatchModel;
import com.bradychiu.sc2ladder.utils.MatchHistoryAdapter;
import com.bradychiu.sc2ladder.utils.RetrofitUtil;
import com.bradychiu.sc2ladder.utils.SharedPrefsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.Arrays;
import java.util.List;

public class MatchHistoryFragment extends Fragment {

    private Context mContext;
    private MatchHistoryModel mMatchHistory;

    private RecyclerView mRecyclerView;
    private MatchHistoryAdapter mMatchHistoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_history, container, false);
        mContext = getActivity();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_match_history);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        MatchModel someMatch = MatchModel.builder()
                .setDate((long) 1234123412)
                .setDecision("Win")
                .setMap("Some Map")
                .setSpeed("Fastest")
                .setType("Ladder")
                .build();
        List<MatchModel> someList = Arrays.asList(someMatch,someMatch,someMatch,someMatch,someMatch);

        mMatchHistoryAdapter = new MatchHistoryAdapter(someList);
        mRecyclerView.setAdapter(mMatchHistoryAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPrefsService sharedPrefsService = SharedPrefsService.getInstance(mContext);
        Retrofit retrofit = RetrofitUtil.getRetrofit(mContext);
        SC2CommunityApi sc2CommunityApi = retrofit.create(SC2CommunityApi.class);

        Call<MatchHistoryModel> matchHistoryCall = sc2CommunityApi.getMatchHistory(sharedPrefsService.getGame(),
                sharedPrefsService.getProfileNumber(),
                sharedPrefsService.getRealmNumber(),
                sharedPrefsService.getProfileName());

        matchHistoryCall.enqueue(new Callback<MatchHistoryModel>() {

            @Override
            public void onResponse(Call<MatchHistoryModel> call, Response<MatchHistoryModel> response) {
                if(response.isSuccessful()) {
                    mMatchHistory = response.body();

                    mMatchHistoryAdapter.updateMatches(mMatchHistory.getMatches());
                } else {
                    //TODO: error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<MatchHistoryModel> call, Throwable t) {
                //TODO: something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });
    }

}
