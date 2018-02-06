package com.mr.entity;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable{
    private static final long serialVersionUID = 6177098212888811096L;

	private Integer empId;

    private Date empAgoLoginDate;

    private String empCode;

    private Date empLoginDate;

    private String empName;

    private String empPass;

    private Integer empPassErr;

    private String phoneNumber;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Date getEmpAgoLoginDate() {
        return empAgoLoginDate;
    }

    public void setEmpAgoLoginDate(Date empAgoLoginDate) {
        this.empAgoLoginDate = empAgoLoginDate;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode == null ? null : empCode.trim();
    }

    public Date getEmpLoginDate() {
        return empLoginDate;
    }

    public void setEmpLoginDate(Date empLoginDate) {
        this.empLoginDate = empLoginDate;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmpPass() {
        return empPass;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass == null ? null : empPass.trim();
    }

    public Integer getEmpPassErr() {
        return empPassErr;
    }

    public void setEmpPassErr(Integer empPassErr) {
        this.empPassErr = empPassErr;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empAgoLoginDate="
				+ empAgoLoginDate + ", empCode=" + empCode + ", empLoginDate="
				+ empLoginDate + ", empName=" + empName + ", empPass="
				+ empPass + ", empPassErr=" + empPassErr + ", phoneNumber="
				+ phoneNumber + "]";
	}
}