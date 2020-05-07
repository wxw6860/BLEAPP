package com.wxwteam.bleapp.bean;

public class ReadParams extends BaseParams {
    private static ReadParams a;

    private String checkCode;

    private int lockCarPwd;

    private int maxVoltage;

    private int minVoltage;

    private int poleNum;

    private int rimSize;

    private String status;

    private String version;

    public static ReadParams getInstance() {
        if (a == null) {
            synchronized (ReadParams.class) {

                if (a == null)
                    a = new ReadParams();
            }

        }
        return a;

        // Byte code:
        //   0: getstatic com/dracom/android/balancecar/common/bluetooth/a/f.a : Lcom/dracom/android/balancecar/common/bluetooth/a/f;
        //   3: ifnonnull -> 28
        //   6: ldc com/dracom/android/balancecar/common/bluetooth/a/f
        //   8: monitorenter
        //   9: getstatic com/dracom/android/balancecar/common/bluetooth/a/f.a : Lcom/dracom/android/balancecar/common/bluetooth/a/f;
        //   12: ifnonnull -> 25
        //   15: new com/dracom/android/balancecar/common/bluetooth/a/f
        //   18: dup
        //   19: invokespecial <init> : ()V
        //   22: putstatic com/dracom/android/balancecar/common/bluetooth/a/f.a : Lcom/dracom/android/balancecar/common/bluetooth/a/f;
        //   25: ldc com/dracom/android/balancecar/common/bluetooth/a/f
        //   27: monitorexit
        //   28: getstatic com/dracom/android/balancecar/common/bluetooth/a/f.a : Lcom/dracom/android/balancecar/common/bluetooth/a/f;
        //   31: areturn
        //   32: astore_0
        //   33: ldc com/dracom/android/balancecar/common/bluetooth/a/f
        //   35: monitorexit
        //   36: aload_0
        //   37: athrow
        // Exception table:
        //   from	to	target	type
        //   9	25	32	finally
        //   25	28	32	finally
        //   33	36	32	finally
    }

    public String getCheckCode() {
        return this.checkCode;
    }

    public int getLockCarPwd() {
        return this.lockCarPwd;
    }

    public int getMaxVoltage() {
        return this.maxVoltage;
    }

    public int getMinVoltage() {
        return this.minVoltage;
    }

    public int getPoleNum() {
        return this.poleNum;
    }

    public int getRimSize() {
        return this.rimSize;
    }

    public String getStatus() {
        return this.status;
    }

    public String getVersion() {
        return this.version;
    }

    public void setCheckCode(String paramString) {
        this.checkCode = paramString;
    }

    public void setLockCarPwd(int paramInt) {
        this.lockCarPwd = paramInt;
    }

    public void setMaxVoltage(int paramInt) {
        this.maxVoltage = paramInt;
    }

    public void setMinVoltage(int paramInt) {
        this.minVoltage = paramInt;
    }

    public void setPoleNum(int paramInt) {
        this.poleNum = paramInt;
    }

    public void setRimSize(int paramInt) {
        this.rimSize = paramInt;
    }

    public void setStatus(String paramString) {
        this.status = paramString;
    }

    public void setVersion(String paramString) {
        this.version = paramString;
    }

    public String toString() {
        return "ReadParams{minVoltage=" + this.minVoltage + ", maxVoltage=" + this.maxVoltage + ", rimSize=" + this.rimSize + ", poleNum=" + this.poleNum + ", lockCarPwd=" + this.lockCarPwd + ", version='" + this.version + '\'' + ", status='" + this.status + '\'' + ", checkCode='" + this.checkCode + '\'' + '}';
    }
}
