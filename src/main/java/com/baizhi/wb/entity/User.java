package com.baizhi.wb.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @Excel(name = "ID")
    private String id;
    @Column(name="nick_name")
    @Excel(name = "用户名")
    private String nickName;
    @Excel(name = "手机号")
    private String phone;
    @Column(name = "pic_img")
    @Excel(name = "头像",type=2)
    private String picImg;
    @Excel(name = "个人简介")
    private String brief;
    @Excel(name = "学分")
    private Double score;
    @Column(name = "create_date")
    @Excel(name = "创建日期",format = "yyyy年MM月dd日",width = 20)
    private Date createDate;
    @Excel(name = "状态")
    private Integer status;
}