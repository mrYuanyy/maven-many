package com.mr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.mr.entity.Employee;
import com.mr.entity.GoodsRedis;
import com.mr.service.ShopCarService;
import com.mr.util.RedisUtil;

@Service
public class ShopCarServiceImpl implements ShopCarService {

	//加入购物车
	@Override
	public void insertShopCar(Integer goodsId, Integer buyCount,HttpSession session) {
		//从缓存中获取登录人的id
		Employee emp = (Employee)RedisUtil.getObject(session.getId());
		//购物车的数据都是放在list集合中
		//List<Map<String,Object>> shopCarList = new ArrayList<Map<String,Object>>();
		//从缓存中获取购物车list集合
		List<Map<String,Object>> shopCarList = (List<Map<String,Object>>)RedisUtil.getObject("shopCarList_"+emp.getEmpId());
		//如果缓存中没有shopCarList集合，则需要创建集合
		if(shopCarList == null){
			shopCarList = new ArrayList<Map<String,Object>>();
		}
		//加一个标识，表明是否进入循环
		boolean flag = true;
		//如果购物车有数据，遍历判断
		if(shopCarList.size() > 0){
				//在新增到map之前，需要判断购物车中商品的id与新增的商品id是否相同(遍历购物车list进行判断)
				for (int i = 0; i < shopCarList.size(); i++) {
					//获取shopCarList中的Goods对象
					GoodsRedis goods = (GoodsRedis)shopCarList.get(i).get("goods");
					//如果相同，合并购买数量
					if(goodsId.equals(goods.getGoodsId())){
						flag = false;
						//给map重新赋值  先获取map中存放的count   然后与此次的数量相加，重新赋值
						Integer buyCountMap = (Integer)shopCarList.get(i).get("count");
						buyCount = buyCountMap+buyCount;
						shopCarList.get(i).put("count",buyCount);
					}
				}
				if(flag){
					//如果购物车没有数据，新建map存储
					//list集合中存储了商品信息及购买数量等信息，信息都存储在Map中
					Map<String,Object> map = new HashMap<String,Object>();
					//获取商品信息
					GoodsRedis goodsRedis = (GoodsRedis)RedisUtil.getObject("goods_"+goodsId);
					//将值赋值给Map
					map.put("goods", goodsRedis);
					map.put("count", buyCount);
					//将map放入list中
					shopCarList.add(map);
				}
		}else{
			//如果购物车没有数据，新建map存储
			//list集合中存储了商品信息及购买数量等信息，信息都存储在Map中
			Map<String,Object> map = new HashMap<String,Object>();
			//获取商品信息
			GoodsRedis goodsRedis = (GoodsRedis)RedisUtil.getObject("goods_"+goodsId);
			//将值赋值给Map
			map.put("goods", goodsRedis);
			map.put("count", buyCount);
			//将map放入list中
			shopCarList.add(map);
		}
		
		
		//最后将购物车的list放入缓存,使用用户id作为唯一标志
		RedisUtil.setObject("shopCarList_"+emp.getEmpId(), shopCarList);
	}

	
	
}
