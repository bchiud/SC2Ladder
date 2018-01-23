package com.bradychiu.sc2ladder.model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class SwarmLevelsRace {

    @Json(name = "level") public abstract int level();
    @Json(name = "totalLevelXP") public abstract int totalLevelXP();
    @Json(name = "currentLevelXP") public abstract int currentLevelXP();

    public static Builder builder() {
        return new AutoValue_SwarmLevelsRace.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setLevel(int value);
        public abstract Builder setTotalLevelXP(int value);
        public abstract Builder setCurrentLevelXP(int value);
        public abstract SwarmLevelsRace build();
    }

    public static JsonAdapter<SwarmLevelsRace> jsonAdapter(Moshi moshi) {
        return new AutoValue_SwarmLevelsRace.MoshiJsonAdapter(moshi);
    }

}
