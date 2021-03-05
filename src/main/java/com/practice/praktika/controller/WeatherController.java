package com.practice.praktika.controller;

import com.practice.praktika.entity.WeatherEntity;
import com.practice.praktika.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private TestService testService;

    @GetMapping
    public ResponseEntity<List<WeatherEntity>> getWeatherJSON() {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(3600, TimeUnit.SECONDS))
                .body(testService.test());
    }

    @GetMapping("/{id}")
    public Optional<WeatherEntity> takeOne(@PathVariable Long id) {
        return testService.getById(id);
    }
}
