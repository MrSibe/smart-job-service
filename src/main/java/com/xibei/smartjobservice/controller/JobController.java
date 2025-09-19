package com.xibei.smartjobservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xibei.smartjobservice.model.dto.Result;
import com.xibei.smartjobservice.model.entity.Job;
import com.xibei.smartjobservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位控制器
 */
@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    /**
     * 获取岗位列表（分页）
     */
    @GetMapping
    public Result<Page<Job>> getJobs(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Job> jobs = jobService.getJobs(page, size);
        return Result.success(jobs);
    }

    /**
     * 根据ID获取岗位
     */
    @GetMapping("/{jobId}")
    @PreAuthorize("hasAuthority('job:detail')")
    public Result<Job> getJobById(@PathVariable Long jobId) {
        Job job = jobService.getJobById(jobId);
        return Result.success(job);
    }

    /**
     * 创建岗位
     */
    @PostMapping
    @PreAuthorize("hasAuthority('job:create')")
    public Result<String> createJob(@RequestBody Job job) {
        boolean success = jobService.createJob(job);
        return success ? Result.success("岗位创建成功") : Result.error("岗位创建失败");
    }

    /**
     * 更新岗位
     */
    @PutMapping("/{jobId}")
    @PreAuthorize("hasAuthority('job:update')")
    public Result<String> updateJob(@PathVariable Long jobId, @RequestBody Job job) {
        job.setId(jobId);
        boolean success = jobService.updateJob(job);
        return success ? Result.success("岗位更新成功") : Result.error("岗位更新失败");
    }

    /**
     * 删除岗位
     */
    @DeleteMapping("/{jobId}")
    @PreAuthorize("hasAuthority('job:delete')")
    public Result<String> deleteJob(@PathVariable Long jobId) {
        boolean success = jobService.deleteJob(jobId);
        return success ? Result.success("岗位删除成功") : Result.error("岗位删除失败");
    }

    /**
     * 批量创建岗位
     */
    @PostMapping("/batch")
    @PreAuthorize("hasAuthority('job:create')")
    public Result<String> createJobs(@RequestBody List<Job> jobs) {
        boolean success = jobService.createJobs(jobs);
        return success ? Result.success("批量创建成功") : Result.error("批量创建失败");
    }

    /**
     * 搜索岗位
     */
    @GetMapping("/search")
    @PreAuthorize("hasAuthority('job:list')")
    public Result<Page<Job>> searchJobs(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Job> jobs = jobService.searchJobs(keyword, page, size);
        return Result.success(jobs);
    }

    /**
     * 启用岗位
     */
    @PostMapping("/{jobId}/enable")
    @PreAuthorize("hasAuthority('job:update')")
    public Result<String> enableJob(@PathVariable Long jobId) {
        boolean success = jobService.enableJob(jobId);
        return success ? Result.success("岗位启用成功") : Result.error("岗位启用失败");
    }

    /**
     * 禁用岗位
     */
    @PostMapping("/{jobId}/disable")
    @PreAuthorize("hasAuthority('job:update')")
    public Result<String> disableJob(@PathVariable Long jobId) {
        boolean success = jobService.disableJob(jobId);
        return success ? Result.success("岗位禁用成功") : Result.error("岗位禁用失败");
    }
}