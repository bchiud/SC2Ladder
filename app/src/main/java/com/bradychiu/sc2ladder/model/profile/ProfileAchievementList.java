package com.bradychiu.sc2ladder.model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

@AutoValue
public abstract class ProfileAchievementList {

    @Json(name = "points") public abstract ProfilePoints points();
    @Json(name = "achievements") public abstract List<ProfileAchievement> achievements();

    public static Builder builder() {
        return new AutoValue_ProfileAchievementList.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setPoints(ProfilePoints value);
        public abstract Builder setAchievements(List<ProfileAchievement> value);
        public abstract ProfileAchievementList build();
    }

    public static JsonAdapter<ProfileAchievementList> jsonAdapter(Moshi moshi) {
        return new AutoValue_ProfileAchievementList.MoshiJsonAdapter(moshi);
    }

}
