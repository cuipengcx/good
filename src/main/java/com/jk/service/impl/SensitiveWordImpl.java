package com.jk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.SensitiveWordMapper;
import com.jk.model.SensitiveWord;
import com.jk.service.SensitiveWordService;

/**
 * Created by JK on 2017/1/19.
 */
@Transactional
@Service
public class SensitiveWordImpl extends BaseServiceImpl<SensitiveWord> implements SensitiveWordService{

    @Autowired
    private SensitiveWordMapper sensitivewordMapper;
    
    @Transactional(readOnly=true)
    @Override
    public PageInfo<SensitiveWord> findPage(Integer pageNum ,Integer pageSize ,String findtext){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("findtext", findtext);

        PageHelper.startPage(pageNum, pageSize,true);
        List<SensitiveWord> sensitivewordlist =sensitivewordMapper.swList(map);
        return new PageInfo<SensitiveWord>(sensitivewordlist);
    }

}
