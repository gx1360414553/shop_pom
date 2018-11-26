package com.qf.shop.shop_kill.service;

import com.qf.entity.Kill;

public interface IKillService {

   Kill queryKillInfo(Integer gid);

   int miaosha(Integer gid, Integer number, Integer uid);

}
