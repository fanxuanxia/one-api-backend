package com.example.oneapibackend.utils;

import org.springframework.util.DigestUtils;

public class Encipher {
    private static final String PRE = "123";
    private static final String END = "456";
    public static String doEncipher(String password){
        return  DigestUtils.md5DigestAsHex(password.getBytes());
    }


    public static void main(String[] args) {

        String md5Password = doEncipher("fxxqwer1345");
        System.out.println(md5Password);
    }
}
