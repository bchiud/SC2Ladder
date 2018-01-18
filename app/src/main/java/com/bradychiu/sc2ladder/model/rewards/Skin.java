package com.bradychiu.sc2ladder.model.rewards;

import com.bradychiu.sc2ladder.model.common.Icon;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Skin {

    @Json(name = "name") public abstract String name();
    @Json(name = "title") public abstract String title();
    @Json(name = "id") public abstract Long id();
    @Json(name = "icon") public abstract Icon icon();
    @Json(name = "achievementId") public abstract Long achievementId();

    public static Builder builder() {
        return new AutoValue_Skin.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setName(String value);
        public abstract Builder setTitle(String value);
        public abstract Builder setId(Long value);
        public abstract Builder setIcon(Icon value);
        public abstract Builder setAchievementId(Long value);
        public abstract Skin build();
    }

    public static JsonAdapter<Skin> jsonAdapter(Moshi moshi) {
        return new AutoValue_Skin.MoshiJsonAdapter(moshi);
    }

}
