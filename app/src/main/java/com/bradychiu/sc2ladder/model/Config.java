package model;

import com.google.auto.value.AutoValue;
import org.jetbrains.annotations.Nullable;

@AutoValue
public abstract class Config {
    public abstract String apiKey();
    public abstract String game();
    @Nullable public abstract Integer ladderNumber();
    public abstract String locale();
    public abstract String profileName();
    public abstract Integer profileNumber();
    public abstract Integer realmNumber();
    public abstract String region();

    public static Builder builder() {
        return new AutoValue_Config.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setApiKey(String value);
        public abstract Builder setGame(String value);
        public abstract Builder setLadderNumber(Integer value);
        public abstract Builder setLocale(String value);
        public abstract Builder setProfileNumber(Integer value);
        public abstract Builder setProfileName(String value);
        public abstract Builder setRealmNumber(Integer value);
        public abstract Builder setRegion(String value);
        public abstract Config build();
    }

    public enum region {cn, us, eu, kr, tw}
}
