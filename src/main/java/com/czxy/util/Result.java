package com.czxy.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;

@Data
@ApiModel(value = "全局统一返回结果")
public class Result {
    @ApiModelProperty(value = "是否成功")
    private boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private HashMap<String,Object> data = new HashMap<>();

    //当前的result不对外提供公开的构造方法，而是提供静态方法，在静态方法中创建result对象
    private Result(){}

    /**
     * 成功调用的方法
     * @return
     */
    public static Result ok(){
        Result r = new Result();//调用自己私有的构造方法
        r.setSuccess(true);
        r.setCode(ResultCode.OK);
        r.setMessage("成功");
        return r;
    }

    /**
     * 失败调用的方法
     * @return
     */
    public static Result error(){
        Result r = new Result();//调用自己私有的构造方法
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    /**
     * 使用者调用ok方法或者error方法之后，如果需要修改message信息，那么可以继续调用这个方法，修改message信息
     * @param message
     * @return
     */
    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(HashMap<String,Object> map){
        this.setData(map);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key,value);
        return this;
    }
}
