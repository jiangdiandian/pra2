package com.lagou.utiles;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utiles {

    public static String Md5(String text,String md5key){
        String s = DigestUtils.md5Hex(text + md5key);
        return s;
    }
    public static boolean verify(String text,String md5key,String afPassWorld){
        String s = DigestUtils.md5Hex(text + md5key);
        if (s.equals(afPassWorld)){
            return true;
        }else {
            return false;
        }
    }
}
