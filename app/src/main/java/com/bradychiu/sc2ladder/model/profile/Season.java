package com.bradychiu.sc2ladder.model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@AutoValue
public abstract class Season {

    @Json(name = "seasonID") public abstract int seasonId();
    @Json(name = "seasonYear") public abstract int seasonYear();
    @Json(name = "totalGamesThisSeason") public abstract int totalGamesThisSeason();
    @Json(name = "stats") @Nullable public abstract List<SeasonStats> seasonStats();

    public static Builder builder() {
        return new AutoValue_Season.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setSeasonId(int value);
        public abstract Builder setSeasonYear(int value);
        public abstract Builder setTotalGamesThisSeason(int value);
        public abstract Builder setSeasonStats(List<SeasonStats> value);
        public abstract Season build();
    }

    public static JsonAdapter<Season> jsonAdapter(Moshi moshi) {
        return new AutoValue_Season.MoshiJsonAdapter(moshi);
    }

}