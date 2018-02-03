package com.bradychiu.sc2ladder.model.ladders;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class LaddersEntryLadderModel implements Parcelable {

    @Json(name = "ladderName") public abstract String ladderName();
    @Json(name = "ladderId") public abstract Integer ladderId();
    @Json(name = "division") public abstract String division();
    @Json(name = "rank") public abstract Integer rank();
    @Json(name = "league") public abstract String league();
    @Json(name = "matchMakingQueue") public abstract String matchMakingQueue();
    @Json(name = "wins") public abstract Integer wins();
    @Json(name = "losses") public abstract Integer losses();
    @Json(name = "showcase") public abstract Boolean showcase();

    public static Builder builder() {
        return new AutoValue_LaddersEntryLadderModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setLadderName(String value);
        public abstract Builder setLadderId(Integer value);
        public abstract Builder setDivision(String value);
        public abstract Builder setRank(Integer value);
        public abstract Builder setLeague(String value);
        public abstract Builder setMatchMakingQueue(String value);
        public abstract Builder setWins(Integer value);
        public abstract Builder setLosses(Integer value);
        public abstract Builder setShowcase(Boolean value);
        public abstract LaddersEntryLadderModel build();
    }

    public static JsonAdapter<LaddersEntryLadderModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_LaddersEntryLadderModel.MoshiJsonAdapter(moshi);
    }

}
