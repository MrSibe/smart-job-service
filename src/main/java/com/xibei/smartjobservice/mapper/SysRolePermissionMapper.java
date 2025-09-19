package com.xibei.smartjobservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xibei.smartjobservice.model.entity.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色-权限关联Mapper
 */
@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
}