package com.bradychiu.sc2ladder.api;

import com.bradychiu.sc2ladder.model.oauth.AccessTokenModel;
import com.bradychiu.sc2ladder.model.oauth.SC2OAuthProfileModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CommunityOAuthProfileApi {

    // https://us.battle.net/oauth/token?grant_type=client_credentials&client_id={api_key}&client_secret={api_secret_key}
    @GET("oauth/token")
    Call<AccessTokenModel> getAccessToken(
            @Query("grant_type") String grantType,
            @Query("client_id") String apiKey,
            @Query("client_secret") String apiSecretKey
    );

    // https://us.api.battle.net/sc2/profile/user?access_token={access_token}
    @GET("sc2/profile/user")
    Call<SC2OAuthProfileModel> getSC2OAuthProfile(
            @Query("access_token") String accessToken
    );

}
