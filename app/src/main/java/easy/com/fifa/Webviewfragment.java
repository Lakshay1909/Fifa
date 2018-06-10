package easy.com.fifa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Hp on 6/10/2018.
 */

public class Webviewfragment extends Fragment {

WebView w;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview, container, false);

        w=(WebView)view.findViewById(R.id.web);
        w.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSdUNt_o6-DibRM8z1CfSzUs-ScdCRtFjgVXIsfUl1oaFg1OGQ/viewform?c=0&w=1");

        // Enable Javascript
        WebSettings webSettings = w.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        w.setWebViewClient(new WebViewClient());
        return view;

    }
}
