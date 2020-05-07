package com.wxwteam.bleapp.utils;

public final class DataDealUtil {
    private static int a(int var0) throws Exception {
        int var1 = 1;
        int var2 = 1;
        if (var0 < 0) {
            throw new Exception("nCount can't small than 1!");
        } else {
            if (var0 != 0) {
                int var3 = 0;

                while (true) {
                    var2 = var1;
                    if (var3 >= var0) {
                        break;
                    }

                    ++var3;
                    var1 <<= 4;
                }
            }

            return var2;
        }
    }

    public static byte[] a(String var0) {
        byte[] var7;
        if (var0 != null && !var0.equals("")) {
            var0 = var0.toUpperCase();
            int var1 = var0.length() / 2;
            char[] var2 = var0.toCharArray();
            byte[] var3 = new byte[var1];
            int var4 = 0;

            while (true) {
                var7 = var3;
                if (var4 >= var1) {
                    break;
                }

                int var5 = var4 << 1;
                byte var6 = (byte) "0123456789ABCDEF".indexOf(var2[var5]);
                var3[var4] = (byte) ((byte) ((byte) "0123456789ABCDEF".indexOf(var2[var5 + 1]) | var6 << 4));
                ++var4;
            }
        } else {
            var7 = null;
        }

        return var7;
    }

    public static String[] a(byte[] var0) {
        String[] var6;
        if (var0 != null && var0.length != 0) {
            StringBuilder var1 = new StringBuilder();
            int var2 = var0.length;
            boolean var3 = true;

            for (int var4 = 0; var4 < var2; var3 = false) {
                byte var5 = var0[var4];
                if (!var3) {
                    var1.append(",");
                }

                var1.append("0123456789ABCDEF".charAt((var5 & 240) >> 4));
                var1.append("0123456789ABCDEF".charAt(var5 & 15));
                ++var4;
            }

            var6 = var1.toString().trim().split(",");
        } else {
            var6 = null;
        }

        return var6;
    }

    public static String b(String var0) {
        String var1;
        if (var0 != null && var0.length() % 2 == 0) {
            String var2 = "";
            int var3 = 0;

            while (true) {
                var1 = var2;
                if (var3 >= var0.length()) {
                    break;
                }

                var1 = "0000" + Integer.toBinaryString(Integer.parseInt(var0.substring(var3, var3 + 1), 16));
                var2 = var2 + var1.substring(var1.length() - 4);
                ++var3;
            }
        } else {
            var1 = null;
        }

        return var1;
    }

    public static int c(String var0) {
        byte var1 = 0;
        byte var2 = 0;
        int var3;
        if (var0.length() <= 2 || var0.charAt(0) != '0' || var0.charAt(1) != 'X' && var0.charAt(1) != 'x') {
            var3 = 0;
        } else {
            var3 = 2;
        }

        boolean var11;
        while (true) {
            if (var3 >= var0.length()) {
                var11 = true;
                break;
            }

            char var4 = var0.charAt(var3);
            if ((var4 < '0' || var4 > '9') && (var4 < 'A' || var4 > 'F') && (var4 < 'a' || var4 > 'f')) {
                var11 = false;
                break;
            }

            ++var3;
        }

        int var8;
        if (!var11) {
            var8 = var2;
        } else {
            String var5 = var0.toUpperCase();
            var0 = var5;
            if (var5.length() > 2) {
                var0 = var5;
                if (var5.charAt(0) == '0') {
                    var0 = var5;
                    if (var5.charAt(1) == 'X') {
                        var0 = var5.substring(2);
                    }
                }
            }

            int var12 = var0.length();
            int var10 = 0;
            var3 = var1;

            while (true) {
                var8 = var3;
                if (var10 >= var12) {
                    break;
                }

                label77:
                {
                    Exception var13;
                    Exception var10000;
                    label124:
                    {
                        char var9 = var0.charAt(var12 - var10 - 1);
                        boolean var10001;
                        if (var9 >= '0' && var9 <= '9') {
                            var8 = var9 - 48;
                        } else if (var9 >= 'a' && var9 <= 'f') {
                            var8 = var9 - 97 + 10;
                        } else {
                            if (var9 < 'A' || var9 > 'F') {
                                try {
                                    var13 = new Exception("error param");
                                    throw var13;
                                } catch (Exception var6) {
                                    var10000 = var6;
                                    var10001 = false;
                                    break label124;
                                }
                            }

                            var8 = var9 - 65 + 10;
                        }

                        try {
                            var8 = var3 + var8 * a(var10);
                        } catch (Exception var7) {
                            var10000 = var7;
                            var10001 = false;
                            break label124;
                        }

                        var3 = var8;
                        break label77;
                    }

                    var13 = var10000;
                    var13.printStackTrace();
                }

                ++var10;
            }
        }

        return var8;
    }
}
