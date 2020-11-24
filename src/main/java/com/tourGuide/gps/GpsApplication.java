package com.tourGuide.gps;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import gpsUtil.GpsUtil;

@EnableFeignClients
@SpringBootApplication
public class GpsApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        SpringApplication.run(GpsApplication.class, args);
    }

    @Bean
    public GpsUtil getGpsUtil() {
        return new GpsUtil();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
