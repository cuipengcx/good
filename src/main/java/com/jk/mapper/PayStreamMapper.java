package com.jk.mapper;

import java.util.List;
import java.util.Map;

import com.jk.model.PayStream;
import com.jk.util.MyMapper;

/**
 * Created by JK on 2017/1/19.
 */
public interface PayStreamMapper extends MyMapper<PayStream>{
    public List<PayStream> paystreamList(Map map);
}
