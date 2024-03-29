// UserVerificationService.java
package com.nagarro.miniAssignment2.service;

import com.nagarro.miniAssignment2.apiResponse.GenderResponse;
import com.nagarro.miniAssignment2.apiResponse.NationalityResponse;
import com.nagarro.miniAssignment2.model.UserData;
import com.nagarro.miniAssignment2.model.UserVerificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UserVerificationService {

    private final ExecutorService executorService;
    private final ExternalAPIService externalAPIService;
    private final NationalityVerifierService nationalityVerifierService;
    private final GenderVerifierService genderVerifierService;

    @Autowired
    public UserVerificationService(ExternalAPIService externalAPIService,
                                   NationalityVerifierService nationalityVerifierService,
                                   GenderVerifierService genderVerifierService) {
        this.externalAPIService = externalAPIService;
        this.nationalityVerifierService = nationalityVerifierService;
        this.genderVerifierService = genderVerifierService;
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public Mono<UserVerificationStatus> verifyUser() {
        return externalAPIService.getRandomUser().flatMap(user -> {
            Mono<NationalityResponse> natRespMono = externalAPIService.getNationality(user.getName().getFirst())
                .subscribeOn(Schedulers.fromExecutor(executorService));

            Mono<GenderResponse> genRespMono = externalAPIService.getGender(user.getName().getFirst())
                .subscribeOn(Schedulers.fromExecutor(executorService));

            return Mono.zip(natRespMono, genRespMono)
                       .map(tuple -> {
                           NationalityResponse natResp = tuple.getT1();
                           GenderResponse genResp = tuple.getT2();
                           return verifyUserDetails(user, natResp, genResp);
                       })
                       .onErrorResume(Mono::error); 
        });
    }

    private UserVerificationStatus verifyUserDetails(UserData user, NationalityResponse natResp, GenderResponse genResp) {
        boolean isNationalityMatch = nationalityVerifierService.verifyNationality(user, natResp);
        boolean isGenderMatch = genderVerifierService.verifyGender(user, genResp);
        boolean isVerified = isNationalityMatch && isGenderMatch;

        UserVerificationStatus verificationStatus = new UserVerificationStatus();
        verificationStatus.setVerified(isVerified ? "VERIFIED" : "TO_BE_VERIFIED");
        verificationStatus.setUserData(user);
        return verificationStatus;
    }

    public void shutdownExecutorService() {
        executorService.shutdown();
    }
}
