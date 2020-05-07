package com.wxwteam.bleapp;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleReadCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wxwteam.bleapp.adapter.ScanBleDevicesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_OPEN_GPS = 1;
    Unbinder unbinder = null;

    RxPermissions rxPermissions;
    @BindView(R.id.search)
    Button username;
    @BindView(R.id.lists)
    TextView lists;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    ScanBleDevicesAdapter devicesAdapter;
    List<BleDevice> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        rxPermissions = new RxPermissions(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        list = new ArrayList<>();
        devicesAdapter = new ScanBleDevicesAdapter(this, list);
        recyclerView.setAdapter(devicesAdapter);
        devicesAdapter.setOnDeviceClickListener(new ScanBleDevicesAdapter.OnDeviceClickListener() {
            @Override
            public void onConnect(BleDevice bleDevice) {
                if (!BleManager.getInstance().isConnected(bleDevice)) {
                    BleManager.getInstance().cancelScan();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            connect(bleDevice);
                        }
                    }, 100);
                }
            }

            @Override
            public void onDisConnect(final BleDevice bleDevice) {
                if (BleManager.getInstance().isConnected(bleDevice)) {
                    BleManager.getInstance().disconnect(bleDevice);
                }
            }

            @Override
            public void onDetail(BleDevice bleDevice) {
                if (BleManager.getInstance().isConnected(bleDevice)) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("ble", bleDevice);
                    startActivity(intent);
                }
            }
        });

    }


    @OnClick(R.id.search)
    void click(View view) {
        switch (view.getId()) {
            case R.id.search:
                searchBle();
                break;
        }
    }

    private boolean checkGPSIsOpen() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null)
            return false;
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
    }

    void searchBle() {

//        showMessage("搜索");
        rxPermissions.request(Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now
//                        showMessage("通过了");

//                        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                        if (!BleManager.getInstance().isBlueEnable()) {

                            showMessage("打开蓝牙后再试");
                            return;
                        }

//                        showMessage("蓝牙已开启");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkGPSIsOpen()) {
                            new AlertDialog.Builder(this)
                                    .setTitle("提示")
                                    .setMessage("需要开启定位")
                                    .setNegativeButton("取消",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
//                                                    finish();
                                                }
                                            })
                                    .setPositiveButton("确定",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                                    startActivityForResult(intent, REQUEST_CODE_OPEN_GPS);
                                                }
                                            })

                                    .setCancelable(false)
                                    .show();
                        } else {
                            startScan(true);
                        }

                    } else {
                        showMessage("拒绝了");
                        // Oups permission denied
                    }
                });
    }

    private void startScan(boolean first) {

        if (first) {
            try {
                BleManager.getInstance().cancelScan();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startScan(false);
                    }
                }, 100);
                return;
            }catch (Exception e){

            }

        }

        BleManager.getInstance().scan(new BleScanCallback() {
            @Override
            public void onScanStarted(boolean success) {
                if (isFinishing()) {
                    return;
                }
                list.clear();
                devicesAdapter.notifyDataSetChanged();
                lists.setText("扫描中...");
            }

            @Override
            public void onLeScan(BleDevice bleDevice) {
                if (isFinishing()) {
                    return;
                }
                super.onLeScan(bleDevice);
            }

            @Override
            public void onScanning(BleDevice bleDevice) {
                if (isFinishing()) {
                    return;
                }

                list.add(bleDevice);
                devicesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScanFinished(List<BleDevice> scanResultList) {
                if (isFinishing()) {
                    return;
                }
//                img_loading.clearAnimation();
//                img_loading.setVisibility(View.INVISIBLE);
//                btn_scan.setText(getString(R.string.start_scan));
                lists.setText("扫描结束");
            }
        });
    }


    private void connect(final BleDevice bleDevice) {
        BleManager.getInstance().connect(bleDevice, new BleGattCallback() {
            @Override
            public void onStartConnect() {
                if (isFinishing()) {
                    return;
                }
                lists.setText("连接中...");
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                if (isFinishing()) {
                    return;
                }
                lists.setText("连接失败");

                showMessage("连接失败");
            }

            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                if (isFinishing()) {
                    return;
                }
                lists.setText("连接成功");
                showMessage("连接成功");
                devicesAdapter.notifyDataSetChanged();
//                List<BluetoothGattService> serviceList = gatt.getServices();
////                System.out.println("=========开始=============");
//                for (BluetoothGattService service : serviceList) {
//                    UUID uuid_service = service.getUuid();
////                    System.out.println("============uuid_service======uid====" + uuid_service);
//                    List<BluetoothGattCharacteristic> characteristicList = service.getCharacteristics();
//                    for (BluetoothGattCharacteristic characteristic : characteristicList) {
//                        UUID uuid_chara = characteristic.getUuid();
//
////                        System.out.println("============uuid_service======uid====" + uuid_service + "==========uuid_chara==" + uuid_chara + "=====type=" + characteristic.getProperties());
//
//
//
//
//                    }
//                }
//                System.out.println("========结束=============");


                //0000180f-0000-1000-8000-00805f9b34fb------00002a19-0000-1000-8000-00805f9b34fb
                //=uid====0000ff90-0000-1000-8000-00805f9b34fb==========uuid_chara==0000ff97-0000-1000-8000-00805f9b34fb
//                BleManager.getInstance().read(
//                        bleDevice,
//                        "0000ff90-0000-1000-8000-00805f9b34fb",
//                        "0000ff97-0000-1000-8000-00805f9b34fb",
//                        new BleReadCallback() {
//                            @Override
//                            public void onReadSuccess(byte[] data) {
//                                // 读特征值数据成功
//
//                                System.out.println("====读特征值数据成功===" + toHexString(data));
//                            }
//
//                            @Override
//                            public void onReadFailure(BleException exception) {
//                                if (isFinishing()) {
//                                    return;
//                                }
//                                // 读特征值数据失败
//                                System.out.println("====读特征值数据失败===");
//                            }
//                        });


            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice bleDevice, BluetoothGatt gatt, int status) {
                if (isFinishing()) {
                    return;
                }
                lists.setText("连接断开");
                showMessage("连接断开");
                list.remove(bleDevice);
                devicesAdapter.notifyDataSetChanged();

                if (isActiveDisConnected) {
                    showMessage("active_disconnected");
                } else {
                    showMessage("disconnected");
//                    ObserverManager.getInstance().notifyObserver(bleDevice);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OPEN_GPS) {
            if (checkGPSIsOpen()) {
                startScan(true);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unbinder.unbind();
            unbinder = null;
        } catch (Exception e) {

        }
        try {
            BleManager.getInstance().cancelScan();
            BleManager.getInstance().disconnectAllDevice();
            BleManager.getInstance().destroy();
        } catch (Exception e) {

        }
    }

    void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 字节数组转成16进制表示格式的字符串
     *
     * @param byteArray 需要转换的字节数组
     * @return 16进制表示格式的字符串
     **/
    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }
}
