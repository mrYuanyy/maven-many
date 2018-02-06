package com.mr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.mr.entity.GoodsRedis;
import com.mr.mapper.GoodsRedisMapper;
import com.mr.service.GoodsService;
import com.mr.util.Page;
import com.mr.util.RedisUtil;
import com.mr.util.SerializeUtils;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsRedisMapper  goodsRedisMapper;

	@Override
	public void insertGoods(GoodsRedis goods) {
		//调用新增方法
		goodsRedisMapper.insert(goods);
		
		//将商品信息放入redis缓存中
		Jedis jedis = new Jedis("localhost");
		jedis.auth("yyy");
		//在将商品信息放入缓存中时，需要使用唯一标志(id)
		jedis.set(("goods_"+goods.getGoodsId()).getBytes(),SerializeUtils.serialize(goods));
	}

	@Override
	public void queryGoodsList(Page pageUtil) {
		//连接redis
		//Jedis jedis = new Jedis("localhost");
		//jedis.auth("yyy");
		
		//查询总条数
		Integer counts = goodsRedisMapper.queryGoodsCounts();
		pageUtil.setCountItem(counts);
		//查询集合
		List<GoodsRedis> goodsList = goodsRedisMapper.queryGoodsList(pageUtil);
		//在查询集合时，只查询id字段，然后遍历集合，从redis中获取对应的Object并重新赋值
		for (int i = 0; i < goodsList.size(); i++) {
			//获取到redis中该id对应的存储对象
			//byte[] goodsByte = jedis.get(("goods_"+goodsList.get(i).getGoodsId()).getBytes());
			//将获取到的存储对象反序列化
			//GoodsRedis goods = (GoodsRedis)SerializeUtils.deSerialize(goodsByte);
			GoodsRedis goods = (GoodsRedis)RedisUtil.getObject("goods_"+goodsList.get(i).getGoodsId());
			//将redis中的对象赋值给集合
			goodsList.set(i, goods);
		}
		pageUtil.setList(goodsList);
	}

}
