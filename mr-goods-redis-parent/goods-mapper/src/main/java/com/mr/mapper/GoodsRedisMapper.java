package com.mr.mapper;

import java.util.List;

import com.mr.entity.GoodsRedis;
import com.mr.util.Page;

public interface GoodsRedisMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(GoodsRedis record);

    int insertSelective(GoodsRedis record);

    GoodsRedis selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(GoodsRedis record);

    int updateByPrimaryKey(GoodsRedis record);

	Integer queryGoodsCounts();

	List<GoodsRedis> queryGoodsList(Page pageUtil);
	
}