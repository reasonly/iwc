package com.iworkcloud.util;


import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.util.Arrays;

import static org.apache.commons.codec.binary.Base64.encodeBase64String;

public class MD5Util {

    /**
     * 构造6位盐值随机数
     * @return salt盐值
     */
    public static String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[15];
        random.nextBytes(bytes);
        String salt = encodeBase64String(bytes).substring(0,6);//截取6位

        System.out.println("MD5Util.ganerateSalt" + "----bytes值= " +Arrays.toString(bytes));
        System.out.println("MD5Util.ganerateSalt" + "----salt= " +salt);

        return salt;
    }

    /**
     *  md5+salt
     * @param inputPass ,salt
     * @return DBPass
     */
    public static String inputPassToDBPass(String inputPass, String salt) {
        String str = inputPass +salt;
        System.out.println("MD5Util.inputPassToDBPass" + "----组合密码inputPass +salt= " + str);
        return DigestUtils.md5Hex(str);
    }

    public static void main(String[] args) {
        //ganerateSalt
        System.out.println("MD5Util.main" + "----Salt值= " + generateSalt());
        //加密后的密码值
        System.out.println(inputPassToDBPass("123456",generateSalt()));
    }

}
