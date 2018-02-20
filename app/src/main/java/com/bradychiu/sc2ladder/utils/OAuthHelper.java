package com.bradychiu.sc2ladder.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.api.CommunityOAuthProfileApi;
import com.bradychiu.sc2ladder.model.oauth.SC2OAuthProfileCharacterModel;
import com.bradychiu.sc2ladder.model.oauth.SC2OAuthProfileModel;
import com.bradychiu.sc2ladder.ui.ProfileFragment;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.net.URL;
import java.util.Arrays;

public class OAuthHelper {

    // TODO: merge profile calls from oauth + profile fragment so there is only 1 call instead of 2

    private AuthorizationCodeFlow mAuthorizationCodeFlow;
    private Context mContext;
    private static final String REDIRECT_URI = "https://dev.battle.net/";
    private SharedPrefsService mSharedPrefsService;

    public OAuthHelper(Context context) {
        mContext = context;
        mSharedPrefsService = SharedPrefsService.getInstance(mContext);
    }

    private void setupAuthCodeFlow() {
        mAuthorizationCodeFlow = new AuthorizationCodeFlow.Builder(BearerToken.authorizationHeaderAccessMethod(),
                new NetHttpTransport(),
                new JacksonFactory(),
                new GenericUrl(getTokenUrl()),
                new ClientParametersAuthentication(mSharedPrefsService.getApiKey(), mSharedPrefsService.getSecretApiKey()),
                mSharedPrefsService.getApiKey(),
                getAuthUrl().toString())
                .build();
    }

    public String getAuthCall() {
        setupAuthCodeFlow();
        return mAuthorizationCodeFlow.newAuthorizationUrl()
                .setClientId(mSharedPrefsService.getApiKey())
                .setScopes(mSharedPrefsService.getScopes())
                .setState("asdf")
                .setRedirectUri(REDIRECT_URI)
                .setResponseTypes(Arrays.asList("code"))
                .build();
    }

    public void retrieveAndStoreAccessToken(String authCode) {
        AccessToken accessToken = new AccessToken(mAuthorizationCodeFlow, authCode);
        accessToken.execute();
    }

    public class AccessToken extends AsyncTask<Void, Void, TokenResponse> {

        private AuthorizationCodeFlow authorizationCodeFlow;
        private String authCode;
        private TokenResponse tokenResponse;

        public AccessToken(AuthorizationCodeFlow authorizationCodeFlow, String authCode) {
            this.authorizationCodeFlow = authorizationCodeFlow;
            this.authCode = authCode;
        }

        @Override
        protected TokenResponse doInBackground(Void... voids) {
            try {
                tokenResponse = authorizationCodeFlow.newTokenRequest(authCode)
                        .setScopes(mSharedPrefsService.getScopes())
                        .setRedirectUri(REDIRECT_URI)
                        .execute();
            } catch(Exception e) {
                e.printStackTrace();
            }

            return tokenResponse;
        }

        @Override
        protected void onPostExecute(TokenResponse tokenResponse) {
            updateToken(tokenResponse);
            updateProfileSharedPrefs();
        }

    }

    private URL getAuthUrl() {
        return new HttpUrl.Builder()
                .scheme("https")
                .host(mSharedPrefsService.getRegion() + ".battle.net")
                .addPathSegments("oauth/authorize")
                .build().url();
    }

    private URL getTokenUrl() {
        return new HttpUrl.Builder()
                .scheme("https")
                .host(mSharedPrefsService.getRegion() + ".battle.net")
                .addPathSegments("oauth/token")
                .build().url();
    }

    public void updateToken(TokenResponse tokenResponse) {
        mSharedPrefsService.setAccessToken(tokenResponse.getAccessToken());
        mSharedPrefsService.setAccessTokenExpires((System.currentTimeMillis() / 1000) + tokenResponse.getExpiresInSeconds());
    }

    public void updateProfileSharedPrefs () {
        Retrofit retrofit = RetrofitUtil.getRetrofit(mContext);
        CommunityOAuthProfileApi communityOAuthProfileApi = retrofit.create(CommunityOAuthProfileApi.class);
        Call<SC2OAuthProfileModel> sc2OAuthProfileModelCall = communityOAuthProfileApi.getSC2OAuthProfile(mSharedPrefsService.getAccessToken());

        sc2OAuthProfileModelCall.enqueue(new Callback<SC2OAuthProfileModel>() {
            @Override
            public void onResponse(Call<SC2OAuthProfileModel> call, Response<SC2OAuthProfileModel> response) {
                if(response.isSuccessful()) {
                    SC2OAuthProfileCharacterModel firstCharacter = response.body().characters().get(0);

                    mSharedPrefsService.setProfileNumber(firstCharacter.id());
                    mSharedPrefsService.setProfileName(firstCharacter.displayName());
                    mSharedPrefsService.setRealmNumber(firstCharacter.realm());

                    getProfileFragment();

                } else {
                    // TODO: error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<SC2OAuthProfileModel> call, Throwable throwable) {
                // TODO: something went completely south (like no internet connection)
                Log.d("Error", throwable.getMessage());
            }
        });
    }

    public void getProfileFragment() {
        FragmentManager fragmentManager = ((Activity) mContext).getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        try {
            fragment = ProfileFragment.class.newInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }
        fragmentTransaction.replace(R.id.fl_content, fragment);
        fragmentTransaction.commit();
    }

}
