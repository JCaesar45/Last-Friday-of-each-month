package com.lastfriday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class LastFridayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LastFridayApplication.class, args);
    }

    @GetMapping("/last-friday")
    public ResponseEntity<?> lastFriday(@RequestParam int year, @RequestParam int month) {
        try {
            YearMonth yearMonth = YearMonth.of(year, month);
            int day = yearMonth.atEndOfMonth()
                    .with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY))
                    .getDayOfMonth();

            return ResponseEntity.ok(Map.of(
                    "year", year,
                    "month", month,
                    "lastFriday", day
            ));
        } catch (DateTimeException exc) {
            return ResponseEntity.badRequest().body(Map.of("error", exc.getMessage()));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}
