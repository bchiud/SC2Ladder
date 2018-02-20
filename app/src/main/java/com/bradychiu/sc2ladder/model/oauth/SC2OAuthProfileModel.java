package com.bradychiu.sc2ladder.model.oauth;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

@AutoValue
public abstract class SC2OAuthProfileModel implements Parcelable {

    @Json(name = "characters") public abstract List<SC2OAuthProfileCharacterModel> characters();

    public static Builder builder() {
        return new AutoValue_SC2OAuthProfileModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setCharacters(List<SC2OAuthProfileCharacterModel> value);
        public abstract SC2OAuthProfileModel build();
    }

    public static JsonAdapter<SC2OAuthProfileModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_SC2OAuthProfileModel.MoshiJsonAdapter(moshi);
    }

}
