package com.bradychiu.sc2ladder.model.profile;

import com.bradychiu.sc2ladder.model.common.IconModel;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.jetbrains.annotations.Nullable;

@AutoValue
public abstract class ProfileModel {

    @Json(name = "id") public abstract int id();
    @Json(name = "realm") public abstract int realm();
    @Json(name = "displayName") public abstract String displayName();
    @Json(name = "clanName") @Nullable public abstract String clanName();
    @Json(name = "clanTag") @Nullable public abstract String clanTag();
    @Json(name = "profilePath") public abstract String profilePath();
    @Json(name = "portrait") public abstract IconModel icon();
    @Json(name = "career") public abstract CareerModel career();
    @Json(name = "swarmLevels") public abstract SwarmLevelsModel swarmLevels();
    @Json(name = "campaign") public abstract CampaignModel campaign();
    @Json(name = "season") public abstract SeasonModel season();
    @Json(name = "rewards") public abstract ProfileRewardsModel rewards();
    @Json(name = "achievements") public abstract ProfileAchievementListModel profileAchievementList();

    public static Builder builder() {
        return new AutoValue_ProfileModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setId(int value);
        public abstract Builder setRealm(int value);
        public abstract Builder setDisplayName(String value);
        public abstract Builder setClanName(String value);
        public abstract Builder setClanTag(String value);
        public abstract Builder setProfilePath(String value);
        public abstract Builder setIcon(IconModel value);
        public abstract Builder setCareer(CareerModel value);
        public abstract Builder setSwarmLevels(SwarmLevelsModel value);
        public abstract Builder setCampaign(CampaignModel value);
        public abstract Builder setSeason(SeasonModel value);
        public abstract Builder setRewards(ProfileRewardsModel value);
        public abstract Builder setProfileAchievementList(ProfileAchievementListModel value);
        public abstract ProfileModel build();
    }

    public static JsonAdapter<ProfileModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_ProfileModel.MoshiJsonAdapter(moshi);
    }

}