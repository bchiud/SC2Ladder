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

public interface SC2CommunityApi {

    // https://us.api.battle.net/sc2/profile/4014615/1/LieZ/?locale=en_US&apikey=
    @GET("/{game}/profile/{profileNumber}/{realm}/{profileName}/")
    Call<ProfileModel> getProfile(
            @Path("game") String game,
            @Path("profileNumber") Integer profileNumber,
            @Path("profileName") String profileName,
            @Path("realm") Integer realm
    );

    // https://us.api.battle.net/sc2/profile/4014615/1/LieZ/ladders?locale=en_US&apikey=
    // https://us.api.battle.net/sc2/profile/1/LieZ/ladders?locale=en_US&apikey=ufpgqhpsu29c7cavcdkdajqmdv65ee23
    @GET("/{game}/profile/{profileNumber}/{realmNumber}/{profileName}/ladders")
    Call<LaddersModel> getLadders(
            @Path("game") String game,
            @Path("profileNumber") Integer profileNumber,
            @Path("realmNumber") Integer realmNumber,
            @Path("profileName") String profileName
    );

    // https://us.api.battle.net/sc2/profile/4014615/1/LieZ/matches?locale=en_US&apikey=
    @GET("/{game}/profile/{profileNumber}/{realmNumber}/{profileName}/matches")
    Call<MatchHistoryModel> getMatchHistory(
            @Path("game") String game,
            @Path("profileNumber") Integer profileNumber,
            @Path("realmNumber") Integer realmNumber,
            @Path("profileName") String profileName
    );

    // https://us.api.battle.net/sc2/ladder/264387?locale=en_US&apikey=
    @GET("/{game}/ladder/{ladderNumber}")
    Call<LadderModel> getLadder(
            @Path("game") String game,
            @Path("ladderNumber") Integer ladderNumber
    );

    // https://us.api.battle.net/sc2/data/achievements?locale=en_US&apikey=
    @GET("/{game}/data/achievements")
    Call<AchievementListModel> getAchievementList(
            @Path("game") String game
    );

    // https://us.api.battle.net/sc2/data/rewards?locale=en_US&apikey=
    @GET("/{game}/data/rewards")
    Call<RewardsModel> getRewards(
            @Path("game") String game
    );

}
