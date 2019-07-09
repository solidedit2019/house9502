package com.team.house.util;

/**
 * @author 王建兵
 * @Classname UserCondition
 * @Description TODO
 * @Date 2019/6/29 9:41
 * @Created by Administrator
 */
//查询条件     继承page类只为添加页码和页大小
public class UserCondition  extends  Page{
    //类型全用包装类、封装类
    private String name;  //名称
    private String telephone;  //电话
    private Integer startAge; //开始年龄
    private Integer endAge;  //结束年龄

    /*添加分页的相关属性
    * private Integer page;
    private Integer rows;
    setter和getter方法
    * */


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }
}
