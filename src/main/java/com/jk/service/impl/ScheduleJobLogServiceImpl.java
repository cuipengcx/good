package com.jk.service.impl;

import com.jk.model.ScheduleJobLog;
import com.jk.service.ScheduleJobLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Created by cuip on 2017/5/20.
 */
@Transactional
@Service
public class ScheduleJobLogServiceImpl extends BaseServiceImpl<ScheduleJobLog> implements ScheduleJobLogService{
}
