package com.bradychiu.sc2ladder.model.ladders;

import android.os.Parcelable;
import com.bradychiu.sc2ladder.model.common.LadderCharacterModel;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class LaddersEntryModel implements Parcelable {

    @Json(name = "ladder") public abstract List<LaddersEntryLadderModel> ladder();
    @Json(name = "characters") public abstract List<LadderCharacterModel> characters();
    @Json(name = "nonRanked") public abstract List<LaddersEntryNonRankedModel> nonRanked();

    public static Builder builder() {
        return new AutoValue_LaddersEntryModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setLadder(List<LaddersEntryLadderModel> value);
        public abstract Builder setCharacters(List<LadderCharacterModel> value);
        public abstract Builder setNonRanked(List<LaddersEntryNonRankedModel> value);
        public abstract LaddersEntryModel build();
    }

    public static JsonAdapter<LaddersEntryModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_LaddersEntryModel.MoshiJsonAdapter(moshi);
    }

}