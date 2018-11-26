package com.qf.shop.shop_kill.dao;

import com.qf.entity.Kill;
import com.qf.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IKillDao {

    Kill queryKillInfo(Integer gid);

    int updateSave(@Param("id") Integer id,@Param("number") Integer number,@Param("version") Integer version);

    int addOrder(Orders orders);
}
