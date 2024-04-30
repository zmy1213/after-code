package com.czxy.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.user.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.vo.UserVo;

import java.text.ParseException;
import java.util.List;


public interface UserService extends IService<User> {
    //登录方法
    User login(User user);
    //通过id找寻
    User findById(Integer id) ;
    //修改
    Integer updateUser(User user);

    //注册
    Integer register(User user);

    //条件分页查询
    Page<User> findCondition(UserVo userVo) ;

    Page<SocietyTeam> findJoinTeam(UserVo userVo) ;


    List<User> findAllAdmin();

    List<User> findAllUser();


    Boolean userJoin(Integer uid,Integer sid);
    Boolean deletePersonFromSocial(Integer uid,Integer sid);
    Integer joinedAccount(Integer uid);
    String findUserNameById(Integer uid);

}
