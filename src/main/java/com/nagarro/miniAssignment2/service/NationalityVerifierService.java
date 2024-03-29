// NationalityVerifierService.java
package com.nagarro.miniAssignment2.service;

import com.nagarro.miniAssignment2.apiResponse.NationalityResponse;
import com.nagarro.miniAssignment2.model.UserData;
import org.springframework.stereotype.Service;

@Service
public class NationalityVerifierService {

    public boolean verifyNationality(UserData user, NationalityResponse natResp) {
        return natResp.getCountry().stream()
            .anyMatch(countryProb -> countryProb.getCountry_id().equalsIgnoreCase(user.getNat()));
    }
}
