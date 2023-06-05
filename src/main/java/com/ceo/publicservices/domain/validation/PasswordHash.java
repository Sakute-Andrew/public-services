package com.ceo.publicservices.domain.validation;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class PasswordHash {

    public static String hashedPassword(String password){
        return  Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

}
