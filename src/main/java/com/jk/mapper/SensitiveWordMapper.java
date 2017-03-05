package com.jk.mapper;

import java.util.List;
import java.util.Map;

import com.jk.model.SensitiveWord;
import com.jk.util.MyMapper;

/**
 * Created by JK on 2017/1/19.
 */
public interface SensitiveWordMapper extends MyMapper<SensitiveWord>{
    public List<SensitiveWord> swList(Map map);
}
