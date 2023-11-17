package com.project05.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "culture") // Specify the table name if different from class name
public class Culture {

    @Id
    @Column(name = "culture_seq")
    private String culture_seq; // Assuming the primary key is of type Long

    @Column(name = "culture_name")
    private String culture_name;

    @Column(name = "culture_address")
    private String culture_address;

    @Column(name = "c_latitude")
    private String c_latitude;

    @Column(name = "c_longitude")
    private String c_longitude;

    public Culture() {
    }

    public Culture(String culture_name, String culture_address, String c_latitude, String c_longitude) {
        this.culture_name = culture_name;
        this.culture_address = culture_address;
        this.c_latitude = c_latitude;
        this.c_longitude = c_longitude;
    }

    public String getCulture_seq() {
		return culture_seq;
	}

	public void setCulture_seq(String culture_seq) {
		this.culture_seq = culture_seq;
	}

	public String getCulture_name() {
        return culture_name;
    }

    public void setCulture_name(String culture_name) {
        this.culture_name = culture_name;
    }

    public String getCulture_address() {
        return culture_address;
    }

    public void setCulture_address(String culture_address) {
        this.culture_address = culture_address;
    }

    public String getC_latitude() {
        return c_latitude;
    }

    public void setC_latitude(String c_latitude) {
        this.c_latitude = c_latitude;
    }

    public String getC_longitude() {
        return c_longitude;
    }

    public void setC_longitude(String c_longitude) {
        this.c_longitude = c_longitude;
    }

}
