package com.bradychiu.sc2ladder.utils;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

@MoshiAdapterFactory
public abstract class AdapterFactory implements JsonAdapter.Factory {

    // Static factory method to access the package
    // private generated implementation
    public static JsonAdapter.Factory create() {
        return new AutoValueMoshi_AdapterFactory();
    }

}