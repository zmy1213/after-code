package com.czxy.teamMoney.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.teamMoney.pojo.TeamMoney;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.vo.TeamMoneyVo;


public interface TeamMoneyService extends IService<TeamMoney> {

    Page<TeamMoney> findCondition(TeamMoneyVo teamMoneyVo);
    Page<TeamMoney> findCondition(TeamMoneyVo teamMoneyVo,Integer uid);
    void pay(Integer id);
    Page<TeamMoney> findConditionByUid(TeamMoneyVo teamMoneyVo,Integer uid);
}
