package com.project05.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_register") // Specify the table name if different from class name
public class User {

    @Id
    @Column(name = "userid")
    private String userid; // Assuming the primary key is of type Long

    @Column(name = "userpw")
    private String userpw;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "regdate")
    private String regdate;

    public User() {
        // Default constructor for JPA and Jackson
    }

    public User(String userid, String userpw, String name, String gender, String address, String phone_number, String regdate) {
        this.userid = userid;
        this.userpw = userpw;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phone_number = phone_number;
        this.regdate = regdate;
    }

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

}
