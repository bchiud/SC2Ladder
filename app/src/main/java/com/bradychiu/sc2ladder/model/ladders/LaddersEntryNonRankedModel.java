package com.bradychiu.sc2ladder.model.ladders;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class LaddersEntryNonRankedModel {

    @Json(name = "mmq") public abstract String mmq();
    @Json(name = "gamesPlayed") public abstract Integer gamesPlayed();

    public static Builder builder() {
        return new AutoValue_LaddersEntryNonRankedModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setMmq(String value);
        public abstract Builder setGamesPlayed(Integer value);
        public abstract LaddersEntryNonRankedModel build();
    }

    public static JsonAdapter<LaddersEntryNonRankedModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_LaddersEntryNonRankedModel.MoshiJsonAdapter(moshi);
    }

}