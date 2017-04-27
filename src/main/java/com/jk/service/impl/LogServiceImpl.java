package com.jk.service.impl;

import com.jk.model.Log;
import com.jk.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cuiP
 * Created by JK on 2017/4/27.
 */
@Transactional
@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService{
}
