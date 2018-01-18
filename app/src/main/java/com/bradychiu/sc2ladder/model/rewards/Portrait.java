package com.bradychiu.sc2ladder.model.rewards;

import com.bradychiu.sc2ladder.model.common.Icon;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Portrait {

    @Json(name = "title") public abstract String title();
    @Json(name = "id") public abstract Long id();
    @Json(name = "icon") public abstract Icon icon();
    @Json(name = "achievementId") public abstract Long achievementId();

    public static Builder builder() {
        return new AutoValue_Portrait.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setTitle(String value);
        public abstract Builder setId(Long value);
        public abstract Builder setIcon(Icon value);
        public abstract Builder setAchievementId(Long value);
        public abstract Portrait build();
    }

    public static JsonAdapter<Portrait> jsonAdapter(Moshi moshi) {
        return new AutoValue_Portrait.MoshiJsonAdapter(moshi);
    }

}
