package dev.rdok;

import com.weather.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * integration: contract test.
 */
public class ForecasterTest
{
    @Test
    public void it_should_fetch_summary() {
        Forecaster forecaster = new Forecaster();

        Forecast forecast = forecaster.forecastFor(Region.LONDON, Day.MONDAY);

        Assert.assertFalse(forecast.summary().isEmpty());
    }
}
