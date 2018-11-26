package com.qf.shop.shop_kill.controller;

import com.qf.entity.Kill;
import com.qf.shop.shop_kill.service.IKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kill")
public class KillController {

    @Autowired
    private IKillService killService;

    @RequestMapping("/queryinfo")
    public String queryKillInfo(Integer gid, Model model){
        Kill kill = killService.queryKillInfo(gid);
        System.out.println("秒杀商品信息。。。。。。。。");
        model.addAttribute("kill",kill);
        return "miaoshainfo";
    }
}
