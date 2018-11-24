package com.qf.shop.shop_service_impl.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.qf.entity.Cart;
import com.qf.entity.Goods;
import com.qf.entity.User;
import com.qf.service.ICartService;
import com.qf.service.IGoodsService;
import com.qf.shop.dao.ICartDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartDao cartDao;
    @Autowired
    private IGoodsService goodsService;
    @Override
    public List<Cart> queryAllByUid(Integer uid) {

        return cartDao.queryAllByUid(uid);
    }

    @Override
    public int addCart(Cart cart) {
        return cartDao.addCart(cart);
    }

    @Override
    public int deleteCart(Integer id) {
        return cartDao.deleteCart(id);
    }

    @Override
    public int deleteAllCart(Integer uid) {
        return cartDao.deleteAllCart(uid);
    }

    @Override
    public List<Cart> getcarts(User user, String carts){
        List<Cart> cartlist = null;
        if(user != null){
            cartlist = cartDao.queryAllByUid(user.getId());
        } else {
            TypeToken<List<Cart>> tt = new TypeToken<List<Cart>>() {};
            cartlist =  new Gson().fromJson(carts,tt.getType());
        }
        if(cartlist != null){
            for (int i = 0; i < cartlist.size(); i++) {
                Goods goods = goodsService.queryById(cartlist.get(i).getGid());
                cartlist.get(i).setGoods(goods);
            }
        }
        return cartlist;
    }

    @Override
    public List<Cart> queryCartByGids(Integer[] gid, Integer uid) {
        List<Cart> carts = cartDao.queryCartByGids(gid, uid);
        for (int i = 0; i < carts.size(); i++) {
            Goods goods = goodsService.queryById(carts.get(i).getGid());
            carts.get(i).setGoods(goods);
        }
        return carts;
    }
}
