package com.xibei.smartjobservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xibei.smartjobservice.model.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户-角色关联Mapper
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}