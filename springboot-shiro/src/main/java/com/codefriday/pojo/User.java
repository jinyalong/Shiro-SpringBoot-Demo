package com.codefriday.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author codefriday
 * @data 2021/4/5
 */
@Data
public class User implements Serializable {
    private int id;
    private String name;
    private String password;
    private String perms;
}
