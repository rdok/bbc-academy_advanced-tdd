package dev.rdok;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class WeatherClient implements ClientAdapter
{
    private Forecaster forecaster;
    private FileCacher fileCacher;

    public WeatherClient(Forecaster forecaster, FileCacher fileCacher)
    {
        this.forecaster = forecaster;
        this.fileCacher = fileCacher;
    }

    public String summary()
    {
        String key = String.format(
            "%s-%s-%s",
            this.getClass().getSimpleName(),
            Region.LONDON,
            Day.MONDAY
        );

        return this.fileCacher.has(key)
            ? this.fileCacher.get(key)
            : this.fileCacher.put(key, this.fetchSummary());

    }

    private String fetchSummary() {

        Forecast forecast = this.forecaster.forecastFor(Region.LONDON, Day.MONDAY);

        return forecast.summary();
    }
}
