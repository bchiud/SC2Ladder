package com.bradychiu.sc2ladder.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.bradychiu.sc2ladder.model.Config;

public class ConfigService {

    public static Config ConfigService(Context context) {

        SharedPreferences sharedPrefs = context.getApplicationContext().getSharedPreferences("MyPref", 0);

        Config config = Config.builder()
                .setApiKey(sharedPrefs.getString("apkKey", "ufpgqhpsu29c7cavcdkdajqmdv65ee23"))
                .setGame(sharedPrefs.getString("game", "sc2"))
                .setLadderNumber(sharedPrefs.getInt("ladderNumber", 264387))
                .setLocale(sharedPrefs.getString("locale", "en_US"))
                .setProfileNumber(sharedPrefs.getInt("profileNumber", 4014615))
                .setProfileName(sharedPrefs.getString("profileName", "LieZ"))
                .setRealmNumber(sharedPrefs.getInt("realmNumber", 1))
                .setRegion(sharedPrefs.getString("region", "us"))
                .build();

        return config;
    }

}
