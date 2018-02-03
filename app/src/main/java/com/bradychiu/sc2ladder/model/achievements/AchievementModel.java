package com.bradychiu.sc2ladder.model.achievements;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.bradychiu.sc2ladder.model.common.IconModel;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class AchievementModel implements Parcelable {

    @Json(name = "title") public abstract String title();
    @Json(name = "description") public abstract String description();
    @Json(name = "achievementId") public abstract Long achievementId();
    @Json(name = "categoryId") public abstract Integer categoryId();
    @Json(name = "points") public abstract Integer points();
    @Json(name = "icon") public abstract IconModel icon();

    public static Builder builder() {
        return new AutoValue_AchievementModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setTitle(String value);
        public abstract Builder setDescription(String value);
        public abstract Builder setAchievementId(Long value);
        public abstract Builder setCategoryId(Integer value);
        public abstract Builder setPoints(Integer value);
        public abstract Builder setIcon(IconModel value);
        public abstract AchievementModel build();
    }

    public static JsonAdapter<AchievementModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_AchievementModel.MoshiJsonAdapter(moshi);
    }

}