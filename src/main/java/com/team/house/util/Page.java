package com.team.house.util;

/**
 * @author 王建兵
 * @Classname Page
 * @Description TODO
 * @Date 2019/6/25 11:43
 * @Created by Administrator
 */
public class Page {  //封装分页的条件
    private Integer page=1;  //页码
    private Integer rows=3;  //页大小

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getRows() {
        return rows;
    }
}
