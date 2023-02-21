package com.xt.sampleffmpegrtspplay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xt.ijkplayer.rtsp.RtspPlayer;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();

    @Nullable
    private Timer timer;

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

        final TextView btn_start_tick = findViewById(R.id.btn_start_tick);
        btn_start_tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) {
                    stopTick();
                    btn_start_tick.setText("开始计时");
                } else {
                    startTick();
                    btn_start_tick.setText("暂停计时");
                }
            }
        });
    }

    private void startTick() {
        final TextView tv_tick = findViewById(R.id.tv_tick);
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                Log.d(TAG, "run: " + System.currentTimeMillis());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        long currentTime = System.currentTimeMillis();

//                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//                        Date date = new Date(currentTime);
//                        tv_tick.setText(formatter.format(date));

                        long time = currentTime / 1000;
                        long time_ms = currentTime % 1000;
                        String ms = String.format("%03d", time_ms);

                        long time_h = time % 3600;
                        long time_m = time_h / 60;
                        long time_s = time_h % 60;
                        String mm = time_m > 9 ? time_m + "" : "0" + time_m;
                        String ss = time_s > 9 ? time_s + "" : "0" + time_s;
                        tv_tick.setText(mm + ":" + ss + "." + ms);
                    }
                });
            }
        }, 0, 1);
    }

    private void stopTick() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
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
//                }
//            });
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTick();
    }
}
