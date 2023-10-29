package com.example.demo.core.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Paths {
    public static final String COOKIES = "/cookies";
    public static final String RANDOMIZE = "/randomize";
    public static final String COOKIE_ID = COOKIES + "/{id}";
    public static final String RANDOM_ACTIVITY = "/activities";
}
