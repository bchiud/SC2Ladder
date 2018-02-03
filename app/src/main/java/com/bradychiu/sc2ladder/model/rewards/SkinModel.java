package com.bradychiu.sc2ladder.model.rewards;

import android.os.Parcelable;
import com.bradychiu.sc2ladder.model.common.IconModel;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class SkinModel implements Parcelable {

    @Json(name = "name") public abstract String name();
    @Json(name = "title") public abstract String title();
    @Json(name = "id") public abstract Long id();
    @Json(name = "icon") public abstract IconModel icon();
    @Json(name = "achievementId") public abstract Long achievementId();

    public static Builder builder() {
        return new AutoValue_SkinModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setName(String value);
        public abstract Builder setTitle(String value);
        public abstract Builder setId(Long value);
        public abstract Builder setIcon(IconModel value);
        public abstract Builder setAchievementId(Long value);
        public abstract SkinModel build();
    }

    public static JsonAdapter<SkinModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_SkinModel.MoshiJsonAdapter(moshi);
    }

}
