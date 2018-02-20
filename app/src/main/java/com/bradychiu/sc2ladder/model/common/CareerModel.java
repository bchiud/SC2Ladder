package com.bradychiu.sc2ladder.model.common;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.jetbrains.annotations.Nullable;

@AutoValue
public abstract class CareerModel implements Parcelable {
    @Json(name = "primaryRace") public abstract String primaryRace();
    @Json(name = "terranWins") public abstract int terranWins();
    @Json(name = "protossWins") public abstract int protossWins();
    @Json(name = "zergWins") public abstract int zergWins();
    @Json(name = "highest1v1Rank") @Nullable public abstract String highest1v1Rank();
    @Json(name = "highestTeamRank") @Nullable public abstract String highestTeamRank();
    @Json(name = "seasonTotalGames") public abstract int seasonTotalGames();
    @Json(name = "careerTotalGames") public abstract int careerTotalGames();

    public static Builder builder() {
        return new AutoValue_CareerModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setPrimaryRace(String value);
        public abstract Builder setTerranWins(int value);
        public abstract Builder setProtossWins(int value);
        public abstract Builder setZergWins(int value);
        public abstract Builder setHighest1v1Rank(@Nullable String value);
        public abstract Builder setHighestTeamRank(@Nullable String value);
        public abstract Builder setSeasonTotalGames(int value);
        public abstract Builder setCareerTotalGames(int value);
        public abstract CareerModel Build();
    }

    public static JsonAdapter<CareerModel> jsonAdapter(Moshi moshi) {
        return new $AutoValue_CareerModel.MoshiJsonAdapter(moshi);
    }

}