package com.practice.praktika.service;

import com.practice.praktika.client.WeatherClient;
import com.practice.praktika.entity.WeatherEntity;
import com.practice.praktika.model.Weather;
import com.practice.praktika.repository.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@EnableScheduling
@Service
public class TestService {

    private final WeatherRepo weatherRepo;
    private final WeatherClient weatherClient;

    public List<WeatherEntity> test() {
        return (List<WeatherEntity>) weatherRepo.findAll();
    }

    @Cacheable("weathers")
    public Optional<WeatherEntity> getById(Long id) {
        return weatherRepo.findById(id);
    }

    @Value("${api.key}")
    String api_key;

    @Scheduled(fixedDelay = 3600000)
    public void updateTable() {

            List<Weather> weathers = null;

            if (weatherRepo.count() < weatherClient.rowsCount(api_key)) {
                weathers = weatherClient.getStopListRules(api_key, weatherRepo.count());

                for (Weather weather : weathers) {

                    WeatherEntity weatherEntity = new WeatherEntity();
                    weatherEntity.setForecastDate(weather.getCells().getForecastDate());
                    weatherEntity.setMinimumTemperature(weather.getCells().getMinimumTemperature());
                    weatherEntity.setMaximumTemperature(weather.getCells().getMaximumTemperature());
                    weatherEntity.setNotes(weather.getCells().getNotes());
                    weatherRepo.save(weatherEntity);
                }
            }
    }

    public String getCount(){
        return Long.toString(weatherRepo.count());
    }

    @Autowired
    public TestService(WeatherClient weatherClient, WeatherRepo weatherRepo) {
        this.weatherClient = weatherClient;
        this.weatherRepo = weatherRepo;
    }

}
