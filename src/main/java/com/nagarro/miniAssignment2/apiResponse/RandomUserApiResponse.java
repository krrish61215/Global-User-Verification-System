package com.nagarro.miniAssignment2.apiResponse;
import java.util.List;

import com.nagarro.miniAssignment2.model.UserData;

public class RandomUserApiResponse {
    private List<UserData> results;

	public RandomUserApiResponse(List<UserData> results) {
		
		this.results = results;
	}
	  public RandomUserApiResponse() {
	        super();
	    }
	public List<UserData> getResults() {
		return results;
	}

	public void setResults(List<UserData> results) {
		this.results = results;
	}

    
}
