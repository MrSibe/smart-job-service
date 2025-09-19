package com.xibei.smartjobservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xibei.smartjobservice.model.entity.Job;
import org.apache.ibatis.annotations.Mapper;

/**
 * 岗位Mapper
 */
@Mapper
public interface JobMapper extends BaseMapper<Job> {
}