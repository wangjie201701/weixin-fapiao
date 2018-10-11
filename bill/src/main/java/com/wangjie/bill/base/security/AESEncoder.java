package com.wangjie.bill.base.security;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES对称加密解密类
 */
public final class AESEncoder {

    private static final Logger LOG = LoggerFactory.getLogger(AESEncoder.class);

    private final static String ENCRYPT_TYPE = "AES";
    private final static String RAW_KEY = "ASCII";

    public AESEncoder() {
    }

    /**
     * 解密方法
     *
     * @param src 密文
     * @param key 密钥 必须是16位长度的
     * @return 解密后的字符串
     */
    public static String decrypt(String src, String key) {
        if (StringUtils.isNotBlank(src) && StringUtils.isNotBlank(key)) {
            try {
                if (key.length() != 16) {
                    return null;
                }
                byte[] raw = key.getBytes(RAW_KEY);
                SecretKeySpec skeySpec = new SecretKeySpec(raw, ENCRYPT_TYPE);
                Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                byte[] encrypted = hex2byte(src);
                byte[] original = cipher.doFinal(encrypted);
                return new String(original);
            } catch (Exception ex) {
                LOG.error("AESEncoder!decrypt, ERROR, ", ex);
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 加密方法
     *
     * @param src 原文
     * @param key 密钥 必须是16位长度的
     * @return 加密后的字符串
     * @throws UnsupportedEncodingException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String encrypt(String src, String key) throws UnsupportedEncodingException,
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        if (StringUtils.isNotBlank(src) && StringUtils.isNotBlank(key)) {
            if (key.length() != 16) {
                return null;
            }
            byte[] raw = key.getBytes(RAW_KEY);
            SecretKeySpec keySpec = new SecretKeySpec(raw, ENCRYPT_TYPE);
            Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(src.getBytes());
            return byte2hex(encrypted).toLowerCase();
        } else {
            return null;
        }
    }

    private static byte[] hex2byte(String hex) {
        if (hex == null) {
            return null;
        }
        int l = hex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] bytes = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }

    private static String byte2hex(byte[] bytes) {
        String hs = "";
        String temp;
        for (byte aByte : bytes) {
            temp = (Integer.toHexString(aByte & 0XFF));
            if (temp.length() == 1) {
                hs = hs + "0" + temp;
            } else {
                hs = hs + temp;
            }
        }
        return hs.toUpperCase();
    }
}