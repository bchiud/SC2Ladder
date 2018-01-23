package com.bradychiu.sc2ladder.model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class CategoryPoints {

    @Json(name = "4330138") public abstract int category1();
    @Json(name = "4386911") public abstract int category2();
    @Json(name = "4364473") public abstract int category3();
    @Json(name = "4325379") public abstract int category4();
    @Json(name = "4325410") public abstract int category5();
    @Json(name = "4325377") public abstract int category6();

    public static Builder builder() {
        return new AutoValue_CategoryPoints.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setCategory1(int value);
        public abstract Builder setCategory2(int value);
        public abstract Builder setCategory3(int value);
        public abstract Builder setCategory4(int value);
        public abstract Builder setCategory5(int value);
        public abstract Builder setCategory6(int value);
        public abstract CategoryPoints build();
    }

    public static JsonAdapter<CategoryPoints> jsonAdapter(Moshi moshi) {
        return new AutoValue_CategoryPoints.MoshiJsonAdapter(moshi);
    }

}