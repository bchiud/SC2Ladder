package com.bradychiu.sc2ladder.model.ladders;

import com.bradychiu.sc2ladder.model.common.LadderCharacter;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import model.ladders.LaddersEntryLadder;
import model.ladders.LaddersEntryNonRanked;

import java.util.List;

@AutoValue
public abstract class LaddersEntry {

    @Json(name = "ladder") public abstract List<LaddersEntryLadder> ladder();
    @Json(name = "characters") public abstract List<LadderCharacter> characters();
    @Json(name = "nonRanked") public abstract List<LaddersEntryNonRanked> nonRanked();

    public static Builder builder() {
        return new AutoValue_LaddersEntry.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setLadder(List<LaddersEntryLadder> value);
        public abstract Builder setCharacters(List<LadderCharacter> value);
        public abstract Builder setNonRanked(List<LaddersEntryNonRanked> value);
        public abstract LaddersEntry build();
    }

    public static JsonAdapter<LaddersEntry> jsonAdapter(Moshi moshi) {
        return new AutoValue_LaddersEntry.MoshiJsonAdapter(moshi);
    }

}