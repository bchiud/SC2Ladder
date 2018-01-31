package com.bradychiu.sc2ladder.model.achievements;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

@AutoValue
public abstract class AchievementListModel {

    @Json(name = "achievements") public abstract List<AchievementModel> cheevos();
    @Json(name = "categories") public abstract List<AchievementCategoryModel> achievementCategory();

    public static Builder builder() {
        return new AutoValue_AchievementListModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setCheevos(List<AchievementModel> value);
        public abstract Builder setAchievementCategory(List<AchievementCategoryModel> value);
        public abstract AchievementListModel build();
    }

    public static JsonAdapter<AchievementListModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_AchievementListModel.MoshiJsonAdapter(moshi);
    }

}