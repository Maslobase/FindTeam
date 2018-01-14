package com.maslobase.findteam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SteamSignInActivity extends AppCompatActivity {

    final String REALM_PARAM = "FindTeam";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url,
                                      Bitmap favicon) {

                setTitle(url);
                Uri Url = Uri.parse(url);

                if (Url.getAuthority().equals(REALM_PARAM.toLowerCase())) {
                    webView.stopLoading();

                    Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
                    String userId = userAccountUrl.getLastPathSegment();

                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                }
            }
        });
        setContentView(webView);

        String url2 = "https://steamcommunity.com/openid/login?" +
                "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.mode=checkid_setup&" +
                "openid.ns=http://specs.openid.net/auth/2.0&" +
                "openid.realm=https://" + REALM_PARAM + "&" +
                "openid.return_to=https://" + REALM_PARAM + "/signin/";

        webView.loadUrl(url2);

    }
}



