package com.tht.tht;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.telephony.TelephonyManager;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends Activity {

    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    //String telephonyManager = "123456";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

     //   cd = new ConnectionDetector(getApplicationContext());

    //    isInternetPresent = cd.isConnectingToInternet();

        // check for Internet status
        //if (isInternetPresent) {

       // } else {
            // Internet connection is not present
            // Ask user to connect to Internet
       //     showAlertDialog(MainActivity.this, "No Internet Connection",
      //              "You don't have internet connection.", false);

       // }
        WebView myWebView = (WebView) findViewById(R.id.myWebView);
        myWebView.loadUrl("http://220.247.244.155/admin/index.php?imei=" + telephonyManager.getDeviceId());
        myWebView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
    }

    private WebView mWebView;

    // Use When the user clicks a link from a web page in your WebView
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("220.247.244.155")) {
                return false;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        //alertDialog.setIcon((status) ? R.drawable.succ : R.drawable.fail);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }



    //String mFirstUrlLoaded = "http://www.lotterix.biz/admin/index.php?imei=" + telephonyManager ;


}
