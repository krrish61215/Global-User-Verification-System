package com.nagarro.miniAssignment2.model;


public class UserData {
    private String gender;
    private Name name;
    private String email;
    private Dob dob;
    private String nat; 
    
	public UserData() {
		super();
		
	}
	
	public UserData(String gender, Name name, String email, Dob dob, String nat) {
		super();
		this.gender = gender;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.nat = nat;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Dob getDob() {
		return dob;
	}
	public void setDob(Dob dob) {
		this.dob = dob;
	}
	public String getNat() {
		return nat;
	}
	public void setNat(String nat) {
		this.nat = nat;
	}

   


public static class Name {
    private String title;
    private String first;
    private String last;

    
    public String getFullName() {
        return first + " " + last;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

    
}

 public static class Dob {
	
    public Dob(String date, int age) {
		super();
		this.date = date;
		this.age = age;
	}
	public Dob() {
		super();
		
	}
	private String date;
    private int age;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

   
    
}}
