package com.bradychiu.sc2ladder.model.profile;

import com.bradychiu.sc2ladder.model.common.Icon;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.jetbrains.annotations.Nullable;

@AutoValue
public abstract class Profile {

    @Json(name = "id") public abstract int id();
    @Json(name = "realm") public abstract int realm();
    @Json(name = "displayName") public abstract String displayName();
    @Json(name = "clanName") @Nullable public abstract String clanName();
    @Json(name = "clanTag") @Nullable public abstract String clanTag();
    @Json(name = "profilePath") public abstract String profilePath();
    @Json(name = "portrait") public abstract Icon icon();
    @Json(name = "career") public abstract Career career();
    @Json(name = "swarmLevels") public abstract SwarmLevels swarmLevels();
    @Json(name = "campaign") public abstract Campaign campaign();
    @Json(name = "season") public abstract Season season();
    @Json(name = "rewards") public abstract ProfileRewards rewards();
    @Json(name = "achievements") public abstract ProfileAchievementList profileAchievementList();

    public static Builder builder() {
        return new AutoValue_Profile.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setId(int value);
        public abstract Builder setRealm(int value);
        public abstract Builder setDisplayName(String value);
        public abstract Builder setClanName(String value);
        public abstract Builder setClanTag(String value);
        public abstract Builder setProfilePath(String value);
        public abstract Builder setIcon(Icon value);
        public abstract Builder setCareer(Career value);
        public abstract Builder setSwarmLevels(SwarmLevels value);
        public abstract Builder setCampaign(Campaign value);
        public abstract Builder setSeason(Season value);
        public abstract Builder setRewards(ProfileRewards value);
        public abstract Builder setProfileAchievementList(ProfileAchievementList value);
        public abstract Profile build();
    }

    public static JsonAdapter<Profile> jsonAdapter(Moshi moshi) {
        return new AutoValue_Profile.MoshiJsonAdapter(moshi);
    }

}