package com.bradychiu.sc2ladder.model.achievements;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class AchievementCategoryChildren {

    @Json(name = "title") public abstract String title();
    @Json(name = "categoryId") public abstract Long categoryId();
    @Json(name = "featuredAchievementId") public abstract Long featuredAchievementId();

    public static Builder builder() {
        return new AutoValue_AchievementCategoryChildren.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setTitle(String value);
        public abstract Builder setCategoryId(Long value);
        public abstract Builder setFeaturedAchievementId(Long value);
        public abstract AchievementCategoryChildren build();
    }

    public static JsonAdapter<AchievementCategoryChildren> jsonAdapter(Moshi moshi) {
        return new AutoValue_AchievementCategoryChildren.MoshiJsonAdapter(moshi);
    }

}