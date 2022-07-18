package com.example.users.security;

import com.example.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private Environment environment;
    private BCryptPasswordEncoder passwordEncoder;
    private UsersService usersService;

    @Autowired
    public WebSecurity(Environment environment, BCryptPasswordEncoder passwordEncoder, UsersService usersService) {
        this.environment = environment;
        this.passwordEncoder = passwordEncoder;
        this.usersService = usersService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"))
                .antMatchers(HttpMethod.GET,"/actuator/health").hasIpAddress(environment.getProperty("gateway.ip"))
                .antMatchers(HttpMethod.GET,"/actuator/circuitbreakerevents").hasIpAddress(environment.getProperty("gateway.ip"))
                .and()
                .addFilter(getAuthenticationfilter());
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(passwordEncoder);
    }

    public Authenticationfilter getAuthenticationfilter() throws Exception {
        Authenticationfilter authenticationfilter = new Authenticationfilter(environment, usersService, authenticationManager());
        authenticationfilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
        return authenticationfilter;
    }
}
