package com.qf.shop.shop_service_impl.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import com.qf.shop.dao.IGoodsDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;

    @Override
    public List<Goods> queryAll() {
        System.out.println("调用了service实现类！！！");
       // Goods goods = new Goods(1,"你号","xinx",1.1,7,1.1,4.4,"dfasdas");
      //  List<Goods> goods1 = Collections.singletonList(goods);
        List<Goods> goods = goodsDao.queryAll();
        for (int i = 0; i < goods.size(); i++) {
            System.out.println(goods.get(i));
        }
        return goods;
    }
}
