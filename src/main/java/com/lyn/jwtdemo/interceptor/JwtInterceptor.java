package com.lyn.jwtdemo.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.lyn.jwtdemo.annotation.LoginToken;
import com.lyn.jwtdemo.annotation.PassToken;
import com.lyn.jwtdemo.entry.User;
import com.lyn.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * JWT token拦截器
 *
 * @author LengYouNuan
 * @create 2021-05-18 下午4:27
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod))
            return true; //映射请求的不是方法时  直接放行

        String token = request.getHeader("token"); //从请求头中拿到token

        HandlerMethod handler1 = (HandlerMethod) handler;
        Method method = handler1.getMethod(); //获取到映射的方法对象

        if (method.isAnnotationPresent(PassToken.class) && method.getAnnotation(PassToken.class).required())
            return true; //方法注解时PassToken注解时 直接放行


        //注解是需要验证token的注解时
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if (loginToken.required()) {

                //验证token
                if (token == null) {
                    throw new RuntimeException("token is null");
                }

                //获取token中的userid
                String userId = JWT.decode(token).getAudience().get(0);
                User user = userService.getUser(userId);
                if (user == null)
                    throw new RuntimeException("user is not exit");

                //验证token
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getPassWord())).build();
                verifier.verify(token);  //此处没有异常则表示验证通过
            }
        }

        return true;
    }
}
