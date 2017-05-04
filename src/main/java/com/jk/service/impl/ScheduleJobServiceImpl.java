package com.jk.service.impl;

import com.jk.model.ScheduleJob;
import com.jk.service.ScheduleJobService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/5/4.
 */
@Transactional
@Service
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJob> implements ScheduleJobService{
    @Override
    public ScheduleJob findScheduleJobById(Long jobId) {
        return null;
    }

    @Override
    public List<ScheduleJob> findPage() {
        return null;
    }

    @Override
    public void saveScheduleJob(ScheduleJob scheduleJob) {

    }

    @Override
    public void updateScheduleJob(ScheduleJob scheduleJob) {

    }

    @Override
    public void deleteBatchScheduleJob(Long[] jobIds) {

    }

    @Override
    public int updateBatchScheduleJob(Long[] jobIds, int status) {
        return 0;
    }

    @Override
    public void run(Long[] jobIds) {

    }

    @Override
    public void pause(Long[] jobIds) {

    }

    @Override
    public void resume(Long[] jobIds) {

    }
}
