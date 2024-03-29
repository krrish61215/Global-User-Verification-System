package com.nagarro.miniAssignment2.model;

public class UserVerificationStatus {
    private String isVerified;
    private UserData userData;
	public String isVerified() {
		return isVerified;
	}
	public void setVerified(String isVerified) {
		this.isVerified = isVerified;
	}
	public UserData getUserData() {
		return userData;
	}
	public void setUserData(UserData userData) {
		this.userData = userData;
	}

    
}
