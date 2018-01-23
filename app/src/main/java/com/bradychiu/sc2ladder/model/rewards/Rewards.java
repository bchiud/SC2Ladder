package com.bradychiu.sc2ladder.model.rewards;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class Rewards {

    @Json(name = "portraits") public abstract List<Portrait> portraits();
    @Json(name = "terranDecals") public abstract List<Portrait> terranDecals();
    @Json(name = "zergDecals") public abstract List<Portrait> zergDecals();
    @Json(name = "protossDecals") public abstract List<Portrait> protossDecals();
    @Json(name = "skins") public abstract List<Skin> skins();
    @Json(name = "animations") public abstract List<Animation> animations();

    public static Builder builder() {
        return new AutoValue_Rewards.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setPortraits(List<Portrait> value);
        public abstract Builder setTerranDecals(List<Portrait> value);
        public abstract Builder setZergDecals(List<Portrait> value);
        public abstract Builder setProtossDecals(List<Portrait> value);
        public abstract Builder setSkins(List<Skin> value);
        public abstract Builder setAnimations(List<Animation> value);
        public abstract Rewards build();
    }

    public static JsonAdapter<Rewards> jsonAdapter(Moshi moshi) {
        return new AutoValue_Rewards.MoshiJsonAdapter(moshi);
    }

}
