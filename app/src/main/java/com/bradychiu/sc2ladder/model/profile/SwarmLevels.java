package com.bradychiu.sc2ladder.model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class SwarmLevels {

    @Json(name = "level") public abstract int level();
    @Json(name = "terran") public abstract SwarmLevelsRace terran();
    @Json(name = "zerg") public abstract SwarmLevelsRace zerg();
    @Json(name = "protoss") public abstract SwarmLevelsRace protoss();

    public static Builder build() {
        return new AutoValue_SwarmLevels.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setLevel(int value);
        public abstract Builder setTerran(SwarmLevelsRace value);
        public abstract Builder setZerg(SwarmLevelsRace value);
        public abstract Builder setProtoss(SwarmLevelsRace value);
        public abstract SwarmLevels build();
    }

    public static JsonAdapter<SwarmLevels> jsonAdapter(Moshi moshi) {
        return new AutoValue_SwarmLevels.MoshiJsonAdapter(moshi);
    }

}