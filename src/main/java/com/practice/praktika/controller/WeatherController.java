package com.practice.praktika.controller;

import com.practice.praktika.entity.WeatherEntity;
import com.practice.praktika.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private TestService testService;

    @GetMapping
    public ResponseEntity<List<WeatherEntity>> getWeatherJSON() {
        return ResponseEntity.ok()
        .body(testService.test());
    }

    @GetMapping("/{id}")
    public Optional<WeatherEntity> deleteUser(@PathVariable Long id) {
        return testService.getById(id);
    }
}
