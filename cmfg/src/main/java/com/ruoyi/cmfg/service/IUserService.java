package com.ruoyi.cmfg.service;

import java.util.List;
import com.ruoyi.cmfg.domain.User;

/**
 * 用户管理Service接口
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
public interface IUserService 
{
    /**
     * 查询用户管理
     * 
     * @param userId 用户管理主键
     * @return 用户管理
     */
    public User selectUserByUserId(Long userId);

    /**
     * 查询用户管理列表
     * 
     * @param user 用户管理
     * @return 用户管理集合
     */
    public List<User> selectUserList(User user);

    /**
     * 新增用户管理
     * 
     * @param user 用户管理
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 修改用户管理
     * 
     * @param user 用户管理
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 批量删除用户管理
     * 
     * @param userIds 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteUserByUserIds(Long[] userIds);

    /**
     * 删除用户管理信息
     * 
     * @param userId 用户管理主键
     * @return 结果
     */
    public int deleteUserByUserId(Long userId);
}
