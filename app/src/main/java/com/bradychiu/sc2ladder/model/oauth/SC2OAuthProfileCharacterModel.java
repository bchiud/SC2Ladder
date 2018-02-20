package com.bradychiu.sc2ladder.model.oauth;

import android.os.Build;
import com.bradychiu.sc2ladder.model.common.IconModel;
import com.bradychiu.sc2ladder.model.common.CareerModel;
import com.bradychiu.sc2ladder.model.common.SwarmLevelsModel;
import com.bradychiu.sc2ladder.model.profile.CampaignModel;
import com.bradychiu.sc2ladder.model.profile.ProfileAchievementListModel;
import com.bradychiu.sc2ladder.model.profile.ProfileRewardsModel;
import com.bradychiu.sc2ladder.model.profile.SeasonModel;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class SC2OAuthProfileCharacterModel {

    @Json(name = "id") public abstract Long id();
    @Json(name = "realm") public abstract Integer realm();
    @Json(name = "name") public abstract String name();
    @Json(name = "displayName") public abstract String displayName();
    @Json(name = "clanName") public abstract String clanName();
    @Json(name = "clanTag") public abstract String clanTag();
    @Json(name = "profilePath") public abstract String profilePath();
    @Json(name = "portrait") public abstract IconModel portrait();
    @Json(name = "avatar") public abstract IconModel avatar();
    @Json(name = "career") public abstract CareerModel career();
    @Json(name = "swarmLevels") public abstract SwarmLevelsModel swarmLevels();
    @Json(name = "campaign") public abstract CampaignModel campaign();
    @Json(name = "season") public abstract SeasonModel season();
    @Json(name = "rewards") public abstract ProfileRewardsModel rewards();
    @Json(name = "achievements") public abstract ProfileAchievementListModel achievements();

    public static Builder builder() {
        return new AutoValue_SC2OAuthProfileCharacterModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setId(Long value);
        public abstract Builder setRealm(Integer value);
        public abstract Builder setName(String value);
        public abstract Builder setDisplayName(String value);
        public abstract Builder setClanName(String value);
        public abstract Builder setClanTag(String value);
        public abstract Builder setProfilePath(String value);
        public abstract Builder setPortrait(IconModel value);
        public abstract Builder setAvatar(IconModel value);
        public abstract Builder setCareer(CareerModel value);
        public abstract Builder setSwarmLevels(SwarmLevelsModel value);
        public abstract Builder setCampaign(CampaignModel value);
        public abstract Builder setSeason(SeasonModel value);
        public abstract Builder setRewards(ProfileRewardsModel value);
        public abstract Builder setAchievements(ProfileAchievementListModel value);
        public abstract SC2OAuthProfileCharacterModel build();
    }

    public static JsonAdapter<SC2OAuthProfileCharacterModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_SC2OAuthProfileCharacterModel.MoshiJsonAdapter(moshi);
    }
}
