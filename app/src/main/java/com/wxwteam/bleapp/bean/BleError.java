package com.wxwteam.bleapp.bean;

import android.content.Context;

import java.io.Serializable;

public enum BleError implements Serializable {
    ACCHANDLE(11, "加速手柄故障"),
    BRAKEHANDLE(10, "刹车手柄故障"),
    DIANJI(1, "电机故障"),
    DOWNBRIDGE(2, "下桥故障"),
    DRIVERBOARD(8, "驱动板通信故障"),
    DUZHUAN(4, "堵转保护"),
    FLOW(7, "过流保护"),
    OTHER(0, "其它故障"),
    OVERVOLTAGE(5, "过压保护"),
    OWEVOLTAGE(6, "欠压保护"),
    SOFTERROR(9, "软件故障"),
    UPBRIDGE(3, "上桥故障");

    private int type;

    private String value = "其他";


    BleError(int paramInt1, String paramString1) {
        this.type = paramInt1;
        this.value = paramString1;
    }


    public static BleError getString(int paramInt) {
        switch (paramInt) {
            default:
                return null;
            case 0:
                return OTHER;
            case 1:
                return DIANJI;
            case 2:
                return DOWNBRIDGE;
            case 3:
                return UPBRIDGE;
            case 4:
                return DUZHUAN;
            case 5:
                return OVERVOLTAGE;
            case 6:
                return OWEVOLTAGE;
            case 7:
                return FLOW;
            case 8:
                return DRIVERBOARD;
            case 9:
                return SOFTERROR;
            case 10:
                return BRAKEHANDLE;
            case 11:
                return ACCHANDLE;
        }
    }

    public final int getType() {
        return this.type;
    }

    public final String getValue(Context paramContext) {
        return this.value;
//    switch (this.type) {
//      default:
//        return "";
//      case 1:
//        return paramContext.getString(2131165221);
//      case 2:
//        return paramContext.getString(2131165216);
//      case 3:
//        return paramContext.getString(2131165227);
//      case 4:
//        return paramContext.getString(2131165225);
//      case 5:
//        return paramContext.getString(2131165217);
//      case 6:
//        return paramContext.getString(2131165219);
//      case 7:
//        return paramContext.getString(2131165222);
//      case 8:
//        return paramContext.getString(2131165218);
//      case 9:
//        return paramContext.getString(2131165223);
//      case 10:
//        return paramContext.getString(2131165226);
//      case 11:
//        return paramContext.getString(2131165224);
//      case 12:
//        break;
//    }
//    return paramContext.getString(2131165220);
    }

    public final String toString() {
        return "BleError{type=" + this.type + ", value='" + this.value + '\'' + '}';
    }
}

