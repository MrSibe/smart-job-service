package com.xibei.smartjobservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xibei.smartjobservice.model.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据手机号查询用户
     */
    SysUser selectByPhone(String phone);
}