package com.czxy.applySociety.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.activity.mapper.ActivityMapper;
import com.czxy.activity.pojo.Activity;
import com.czxy.activity.service.ActivityService;
import com.czxy.applySociety.mapper.ApplySocietyMapper;
import com.czxy.applySociety.pojo.applySociety;
import com.czxy.applySociety.service.ApplySocietyService;
import com.czxy.createTeamApplication.mapper.CreateTeamApplicationMapper;
import com.czxy.createTeamApplication.pojo.CreateTeamApplication;
import com.czxy.createTeamApplication.service.CreateTeamApplicationService;
import com.czxy.societyTeam.mapper.SocietyTeamMapper;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.teamImage.mapper.TeamImageMapper;
import com.czxy.teamImage.pojo.TeamImage;
import com.czxy.teamMoney.mapper.TeamMoneyMapper;
import com.czxy.teamMoney.pojo.TeamMoney;
import com.czxy.team_manager.mapper.TeamManagerMapper;
import com.czxy.team_manager.pojo.TeamManager;
import com.czxy.team_manager.service.TeamManagerService;
import com.czxy.user.mapper.UserMapper;
import com.czxy.user.pojo.User;
import com.czxy.user.service.UserService;
import com.czxy.userTeam.mapper.UserTeamMapper;
import com.czxy.userTeam.pojo.UserTeam;
import com.czxy.vo.*;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.runtime.directive.Parse;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("ApplySocietyService")
public class ApplySocietyServiceImpl extends ServiceImpl<ApplySocietyMapper, applySociety>  implements ApplySocietyService{
   @Resource
    private ApplySocietyMapper applySocietyMapper;
    @Resource
    private UserTeamMapper userTeamMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private TeamManagerMapper teamManagerMapper;
    @Resource
    private TeamMoneyMapper teamMoneyMapper;
    @Resource
    private SocietyTeamMapper societyTeamMapper;
    @Resource
    private UserService userService;
    @Resource
    private TeamManagerService teamManagerService;
    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private CreateTeamApplicationMapper createTeamApplicationMapper;
    @Resource
    private TeamImageMapper teamImageMapper;

    @Override
    public String applyJoinSociety(Integer uid,Integer sid,String reason,String userName,String teamName,String classname){

        QueryWrapper<applySociety> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid).eq("sid",sid);

        QueryWrapper<UserTeam> w = new QueryWrapper<>();
        wrapper.eq("uid", uid).eq("sid", sid);

        List<UserTeam> userTeams = userTeamMapper.findSidByUidAndUid(uid,sid);
        if(!userTeams.isEmpty()){
            return "你已经加入了该社团";
        }

        List<applySociety> applySocieties = applySocietyMapper.selectList(wrapper);
        if(!applySocieties.isEmpty()){
            return "你已经申请过了";
        }



        applySociety as = new applySociety();
        as.setUid(uid);
        as.setSid(sid);
        as.setUsername(userName);
        as.setTeamname(teamName);
        as.setReason(reason);
        as.setClassname(classname);
        as.setStatue(1);

