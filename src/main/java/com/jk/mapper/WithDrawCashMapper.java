package com.jk.mapper;

import java.util.List;
import java.util.Map;

import com.jk.model.WithDrawCash;
import com.jk.util.MyMapper;

/**
 * Created by JK on 2017/1/19.
 */
public interface WithDrawCashMapper extends MyMapper<WithDrawCash>{
    public List<WithDrawCash> withdrawcashList(Map map);
}
