package com.bradychiu.sc2ladder.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SharedPrefsService {

    private static SharedPrefsService singleton_instance = null;

    private static final String K_ACCESS_TOKEN = "accessToken";
    private static final String K_API_KEY = "apiKey";
    private static final String K_API_SECRET_KEY = "apiSecretKey";
    private static final String K_GAME = "game";
    private static final String K_LADDER_NUMBER = "ladderNumber";
    private static final String K_LOCALE = "locale";
    private static final String K_PROFILE_NAME = "profileName";
    private static final String K_PROFILE_NUMBER = "profileNumber";
    private static final String K_REALM_NUMBER = "realmNumber";
    private static final String K_REGION = "region";
    private static final String K_SCOPES = "scopes";

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor sharedPrefsEditor;

    private SharedPrefsService(Context context) {

        sharedPrefs = context.getSharedPreferences("MyPref", 0);
        sharedPrefsEditor = sharedPrefs.edit();

    }

    public static SharedPrefsService getInstance(Context context) {
        if(singleton_instance == null) singleton_instance = new SharedPrefsService(context);
        return singleton_instance;
    }

    public String getAccessToken() {
        return sharedPrefs.getString(K_ACCESS_TOKEN, null);
    }

    public void setAccessToken(String accessToken) {
        sharedPrefsEditor.putString(K_ACCESS_TOKEN, accessToken).apply();
    }

    public String getApiKey() {
        return sharedPrefs.getString(K_API_KEY, "7a3fdeh3yrzp8d4z94pyz4vwx9h5wgbd");
    }

    public void setApiKey(String apiKey) {
        sharedPrefsEditor.putString(K_API_KEY, apiKey).apply();
    }

    public String getSecretApiKey() {
        return sharedPrefs.getString(K_API_SECRET_KEY, "24tHXguuMFTvJKUfAp2XrxdQw8Nb3v4u");
    }

    public void setSecretApiKey(String apiSecretKey) {
        sharedPrefsEditor.putString(K_API_SECRET_KEY, apiSecretKey).apply();
    }

    public String getGame() {
        return sharedPrefs.getString(K_GAME, "sc2");
    }

    public void setGame(String game) {
        sharedPrefsEditor.putString(K_GAME, game).apply();
    }

    public Integer getLadderNumber() {
        return sharedPrefs.getInt(K_LADDER_NUMBER, 264387);
    }

    public void setLadderNumber(Integer ladderNumber) {
        sharedPrefsEditor.putInt(K_LADDER_NUMBER, ladderNumber).apply();
    }

    public String getLocale() {
        return sharedPrefs.getString(K_LOCALE, "en_US");
    }

    public void setLocale(String locale) {
        sharedPrefsEditor.putString(K_LOCALE, locale).apply();
    }

    public String getProfileName() {
        return sharedPrefs.getString(K_PROFILE_NAME, "LieZ");
    }

    public void setProfileName(String profileName) {
        sharedPrefsEditor.putString(K_PROFILE_NAME, profileName).apply();
    }

    public Integer getProfileNumber() {
        return sharedPrefs.getInt(K_PROFILE_NUMBER, 4014615);
    }

    public void setProfileNumber(Integer profileNumber) {
        sharedPrefsEditor.putInt(K_PROFILE_NUMBER, profileNumber).apply();
    }

    public Integer getRealmNumber() {
        return sharedPrefs.getInt(K_REALM_NUMBER, 1);
    }

    public void setRealmNumber(Integer realmNumber) {
        sharedPrefsEditor.putInt(K_REALM_NUMBER, realmNumber).apply();
    }

    public String getRegion() {
        return sharedPrefs.getString(K_REGION, "us");
    }

    public void setRegion(String region) {
        sharedPrefsEditor.putString(K_REGION, region).apply();
    }

    public Set<String> getScopes() {
        Set<String> defaultScopes = new HashSet<>();
        defaultScopes.addAll(Arrays.asList("sc2.profile"));

        return sharedPrefs.getStringSet(K_SCOPES, defaultScopes);
    }

    public void setScopes(Set<String> scopes) {
        sharedPrefsEditor.putStringSet(K_SCOPES, scopes).apply();
    }
}
