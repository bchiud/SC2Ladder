package com.bradychiu.sc2ladder.model.ladder;

import android.os.Parcelable;
import com.bradychiu.sc2ladder.model.common.LadderCharacterModel;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.jetbrains.annotations.Nullable;

@AutoValue
public abstract class LadderMemberModel implements Parcelable {

    @Json(name = "character") public abstract LadderCharacterModel character();
    @Json(name = "joinTimestamp") public abstract Long joinTimestamp();
    @Json(name = "points") public abstract String points();
    @Json(name = "wins") public abstract Integer wins();
    @Json(name = "losses") public abstract Integer losses();
    @Json(name = "highestRank") public abstract Integer highestRank();
    @Json(name = "previousRank") public abstract Integer previousRank();
    @Json(name = "favoriteRaceP1") @Nullable public abstract String favoriteRaceP1();

    public static Builder builder() {
        return new AutoValue_LadderMemberModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setCharacter(LadderCharacterModel value);
        public abstract Builder setJoinTimestamp(Long realm);
        public abstract Builder setPoints(String value);
        public abstract Builder setWins(Integer value);
        public abstract Builder setLosses(Integer value);
        public abstract Builder setHighestRank(Integer value);
        public abstract Builder setPreviousRank(Integer value);
        public abstract Builder setFavoriteRaceP1(@Nullable String value);
        public abstract LadderMemberModel build();
    }

    public static JsonAdapter<LadderMemberModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_LadderMemberModel.MoshiJsonAdapter(moshi);
    }

}
