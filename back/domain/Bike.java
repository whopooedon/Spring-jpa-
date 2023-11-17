package com.project05.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bike") // 실제 데이터베이스 테이블 이름에 맞게 설정
public class Bike {

    @Id
    @Column(name = "bike_id")
    private String bikeId;

    @Column(name = "bike_addr1")
    private String bikeAddr1;

    @Column(name = "bike_addr2")
    private String bikeAddr2;

    @Column(name = "latitude")
    private String latitude;  // 위도 필드

    @Column(name = "longitude")
    private String longitude; // 경도 필드

    // 기본 생성자
    public Bike() {
    	
    }

    // 매개변수를 가진 생성자
    public Bike(String bikeId, String bikeAddr1, String bikeAddr2, String latitude, String longitude) {
        this.bikeId = bikeId;
        this.bikeAddr1 = bikeAddr1;
        this.bikeAddr2 = bikeAddr2;
        this.latitude = latitude;
        this.longitude = longitude;
    }

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	public String getBikeAddr1() {
		return bikeAddr1;
	}

	public void setBikeAddr1(String bikeAddr1) {
		this.bikeAddr1 = bikeAddr1;
	}

	public String getBikeAddr2() {
		return bikeAddr2;
	}

	public void setBikeAddr2(String bikeAddr2) {
		this.bikeAddr2 = bikeAddr2;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
