package com.bradychiu.sc2ladder.model.ladder;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class LadderModel implements Parcelable {

    @Json(name = "ladderMembers") public abstract List<LadderMemberModel> ladderMembers();

    public static Builder builder() {
        return new AutoValue_LadderModel.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setLadderMembers(List<LadderMemberModel> value);
        public abstract LadderModel build();
    }

    public static JsonAdapter<LadderModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_LadderModel.MoshiJsonAdapter(moshi);
    }

}
