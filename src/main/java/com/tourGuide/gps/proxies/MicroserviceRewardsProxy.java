package com.tourGuide.gps.proxies;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "microservice-rewards", url = "${proxy.rewards}")
public interface MicroserviceRewardsProxy {

    @GetMapping("/getAttractionRewards/{attractionId}/{userId}")
    public int getAttractionRewards(
            @PathVariable("attractionId") final UUID attractionId,
            @PathVariable("userId") final UUID userId);

}
