package com.model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class ProfileRewards {

    @Json(name = "selected") public abstract List<Long> selected();
    @Json(name = "earned") public abstract List<Long> earned();

    public static Builder Builder() {
        return new AutoValue_ProfileRewards.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setSelected(List<Long> value);
        public abstract Builder setEarned(List<Long> value);
        public abstract ProfileRewards build();
    }

    public static JsonAdapter<ProfileRewards> jsonAdapter(Moshi moshi) {
        return new AutoValue_ProfileRewards.MoshiJsonAdapter(moshi);
    }

}
