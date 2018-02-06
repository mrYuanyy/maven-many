package com.mr.service;

import java.util.List;
import java.util.Map;

import com.mr.entity.GoodsRedis;
import com.mr.util.Page;

public interface GoodsService {

	void insertGoods(GoodsRedis goods);

	void queryGoodsList(Page pageUtil);
	
}
