package com.bradychiu.sc2ladder.model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class ProfileAchievement {

    @Json(name = "achievementId") public abstract long achievementId();
    @Json(name = "completionDate") public abstract long completionDate();

    public static Builder builder() {
        return new AutoValue_ProfileAchievement.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setAchievementId(long value);
        public abstract Builder setCompletionDate(long value);
        public abstract ProfileAchievement build();
    }

    public static JsonAdapter<ProfileAchievement> jsonAdapter(Moshi moshi) {
        return new AutoValue_ProfileAchievement.MoshiJsonAdapter(moshi);
    }

}
