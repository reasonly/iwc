package com.iworkcloud.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private String userAccount;
    private String userPassword;
    private String userEmail;
    private String userAuthority;
    private String userSalt;


}
