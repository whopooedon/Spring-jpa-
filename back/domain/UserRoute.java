package com.project05.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_route") // Adjust the table name as per your database schema
public class UserRoute {
    
    @Id
    @Column(name = "route_seq") // Assuming route_seq is your primary key
    private int routeSeq;

    @Column(name = "userid")
    private String userid;

    @Column(name = "b_mark")
    private String b_mark;

    @Column(name = "c_mark")
    private String c_mark;

    @Column(name = "b_latitude")
    private Double b_latitude;

    @Column(name = "b_longitude")
    private Double b_longitude;

    @Column(name = "c_latitude")
    private Double c_latitude;

    @Column(name = "c_longitude")
    private Double c_longitude;

    public UserRoute() {
        // Default constructor for JPA and Jackson
    }

    public UserRoute(int routeSeq, String userid, String b_mark, String c_mark, Double b_latitude, Double b_longitude, Double c_latitude, Double c_longitude) {
        this.routeSeq = routeSeq;
        this.userid = userid;
        this.b_mark = b_mark;
        this.c_mark = c_mark;
        this.b_latitude = b_latitude;
        this.b_longitude = b_longitude;
        this.c_latitude = c_latitude;
        this.c_longitude = c_longitude;
    }

	public int getRouteSeq() {
		return routeSeq;
	}

	public void setRouteSeq(int routeSeq) {
		this.routeSeq = routeSeq;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getB_mark() {
		return b_mark;
	}

	public void setB_mark(String b_mark) {
		this.b_mark = b_mark;
	}

	public String getC_mark() {
		return c_mark;
	}

	public void setC_mark(String c_mark) {
		this.c_mark = c_mark;
	}

	public Double getB_latitude() {
		return b_latitude;
	}

	public void setB_latitude(Double b_latitude) {
		this.b_latitude = b_latitude;
	}

	public Double getB_longitude() {
		return b_longitude;
	}

	public void setB_longitude(Double b_longitude) {
		this.b_longitude = b_longitude;
	}

	public Double getC_latitude() {
		return c_latitude;
	}

	public void setC_latitude(Double c_latitude) {
		this.c_latitude = c_latitude;
	}

	public Double getC_longitude() {
		return c_longitude;
	}

	public void setC_longitude(Double c_longitude) {
		this.c_longitude = c_longitude;
	}

}
