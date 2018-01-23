package com.model.achievements;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

@AutoValue
public abstract class AchievementList {

    @Json(name = "achievements") public abstract List<Achievement> cheevos();
    @Json(name = "categories") public abstract List<AchievementCategory> achievementCategory();

    public static Builder builder() {
        return new AutoValue_AchievementList.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setCheevos(List<Achievement> value);
        public abstract Builder setAchievementCategory(List<AchievementCategory> value);
        public abstract AchievementList build();
    }

    public static JsonAdapter<AchievementList> jsonAdapter(Moshi moshi) {
        return new AutoValue_AchievementList.MoshiJsonAdapter(moshi);
    }

}