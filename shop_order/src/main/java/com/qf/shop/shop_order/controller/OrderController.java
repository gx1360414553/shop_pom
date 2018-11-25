package com.qf.shop.shop_order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Address;
import com.qf.entity.Cart;
import com.qf.entity.Orders;
import com.qf.entity.User;
import com.qf.service.IAddressService;
import com.qf.service.ICartService;
import com.qf.service.IOrderService;
import com.qf.util.IsLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Reference
    private IOrderService orderService;
    @Reference
    private IAddressService addressService;
    @Reference
    private ICartService cartService;
    /**
     * 编辑订单 - 必须登录，如果未登录则跳转到登录页面，
     * @return
     */
    @IsLogin(tologin = true)
    @RequestMapping("/orderedit")
    public String eidtOrder(Integer[] gid, User user, Model model){
        System.out.println("编辑订单：" + Arrays.toString(gid));
        System.out.println("登录的用户信息：" + user);

        List<Cart> cartlist = cartService.queryCartByGids(gid, user.getId());
        List<Address> addresses = addressService.queryByUid(user.getId());
        model.addAttribute("carts",cartlist);
        model.addAttribute("address",addresses);

        return "orderedit";
    }
    @IsLogin
    @RequestMapping("/addaddress")
    @ResponseBody
    public Address addAddress(Address address, User user){
        address.setUid(user.getId());
        addressService.addAddress(address);
        System.out.println(address);
        return address;
    }
    @IsLogin( tologin = true)
    @RequestMapping("/addorder")
    @ResponseBody
    public String addOrder(Integer[] cid, Integer aid,User user){
        System.out.println("下单的购物车id：" + Arrays.toString(cid));
        System.out.println("收货地址id：" + aid);
        String orderid = null;
        try {
            orderid = orderService.addOrderAndOrderDetils(cid, aid, user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderid;
    }
    @IsLogin( tologin = true)
    @RequestMapping("orderlist")
    public String orderlist(User user, Model model){
        List<Orders> orders = orderService.queryByUid(user.getId());
        model.addAttribute("orders",orders);
        return "orderlist";
    }
}
