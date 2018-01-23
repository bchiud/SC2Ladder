package com.bradychiu.sc2ladder.model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class SeasonStats {

    @Json(name = "type") public abstract String type();
    @Json(name = "wins") public abstract int wins();
    @Json(name = "games") public abstract int games();

    public static Builder builder() {
        return new AutoValue_SeasonStats.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setType(String value);
        public abstract Builder setWins(int value);
        public abstract Builder setGames(int value);
        public abstract SeasonStats build();
    }

    public static JsonAdapter<SeasonStats> jsonAdapter(Moshi moshi) {
        return new AutoValue_SeasonStats.MoshiJsonAdapter(moshi);
    }
}
