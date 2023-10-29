package com.example.demo.configuration.integration;

import com.example.demo.core.model.Activity;
import com.example.demo.core.model.ActivityTypes;
import com.example.demo.exceptions.boredexceptions.BadRequestBoredApiException;
import com.example.demo.exceptions.boredexceptions.NotFoundBoredApiException;
import com.example.demo.exceptions.boredexceptions.UnavialableBoredApiException;
import static com.example.demo.core.utils.Constants.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class BoredApiService {

    static final String URL = "http://www.boredapi.com";
    static final String API_PATH = "/api/activity";

    public Activity getActivity(String type, Long participants) {
        String path = URL + API_PATH;
        RestTemplate restTemplate = new RestTemplate();

        if (!ActivityTypes.isPresent(type.toUpperCase())){
            throw new BadRequestBoredApiException();
        }

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(path)
                .queryParam(TYPE, type.toLowerCase())
                .queryParam(PARTICIPANTS, participants);

        ResponseEntity response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, Activity.class);

        if (response.getBody().toString().contains("null")) {
            throw new NotFoundBoredApiException();
        }
        if (response.getStatusCode().is5xxServerError()) {
            throw new UnavialableBoredApiException();
        }
        return (Activity) response.getBody();
    }
}
