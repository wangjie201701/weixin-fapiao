package com.wangjie.bill.base.security;

public class HexUtil {

    private static final String HEX_CHARS = "0123456789abcdef";

    public HexUtil() {
    }

    /**
     * Converts a byte array to hex string.
     *
     * @param bytes the input byte array
     * @return hex string representation of b.
     */
    public static String toHexString(byte[] bytes) {
        StringBuilder buffer = new StringBuilder();
        for (byte aByte : bytes) {
            buffer.append(HexUtil.HEX_CHARS.charAt(aByte >>> 4 & 0x0F));
            buffer.append(HexUtil.HEX_CHARS.charAt(aByte & 0x0F));
        }
        return buffer.toString();
    }

    /**
     * Converts a hex string into a byte array.
     *
     * @param string string to be converted
     * @return byte array converted from s
     */
    public static byte[] toByteArray(String string) {
        byte[] buffer = new byte[string.length() / 2];
        int j = 0;
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) ((Character.digit(string.charAt(j++), 16) << 4) | Character.digit(string.charAt(j++), 16));
        }
        return buffer;
    }

    public static String appendParam(String returnStr, String paramId, String paramValue) {
        if (!returnStr.equals("")) {
            if (!paramValue.equals("")) {
                returnStr = returnStr + "&" + paramId + "=" + paramValue;
            }
        } else {
            if (!paramValue.equals("")) {
                returnStr = paramId + "=" + paramValue;
            }
        }
        return returnStr;
    }
}
