package com.blogspot.atifsoftwares.networconnectionstatus;

import android.content.Context;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Views
    ImageView mConStatusIv;
    TextView mConStatusTv;
    Button mConStatusBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link views with xml
        mConStatusIv = findViewById(R.id.conStatusIv);
        mConStatusTv = findViewById(R.id.conStatusTv);
        mConStatusBtn = findViewById(R.id.conStatusBtn);

        //button click to check network status
        mConStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //function call to check network connection status
                checkNetworkConnectionStatus();
            }
        });

    }

    private void checkNetworkConnectionStatus() {
        boolean wifiConnected;
        boolean mobileConnected;
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()){ //connected with either mobile or wifi
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected){ //wifi connected
                mConStatusIv.setImageResource(R.drawable.ic_action_wifi);
                mConStatusTv.setText("Connected with Wifi");
            }
            else if (mobileConnected){ //mobile data connected
                mConStatusIv.setImageResource(R.drawable.ic_action_mobile);
                mConStatusTv.setText("Connected with Mobile Data Connection");
            }
        }
        else { //no internet connection
            mConStatusIv.setImageResource(R.drawable.ic_action_no);
            mConStatusTv.setText("No internet connection");
        }
    }
}
/*In this tutorial we will check internet connection like:
 * No internet connection
 * Connected with Wifi
 * Connected with Mobile Data
 *
 * So lets design our ui first
 * Add icons for network status icons
 * Add ACCESS_NETWORK_STATE permission in manifest*/