package com.example.demo.configuration.integration;

import com.example.demo.core.model.Activity;
import com.example.demo.core.utils.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoredApiController {
    private final BoredApiService boredApiService;

    @GetMapping(Paths.RANDOM_ACTIVITY)
    @ResponseStatus(HttpStatus.OK)
    public Activity getActivity(@RequestParam String type,
                                @RequestParam Long participants) {
        return boredApiService.getActivity(type, participants);
    }
}