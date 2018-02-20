package com.bradychiu.sc2ladder.model.oauth;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class AccessTokenModel implements Parcelable {

    @Json(name = "access_token") public abstract String getAccessToken();
    @Json(name = "expires_in") public abstract Long getExpiresIn();
    @Json(name = "scope") public abstract String getScope();
    @Json(name = "token_type") public abstract String getTokenType();

    public static Builder builder() {
        return new AutoValue_AccessTokenModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setAccessToken(String value);
        public abstract Builder setExpiresIn(Long value);
        public abstract Builder setScope(String value);
        public abstract Builder setTokenType(String value);
        public abstract AccessTokenModel build();
    }

    public static JsonAdapter<AccessTokenModel> jsonAdapter(Moshi moshi){
        return new AutoValue_AccessTokenModel.MoshiJsonAdapter(moshi);
    }
}
