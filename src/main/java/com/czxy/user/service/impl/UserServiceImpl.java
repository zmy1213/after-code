package com.czxy.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.societyTeam.mapper.SocietyTeamMapper;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.teamMoney.mapper.TeamMoneyMapper;
import com.czxy.teamMoney.pojo.TeamMoney;
import com.czxy.user.pojo.User;
import com.czxy.user.mapper.UserMapper;
import com.czxy.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.userTeam.mapper.UserTeamMapper;
import com.czxy.userTeam.pojo.UserTeam;
import com.czxy.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper mapper;

    @Resource
    private UserTeamMapper userTeamMapper;

    @Resource
    private SocietyTeamMapper societyTeamMapper;

    @Resource
    private TeamMoneyMapper teamMoneyMapper;

    @Override
    public User login(User user) {
        //类似于包装的查询器，用于写一些附加条件,
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(user.getUsername())) {
            wrapper.eq("username", user.getUsername());
        }
        if (StringUtils.isNotBlank(user.getPassword())) {
            wrapper.eq("password", user.getPassword());
        }
        //通过查询器，查找一条数据
        User tUsers = mapper.selectOne(wrapper);
        return tUsers;
    }


    @Override
    public User findById(Integer id) {
        //相等于select * from user where id = ?
        User user = mapper.selectById(id);
        return user;
    }

    @Override
    public Integer updateUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //修改的时候，不能改成已有的账号，
        wrapper.eq("username", user.getUsername()).ne("id", user.getId());
        User user1 = mapper.selectOne(wrapper);
        //如果找到了已有想同的名字，并且不是自己的，则返回已有此用户名
        if (user1 != null) {
            return 0;
        }
        mapper.updateById(user);
        return 1;
    }

    @Override
    public Integer register(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        User user1 = mapper.selectOne(wrapper);
        if (user1 != null) {
            return 0;
        }
        mapper.insert(user);
        return 1;
    }

    @Override
    public Page<User> findCondition(UserVo userVo) {
        System.err.println("UserVo:"+userVo);
        Page<User> page = new Page<>(userVo.getPage(), userVo.getPageSize());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (userVo.getRoleId() != null) {
            wrapper.eq("role_id", userVo.getRoleId());
        }
        if (StringUtils.isNotBlank(userVo.getName())) {
            wrapper.like("name", userVo.getName());

        }
        baseMapper.selectPage(page, wrapper);
        return page;
    }

    @Override
    public Page<SocietyTeam> findJoinTeam(UserVo userVo) {
        List<Integer> byUid = userTeamMapper.findSidByUid(userVo.getUid());
        System.err.println("byUid"+byUid);
        Page<SocietyTeam> page = new Page<>(userVo.getPage(), userVo.getPageSize());
        QueryWrapper<SocietyTeam> wrapper = new QueryWrapper<>();
        if (!byUid.isEmpty()) {
            wrapper.in("id", byUid);
        }else {
            wrapper.eq("id",-1);
        }
        societyTeamMapper.selectPage(page, wrapper);
        return page;
    }

    @Override
    public List<User> findAllAdmin() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", "0");
        List<User> users = baseMapper.selectList(wrapper);
        return users;
    }

    @Override
    public List<User> findAllUser() {
        QueryWrapper<User> wrapper1 = new QueryWrapper<>();
        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        wrapper1.eq("role_id", "1");
        List<User> users1 = baseMapper.selectList(wrapper1);
        wrapper2.eq("role_id","2");
        List<User> users2 = baseMapper.selectList(wrapper2);
        users1.addAll(users2);
        return users1;
    }

    @Override
    public Boolean userJoin(Integer uid, Integer sid) {
        //查询以前是否加入过当前社团
        QueryWrapper<UserTeam> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid).eq("sid", sid);
        List<UserTeam> userTeams = userTeamMapper.selectList(wrapper);
        if (!userTeams.isEmpty()) {
            return false;
        }

        if(joinedAccount(uid)==0){

        }
        UserTeam userTeam = new UserTeam();
        userTeam.setUid(uid);
        userTeam.setSid(sid);


        //加入社团后，要交社团费用了
        userTeamMapper.insert(userTeam);
        SocietyTeam societyTeam = societyTeamMapper.selectById(sid);
        TeamMoney teamMoney = new TeamMoney();
        teamMoney.setUid(uid);
        teamMoney.setSid(sid);
        teamMoney.setPrice(societyTeam.getJoinPrice());
        teamMoney.setState("0");
        teamMoneyMapper.insert(teamMoney);
        return true;
    }
    @Override
    public Boolean deletePersonFromSocial(Integer uid,Integer sid){
        QueryWrapper<UserTeam> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid).eq("sid", sid);
        List<UserTeam> userTeams = userTeamMapper.selectList(wrapper);
        if(userTeams.isEmpty()){
            return false;
        }

        UserTeam userTeam = new UserTeam();
        userTeam.setUid(uid);
        userTeam.setSid(sid);
        userTeamMapper.deleteByUidAndSid(uid,sid);


        SocietyTeam societyTeam = societyTeamMapper.selectById(sid);
        TeamMoney teamMoney = new TeamMoney();

        teamMoney.setUid(uid);
        teamMoney.setSid(sid);
        teamMoney.setPrice(societyTeam.getJoinPrice());

        teamMoneyMapper.deleteByUidAndSid(uid,sid);

        mapper.updataByid(uid,1);
        if(joinedAccount(uid)==0){
            System.out.println("空");
        }

        return true;
    }
    @Override
    public Integer joinedAccount(Integer uid){

        QueryWrapper<UserTeam> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);

        return userTeamMapper.selectCount(wrapper);
    }
    @Override
    public String findUserNameById(Integer uid){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",uid);
        return mapper.selectById(uid).getName();
    }
}
