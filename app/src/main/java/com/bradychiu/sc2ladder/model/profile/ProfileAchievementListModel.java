package com.bradychiu.sc2ladder.model.profile;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

@AutoValue
public abstract class ProfileAchievementListModel implements Parcelable {

    @Json(name = "points") public abstract ProfilePointsModel points();
    @Json(name = "achievements") public abstract List<ProfileAchievementModel> achievements();

    public static Builder builder() {
        return new AutoValue_ProfileAchievementListModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setPoints(ProfilePointsModel value);
        public abstract Builder setAchievements(List<ProfileAchievementModel> value);
        public abstract ProfileAchievementListModel build();
    }

    public static JsonAdapter<ProfileAchievementListModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_ProfileAchievementListModel.MoshiJsonAdapter(moshi);
    }

}
