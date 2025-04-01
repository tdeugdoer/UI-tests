package utils;

import lombok.experimental.UtilityClass;

import java.time.Duration;

@UtilityClass
public class TestConstant {
    public final String EMAIL_SUFFIX = "@a.a";
    public final Duration DEFAULT_EXPLICIT_DURATION = Duration.ofMinutes(3);
    public final String BASE_URL = "https://pizzeria.skillbox.cc/";

}
