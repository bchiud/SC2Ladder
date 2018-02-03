package com.bradychiu.sc2ladder.model.profile;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class ProfileRewardsModel implements Parcelable {

    @Json(name = "selected") public abstract List<Long> selected();
    @Json(name = "earned") public abstract List<Long> earned();

    public static Builder Builder() {
        return new AutoValue_ProfileRewardsModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setSelected(List<Long> value);
        public abstract Builder setEarned(List<Long> value);
        public abstract ProfileRewardsModel build();
    }

    public static JsonAdapter<ProfileRewardsModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_ProfileRewardsModel.MoshiJsonAdapter(moshi);
    }

}
