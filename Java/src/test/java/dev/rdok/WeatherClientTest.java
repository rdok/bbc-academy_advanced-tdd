package dev.rdok;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class WeatherClientTest
{
    FileCacher fileCacher;
    Forecaster forecaster;
    Forecast forecast;

    @Before
    public void beforeEachTestMethod() {
        fileCacher = Mockito.mock(FileCacher.class);
        forecaster = Mockito.mock(Forecaster.class);
        forecast = Mockito.mock(Forecast.class);
    }


    @Test
    public void it_should_cache_data()
    {
        when(forecast.summary()).thenReturn("cloudy-tdd");
        when(forecaster.forecastFor(Region.LONDON, Day.MONDAY)).thenReturn(forecast);
        when(fileCacher.cache(Region.LONDON, Day.MONDAY)).thenReturn("cloudy-tdd");

        WeatherClient weatherClient = new WeatherClient(forecaster, fileCacher);

        Assert.assertEquals("cloudy-tdd", weatherClient.summary());
        Assert.assertEquals("cloudy-tdd", weatherClient.summary());

        Mockito.verify(forecaster, Mockito.times(1)).forecastFor(Region.LONDON, Day.MONDAY);
        Mockito.verify(forecast, Mockito.times(1)).summary();
        Mockito.verify(fileCacher, Mockito.times(1)).cache(Region.LONDON, Day.MONDAY);
    }

    @Test
    public void it_should_fetch_summary_data()
    {
        when(forecast.summary()).thenReturn("cloudy-tdd");
        when(forecaster.forecastFor(Region.LONDON, Day.MONDAY)).thenReturn(forecast);

        WeatherClient weatherClient = new WeatherClient(forecaster, fileCacher);

        Assert.assertEquals("cloudy-tdd", weatherClient.summary());

        Mockito.verify(forecaster, Mockito.times(1)).forecastFor(Region.LONDON, Day.MONDAY);
        Mockito.verify(forecast, Mockito.times(1)).summary();
    }

}
