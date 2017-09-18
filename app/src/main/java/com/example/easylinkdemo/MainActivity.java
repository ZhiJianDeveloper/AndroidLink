package com.example.easylinkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mxchip.api.EasyLink;
import com.mxchip.helper.EasyLinkCallBack;
import com.mxchip.helper.EasyLinkParams;

public class MainActivity extends AppCompatActivity {
    private Button startsearch;
    private EditText wifissid;
    private EditText wifipsw;
    private EditText sleeptime;
    public EasyLink elink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifissid = (EditText) findViewById(R.id.wifiName);
        wifissid.setEnabled(false);
        wifipsw = (EditText) findViewById(R.id.wifiPwd);
        startsearch = (Button) findViewById(R.id.linkBtn);
        sleeptime = (EditText) findViewById(R.id.sleeptime);
        elink = new EasyLink(MainActivity.this);
        wifissid.setText(elink.getSSID());

        startsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startsearch.setText("正在连接...");
                String sltStr = sleeptime.getText().toString().trim();
                int sleeptime = Integer.parseInt(sltStr);
                EasyLinkParams easylinkPara = new EasyLinkParams();
                easylinkPara.ssid = wifissid.getText().toString().trim();
                easylinkPara.password = wifipsw.getText().toString().trim();
                easylinkPara.runSecond = 20000;
                easylinkPara.sleeptime = sleeptime;
                //配网成功与否无反馈 通过观察印章灯光声音等判断是否配网成功
                //开始配网
                elink.startEasyLink(easylinkPara, new EasyLinkCallBack() {
                    @Override
                    public void onSuccess(int code, String message) {
                        // loadingMdns();
                    }

                    @Override
                    public void onFailure(int code, String message) {
                    }
                });
                //取消配网
//                elink.stopEasyLink(new EasyLinkCallBack() {
//                    @Override
//                    public void onSuccess(int i, String s) {
//                    }
//
//                    @Override
//                    public void onFailure(int i, String s) {
//                    }
//                });
            }
        });
    }
}
