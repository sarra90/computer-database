package com.excilys.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
        //in local memory
     // auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
        //in data base 
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select username as principal,password as credentials, true from users where username=?")
        .authoritiesByUsernameQuery("select users_username as principal, roles_role from USERS_ROLES where users_username=?");
        
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

      http.authorizeRequests()
        .anyRequest().authenticated()
        .and()
            .formLogin()
                .loginPage("/login").permitAll().defaultSuccessUrl("/dashboard").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")                
            .and()
                .logout().logoutSuccessUrl("/login?logout"); 
        
    }
                  

    
}
