package com.jk.mapper;

import java.util.List;
import java.util.Map;

import com.jk.model.Recommend;
import com.jk.util.MyMapper;

/**
 * Created by JK on 2017/1/19.
 */
public interface RecommendMapper extends MyMapper<Recommend>{

    public List<Recommend> recommendList(Map map);

}
