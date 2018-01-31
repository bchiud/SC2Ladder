package com.bradychiu.sc2ladder.model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@AutoValue
public abstract class SeasonModel {

    @Json(name = "seasonID") public abstract int seasonId();
    @Json(name = "seasonYear") public abstract int seasonYear();
    @Json(name = "totalGamesThisSeason") public abstract int totalGamesThisSeason();
    @Json(name = "stats") @Nullable public abstract List<SeasonStatsModel> seasonStats();

    public static Builder builder() {
        return new AutoValue_SeasonModel.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setSeasonId(int value);
        public abstract Builder setSeasonYear(int value);
        public abstract Builder setTotalGamesThisSeason(int value);
        public abstract Builder setSeasonStats(List<SeasonStatsModel> value);
        public abstract SeasonModel build();
    }

    public static JsonAdapter<SeasonModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_SeasonModel.MoshiJsonAdapter(moshi);
    }

}