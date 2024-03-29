package com.nagarro.miniAssignment2.service;

import com.nagarro.miniAssignment2.apiResponse.GenderResponse;
import com.nagarro.miniAssignment2.apiResponse.NationalityResponse;
import com.nagarro.miniAssignment2.apiResponse.RandomUserApiResponse;
import com.nagarro.miniAssignment2.exception.InvalidInputException;
import com.nagarro.miniAssignment2.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@Service
public class ExternalAPIService {

    private final WebClient webClientRandomUser;
    private final WebClient webClientNationalize;
    private final WebClient webClientGenderize;

    public ExternalAPIService(WebClient webClientRandomUser, WebClient webClientNationalize, WebClient webClientGenderize) {
        this.webClientRandomUser = webClientRandomUser;
        this.webClientNationalize = webClientNationalize;
        this.webClientGenderize = webClientGenderize;
    }

    public <T> Mono<T> makeApiCall(WebClient webClient, String uri, Class<T> responseType) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorMap(WebClientResponseException.class, ex -> handleWebClientResponseException(ex));
    }

    private Throwable handleWebClientResponseException(WebClientResponseException ex) {
        String timestamp = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        InvalidInputException invalidInputException = new InvalidInputException(
            "Error during WebClient call: " + ex.getMessage(),
            ex.getRawStatusCode(),
            timestamp
        );
        return invalidInputException;
    }


    public Mono<UserData> getRandomUser() {
        return makeApiCall(webClientRandomUser, "/api", RandomUserApiResponse.class)
                .map(response -> response.getResults().get(0));
    }

    public Mono<NationalityResponse> getNationality(String name) {
        return makeApiCall(webClientNationalize, "/?name=" + name, NationalityResponse.class);
    }

    public Mono<GenderResponse> getGender(String name) {
        return makeApiCall(webClientGenderize, "/?name=" + name, GenderResponse.class);
    }
}
