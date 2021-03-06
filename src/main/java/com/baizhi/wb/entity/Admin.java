package com.baizhi.wb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {
    @Id
    private String id;

    private String username;

    private String password;
}