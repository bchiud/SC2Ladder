package com.bradychiu.sc2ladder.model.drawer;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class DrawerItem {

    public abstract int getIcon();
    public abstract String getTitle();

    public static Builder builder() {
        return new AutoValue_DrawerItem.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setIcon(int value);
        public abstract Builder setTitle(String title);
        public abstract DrawerItem build();
    }

}
