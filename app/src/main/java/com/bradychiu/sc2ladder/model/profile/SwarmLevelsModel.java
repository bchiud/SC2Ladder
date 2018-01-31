package com.bradychiu.sc2ladder.model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class SwarmLevelsModel {

    @Json(name = "level") public abstract int level();
    @Json(name = "terran") public abstract SwarmLevelsRaceModel terran();
    @Json(name = "zerg") public abstract SwarmLevelsRaceModel zerg();
    @Json(name = "protoss") public abstract SwarmLevelsRaceModel protoss();

    public static Builder build() {
        return new AutoValue_SwarmLevelsModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setLevel(int value);
        public abstract Builder setTerran(SwarmLevelsRaceModel value);
        public abstract Builder setZerg(SwarmLevelsRaceModel value);
        public abstract Builder setProtoss(SwarmLevelsRaceModel value);
        public abstract SwarmLevelsModel build();
    }

    public static JsonAdapter<SwarmLevelsModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_SwarmLevelsModel.MoshiJsonAdapter(moshi);
    }

}