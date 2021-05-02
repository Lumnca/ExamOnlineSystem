package app.config;

import app.datamodel.security.Authority;
import app.datamodel.security.UserLogin;
import app.server.UserServer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
     private static final String HOST = "http://47.106.254.86:100/";
    @Autowired
    UserServer userServer;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userServer);
    }
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasRole("admin")
                .antMatchers("/teacher/**")
                .hasRole("teacher")
                .antMatchers("/student/**")
                .hasRole("student")
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
                            , Authentication auth)throws IOException {
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        PrintWriter out = response.getWriter();
                        Object principal = auth.getPrincipal();

                        UserLogin userLogin = JSONObject.parseObject(JSONObject.toJSONString(principal),UserLogin.class);
                        //request.getSession().setAttribute("userId",userLogin.getId());
                        userLogin.setTimeout(new Date().getTime());
                        String info = JSONObject.toJSONString(userLogin);
                        Cookie cookie = new Cookie("_",userLogin.getUsername()+"_");
                        cookie.setMaxAge(120);
                        response.addCookie(cookie);
                        System.out.println(info);
                        for (Authority a:userLogin.getAuthorities()
                             ) {
                            if(a.getAuthority().equals("ROLE_admin")){
                                out.print("<script type='text/javascript'>window.localStorage.setItem('_user','"+info+"');alert('管理员账号登录成功');window.location.href='"+HOST+"teacher/user.html';</script>");
                            }
                            else if(a.getAuthority().equals("ROLE_teacher")){
                                out.print("<script type='text/javascript'>window.localStorage.setItem('_user','"+info+"');alert('教师账号登录成功');window.location.href='"+HOST+"teacher/user.html';</script>");
                            }
                            else if(a.getAuthority().equals("ROLE_student")){
                                out.print("<script type='text/javascript'>window.localStorage.setItem('_user','"+info+"');alert('学生账号登录成功');window.location.href='"+HOST+"student/user.html';</script>");
                            }
                        }
                    }
                    //登录失败处理
                }).failureHandler(new AuthenticationFailureHandler(){
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                response.setStatus(401);
                Map<String,Object> map = new HashMap<>();
                map.put("status",401);
                if(e instanceof LockedException){
                    map.put("msg","账户被锁定，登录失败");
                }
                else  if(e instanceof BadCredentialsException){
                    map.put("msg","用户名或密码错误，登录失败");
                }
                else  if(e instanceof DisabledException){
                    map.put("msg","账户被禁用，登录失败");
                }
                else  if(e instanceof AccountExpiredException){
                    map.put("msg","账户已过期，登录失败");
                }
                else  if(e instanceof CredentialsExpiredException){
                    map.put("msg","密码已过期，登录失败");
                }
                else {
                    map.put("msg","未知错误，登录失败，请联系管理人员");
                }
                out.print("<script type='text/javascript'>alert('"+ JSONObject.toJSONString(map)+"');window.location.href='"+HOST+"';</script>");
            }
        }).and()
                .logout()
                .logoutUrl("/logout")             //注销接口
                .clearAuthentication(true)        //清除认证信息
                .invalidateHttpSession(true)      //是否使Session失效
                .addLogoutHandler(new LogoutHandler() {        //配置登出处理器
                    @Override
                    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

                    }
                }).logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            //处理器
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                //重定向，也可以进行其他操作
                httpServletResponse.sendRedirect(HOST);
            }
        }).permitAll()
                .and().csrf().disable();
    }
}