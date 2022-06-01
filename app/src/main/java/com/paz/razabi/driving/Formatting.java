package com.paz.razabi.driving;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Formatting {
    static String formatPhone(String phone) {
        if(phone.charAt(0) == '0')
            return phone.replaceFirst("0", "972");
        else if (phone.charAt(0) == '+')
            return phone.replaceFirst("\\+", "");
        return phone;
    }

    static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
}
