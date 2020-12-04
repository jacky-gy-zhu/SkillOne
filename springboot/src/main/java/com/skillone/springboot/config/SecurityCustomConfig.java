package com.skillone.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityCustomConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityCustomConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // logout
        http.logout().logoutUrl("/security/logout").logoutSuccessUrl("/security/hello").permitAll();

        // exception page
        http.exceptionHandling().accessDeniedPage("/403.html");

        // auth
        http.formLogin()    // 自定义编写登陆页面
                .loginPage("/login.html")                           // 登陆页面设置
                .loginProcessingUrl("/user/login")                  // 登陆访问的路径
                .defaultSuccessUrl("/success.html").permitAll()   // 登陆成功后，跳转路径
                .and().authorizeRequests()
                .antMatchers("/", "/security/hello", "/security/login").permitAll() // 设置哪些路径可以直接访问，不需要认证
                .antMatchers("/security/index").hasAnyAuthority("admin","role2")
                .antMatchers("/security/index").hasRole("sales")
                .anyRequest().authenticated()
                .and().csrf().disable();    //关闭csrf防护

    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }
}
