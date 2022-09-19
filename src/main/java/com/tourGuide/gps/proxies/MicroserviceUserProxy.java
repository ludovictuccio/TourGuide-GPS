package com.tourGuide.gps.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tourGuide.gps.domain.dto.UserDto;

@FeignClient(value = "microservice-users", url = "${proxy.user}")
public interface MicroserviceUserProxy {

    @GetMapping("/getUserDto/{userName}")
    public UserDto getUserDto(@PathVariable("userName") String userName);

}
