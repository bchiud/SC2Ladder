package model.profile;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class ProfilePoints {

    @Json(name = "totalPoints") public abstract int totalPoints();
    @Json(name = "categoryPoints") public abstract CategoryPoints categoryPoints();

    public static Builder builder() {
        return new AutoValue_ProfilePoints.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setTotalPoints(int value);
        public abstract Builder setCategoryPoints(CategoryPoints value);
        public abstract ProfilePoints build();
    }

    public static JsonAdapter<ProfilePoints> jsonAdapter(Moshi moshi) {
        return new AutoValue_ProfilePoints.MoshiJsonAdapter(moshi);
    }
}