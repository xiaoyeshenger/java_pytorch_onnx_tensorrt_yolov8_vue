package cn.kafuka.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {

    //利用java原生的类实现SHA256加密
    public static String getSHA256(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    //将byte转为16进制
    private static String byte2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String temp = null;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    public static String getSHA256Salt(String pwd, String salt) {
        if (ObjUtil.isEmpty(pwd)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        if (ObjUtil.isEmpty(salt)) {
            throw new IllegalArgumentException("盐值不能为空");
        }
        return DigestUtils.sha256Hex(pwd + salt);
    }

/*    public static void main(String[] args) {
        String ori1 = getSHA256Salt("123456", "kfk20ac23sj99kfk");
        String ori2 = getSHA256Salt("test123", "kfk20ac23sj99kfk");
        System.out.println("1.-->"+ori1);
        System.out.println("2.-->"+ori2);
    }*/

}

