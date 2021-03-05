package com.practice.praktika.controller;

import com.practice.praktika.entity.WeatherEntity;
import com.practice.praktika.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private TestService testService;

    @GetMapping
    public ResponseEntity<List<WeatherEntity>> getWeatherJSON(@RequestHeader("eTag") String etag) {
        if(etag.equals(testService.getCount())){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        } else {
            return ResponseEntity.ok()
                    .eTag(testService.getCount())
                    .body(testService.test());
        }
    }

    @GetMapping("/{id}")
    public Optional<WeatherEntity> takeOne(@PathVariable Long id) {
        return testService.getById(id);
    }
}
