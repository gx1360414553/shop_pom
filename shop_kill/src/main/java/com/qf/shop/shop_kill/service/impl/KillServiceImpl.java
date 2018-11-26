package com.qf.shop.shop_kill.service.impl;

import com.qf.entity.Kill;
import com.qf.shop.shop_kill.dao.IKillDao;
import com.qf.shop.shop_kill.service.IKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KillServiceImpl  implements IKillService {

    @Autowired
    private IKillDao killDao;
    @Override
    public Kill queryKillInfo(Integer gid) {
        return killDao.queryKillInfo(gid);
    }

    @Override
    public int miaosha(Integer gid, Integer number, Integer uid) {
        return 0;
    }
}
