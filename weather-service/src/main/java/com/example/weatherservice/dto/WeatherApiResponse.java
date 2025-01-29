package com.example.weatherservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherApiResponse {

    private Location location;
    private Current current;

    @Data
    public static class Location {
        private String name;
    }

    @Data
    public static class Current {
        @JsonProperty("temp_c")
        private Double tempC;

        private Condition condition;

        private Integer cloud;

        private Integer humidity;

        @JsonProperty("is_day")
        private Integer isDay;

        @Data
        public static class Condition {
            private String text;
        }
    }
}
