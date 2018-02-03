package com.bradychiu.sc2ladder.model.profile;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class ProfilePointsModel implements Parcelable {

    @Json(name = "totalPoints") public abstract int totalPoints();
    @Json(name = "categoryPoints") public abstract CategoryPointsModel categoryPoints();

    public static Builder builder() {
        return new AutoValue_ProfilePointsModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setTotalPoints(int value);
        public abstract Builder setCategoryPoints(CategoryPointsModel value);
        public abstract ProfilePointsModel build();
    }

    public static JsonAdapter<ProfilePointsModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_ProfilePointsModel.MoshiJsonAdapter(moshi);
    }
}