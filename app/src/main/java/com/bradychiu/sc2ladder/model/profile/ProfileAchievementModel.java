package com.bradychiu.sc2ladder.model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class ProfileAchievementModel {

    @Json(name = "achievementId") public abstract long achievementId();
    @Json(name = "completionDate") public abstract long completionDate();

    public static Builder builder() {
        return new AutoValue_ProfileAchievementModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setAchievementId(long value);
        public abstract Builder setCompletionDate(long value);
        public abstract ProfileAchievementModel build();
    }

    public static JsonAdapter<ProfileAchievementModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_ProfileAchievementModel.MoshiJsonAdapter(moshi);
    }

}
