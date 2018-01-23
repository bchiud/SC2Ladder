package com.bradychiu.sc2ladder.utils;

import android.content.Context;
import android.content.res.AssetManager;
import com.bradychiu.sc2ladder.model.Config;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {

    public static Config ConfigService(Context context) {

        Properties prop = new Properties();

        try {
            AssetManager am = context.getAssets();
            prop.load(am.open("config.properties"));
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        System.out.println(prop.getProperty("apiKey"));

        Config config = Config.builder()
                .setApiKey(prop.getProperty("apiKey"))
                .setGame(prop.getProperty("game"))
                .setLadderNumber( prop.containsKey("ladderNumber") ? Integer.valueOf(prop.getProperty("ladderNumber")) : null )
                .setLocale(prop.getProperty("locale"))
                .setProfileNumber(Integer.valueOf(prop.getProperty("profileNumber")))
                .setProfileName(prop.getProperty("profileName"))
                .setRegion(prop.getProperty("region"))
                .setRealmNumber(Integer.valueOf(prop.getProperty("realmNumber")))
                .build();

        System.out.println(config.apiKey());

        return config;
    }

}
