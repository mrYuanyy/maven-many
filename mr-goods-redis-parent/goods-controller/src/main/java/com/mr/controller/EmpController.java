package com.mr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mr.entity.Employee;
import com.mr.service.EmpService;
import com.mr.util.Json;

@Controller
@RequestMapping(value="emp")
public class EmpController {
	
	@Autowired
	private EmpService empService;

	@RequestMapping(value="login")
	public String login(Employee emp,ModelMap map,HttpSession session){
		
		//调用service层，判断账号密码是否正确
		Json json = empService.checkEmpLogin(emp,session);
		if(json.getSuccess()){
			//判断，如果成功，调用查询方法
			return "redirect:/goods/toShowPage.do";
		}else{
			//如果失败，跳转到登录页面.
			map.put("msg", json.getMsg());
			map.put("message1111",json.getMsg());
			System.out.println("赵彩云有吃东西了");
			return "../../login/login-page";
		}
	}
	
}
