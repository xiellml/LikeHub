package com.pn.sie.likehub.xutil;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p> 不可逆（Message Digest，消息摘要算法）
 * Description: TODO
 * 另外: byte与base64/hex16 string的互转
 * https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/src/main/java/com/blankj/utilcode/util/EncryptUtils.java
 * </p>
 */
public class Digest {
    private Digest() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * @param data      参数为byte数组
     * @param algorithm MD5/SHA1
     * @return byte数组
     */
    public static byte[] hashByte(byte[] data, String algorithm) {
        if (data == null || data.length <= 0) return null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String hashHexVal(byte[] data, String algorithm) {
        byte[] resultBytes = hashByte(data, algorithm);
        //转化为16进制String形式的hash值
        StringBuilder builder = new StringBuilder();
        if (resultBytes == null) return builder.toString();
        for (byte resultByte : resultBytes) {
            if (Integer.toHexString(0xFF & resultByte).length() == 1) {
                builder.append("0").append(
                        Integer.toHexString(0xFF & resultByte));
            } else {
                builder.append(Integer.toHexString(0xFF & resultByte));
            }
        }
        return builder.toString();
    }

    /**
     * @param context 上下文
     * @param appId   BuildConfig.APPLICATION_ID
     * @return 签名信息
     */
    public static String signatureHash(Context context, String appId) {
        // Add code to print out the key hash
        String keyHash = null;
        try {
            PackageInfo info = context.getApplicationContext().getPackageManager().
                    getPackageInfo(appId, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.d("KeyHash:", keyHash);
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyHash;
    }
}