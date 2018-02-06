package com.mr.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.mr.entity.Employee;
import com.mr.util.Json;

public interface EmpService {

	public Json checkEmpLogin(Employee emp,HttpSession session);
	
}
