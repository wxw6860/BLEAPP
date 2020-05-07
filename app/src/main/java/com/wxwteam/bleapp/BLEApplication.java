package com.wxwteam.bleapp;

import android.app.Application;

import com.clj.fastble.BleManager;

public class BLEApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }


    private void init(){

        BleManager.getInstance().init(this);
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1, 5000)
                .setConnectOverTime(20000)
                .setOperateTimeout(5000);
    }


}
