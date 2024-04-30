package com.czxy.teamMoney.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.societyTeam.mapper.SocietyTeamMapper;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.teamMoney.pojo.TeamMoney;
import com.czxy.teamMoney.mapper.TeamMoneyMapper;
import com.czxy.teamMoney.service.TeamMoneyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.team_manager.service.TeamManagerService;
import com.czxy.user.mapper.UserMapper;
import com.czxy.user.pojo.User;
import com.czxy.vo.TeamManagerVo;
import com.czxy.vo.TeamMoneyVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TeamMoneyServiceImpl extends ServiceImpl<TeamMoneyMapper, TeamMoney> implements TeamMoneyService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private SocietyTeamMapper societyTeamMapper;
    @Resource
    private TeamManagerService teamManagerService;

    @Override
    public Page<TeamMoney> findCondition(TeamMoneyVo teamMoneyVo) {
        Page<TeamMoney> page = new Page<>(teamMoneyVo.getPage(), teamMoneyVo.getPageSize());
        QueryWrapper<TeamMoney> wrapper = new QueryWrapper<>();
        if (teamMoneyVo.getSId()!=null){
            wrapper.like("sid",teamMoneyVo.getSId());

        }
        if (teamMoneyVo.getUid()!=null){
            wrapper.like("uid",teamMoneyVo.getUid());

        }
        if (teamMoneyVo.getState()!=null){
            wrapper.like("state",teamMoneyVo.getState());

        }
        baseMapper.selectPage(page,wrapper);
        List<TeamMoney> records = page.getRecords();
        for (TeamMoney record : records) {
            User user = userMapper.selectById(record.getUid());
            if (user!=null){
                record.setUserName(user.getName());
            }
            SocietyTeam team = societyTeamMapper.selectById(record.getSid());
            if (team!=null){
                record.setTeamName(team.getName());
            }
        }
        return page;
    }
    @Override  public Page<TeamMoney> findConditionByUid(TeamMoneyVo teamMoneyVo,Integer uid){
        Page<TeamMoney> page = new Page<>(teamMoneyVo.getPage(), teamMoneyVo.getPageSize());
        QueryWrapper<TeamMoney> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        baseMapper.selectPage(page,wrapper);
        List<TeamMoney> records = page.getRecords();
        for (TeamMoney record : records) {
            User user = userMapper.selectById(record.getUid());
            if (user!=null){
                record.setUserName(user.getName());
            }
            SocietyTeam team = societyTeamMapper.selectById(record.getSid());
            if (team!=null){
                record.setTeamName(team.getName());
            }
        }
        return page;
    }
    @Override
    public Page<TeamMoney> findCondition(TeamMoneyVo teamMoneyVo,Integer uid) {
        Page<TeamMoney> page = new Page<>(teamMoneyVo.getPage(), teamMoneyVo.getPageSize());
        QueryWrapper<TeamMoney> wrapper = new QueryWrapper<>();
        wrapper.eq("sid",teamManagerService.findMyTeamSid(uid));
        baseMapper.selectPage(page,wrapper);
        List<TeamMoney> records = page.getRecords();
        for (TeamMoney record : records) {
            User user = userMapper.selectById(record.getUid());
            if (user!=null){
                record.setUserName(user.getName());
            }
            SocietyTeam team = societyTeamMapper.selectById(record.getSid());
            if (team!=null){
                record.setTeamName(team.getName());
            }
        }
        return page;
    }
    @Override
    public void pay(Integer id) {
        TeamMoney teamMoney = baseMapper.selectById(id);
        teamMoney.setState("1");
        baseMapper.updateById(teamMoney);
    }
}
