package com.qf.service;

import com.qf.entity.ResultData;
import com.qf.entity.User;

public interface IUserService {
    ResultData<User> queryByUserName(String username, String password);
}
