package com.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mr.entity.Employee;
import com.mr.entity.GoodsRedis;
import com.mr.entity.GoodsTypeRedis;
import com.mr.service.GoodsService;
import com.mr.service.GoodsTypeService;
import com.mr.service.ShopCarService;
import com.mr.util.Page;
import com.mr.util.RedisUtil;

@Controller
@RequestMapping(value="goods")
public class GoodsController {
	
	@Autowired
	private GoodsTypeService goodsTypeService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private ShopCarService shopCarService;

	//跳转到新增页面
	@RequestMapping(value="toInsertPage")
	public String toInsertPage(ModelMap map){
		
		//调用查询所有商品类型的方法，并接收返回的集合
		List<GoodsTypeRedis> typeList = goodsTypeService.queryGoodsTypeList();
		//将集合传递到前台页面
		map.put("typeList", typeList);
		
		return "goods/insert-page";
	}
	
	//新增方法
	@RequestMapping(value="insertGoods")
	@ResponseBody
	public void insertGoods(GoodsRedis goods){
		goodsService.insertGoods(goods);
	}
	
	//跳转到查询页面
	@RequestMapping(value="toShowPage")
	public String toShowPage(ModelMap map,HttpSession session){
		//跳转页面之前，从缓存中获取用户信息，放在modelMap中
		Employee emp = (Employee)RedisUtil.getObject(session.getId());
		map.put("login_user", emp);
		return "goods/show-page";
	}
	
	//查询方法
	@RequestMapping(value="queryGoodsList")
	@ResponseBody
	public Map queryGoodsList(Integer page,Integer rows,Page pageUtil){
		pageUtil.setCurrentPage(page);
		pageUtil.setPageItem(rows);
		goodsService.queryGoodsList(pageUtil);
		//easyui会解析两个参数，一个参数为total(总条数)，一个参数是rows(展示集合)
		Map map = new HashMap();
		map.put("total", pageUtil.getCountItem());
		map.put("rows",pageUtil.getList());
		return map;
	}
	
	//跳转到商品详情页面
	@RequestMapping("toGoodsInfo")
	public String toGoodsInfo(Integer goodsId,ModelMap map){
		//获取商品详情
		GoodsRedis goods =  (GoodsRedis)RedisUtil.getObject("goods_"+goodsId);
		map.put("goods", goods);
		return "goods/info-page";
	}
	
	
	//加入购物车
	@RequestMapping("insertShopCar")
	public String insertShopCar(Integer goodsId,Integer buyCount,HttpSession session){
		System.out.println("商品id:"+goodsId+",购买数量:"+buyCount);
		shopCarService.insertShopCar(goodsId, buyCount, session);
		return "goods/success-page";
	}
	
	//查看购物车
	@RequestMapping("showShopCar")
	public String showShopCar(HttpSession  session,ModelMap map){
		//从缓存中获取用户信息
		Employee emp = (Employee)RedisUtil.getObject(session.getId());
		//从缓存中获取用户下的购物车
		List<Map<String,Object>> carList= (List<Map<String,Object>>)RedisUtil.getObject("shopCarList_"+emp.getEmpId());
		//返回到前台页面
		map.put("carList", carList);
		return "goods/shopCar-page";
	}
	
}
