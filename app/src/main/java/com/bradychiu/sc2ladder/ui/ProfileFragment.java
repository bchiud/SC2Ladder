package com.bradychiu.sc2ladder.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.api.CommunityOAuthProfileApi;
import com.bradychiu.sc2ladder.api.SC2CommunityApi;
import com.bradychiu.sc2ladder.model.common.MessageEvent;
import com.bradychiu.sc2ladder.model.oauth.SC2OAuthProfileCharacterModel;
import com.bradychiu.sc2ladder.model.oauth.SC2OAuthProfileModel;
import com.bradychiu.sc2ladder.model.profile.ProfileModel;
import com.bradychiu.sc2ladder.utils.ProfileService;
import com.bradychiu.sc2ladder.utils.RetrofitUtil;
import com.bradychiu.sc2ladder.utils.SharedPrefsService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileFragment extends Fragment {

    // TODO: what part of lifecycle is network call supposed to go?

    private Context mContext;
    private ProfileService mProfileSerivce;
    private SC2OAuthProfileModel mSC2OAuthProfileModel;
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
    public void onResume() {
        super.onResume();

        mProfileSerivce = ProfileService.getInstance(mContext);
        mSC2OAuthProfileModel = mProfileSerivce.getProfile();
        SC2OAuthProfileCharacterModel firstPlayer = mSC2OAuthProfileModel.characters().get(0);

        mTextView.append("Player: " + firstPlayer.displayName() + "\n");
        mTextView.append("Highest 1v1 Rank: " + firstPlayer.career().highest1v1Rank() + "\n");
        mTextView.append("Highest Team Rank: " + firstPlayer.career().highestTeamRank() + "\n");
        mTextView.append("Season Games: " + firstPlayer.career().seasonTotalGames() + "\n");
        mTextView.append("Career Games: " + firstPlayer.career().careerTotalGames() + "\n");
    }

}
