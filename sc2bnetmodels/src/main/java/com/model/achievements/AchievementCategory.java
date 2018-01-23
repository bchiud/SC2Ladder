package com.model.achievements;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@AutoValue
public abstract class AchievementCategory {

    @Json(name = "title") public abstract String title();
    @Json(name = "categoryId") public abstract Long categoryId();
    @Json(name = "featuredAchievementId") public abstract Long featuredAchievementId();
    @Json(name = "children") @Nullable abstract List<AchievementCategoryChildren> achievementCategoryChildren();

    public static Builder builder() {
        return new AutoValue_AchievementCategory.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setTitle(String value);
        public abstract Builder setCategoryId(Long value);
        public abstract Builder setFeaturedAchievementId(Long value);
        public abstract Builder setAchievementCategoryChildren(List<AchievementCategoryChildren> value);
        public abstract AchievementCategory build();
    }

    public static JsonAdapter<AchievementCategory> jsonAdapter(Moshi moshi) {
        return new AutoValue_AchievementCategory.MoshiJsonAdapter(moshi);
    }

}