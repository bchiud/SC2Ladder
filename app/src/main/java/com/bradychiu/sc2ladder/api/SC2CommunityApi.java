package com.bradychiu.sc2ladder.api;

import com.bradychiu.sc2ladder.model.achievements.AchievementListModel;
import com.bradychiu.sc2ladder.model.ladder.LadderModel;
import com.bradychiu.sc2ladder.model.ladders.LaddersModel;
import com.bradychiu.sc2ladder.model.matchHistory.MatchHistoryModel;
import com.bradychiu.sc2ladder.model.profile.ProfileModel;
import com.bradychiu.sc2ladder.model.rewards.RewardsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface SC2CommunityApi {

    // https://us.api.battle.net/sc2/profile/4014615/1/LieZ/?locale={locale}&apikey={apiKey}
    @GET("/{game}/profile/{profileNumber}/{realm}/{profileName}/")
    Call<ProfileModel> getProfile(
            @Path("game") String game,
            @Path("profileNumber") Integer profileNumber,
            @Path("profileName") String profileName,
            @Path("realm") Integer realm,
            @Query("locale") String locale,
            @Query("apiKey") String apiKey
    );

    // https://us.api.battle.net/sc2/profile/4014615/1/LieZ/ladders?locale={locale}&apikey={apiKey}
    @GET("/{game}/profile/{profileNumber}/{realmNumber}/{profileName}/ladders")
    Call<LaddersModel> getLadders(
            @Path("game") String game,
            @Path("profileNumber") Integer profileNumber,
            @Path("realmNumber") Integer realmNumber,
            @Path("profileName") String profileName,
            @Query("locale") String locale,
            @Query("apiKey") String apiKey
    );

    // https://us.api.battle.net/sc2/profile/4014615/1/LieZ/matches?locale={locale}&apikey={apiKey}
    @GET("/{game}/profile/{profileNumber}/{realmNumber}/{profileName}/matches")
    Call<MatchHistoryModel> getMatchHistory(
            @Path("game") String game,
            @Path("profileNumber") Integer profileNumber,
            @Path("realmNumber") Integer realmNumber,
            @Path("profileName") String profileName,
            @Query("locale") String locale,
            @Query("apiKey") String apiKey
    );

    // https://us.api.battle.net/sc2/ladder/264387?locale={locale}&apikey={apiKey}
    @GET("/{game}/ladder/{ladderNumber}")
    Call<LadderModel> getLadder(
            @Path("game") String game,
            @Path("ladderNumber") Integer ladderNumber,
            @Query("locale") String locale,
            @Query("apiKey") String apiKey
    );

    // https://us.api.battle.net/sc2/data/achievements?locale={locale}&apikey={apiKey}
    @GET("/{game}/data/achievements")
    Call<AchievementListModel> getAchievementList(
            @Path("game") String game,
            @Query("locale") String locale,
            @Query("apiKey") String apiKey
    );

    // https://us.api.battle.net/sc2/data/rewards?locale={locale}&apikey={apiKey}
    @GET("/{game}/data/rewards")
    Call<RewardsModel> getRewards(
            @Path("game") String game,
            @Query("locale") String locale,
            @Query("apiKey") String apiKey
    );

}
