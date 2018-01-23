package com.bradychiu.sc2ladder.model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.jetbrains.annotations.Nullable;

@AutoValue
public abstract class Campaign {

    @Json(name = "wol") @Nullable public abstract String wol();
    @Json(name = "hots") @Nullable public abstract String hots();
    @Json(name = "lotv") @Nullable public abstract String lotv();

    public static Builder builder() {
        return new AutoValue_Campaign.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setWol(String value);
        public abstract Builder setHots(String value);
        public abstract Builder setLotv(String value);
        public abstract Campaign build();
    }

    public static JsonAdapter<Campaign> jsonAdapter(Moshi moshi) {
        return new AutoValue_Campaign.MoshiJsonAdapter(moshi);
    }

}
