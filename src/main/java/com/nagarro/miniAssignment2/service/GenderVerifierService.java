// GenderVerifierService.java
package com.nagarro.miniAssignment2.service;

import com.nagarro.miniAssignment2.apiResponse.GenderResponse;
import com.nagarro.miniAssignment2.model.UserData;
import org.springframework.stereotype.Service;

@Service
public class GenderVerifierService {

    public boolean verifyGender(UserData user, GenderResponse genResp) {
        return user.getGender().equalsIgnoreCase(genResp.getGender());
    }
}

