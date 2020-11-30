package com.baizhi.wb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Table(name = "logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logs implements Serializable {
    @Id
    private String id;

    private String operator;

    private String operation;
    @Column(name = "date_time")
    private Date dateTime;

    private String res;

}