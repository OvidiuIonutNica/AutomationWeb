package util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnvUtils {

    public static final String BASE_URL = "https://www.airbnb.com/";

    static {
        log.info("The base URL is {}", BASE_URL);
    }
}