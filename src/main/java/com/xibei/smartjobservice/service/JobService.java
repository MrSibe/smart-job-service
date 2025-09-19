package com.xibei.smartjobservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xibei.smartjobservice.model.entity.Job;

import java.util.List;

/**
 * 岗位服务接口
 */
public interface JobService {

    /**
     * 获取所有岗位（分页）
     */
    Page<Job> getJobs(int page, int size);

    /**
     * 根据ID获取岗位
     */
    Job getJobById(Long jobId);

    /**
     * 创建岗位
     */
    boolean createJob(Job job);

    /**
     * 更新岗位
     */
    boolean updateJob(Job job);

    /**
     * 删除岗位
     */
    boolean deleteJob(Long jobId);

    /**
     * 批量创建岗位
     */
    boolean createJobs(List<Job> jobs);

    /**
     * 搜索岗位
     */
    Page<Job> searchJobs(String keyword, int page, int size);

    /**
     * 启用岗位
     */
    boolean enableJob(Long jobId);

    /**
     * 禁用岗位
     */
    boolean disableJob(Long jobId);
}