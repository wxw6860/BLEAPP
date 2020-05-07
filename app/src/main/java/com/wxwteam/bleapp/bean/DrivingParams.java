package com.wxwteam.bleapp.bean;

import java.io.Serializable;

public class DrivingParams implements Serializable {
    public static final String STALL_EMPTY = "000";

    public static final String STALL_ONE = "001";

    public static final String STALL_THREE = "011";

    public static final String STALL_TWO = "010";

    private static DrivingParams a;

    private String commond;

    private int currentMiles;

    private int currentRideTime;

    private float currentSpeed;

    private int currentVoltage;

    private double driveAvgSpeed = 0.0D;

    private double driveMaxSpeed = 0.0D;

    private String error;

    private String first;

    private String length;

    private String msgCount;

    private String status;

    private int statusAutoLed;

    private int statusCharge;

    private int statusLed;

    private int statusLock;

    private int statusOff;

    private String statusStall;

    private int totalMiles;

    public static DrivingParams getInstance() {
        // Byte code:
        //   0: getstatic com/dracom/android/balancecar/common/bluetooth/a/d.a : Lcom/dracom/android/balancecar/common/bluetooth/a/d;
        //   3: ifnonnull -> 28
        //   6: ldc com/dracom/android/balancecar/common/bluetooth/a/d
        //   8: monitorenter
        //   9: getstatic com/dracom/android/balancecar/common/bluetooth/a/d.a : Lcom/dracom/android/balancecar/common/bluetooth/a/d;
        //   12: ifnonnull -> 25
        //   15: new com/dracom/android/balancecar/common/bluetooth/a/d
        //   18: dup
        //   19: invokespecial <init> : ()V
        //   22: putstatic com/dracom/android/balancecar/common/bluetooth/a/d.a : Lcom/dracom/android/balancecar/common/bluetooth/a/d;
        //   25: ldc com/dracom/android/balancecar/common/bluetooth/a/d
        //   27: monitorexit
        //   28: getstatic com/dracom/android/balancecar/common/bluetooth/a/d.a : Lcom/dracom/android/balancecar/common/bluetooth/a/d;
        //   31: areturn
        //   32: astore_0
        //   33: ldc com/dracom/android/balancecar/common/bluetooth/a/d
        //   35: monitorexit
        //   36: aload_0
        //   37: athrow
        // Exception table:
        //   from	to	target	type
        //   9	25	32	finally
        //   25	28	32	finally
        //   33	36	32	finally

        if (a == null) {
            synchronized (DrivingParams.class) {

                if (a == null)
                    a = new DrivingParams();
            }

        }
        return a;
    }

    public String getCommond() {
        return this.commond;
    }

    public int getCurrentMiles() {
        return this.currentMiles;
    }

    public int getCurrentRideTime() {
        return this.currentRideTime;
    }

    public float getCurrentSpeed() {
        return this.currentSpeed;
    }

    public int getCurrentVoltage() {
        return this.currentVoltage;
    }

    public double getDriveAvgSpeed() {
        return this.driveAvgSpeed;
    }

    public double getDriveMaxSpeed() {
        return this.driveMaxSpeed;
    }

    public String getError() {
        return this.error;
    }

    public String getFirst() {
        return this.first;
    }

    public String getLength() {
        return this.length;
    }

    public String getMsgCount() {
        return this.msgCount;
    }

    public String getStatus() {
        return this.status;
    }

    public int getStatusAutoLed() {
        return this.statusAutoLed;
    }

    public int getStatusCharge() {
        return this.statusCharge;
    }

    public int getStatusLed() {
        return this.statusLed;
    }

    public int getStatusLock() {
        return this.statusLock;
    }

    public int getStatusOff() {
        return this.statusOff;
    }

    public String getStatusStall() {
        return this.statusStall;
    }

    public int getTotalMiles() {
        return this.totalMiles;
    }

    public void setCommond(String paramString) {
        this.commond = paramString;
    }

    public void setCurrentMiles(int paramInt) {
        this.currentMiles = paramInt;
    }

    public void setCurrentRideTime(int paramInt) {
        this.currentRideTime = paramInt;
    }

    public void setCurrentSpeed(float paramFloat) {
        this.currentSpeed = paramFloat;
    }

    public void setCurrentVoltage(int paramInt) {
        this.currentVoltage = paramInt;
    }

    public void setDriveAvgSpeed(double paramDouble) {
        this.driveAvgSpeed = paramDouble;
    }

    public void setDriveMaxSpeed(double paramDouble) {
        this.driveMaxSpeed = paramDouble;
    }

    public void setError(String paramString) {
        this.error = paramString;
    }

    public void setFirst(String paramString) {
        this.first = paramString;
    }

    public void setLength(String paramString) {
        this.length = paramString;
    }

    public void setMsgCount(String paramString) {
        this.msgCount = paramString;
    }

    public void setStatus(String paramString) {
        this.status = paramString;
    }

    public void setStatusAutoLed(int paramInt) {
        this.statusAutoLed = paramInt;
    }

    public void setStatusCharge(int paramInt) {
        this.statusCharge = paramInt;
    }

    public void setStatusLed(int paramInt) {
        this.statusLed = paramInt;
    }

    public void setStatusLock(int paramInt) {
        this.statusLock = paramInt;
    }

    public void setStatusOff(int paramInt) {
        this.statusOff = paramInt;
    }

    public void setStatusStall(String paramString) {
        this.statusStall = paramString;
    }

    public void setTotalMiles(int paramInt) {
        this.totalMiles = paramInt;
    }

    public String toString() {
        return "DrivingParams{first='" + this.first + '\'' + ", length='" + this.length + '\'' + ", msgCount='" + this.msgCount + '\'' + ", commond='" + this.commond + '\'' + ", currentVoltage=" + this.currentVoltage + ", currentSpeed=" + this.currentSpeed + ", totalMiles=" + this.totalMiles + ", currentMiles=" + this.currentMiles + ", currentRideTime=" + this.currentRideTime + ", status='" + this.status + '\'' + ", statusStall='" + this.statusStall + '\'' + ", statusLock=" + this.statusLock + ", statusCharge=" + this.statusCharge + ", statusLed=" + this.statusLed + ", statusAutoLed=" + this.statusAutoLed + ", statusOff=" + this.statusOff + ", error='" + this.error + '\'' + ", driveAvgSpeed=" + this.driveAvgSpeed + ", driveMaxSpeed=" + this.driveMaxSpeed + '}';
    }
}
