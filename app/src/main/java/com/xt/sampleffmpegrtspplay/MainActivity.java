package com.xt.sampleffmpegrtspplay;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xt.ijkplayer.rtsp.RtspPlayer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_connect = findViewById(R.id.btn_connect);
        final EditText et_server_path = findViewById(R.id.et_server_path);
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = et_server_path.getText().toString();
                RtspPlayer.url = path;
                toRtspLive();
            }
        });

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void toRtspLive() {
//        if (Build.VERSION.SDK_INT >= 21) {
//            final ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkRequest.Builder    builder             = new NetworkRequest.Builder();
//
//            // 设置指定的网络传输类型(蜂窝传输) 等于手机网络
//            builder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI);
//
//            // 设置感兴趣的网络功能
//            // builder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
//
//            // 设置感兴趣的网络：计费网络
//            // builder.addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
//
//            NetworkRequest request = builder.build();
//            ConnectivityManager.NetworkCallback callback = new ConnectivityManager.NetworkCallback() {
//                @TargetApi(Build.VERSION_CODES.M)
//                @Override
//                public void onAvailable(Network network) {
//                    super.onAvailable(network);
//
//                    // 可以通过下面代码将app接下来的请求都绑定到这个网络下请求
//                    if (Build.VERSION.SDK_INT >= 23) {
//                        connectivityManager.bindProcessToNetwork(network);
//                    } else {
//                        // 23后这个方法舍弃了
//                        ConnectivityManager.setProcessDefaultNetwork(network);
//                    }
//                    connectivityManager.unregisterNetworkCallback(this);
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            RtspLiveActivity.start(MainActivity.this);
//                            finish();
//                        }
//                    });
//                }
//            };
//            connectivityManager.requestNetwork(request, callback);
//        }else{
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
                    RtspLiveActivity.start(MainActivity.this);
                    finish();
//                }
//            });
//        }
    }
}
