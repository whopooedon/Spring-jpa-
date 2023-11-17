package com.project05.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address") // 실제 데이터베이스 테이블 이름에 맞게 설정
public class Address {

    @Id
    @Column(name = "addr_seq")
    private String addrSeq;

    @Column(name = "addr1")
    private String addr1;

    @Column(name = "addr2")
    private String addr2;

    // 기본 생성자
    public Address() {
    	
    }

    // 매개변수를 가진 생성자
    public Address(String addrSeq, String addr1, String addr2) {
        this.addrSeq = addrSeq;
        this.addr1 = addr1;
        this.addr2 = addr2;
    }

	public String getAddrSeq() {
		return addrSeq;
	}

	public void setAddrSeq(String addrSeq) {
		this.addrSeq = addrSeq;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

}

