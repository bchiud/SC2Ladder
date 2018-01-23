package com.bradychiu.sc2ladder.model.ladders;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class Ladders {

    @Json(name = "currentSeason") public abstract List<LaddersEntry> currentSeason();
    @Json(name = "previousSeason") public abstract List<LaddersEntry> previousSeason();
    @Json(name = "showcasePlacement") public abstract List<LaddersEntry> showcasePlacement();

    public static Builder builder() {
        return new AutoValue_Ladders.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setCurrentSeason(List<LaddersEntry> value);
        public abstract Builder setPreviousSeason(List<LaddersEntry> value);
        public abstract Builder setShowcasePlacement(List<LaddersEntry> value);
        public abstract Ladders build();
    }

    public static JsonAdapter<Ladders> jsonAdapter(Moshi moshi) {
        return new AutoValue_Ladders.MoshiJsonAdapter(moshi);
    }

}
