package com.wxwteam.bleapp.bean;

import java.io.Serializable;

public class BleCarParams implements Serializable {
    private static BleCarParams a;

    private String carSerialNumber;

    public static BleCarParams getmInstance() {
        if (a == null) {
            synchronized (BleCarParams.class) {

                if (a == null)
                    a = new BleCarParams();
            }

        }
        return a;
        // Byte code:
        //   0: getstatic com/dracom/android/balancecar/common/bluetooth/a/c.a : Lcom/dracom/android/balancecar/common/bluetooth/a/c;
        //   3: ifnonnull -> 28
        //   6: ldc com/dracom/android/balancecar/common/bluetooth/a/c
        //   8: monitorenter
        //   9: getstatic com/dracom/android/balancecar/common/bluetooth/a/c.a : Lcom/dracom/android/balancecar/common/bluetooth/a/c;
        //   12: ifnonnull -> 25
        //   15: new com/dracom/android/balancecar/common/bluetooth/a/c
        //   18: dup
        //   19: invokespecial <init> : ()V
        //   22: putstatic com/dracom/android/balancecar/common/bluetooth/a/c.a : Lcom/dracom/android/balancecar/common/bluetooth/a/c;
        //   25: ldc com/dracom/android/balancecar/common/bluetooth/a/c
        //   27: monitorexit
        //   28: getstatic com/dracom/android/balancecar/common/bluetooth/a/c.a : Lcom/dracom/android/balancecar/common/bluetooth/a/c;
        //   31: areturn
        //   32: astore_0
        //   33: ldc com/dracom/android/balancecar/common/bluetooth/a/c
        //   35: monitorexit
        //   36: aload_0
        //   37: athrow
        // Exception table:
        //   from	to	target	type
        //   9	25	32	finally
        //   25	28	32	finally
        //   33	36	32	finally
    }

    public static void setNull() {
        if (a != null)
            a = null;
    }

    public String getCarSerialNumber() {
        return (this.carSerialNumber == null) ? "" : this.carSerialNumber;
    }

    public void setCarSerialNumber(String paramString) {
        this.carSerialNumber = paramString;
    }
}
