package com.sjr.handlerdemo;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pb;
    private TextView tvPb;
    private static final int FLAGS = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FLAGS:
                    pb.setProgress(msg.arg1);
                    tvPb.setText(String.valueOf("正在下载" + msg.arg1));
                    break;
                default:
                    break;
            }

        }
    };
//    private Handler mHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            switch (msg.what) {
//                case FLAGS:
//                    pb.setProgress(msg.arg1);
//                    tvPb.setText(String.valueOf(msg.arg1));
//
//                    break;
//                default:
//                    break;
//            }
//            return true;
//        }
//    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = (ProgressBar) findViewById(R.id.pb);
        tvPb = (TextView) findViewById(R.id.tv_pb);
        tvPb.setText("aaa");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    Message msg = mHandler.obtainMessage();
                    SystemClock.sleep(200);
                    msg.what = FLAGS;
                    msg.arg1 = i;
                    mHandler.sendMessage(msg);
                }
            }
        }).start();
    }
}

