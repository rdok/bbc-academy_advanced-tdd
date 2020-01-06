package dev.rdok;

import com.weather.Day;
import com.weather.Region;

public class FileCacher
{
    public String cache(String key, Runnable callback)
    {
        String value = callback.run();

        return null;
    }
}
