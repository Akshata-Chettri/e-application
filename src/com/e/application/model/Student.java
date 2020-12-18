package com.e.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "student")
@Component
public class Student {

	@Id
	private int uniqueid;
	private String firstname;
	private String lastname;
	private String bonafideCertificate;
	private String transferCertificate;
	private String staffBonafideStatus;
	private String staffTransferStatus;
	private String principalBonafideStatus;
	private String principalTransferStatus;

	public int getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(int uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBonafideCertificate() {
		return bonafideCertificate;
	}

	public void setBonafideCertificate(String bonafideCertificate) {
		this.bonafideCertificate = bonafideCertificate;
	}

	public String getTransferCertificate() {
		return transferCertificate;
	}

	public void setTransferCertificate(String transferCertificate) {
		this.transferCertificate = transferCertificate;
	}

	public String getStaffBonafideStatus() {
		return staffBonafideStatus;
	}

	public void setStaffBonafideStatus(String staffBonafideStatus) {
		this.staffBonafideStatus = staffBonafideStatus;
	}

	public String getStaffTransferStatus() {
		return staffTransferStatus;
	}

	public void setStaffTransferStatus(String staffTransferStatus) {
		this.staffTransferStatus = staffTransferStatus;
	}

	public String getPrincipalBonafideStatus() {
		return principalBonafideStatus;
	}

	public void setPrincipalBonafideStatus(String principalBonafideStatus) {
		this.principalBonafideStatus = principalBonafideStatus;
	}

	public String getPrincipalTransferStatus() {
		return principalTransferStatus;
	}

	public void setPrincipalTransferStatus(String principalTransferStatus) {
		this.principalTransferStatus = principalTransferStatus;
	}

}
