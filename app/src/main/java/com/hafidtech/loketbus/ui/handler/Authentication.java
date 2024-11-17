package com.hafidtech.loketbus.ui.handler;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Authentication {
    String AUTH_3DS = "3ds";
    String AUTH_NONE = "none";
    String AUTH_RBA = "rba";
}

