package com.example.jhyangnewthings.api.nacos.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author jhYang
 * @Date 2021/7/26 0026 15:23
 * @Discription todo
 */
public class UserOfNacosUtil {
    public static void main(String[] args) {
        ModifiyPassword("1234356");
    }

    public static void ModifiyPassword(String password){
       String encoder = new BCryptPasswordEncoder().encode(password);
        System.out.println(encoder);
    }
}
