package com.qf.shop.shop_service_impl.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.ResultData;
import com.qf.entity.User;
import com.qf.service.IUserService;
import com.qf.shop.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public ResultData<User> queryByUserName(String username, String password) {
        int code;
        String msg;
        User user = userDao.queryByUserName(username);
        if(user != null){
            if (user.getPassword().equals(password)){
                code = 0;
                msg = "succ";
            }else {
                code = 1;
                msg = "密码错误";
                user = null;
            }
        }else {
            code = 2;
            msg = "账号错误";
        }
        ResultData<User> userResultData = new ResultData<>(code, msg, user);
        return userResultData;
    }
}
