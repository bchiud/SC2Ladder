package com.bradychiu.sc2ladder.model.matchHistory;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@AutoValue
public abstract class MatchHistory {

    // TODO: is nullable the right way to treat non existing values? tried "optional" from auto value but moshi no like
    @Json(name = "matches") @Nullable public abstract List<Match> getMatches();

    public static Builder builder() {
        return new AutoValue_MatchHistory.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setMatches(@Nullable List<Match> matches);
        public abstract MatchHistory build();
    }

    public static JsonAdapter<MatchHistory> jsonAdapter(Moshi moshi) {
        return new AutoValue_MatchHistory.MoshiJsonAdapter(moshi);
    }

}
