package com.codebyte.bugservice.util;

import com.github.f4b6a3.ulid.UlidCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Getter
@Slf4j
public class Util {

    public static String getUlid() {
        return UlidCreator.getMonotonicUlid(Instant.now().toEpochMilli()).toRfc4122().toString();
    }
    public static boolean isNullOrEmptyString(String string)
    {
        try
        {
            if (null == string || "".equals(string.trim()) || "null".equals(string))
                return true;
            else
                return false;
        }
        catch(Exception ex)
        {
            log.error("Exception in CommonUtils isNullOrEmptyString {}",ex.getMessage());
            return true;
        }
    }
}
