package com.qf.shop.shop_cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.qf.entity.Cart;
import com.qf.entity.User;
import com.qf.service.ICartService;
import com.qf.service.IGoodsService;
import com.qf.util.Constact;
import com.qf.util.IsLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Reference
    private ICartService cartService;
    @Reference
    private IGoodsService goodsService;
    @IsLogin
    @RequestMapping("/addcart")
    public String addCart(Cart cart,
                          User user,
                          HttpServletResponse response,
                          @CookieValue(value = "cart_token",required = false)String carts) {
        System.out.println(user);
        System.out.println(cart);
        if (user != null) {
            cart.setUid(user.getId());
            cartService.addCart(cart);
        } else {
            List<Cart> cartlist = null;
            if(carts != null){
                TypeToken<List<Cart>> tt = new TypeToken<List<Cart>>(){};
                cartlist =  new Gson().fromJson(carts,tt.getType());
                cartlist.add(cart);
            }else{
                cartlist =  Collections.singletonList(cart);
            }
            System.out.println("添加购物车的信息：" + cartlist);

            String json = new Gson().toJson(cartlist);
            try {
                json = URLEncoder.encode(json,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Cookie cookie = new Cookie(Constact.CART_TOKEN, json);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            cookie.setPath("/");
            System.out.println("cookie..................");
            response.addCookie(cookie);
        }

        return "addsucc";
    }

    /**
     * 购物车合并
     * @return
     */
    @RequestMapping("/hebing")
    @ResponseBody
    public String heBing(Integer uid, @CookieValue(value="cart_token", required = false) String carts){
        //carts -> List<Cart>
        TypeToken<List<Cart>> tt = new TypeToken<List<Cart>>(){};
        List<Cart> cartList = new Gson().fromJson(carts, tt.getType());

        for(Cart cart : cartList){
            cart.setUid(uid);
            cartService.addCart(cart);
        }

        return "succ";
    }

    /**
     * ajax获取购物车
     */
    @IsLogin
    @RequestMapping("/getcarts")
    @ResponseBody
    public String getCarts(@CookieValue(value = "cart_token", required = false)String carts,
                           User user){
        List<Cart> cartlist = cartService.getcarts(user,carts);
            return "getCart(" + new Gson().toJson(cartlist) + ")";
    }

    /**
     * 去购物列表
     * @param carts
     * @param user
     * @param model
     * @return
     */
    @IsLogin
    @RequestMapping("/cartlist")
    public String cartlist(@CookieValue(value = "cart_token", required = false)String carts,
                           User user,
                           Model model){
        List<Cart> cartlist = cartService.getcarts(user,carts);
        model.addAttribute("carts",cartlist);
        return "cartlist";
    }

}
