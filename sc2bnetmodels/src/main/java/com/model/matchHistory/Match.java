package com.model.matchHistory;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Match {

    @Json(name = "map") public abstract String map();
    @Json(name = "type") abstract String type();
    @Json(name = "decision") public abstract String decision();
    @Json(name = "speed") public abstract String speed();
    @Json(name = "date") public abstract Long date();

    public static Builder builder() {
        return new AutoValue_Match.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setMap(String value);
        public abstract Builder setType(String value);
        public abstract Builder setDecision(String value);
        public abstract Builder setSpeed(String value);
        public abstract Builder setDate(Long value);
        public abstract Match build();
    }

    public static JsonAdapter<Match> jsonAdapter(Moshi moshi) {
        return new AutoValue_Match.MoshiJsonAdapter(moshi);
    }

}
