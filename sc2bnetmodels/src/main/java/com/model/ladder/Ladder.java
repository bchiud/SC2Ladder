package com.model.ladder;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class Ladder {

    @Json(name = "ladderMembers") public abstract List<LadderMember> ladderMembers();

    public static Builder builder() {
        return new AutoValue_Ladder.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setLadderMembers(List<LadderMember> value);
        public abstract Ladder build();
    }

    public static JsonAdapter<Ladder> jsonAdapter(Moshi moshi) {
        return new AutoValue_Ladder.MoshiJsonAdapter(moshi);
    }
}
