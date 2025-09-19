package com.xibei.smartjobservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xibei.smartjobservice.constant.Constants;
import com.xibei.smartjobservice.model.entity.Job;
import com.xibei.smartjobservice.mapper.JobMapper;
import com.xibei.smartjobservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 岗位服务实现
 */
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobMapper jobMapper;

    @Override
    public Page<Job> getJobs(int page, int size) {
        Page<Job> pageParam = new Page<>(page, size);
        QueryWrapper<Job> wrapper = new QueryWrapper<>();
        wrapper.eq("is_active", Constants.JOB_ACTIVE)
               .orderByDesc("gmt_create");
        return jobMapper.selectPage(pageParam, wrapper);
    }

    @Override
    public Job getJobById(Long jobId) {
        return jobMapper.selectById(jobId);
    }

    @Override
    @Transactional
    public boolean createJob(Job job) {
        job.setIsActive(Constants.JOB_ACTIVE);
        // 设置默认到期时间为一年后
        if (job.getExpirationDate() == null) {
            job.setExpirationDate(LocalDateTime.now().plusYears(1));
        }
        return jobMapper.insert(job) > 0;
    }

    @Override
    @Transactional
    public boolean updateJob(Job job) {
        return jobMapper.updateById(job) > 0;
    }

    @Override
    @Transactional
    public boolean deleteJob(Long jobId) {
        return jobMapper.deleteById(jobId) > 0;
    }

    @Override
    @Transactional
    public boolean createJobs(List<Job> jobs) {
        jobs.forEach(job -> {
            job.setIsActive(Constants.JOB_ACTIVE);
            // 设置默认到期时间为一年后
            if (job.getExpirationDate() == null) {
                job.setExpirationDate(LocalDateTime.now().plusYears(1));
            }
        });
        int successCount = 0;
        for (Job job : jobs) {
            successCount += jobMapper.insert(job);
        }
        return successCount == jobs.size();
    }

    @Override
    public Page<Job> searchJobs(String keyword, int page, int size) {
        Page<Job> pageParam = new Page<>(page, size);
        QueryWrapper<Job> wrapper = new QueryWrapper<>();
        wrapper.eq("is_active", Constants.JOB_ACTIVE)
               .and(qw -> qw.like("name", keyword)
                           .or().like("company_name", keyword)
                           .or().like("location", keyword)
                           .or().like("description", keyword))
               .orderByDesc("gmt_create");
        return jobMapper.selectPage(pageParam, wrapper);
    }

    @Override
    @Transactional
    public boolean enableJob(Long jobId) {
        Job job = jobMapper.selectById(jobId);
        if (job == null) {
            return false;
        }
        job.setIsActive(Constants.JOB_ACTIVE);
        return jobMapper.updateById(job) > 0;
    }

    @Override
    @Transactional
    public boolean disableJob(Long jobId) {
        Job job = jobMapper.selectById(jobId);
        if (job == null) {
            return false;
        }
        job.setIsActive(Constants.JOB_INACTIVE);
        return jobMapper.updateById(job) > 0;
    }
}