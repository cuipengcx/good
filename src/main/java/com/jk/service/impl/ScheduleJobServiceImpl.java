package com.jk.service.impl;

import com.jk.model.ScheduleJob;
import com.jk.service.ScheduleJobService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cuiP
 * Created by JK on 2017/5/4.
 */
@Transactional
@Service
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJob> implements ScheduleJobService{
}
