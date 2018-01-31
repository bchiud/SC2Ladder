package com.bradychiu.sc2ladder.model.rewards;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class RewardsModel {

    @Json(name = "portraits") public abstract List<PortraitModel> portraits();
    @Json(name = "terranDecals") public abstract List<PortraitModel> terranDecals();
    @Json(name = "zergDecals") public abstract List<PortraitModel> zergDecals();
    @Json(name = "protossDecals") public abstract List<PortraitModel> protossDecals();
    @Json(name = "skins") public abstract List<SkinModel> skins();
    @Json(name = "animations") public abstract List<AnimationModel> animations();

    public static Builder builder() {
        return new AutoValue_RewardsModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setPortraits(List<PortraitModel> value);
        public abstract Builder setTerranDecals(List<PortraitModel> value);
        public abstract Builder setZergDecals(List<PortraitModel> value);
        public abstract Builder setProtossDecals(List<PortraitModel> value);
        public abstract Builder setSkins(List<SkinModel> value);
        public abstract Builder setAnimations(List<AnimationModel> value);
        public abstract RewardsModel build();
    }

    public static JsonAdapter<RewardsModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_RewardsModel.MoshiJsonAdapter(moshi);
    }

}
