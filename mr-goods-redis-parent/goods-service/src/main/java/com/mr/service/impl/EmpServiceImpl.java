package com.mr.service.impl;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mr.entity.Employee;
import com.mr.mapper.EmployeeMapper;
import com.mr.service.EmpService;
import com.mr.util.Json;
import com.mr.util.RedisUtil;

@Service
public class EmpServiceImpl implements EmpService{
	
	@Autowired
	private EmployeeMapper empMapper;

	@Override
	public Json checkEmpLogin(Employee emp,HttpSession session) {
		Json json = new Json();
		//根据账号密码进行数据库查询，并接收返回值
		Employee returnEmp = empMapper.selectByCodePass(emp);
		if(returnEmp == null){
			//说明账号或密码错误
			json.setMsg("账号或密码错误");
			json.setSuccess(false);
		}else{
			json.setMsg("登录成功！");
			//登录成功后，将用户信息放入redis中，并指定生命周期
			//session.setAttribute("login_user", returnEmp);
			//将sessionId作为键，用户对象作为值存储在缓存中
			RedisUtil.setObject(session.getId(), returnEmp);
			RedisUtil.setObjectLife(session.getId(), 30);
			json.setSuccess(true);
		}
		return json;
	}

}
