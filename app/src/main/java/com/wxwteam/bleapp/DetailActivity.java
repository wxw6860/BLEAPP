package com.wxwteam.bleapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleReadCallback;
import com.clj.fastble.callback.BleRssiCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.wxwteam.bleapp.bean.DrivingParams;
import com.wxwteam.bleapp.bean.ReadParams;
import com.wxwteam.bleapp.utils.DataDealUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DetailActivity extends AppCompatActivity {
    Unbinder unbinder = null;

    @BindView(R.id.read)
    Button read;

    @BindView(R.id.write)
    Button write;

    @BindView(R.id.notify)
    Button notify;
    @BindView(R.id.rssi)
    Button rssi;

    @BindView(R.id.read_result)
    TextView read_result;

    @BindView(R.id.erite_result)
    TextView erite_result;

    @BindView(R.id.notify_result)
    TextView notify_result;
    BleDevice bleDevice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        unbinder = ButterKnife.bind(this);
        bleDevice = getIntent().getParcelableExtra("ble");

        if (bleDevice == null) {
            showMessage("异常");
            finish();
            return;
        }


    }


    @OnClick({R.id.read,
            R.id.write,
            R.id.write2,
            R.id.write3,
            R.id.notify,
            R.id.level1,
            R.id.level2,
            R.id.level3,
            R.id.light0,
            R.id.light1,
            R.id.light2,
            R.id.lock,
            R.id.unlock,
            R.id.power,
            R.id.rssi

    })
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.read: {
                BleManager.getInstance().read(
                        bleDevice,
//                        "0000ff90-0000-1000-8000-00805f9b34fb",
//                        "0000ff97-0000-1000-8000-00805f9b34fb",
                        "0000ff90-0000-1000-8000-00805f9b34fb",
                        "0000ff97-0000-1000-8000-00805f9b34fb",
                        new BleReadCallback() {
                            @Override
                            public void onReadSuccess(byte[] data) {
                                // 读特征值数据成功

                                System.out.println("====读特征值数据成功===" + toHexString(data));
                            }

                            @Override
                            public void onReadFailure(BleException exception) {
                                // 读特征值数据失败
                                System.out.println("====读特征值数据失败===");
                            }
                        });

                break;
            }
            case R.id.write: {//200L
                ArrayList var1 = new ArrayList();
                var1.add(165);
                var1.add(5);
                var1.add(g());
                var1.add(145);
                sendData(a(var1));
            }
            break;
            case R.id.write2: {//1000L
                ArrayList var1 = new ArrayList();
                var1.add(165);
                var1.add(5);
                var1.add(g());
                var1.add(129);
                sendData(a(var1));
            }
            break;
            case R.id.write3: {//1500L,1000Lreturn
                ArrayList var1 = new ArrayList();
                var1.add(165);
                var1.add(5);
                var1.add(g());
                var1.add(131);
                sendData(a(var1));
            }
            break;
            case R.id.notify: {
                BleManager.getInstance().notify(
                        bleDevice,
                        "0000ffe0-0000-1000-8000-00805f9b34fb",
                        "0000ffe4-0000-1000-8000-00805f9b34fb",
//                                    uuid_service.toString(),
//                                    uuid_chara.toString(),
                        new BleNotifyCallback() {
                            @Override
                            public void onNotifySuccess() {
                                // 打开通知操作成功

                                System.out.println("===打开通知操作成功==");


                            }

                            @Override
                            public void onNotifyFailure(BleException exception) {
                                // 打开通知操作失败
                                System.out.println("===打开通知操作失败==");
                            }

                            @Override
                            public void onCharacteristicChanged(byte[] data) {
                                // 打开通知后，设备发过来的数据将在这里出现

                                System.out.println("====发过来的数据===" + data);


                                for (byte a : data) {
                                    System.out.println("========发来的数据=a===" + a);
//                                    System.out.println("========发来的数据=a==="+new String(a));
                                }

                                System.out.println("====FFE4数据读取=======" + new String(data));
                                if ((data[3] & 255) == 130) {
//                                com.dracom.android.balancecar.common.bluetooth.h.c(var2);

                                    System.out.println("====FFE4数据读取===条件2====" + toHexString(data));
                                    ReadParams readParams = dealReadParams(data);

                                    System.out.println("====解析成功2===" + readParams);
                                }
//
                                if ((data[3] & 255) == 132) {

                                    System.out.println("====FFE4数据读取===条件3====" + toHexString(data));

                                    DrivingParams drivingParams = dealDrivingParams(data);


                                    System.out.println("====解析成功3===" + drivingParams);


//                                d var3 = com.dracom.android.balancecar.common.bluetooth.h.b(var2);
//                                Intent var4 = new Intent(var0);
//                                var4.putExtra("com.dracom.android.balancecar.EXTRA_UUID_CHAR", var1.getUuid().toString());
//                                var4.putExtra("com.dracom.android.balancecar.EXTRA_DATA_RAW", var3);
//                                CarApplication.b().sendBroadcast(var4);
                                }
//
                                if ((data[3] & 255) == 146) {

                                    System.out.println("====FFE4数据读取===条件1====" + toHexString(data));
                                    String number = dealInfoNumber(data);

                                    System.out.println("====解析成功1=== number = " + number);
//                                com.dracom.android.balancecar.common.bluetooth.h.a(var2);
                                }
                            }
                        });
                break;
            }
            case R.id.rssi: {
                BleManager.getInstance().readRssi(
                        bleDevice,
                        new BleRssiCallback() {

                            @Override
                            public void onRssiFailure(BleException exception) {
                                // 读取设备的信号强度失败

                                System.out.println("====读取设备的信号强度失败===");

                            }

                            @Override
                            public void onRssiSuccess(int rssi) {
                                // 读取设备的信号强度成功

                                System.out.println("====读取设备的信号强度成功===" + rssi);
                            }
                        });
                break;
            }
            case R.id.power: {
                BleManager.getInstance().read(
                        bleDevice,
                        "0000180f-0000-1000-8000-00805f9b34fb",
                        "00002a19-0000-1000-8000-00805f9b34fb",
                        new BleReadCallback() {
                            @Override
                            public void onReadSuccess(byte[] data) {
                                // 读特征值数据成功

                                System.out.println("====读特征值数据成功===" + new String(data));
                                System.out.println("====读特征值数据成功===" + toHexString(data));
                                System.out.println("====读特征值数据成功===" + Arrays.asList(a(data)));
                            }

                            @Override
                            public void onReadFailure(BleException exception) {
                                // 读特征值数据失败
                                System.out.println("====读特征值数据失败===");
                            }
                        });
            }
            break;
            case R.id.level1:
                sePowerLevel(1);
                break;
            case R.id.level2:
                sePowerLevel(2);
                break;
            case R.id.level3:
                sePowerLevel(3);
                break;
            case R.id.light0:
                setLight(0);
                break;
            case R.id.light1:
                setLight(1);
                break;
            case R.id.light2:
                setLight(2);
                break;
            case R.id.lock:
                lockOrUnlok(1);
                break;
            case R.id.unlock:
                lockOrUnlok(2);
                break;
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
            BleManager.getInstance().stopNotify(bleDevice,
                    "0000ffe0-0000-1000-8000-00805f9b34fb",
                    "0000ffe4-0000-1000-8000-00805f9b34fb");
        } catch (Exception e) {

        }
    }


    /**
     * 解析接受到的数据为 DrivingParams
     *
     * @param data
     * @return
     */
    DrivingParams dealDrivingParams(byte[] data) {
        String[] var1 = a(data);
        DrivingParams var4;
        if (var1 != null && var1.length != 0) {
            if (var1.length != 18) {
                var4 = null;
            } else {
                var4 = DrivingParams.getInstance();
                StringBuilder var2 = new StringBuilder();
                var2.append(var1[4]).append(var1[5]);
                var4.setCurrentVoltage(DataDealUtil.c(var2.toString()));
                var4.setCurrentSpeed((float) (DataDealUtil.c(var1[6]) | (DataDealUtil.c(var1[7]) & 240) << 4));
                var2.setLength(0);
                var2.append(DataDealUtil.c(var1[7]) & 15);
                var2.append(var1[8]);
                var2.append(var1[9]);
                var4.setTotalMiles(DataDealUtil.c(var2.toString()));
                var2.setLength(0);
                var2.append(var1[10]).append(var1[11]);
                var4.setCurrentMiles(DataDealUtil.c(var2.toString()));
                var2.setLength(0);
                var2.append(var1[12]).append(var1[13]);
                var4.setCurrentRideTime(DataDealUtil.c(var2.toString()));
                String var3 = DataDealUtil.b(var1[14]);
                var4.setStatus(var3);
                var4.setStatusLock(DataDealUtil.c(var3.substring(0, 1)));
                var4.setStatusCharge(DataDealUtil.c(var3.substring(1, 2)));
                var4.setStatusLed(DataDealUtil.c(var3.substring(2, 3)));
                var4.setStatusAutoLed(DataDealUtil.c(var3.substring(3, 4)));
                var4.setStatusOff(DataDealUtil.c(var3.substring(4, 5)));
                var4.setStatusStall(var3.substring(5, 8));
                var2.setLength(0);
                var2.append(DataDealUtil.b(var1[15]));
                var2.append(DataDealUtil.b(var1[16]));
                var4.setError(var2.toString());
            }
        } else {
            var4 = null;
        }


        return var4;
    }

    ReadParams dealReadParams(byte[] data) {

        String[] var1 = a(data);
        ReadParams var5;
        if (var1 != null && var1.length != 0) {
            var5 = ReadParams.getInstance();
            var5.setMinVoltage(DataDealUtil.c(var1[4]));
            var5.setMaxVoltage(DataDealUtil.c(var1[5]));
            var5.setRimSize(DataDealUtil.c(var1[6]));
            var5.setPoleNum(DataDealUtil.c(var1[7]));
            int var2 = DataDealUtil.c(var1[8]);
            int var3 = DataDealUtil.c(var1[9]);
            StringBuilder var4 = new StringBuilder();
            var5.setLockCarPwd((var2 & 255) << 8 | var3 & 255);
            var4.setLength(0);
            var4.append(var1[13]).append(var1[14]);
            var5.setVersion(var4.toString());
            var5.setStatus(DataDealUtil.b(var1[15]));
            var5.setCheckCode(var1[16]);
        } else {
            var5 = null;
        }

        return var5;

    }

    String dealInfoNumber(byte[] data) {
        if (data != null && data.length != 0) {
            StringBuilder var1 = new StringBuilder();

            for (int var2 = 4; var2 < 20; ++var2) {
                if (data[var2] != 0) {
                    var1.append((char) (data[var2] & 255));
                } else {
                    if (var2 != 15) {
                        break;
                    }

                    var1.append(" ");
                }
            }

            String var4 = var1.toString().trim();
            String var5 = var4;
            if (var4.length() > 15) {
                var5 = var4.substring(0, 15).trim();
            }

            return var5;
        }
        return "";
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


    void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 数字转码16进制字节码
     *
     * @param paramList
     * @return
     */
    private static byte[] a(List<Integer> paramList) {
        if (paramList.size() <= 0)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < paramList.size()) {
            int k = ((Integer) paramList.get(i)).intValue() & 0xFF;
            String str4 = Integer.toHexString(k & 0xFF);
            String str3 = str4;
            if (str4.length() < 2)
                str3 = "0" + str4;
            stringBuilder.append(str3);
            j += k;
            i++;
        }
        String str2 = Integer.toHexString(j & 0xFF);
        String str1 = str2;
        if (str2.length() < 2)
            str1 = "0" + str2;
        stringBuilder.append(str1);
        return a(stringBuilder.toString());
    }


    public static byte[] a(String paramString) {
        if (paramString == null || paramString.equals(""))
            return null;
        paramString = paramString.toUpperCase();
        int j = paramString.length() / 2;
        char[] arrayOfChar = paramString.toCharArray();
        byte[] arrayOfByte = new byte[j];
        int i = 0;
        while (true) {
            byte[] arrayOfByte1 = arrayOfByte;
            if (i < j) {
                int k = i << 1;
                byte b = (byte) "0123456789ABCDEF".indexOf(arrayOfChar[k]);
                arrayOfByte[i] = (byte) ((byte) "0123456789ABCDEF".indexOf(arrayOfChar[k + 1]) | b << 4);
                i++;
                continue;
            }
            return arrayOfByte1;
        }
    }

    /**
     * 接收字节数组数据转换为字符串数组
     *
     * @param paramArrayOfbyte
     * @return
     */
    public static String[] a(byte[] paramArrayOfbyte) {
        if (paramArrayOfbyte == null || paramArrayOfbyte.length == 0)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        int j = paramArrayOfbyte.length;
        boolean bool = true;
        int i = 0;
        while (i < j) {
            byte b = paramArrayOfbyte[i];
            if (!bool)
                stringBuilder.append(",");
            stringBuilder.append("0123456789ABCDEF".charAt((b & 0xF0) >> 4));
            stringBuilder.append("0123456789ABCDEF".charAt(b & 0xF));
            i++;
            bool = false;
        }
        return stringBuilder.toString().trim().split(",");
    }

    public static String b(String paramString) {
        if (paramString == null || paramString.length() % 2 != 0)
            return null;
        String str = "";
        int i = 0;
        while (true) {
            String str1 = str;
            if (i < paramString.length()) {
                str1 = "0000" + Integer.toBinaryString(Integer.parseInt(paramString.substring(i, i + 1), 16));
                str = str + str1.substring(str1.length() - 4);
                i++;
                continue;
            }
            return str1;
        }
    }


    private static int b = -1;

    private static int g() {
        if (b > 254) {
            b = 0;
            return b;
        }
        b++;
        return b;
    }


    /**
     * 设置档位
     *
     * @param var0 1,2,3
     */
    private void sePowerLevel(int var0) {
        ArrayList<Integer> var9 = new ArrayList();
        var9.add(165);
        var9.add(6);
        var9.add(g());
        var9.add(135);
        var9.add(var0);
        byte[] data = a(var9);
        sendData(data);
    }

    /**
     * 设置车灯
     *
     * @param var0 0自动，1打开，2关闭
     */
    private void setLight(int var0) {
        ArrayList var2 = new ArrayList();
        var2.add(165);
        var2.add(6);
        var2.add(g());
        var2.add(137);
        var2.add(var0);
        byte[] data = a(var2);
        sendData(data);

    }


    /**
     * 设置车灯
     *
     * @param var0 1锁车，2关闭
     */
    private void lockOrUnlok(int var0) {

        int var3 = Integer.parseInt("3214");
        ArrayList var11 = new ArrayList();
        var11.add(165);
        var11.add(8);
        var11.add(g());
        var11.add(133);
        var11.add(var0 == 1 ? 49 : 48);
        var11.add(var3 >> 8 & 255);
        var11.add(var3 & 255);

        byte[] data = a(var11);
        sendData(data);

    }


    /**
     * 发送数据
     *
     * @param data
     */
    private void sendData(byte[] data) {
        BleManager.getInstance().write(
                bleDevice,
                "0000ffe5-0000-1000-8000-00805f9b34fb",
                "0000ffe9-0000-1000-8000-00805f9b34fb",
                data,
                new BleWriteCallback() {
                    @Override
                    public void onWriteSuccess(int current, int total, byte[] justWrite) {
                        // 发送数据到设备成功（分包发送的情况下，可以通过方法中返回的参数可以查看发送进度）

                        System.out.println("===发送数据到设备成功==current==" + current + "==total==" + total + "====" + toHexString(justWrite));
                    }

                    @Override
                    public void onWriteFailure(BleException exception) {
                        // 发送数据到设备失败


                        System.out.println("===发送数据到设备失败==" + exception);
                    }
                });
    }

}
