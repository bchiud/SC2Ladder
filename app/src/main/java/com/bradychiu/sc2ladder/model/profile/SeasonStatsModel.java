package com.bradychiu.sc2ladder.model.profile;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class SeasonStatsModel implements Parcelable {

    @Json(name = "type") public abstract String type();
    @Json(name = "wins") public abstract int wins();
    @Json(name = "games") public abstract int games();

    public static Builder builder() {
        return new AutoValue_SeasonStatsModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setType(String value);
        public abstract Builder setWins(int value);
        public abstract Builder setGames(int value);
        public abstract SeasonStatsModel build();
    }

    public static JsonAdapter<SeasonStatsModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_SeasonStatsModel.MoshiJsonAdapter(moshi);
    }
}
