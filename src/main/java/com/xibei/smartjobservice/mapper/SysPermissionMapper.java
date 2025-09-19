package com.xibei.smartjobservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xibei.smartjobservice.model.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限Mapper
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户ID获取权限列表
     */
    List<String> getPermissionsByUserId(@Param("userId") Long userId);
}