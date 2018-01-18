package com.bradychiu.sc2ladder.model.common;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Icon {

    @Json(name = "x") public abstract Integer x();
    @Json(name = "y") public abstract Integer y();
    @Json(name = "w") public abstract Integer w();
    @Json(name = "h") public abstract Integer h();
    @Json(name = "offset") public abstract Integer offset();
    @Json(name = "url") public abstract String url();

    public static Builder builder() {
        return new AutoValue_Icon.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setX(Integer value);
        public abstract Builder setY(Integer value);
        public abstract Builder setW(Integer value);
        public abstract Builder setH(Integer value);
        public abstract Builder setOffset(Integer value);
        public abstract Builder setUrl(String value);
        public abstract Icon build();
    }

    public static JsonAdapter<Icon> jsonAdapter(Moshi moshi) {
        return new AutoValue_Icon.MoshiJsonAdapter(moshi);
    }

}