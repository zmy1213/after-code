package com.czxy.team_manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.teamMoney.pojo.TeamMoney;
import com.czxy.team_manager.mapper.TeamManagerMapper;
import com.czxy.team_manager.pojo.TeamManager;
import com.czxy.team_manager.service.TeamManagerService;
import com.czxy.user.mapper.UserMapper;
import com.czxy.user.pojo.User;
import com.czxy.team_manager.pojo.TeamManager;
import com.czxy.team_manager.mapper.TeamManagerMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.userTeam.mapper.UserTeamMapper;
import com.czxy.userTeam.pojo.UserTeam;
import com.czxy.vo.TeamManagerVo;
import com.czxy.vo.TeamMoneyVo;
import com.czxy.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author
 * @since 2024-03-01
 */
@Service
public class TeamManagerImpl extends ServiceImpl<TeamManagerMapper,TeamManager> implements TeamManagerService {

    @Resource
    UserMapper userMapper;
    @Resource
    TeamManagerMapper teamManagerMapper;
    @Resource
    UserTeamMapper userTeamMapper;
    @Override
    public List<User> findMyTeamMates(Integer uid){
       List<UserTeam> res = findMyTeamUserIds(findMyTeamSid(uid));

       List<User> users = new ArrayList<>();
       QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",uid);
        for (UserTeam userTeam : res) {
            Integer userId = userTeam.getUid();
            // 根据用户ID查询对应的用户信息
            wrapper.eq("id",userId);
            User user = userMapper.selectById(userId);
            if (user != null) {
                // 将查询到的用户信息加入到链表中
                users.add(user);
            }
        }
        return users;
    }
    @Override
    public Integer findMyTeamSid(Integer uid){
        QueryWrapper<TeamManager> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        TeamManager res = teamManagerMapper.selectOne(wrapper);
        return res.getSid();
    }
    @Override
    public Integer findUidBySid(Integer sid){
        QueryWrapper<TeamManager> wrapper = new QueryWrapper<>();
        wrapper.eq("sid",sid);
        return teamManagerMapper.selectOne(wrapper).getUid();
    }
    @Override
    public List<UserTeam> findMyTeamUserIds(Integer sid){
        QueryWrapper<UserTeam> wrapper = new QueryWrapper<>();
        wrapper.eq("sid",sid);
        return userTeamMapper.selectList(wrapper);
    }



    @Override
    public Page<TeamManager> findCondition(Integer uid,TeamManagerVo teamManagerVo) {
        Page<TeamManager> page = new Page<>(teamManagerVo.getPage(), teamManagerVo.getPageSize());
        QueryWrapper<TeamManager> wrapper = new QueryWrapper<>();
        if (teamManagerVo.getSid() != null) {
            wrapper.like("sid", teamManagerVo.getSid());

        }
        if (teamManagerVo.getUid() != null) {
            wrapper.like("uid", teamManagerVo.getUid());

        }

        baseMapper.selectPage(page, wrapper);
        List<TeamManager> records = page.getRecords();
        for (TeamManager record : records) {
            User user = userMapper.selectById(record.getUid());
            record.setUsername(user.getUsername());
        }
        return page;

    }
}
