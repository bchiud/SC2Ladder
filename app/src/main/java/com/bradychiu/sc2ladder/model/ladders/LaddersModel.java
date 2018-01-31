package com.bradychiu.sc2ladder.model.ladders;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class LaddersModel {

    @Json(name = "currentSeason") public abstract List<LaddersEntryModel> currentSeason();
    @Json(name = "previousSeason") public abstract List<LaddersEntryModel> previousSeason();
    @Json(name = "showcasePlacement") public abstract List<LaddersEntryModel> showcasePlacement();

    public static Builder builder() {
        return new AutoValue_LaddersModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setCurrentSeason(List<LaddersEntryModel> value);
        public abstract Builder setPreviousSeason(List<LaddersEntryModel> value);
        public abstract Builder setShowcasePlacement(List<LaddersEntryModel> value);
        public abstract LaddersModel build();
    }

    public static JsonAdapter<LaddersModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_LaddersModel.MoshiJsonAdapter(moshi);
    }



}
