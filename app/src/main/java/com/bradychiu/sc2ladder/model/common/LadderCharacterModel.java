package com.bradychiu.sc2ladder.model.common;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.jetbrains.annotations.Nullable;

@AutoValue
public abstract class LadderCharacterModel implements Parcelable {

    @Json(name = "id") public abstract int id();
    @Json(name = "realm") public abstract int realm();
    @Json(name = "displayName") public abstract String displayName();
    @Json(name = "clanName") @Nullable public abstract String clanName();
    @Json(name = "clanTag") @Nullable public abstract String clanTag();
    @Json(name = "profilePath") public abstract String profilePath();

    public static Builder builder() {
        return new AutoValue_LadderCharacterModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setId(int value);
        public abstract Builder setRealm(int value);
        public abstract Builder setDisplayName(String value);
        public abstract Builder setClanName(String value);
        public abstract Builder setClanTag(String value);
        public abstract Builder setProfilePath(String value);
        public abstract LadderCharacterModel build();
    }

    public static JsonAdapter<LadderCharacterModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_LadderCharacterModel.MoshiJsonAdapter(moshi);
    }

}