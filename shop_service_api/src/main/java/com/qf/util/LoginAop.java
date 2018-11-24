package com.qf.util;

import com.qf.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
public class LoginAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate redisTemplate;

    @Around("execution(* *..*Controller.*(..)) && @annotation(com.qf.util.IsLogin)")
    public Object isLogin(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("----->aop");
        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        IsLogin isLogin = method.getAnnotation(IsLogin.class);

        String token = null;

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constact.LOGIN_TOKEN)){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        User user = null;
        if (token != null){
            user = (User) redisTemplate.opsForValue().get(token);
        }
        if (user == null &&isLogin.tologin()){
            String returnUrl = request.getRequestURL() + "?" + request.getQueryString();
            returnUrl = returnUrl.replace("&", "*");
            System.out.println(returnUrl);
            return "redirect:http://localhost:8084/sso/tologin?returnUrl=" + returnUrl;
        }

        Object[] args = proceedingJoinPoint.getArgs();
//        if (user == null && isLogin.tologin()){
//            for (int i = 0; i < args.length; i++) {
//               if(args[i] != null && args[i].getClass() == User.class) {
//                   args[i] = user;
//               }
//            }
//        }
        //已经登录
        //获得目标方法的实参列表
        for(int i = 0; i < args.length; i++){
            if(args[i] != null && args[i].getClass()==User.class){
                args[i] = user;
            }
        }
        Object obj = null;
        try {
            obj = proceedingJoinPoint.proceed(args);//放行目标方法
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
}
