package com.bradychiu.sc2ladder.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsService {

    private static SharedPrefsService singleton_instance = null;

    private static final String K_API_KEY = "apiKey";
    private String apiKey;
    private static final String K_GAME = "game";
    private String game;
    private static final String K_LADDER_NUMBER = "ladderNumber";
    private Integer ladderNumber;
    private static final String K_LOCALE = "locale";
    private String locale;
    private static final String K_PROFILE_NAME = "profileName";
    private String profileName;
    private static final String K_PROFILE_NUMBER = "profileNumber";
    private Integer profileNumber;
    private static final String K_REALM_NUMBER = "realmNumber";
    private Integer realmNumber;
    private static final String K_REGION = "region";
    private String region;

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor sharedPrefsEditor;

    private SharedPrefsService(Context context) {

        Context nContext = context.getApplicationContext();
        sharedPrefs = nContext.getSharedPreferences("MyPref", 0);
        sharedPrefsEditor = sharedPrefs.edit();

        apiKey = sharedPrefs.getString(K_API_KEY, "ufpgqhpsu29c7cavcdkdajqmdv65ee23");
        game = sharedPrefs.getString(K_GAME, "sc2");
        ladderNumber = sharedPrefs.getInt(K_LADDER_NUMBER, 264387);
        locale = sharedPrefs.getString(K_LOCALE, "en_US");
        profileName = sharedPrefs.getString(K_PROFILE_NAME, "LieZ");
        profileNumber = sharedPrefs.getInt(K_PROFILE_NUMBER, 4014615);
        realmNumber = sharedPrefs.getInt(K_REALM_NUMBER, 1);
        region = sharedPrefs.getString(K_REGION, "us");

    }

    public static SharedPrefsService getInstance(Context context) {
        if(singleton_instance == null) singleton_instance = new SharedPrefsService(context);
        return singleton_instance;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        sharedPrefsEditor.putString(K_API_KEY, apiKey);
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        sharedPrefsEditor.putString(K_GAME, game);
    }

    public Integer getLadderNumber() {
        return ladderNumber;
    }

    public void setLadderNumber(Integer ladderNumber) {
        sharedPrefsEditor.putInt(K_LADDER_NUMBER, ladderNumber);
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        sharedPrefsEditor.putString(K_LOCALE, locale);
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        sharedPrefsEditor.putString(K_PROFILE_NAME, profileName);
    }

    public Integer getProfileNumber() {
        return profileNumber;
    }

    public void setProfileNumber(Integer profileNumber) {
        sharedPrefsEditor.putInt(K_PROFILE_NUMBER, profileNumber);
    }

    public Integer getRealmNumber() {
        return realmNumber;
    }

    public void setRealmNumber(Integer realmNumber) {
        sharedPrefsEditor.putInt(K_REALM_NUMBER, realmNumber);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        sharedPrefsEditor.putString(K_REGION, region);
    }
}
