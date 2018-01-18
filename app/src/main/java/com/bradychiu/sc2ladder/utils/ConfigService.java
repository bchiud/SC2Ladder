package com.bradychiu.sc2ladder.utils;

import model.Config;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigService {

    public static Config ConfigService() {
        InputStream in = null;
        Properties prop = new Properties();

        try {
            in = new FileInputStream("config.properties");
            prop.load(in);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

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

        return config;
    }

}
