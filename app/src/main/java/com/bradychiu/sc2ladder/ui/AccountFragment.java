package com.bradychiu.sc2ladder.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.utils.OAuthHelper;
import com.bradychiu.sc2ladder.utils.SharedPrefsService;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.TokenResponse;

public class AccountFragment extends Fragment {

    // bnet oauth documentation: https://dev.battle.net/docs/read/oauth
    // TODO: check if access token is expired; if not, skip oauth
    // TODO: hide redirect page
    // TODO: auth code flow requires secret key usage

    private Context mContext;
    private OAuthHelper mOAuthHelper;
    private static final String REDIRECT_URI = "https://dev.battle.net/";
    private SharedPrefsService mSharedPrefsService;
    private WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container,false);

        mContext = getActivity();
        mOAuthHelper = new OAuthHelper(mContext);
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

                if(url.contains("?code=")) {
                    Uri uri = Uri.parse(url);
                    String authCode= uri.getQueryParameter("code");

                    mOAuthHelper.retrieveAndStoreAccessToken(authCode);
                }

                // TODO: make this wait until access token is set in shareprefs
                getProfileFragment();
            }
        });

        mWebView.loadUrl(mOAuthHelper.getAuthCall());

        return view;
    }

    private void getProfileFragment() {
        FragmentManager fragmentManager = getFragmentManager();
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
