package com.bradychiu.sc2ladder.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.SyncStats;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.model.oauth.AccessTokenModel;
import com.bradychiu.sc2ladder.model.oauth.SC2OAuthProfileModel;
import com.bradychiu.sc2ladder.utils.SharedPrefsService;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import okhttp3.HttpUrl;

import java.net.URL;
import java.util.Arrays;

public class AccountFragment extends Fragment {

    // bnet oauth documentation: https://dev.battle.net/docs/read/oauth
    // TODO: move all network calls (auth uri call) to background thread
    // TODO: auth code flow requires secret key usage

    private String mAuthCode;
    private AccessTokenModel mAccessToken;
    private AuthorizationCodeFlow mAuthorizationCodeFlow;
    private Context mContext;
    private static final String REDIRECT_URI = "https://dev.battle.net/";
    private SharedPrefsService mSharedPrefsService;
    private TokenResponse mTokenResponse;
    private WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container,false);

        mContext = getActivity();
        mSharedPrefsService = SharedPrefsService.getInstance(mContext);
        mWebView = view.findViewById(R.id.wv_account_login);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("on page finished fired");
                System.out.println("this is return url: " + url);

                if(url.contains("?code=")) {
                    Uri uri = Uri.parse(url);
                    mAuthCode = uri.getQueryParameter("code");

                    AccessToken accessToken = new AccessToken(mAuthorizationCodeFlow, mAuthCode);
                    accessToken.execute();
                }
            }
        });

        mAuthorizationCodeFlow = new AuthorizationCodeFlow.Builder(BearerToken.authorizationHeaderAccessMethod(),
                new NetHttpTransport(),
                new JacksonFactory(),
                new GenericUrl(getTokenUrl()),
                new ClientParametersAuthentication(mSharedPrefsService.getApiKey(), mSharedPrefsService.getSecretApiKey()),
                mSharedPrefsService.getApiKey(),
                getAuthUrl().toString())
                .build();

        String authUrl = mAuthorizationCodeFlow.newAuthorizationUrl()
                .setClientId(mSharedPrefsService.getApiKey())
                .setScopes(mSharedPrefsService.getScopes())
                .setState("asdf")
                .setRedirectUri(REDIRECT_URI)
                .setResponseTypes(Arrays.asList("code"))
                .build();

        mWebView.loadUrl(authUrl);
        System.out.println("auth url called: " + mWebView.getUrl());

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public class AccessToken extends AsyncTask<Void, Void, TokenResponse> {

        private AuthorizationCodeFlow acf;
        private String authCode;
        private TokenResponse tr;

        public AccessToken(AuthorizationCodeFlow acf, String authCode) {
            this.acf = acf;
            this.authCode = authCode;
        }

        @Override
        protected TokenResponse doInBackground(Void... voids) {
            try {
                tr = acf.newTokenRequest(authCode)
                        .setScopes(mSharedPrefsService.getScopes())
                        .setRedirectUri(REDIRECT_URI)
                        .execute();
            } catch(Exception e) {
                e.printStackTrace();
            }

            return tr;
        }

        @Override
        protected void onPostExecute(TokenResponse tr) {
            mTokenResponse = tr;
            mSharedPrefsService.setAccessToken(tr.getAccessToken());
            System.out.println("Access Token: " + mSharedPrefsService.getAccessToken());
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

}