        applySocietyMapper.insert(as);
        return "申请成功，等待审核";
    }

    @Override
    public Page<applySociety> findCondition(SearchVo applyVo) {
        System.err.println("ApplyVo:"+applyVo);
        Page<applySociety> page = new Page<>(applyVo.getPage(), applyVo.getPageSize());
        QueryWrapper<applySociety> wrapper = new QueryWrapper<>();
        baseMapper.selectPage(page, wrapper);
        return page;
    }
    @Override
    public Page<applySociety> findCondition(SearchVo applyVo,Integer uid){
        Page<applySociety> page = new Page<>(applyVo.getPage(), applyVo.getPageSize());
        QueryWrapper<applySociety> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        baseMapper.selectPage(page, wrapper);
        return page;
    }
    public Page<applySociety> findConditionApply(ApplySocietyVo applySociety){

        Page<applySociety> page = new Page<>(applySociety.getPage(), applySociety.getPageSize());
        QueryWrapper <applySociety> wrapper = new QueryWrapper<>();

        if(applySociety.getTeamname()!= null){
            if(!applySociety.getTeamname().isEmpty()) {
                wrapper.eq("teamname", applySociety.getTeamname());
                System.out.println(applySociety.getTeamname());
            }
        }
        if(applySociety.getUsername()!=null){
            if(!applySociety.getUsername().isEmpty()) {
                wrapper.eq("username", applySociety.getUsername());
            }
        }
        baseMapper.selectPage(page, wrapper);
        return page;
    }
    public Page<applySociety> findConditionApplyById(ApplySocietyVo applySociety,Integer uid){

        Page<applySociety> page = new Page<>(applySociety.getPage(), applySociety.getPageSize());
        QueryWrapper <applySociety> wrapper = new QueryWrapper<>();

        if(applySociety.getTeamname()!= null){
            if(!applySociety.getTeamname().isEmpty()) {
                wrapper.eq("teamname", applySociety.getTeamname());
            }
        }
        wrapper.eq("uid",uid);
        baseMapper.selectPage(page, wrapper);
        return page;
    }
    public Page<applySociety> findConditionApplyBySid(ApplySocietyVo applySociety,Integer uid){

        Page<applySociety> page = new Page<>(applySociety.getPage(), applySociety.getPageSize());
        QueryWrapper <applySociety> wrapper = new QueryWrapper<>();
        Integer sid =  teamManagerService.findMyTeamSid(uid);
        if(applySociety.getTeamname()!= null){
            if(!applySociety.getTeamname().isEmpty()) {
                wrapper.eq("teamname", applySociety.getTeamname());
            }
        }
        wrapper.eq("sid",sid);
        baseMapper.selectPage(page, wrapper);
        return page;
    }
    @Override
    public Page<applySociety> findManCondition(SearchVo applyVo,Integer uid) {
        Integer sid = teamManagerService.findMyTeamSid(uid);

        Page<applySociety> page = new Page<>(applyVo.getPage(), applyVo.getPageSize());
        QueryWrapper<applySociety> wrapper = new QueryWrapper<>();
        wrapper.eq("sid",sid);
        baseMapper.selectPage(page, wrapper);
        return page;
    }
    @Override
    public boolean joinTeamById(Integer uid,Integer sid){
        userMapper.updataByid(uid,2);
        if(applySocietyMapper.updateByUidAndSid(uid,sid,2)) {
            return true;
        }
        return false;
    }
    @Override
    public boolean refuseApplyById(Integer uid,Integer sid){

        if(applySocietyMapper.updateByUidAndSid(uid,sid,3)){
            return true;
        }
        return false;
    }
    @Override
    public boolean deleteApplyById(Integer uid,Integer sid){

        if(applySocietyMapper.deleteByUidAndSid(uid,sid)){
            return true;
        }
        return false;
    }
    @Override
    public String createTeamApplicationAndCheckIsCreated (Integer uid, SocietyTeamVo teamInfo) throws ParseException {

        System.out.println(teamInfo);
        if(isCreated(uid)){
            return "你已经创建过社团了";
        }

        QueryWrapper<CreateTeamApplication> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid).eq("add_or_delete",0);
        if(createTeamApplicationMapper.selectCount(wrapper)>0){
            return "你已经申请过了";
        }


        if(societyTeamMapper.selectCount(new QueryWrapper<SocietyTeam>().eq("name", teamInfo.getName()))>0)
            return "名字重复";

        QueryWrapper<User> w = new QueryWrapper<>();
        w.eq("id",uid);
        User user = userMapper.selectOne(w);

        CreateTeamApplication createTeamApplication = new CreateTeamApplication();
        createTeamApplication.setUid(uid);
        createTeamApplication.setStatus(0);
        createTeamApplication.setAddOrDelete(0);

        SimpleDateFormat formatter = new SimpleDateFormat(teamInfo.getCreate_time());
        createTeamApplication.setCreateTime(formatter.parse(teamInfo.getCreate_time()));
        createTeamApplication.setTeamname(teamInfo.getName());
        createTeamApplication.setUsername(user.getName());
        createTeamApplication.setJoinPrice(teamInfo.getJoin_price());
        createTeamApplication.setRemark(teamInfo.getRemark());
        createTeamApplication.setSlogan(teamInfo.getSlogan());
        createTeamApplication.setType(teamInfo.getType());


        if(createTeamApplicationMapper.insert(createTeamApplication)>0){
            return "申请成功，请等待";
        }

        return  "失败";

    }
    @Override
    public boolean createTeam(Integer uid){
        QueryWrapper<CreateTeamApplication> wrapper = new QueryWrapper<>();
        QueryWrapper<TeamImage> teamImageQueryWrapper = new QueryWrapper<>();
        teamImageQueryWrapper.eq("uid",uid);

        TeamImage teamImage = new TeamImage();
        teamImage.setStatus(1);//1代表是申请成功社团的图片
        wrapper.eq("uid",uid);
        teamImageMapper.update(teamImage,teamImageQueryWrapper);

        CreateTeamApplication teamInfo = createTeamApplicationMapper.selectOne(wrapper);
        teamInfo.setStatus(1);

        createTeamApplicationMapper.update(teamInfo,wrapper);

        SocietyTeam societyTeam = new SocietyTeam();
        societyTeam.setName(teamInfo.getTeamname());
        societyTeam.setCreateTime(teamInfo.getCreateTime());
        societyTeam.setSlogan(teamInfo.getSlogan());
        societyTeam.setRemark(teamInfo.getRemark());
        societyTeam.setType(teamInfo.getType());
        societyTeam.setJoinPrice(teamInfo.getJoinPrice());



        int affectedRows = societyTeamMapper.insert(societyTeam);

        SocietyTeam result = societyTeamMapper.selectOne(new QueryWrapper<SocietyTeam>().eq("name", teamInfo.getTeamname()));
        TeamManager teamManager = new TeamManager();
        teamManager.setSid(result.getId());
        teamManager.setUsername(userService.findUserNameById(uid));
        teamManager.setUid(uid);
        teamManager.setTeamname(societyTeam.getName());



        if(affectedRows>0&&teamManagerMapper.insert(teamManager)>0){
            return true;
        }
        societyTeamMapper.delete(new QueryWrapper<SocietyTeam>().eq("id",result.getId()));


        return  false;
    }
    @Override
    public boolean isCreated(Integer uid){
        QueryWrapper<TeamManager> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        Integer res = teamManagerMapper.selectCount(wrapper);
        if(res<=0)
            return false;
        else
            return true;
    }
    @Override
    public String findTeamName(Integer uid){

        Integer sid = teamManagerService.findMyTeamSid(uid);
        QueryWrapper<TeamManager> wrapper = new QueryWrapper<>();
        wrapper.eq("sid",sid);
        return teamManagerMapper.selectOne(wrapper).getTeamname();
    }

    @Override
    public String createDeleteTeamApplicationAndCheckIsCreated (Integer uid, SocietyTeamVo teamInfo) throws ParseException {


        if(!isCreated(uid)){
            return "你没有创建社团";
        }

        QueryWrapper<SocietyTeam> wrap = new QueryWrapper<>();
        wrap.eq("name",teamInfo.getName());
        SocietyTeam st = new SocietyTeam();
        st = societyTeamMapper.selectOne(wrap);
        teamInfo.setSlogan(st.getSlogan());
        teamInfo.setType(st.getType());
        teamInfo.setRemark(st.getRemark());
        teamInfo.setCreate_time(st.getCreateTime().toString());
        teamInfo.setJoin_price(st.getJoinPrice());
        teamInfo.setId(st.getId());

        QueryWrapper<CreateTeamApplication> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid).eq("add_or_delete",1);
        if(createTeamApplicationMapper.selectCount(wrapper)>0){
            return "你已经申请过了";
        }

        QueryWrapper<CreateTeamApplication> wr = new QueryWrapper<>();
        wr.eq("uid",uid).eq("add_or_delete",0);
        if(createTeamApplicationMapper.selectCount(wrapper)>0){
            return "请删除创建的申请";
        }

        QueryWrapper<User> w = new QueryWrapper<>();
        w.eq("id",uid);
        User user = userMapper.selectOne(w);

        CreateTeamApplication createTeamApplication = new CreateTeamApplication();
        createTeamApplication.setUid(uid);
        createTeamApplication.setStatus(0);
        createTeamApplication.setAddOrDelete(1);

        createTeamApplication.setCreateTime(st.getCreateTime());
        createTeamApplication.setTeamname(teamInfo.getName());
        createTeamApplication.setUsername(user.getName());
        createTeamApplication.setJoinPrice(teamInfo.getJoin_price());
        createTeamApplication.setRemark(teamInfo.getRemark());
        createTeamApplication.setSlogan(teamInfo.getSlogan());
        createTeamApplication.setType(teamInfo.getType());

        if(createTeamApplicationMapper.insert(createTeamApplication)>0){
            return "申请成功，请等待";
        }

        return  "失败";
    }
    @Override
    public boolean deleteTeam(Integer uid){


        QueryWrapper<CreateTeamApplication> wrapper = new QueryWrapper<>();


        wrapper.eq("uid",uid);
        CreateTeamApplication teamInfo = createTeamApplicationMapper.selectOne(wrapper);
        teamInfo.setStatus(2);

        QueryWrapper<TeamImage> teamImageQueryWrapper = new QueryWrapper<>();
        teamImageQueryWrapper.eq("uid",uid);
        teamImageMapper.delete(teamImageQueryWrapper);

        createTeamApplicationMapper.update(teamInfo,wrapper);


        Integer sid = teamManagerService.findMyTeamSid(uid);
        // 查询与社团相关的所有记录
        QueryWrapper<TeamManager> wrapper_tm = new QueryWrapper<>();
        wrapper_tm.eq("sid", sid);
        List<TeamManager> teamManagers = teamManagerMapper.selectList(wrapper_tm);

        teamManagerMapper.delete(wrapper_tm);

        QueryWrapper<SocietyTeam> wrapper_st = new QueryWrapper<>();
        wrapper_st.eq("id", sid);
        SocietyTeam societyTeam = societyTeamMapper.selectOne(wrapper_st);
        societyTeamMapper.delete(wrapper_st);

        QueryWrapper<TeamMoney> wrapper_teamMon = new QueryWrapper<>();
        wrapper_teamMon.eq("sid", sid);
        List<TeamMoney> teamMoneys = teamMoneyMapper.selectList(wrapper_teamMon);
        for(Integer i = 0;i<teamMoneys.size();++i){
            teamMoneyMapper.delete(wrapper_teamMon);
        }

        QueryWrapper<UserTeam> wrapper_userTeam = new QueryWrapper<>();
        wrapper_userTeam.eq("sid", sid);
        List<UserTeam> userTeams = userTeamMapper.selectList(wrapper_userTeam);
        for(Integer i = 0;i<userTeams.size();++i){
            userTeamMapper.delete(wrapper_userTeam);
        }

        QueryWrapper<applySociety> wrapper_as = new QueryWrapper<>();
        wrapper_as.eq("sid", sid);
        List<applySociety> applySocieties = applySocietyMapper.selectList(wrapper_as);
        for(Integer i = 0;i<applySocieties.size();++i){
            applySocietyMapper.delete(wrapper_as);
        }

        QueryWrapper<Activity> wrapper_ac = new QueryWrapper<>();
        wrapper_ac.eq("s_id", sid);
        List<Activity> activities = activityMapper.selectList(wrapper_ac);
        for(Integer i = 0;i<activities.size();++i){
            activityMapper.delete(wrapper_ac);
        }

// 执行删除操作
        return true;
    }

}
