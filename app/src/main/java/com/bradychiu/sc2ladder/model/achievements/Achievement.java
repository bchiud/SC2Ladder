package com.bradychiu.sc2ladder.model.achievements;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.bradychiu.sc2ladder.model.common.Icon;

@AutoValue
public abstract class Achievement {

    @Json(name = "title") public abstract String title();
    @Json(name = "description") public abstract String description();
    @Json(name = "achievementId") public abstract Long achievementId();
    @Json(name = "categoryId") public abstract Integer categoryId();
    @Json(name = "points") public abstract Integer points();
    @Json(name = "icon") public abstract Icon icon();

    public static Builder builder() {
        return new AutoValue_Achievement.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setTitle(String value);
        public abstract Builder setDescription(String value);
        public abstract Builder setAchievementId(Long value);
        public abstract Builder setCategoryId(Integer value);
        public abstract Builder setPoints(Integer value);
        public abstract Builder setIcon(Icon value);
        public abstract Achievement build();
    }

    public static JsonAdapter<Achievement> jsonAdapter(Moshi moshi) {
        return new AutoValue_Achievement.MoshiJsonAdapter(moshi);
    }

}