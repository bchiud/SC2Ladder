package model.ladders;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class LaddersEntryLadder {

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
        return new AutoValue_LaddersEntryLadder.Builder();
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
        public abstract LaddersEntryLadder build();
    }

    public static JsonAdapter<LaddersEntryLadder> jsonAdapter(Moshi moshi) {
        return new AutoValue_LaddersEntryLadder.MoshiJsonAdapter(moshi);
    }

}
