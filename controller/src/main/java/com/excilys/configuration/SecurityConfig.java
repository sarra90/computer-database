package com.excilys.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

import com.excilys.service.impl.UserServiceDetails;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.excilys.service")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceDetails userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public DigestAuthenticationEntryPoint digestEntryPoint() {
        DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new DigestAuthenticationEntryPoint();
        digestAuthenticationEntryPoint.setKey("uniqueAndSecret");
        digestAuthenticationEntryPoint.setRealmName("Digest WF Realm");
        return digestAuthenticationEntryPoint;
    }

    @Bean
    public DigestAuthenticationFilter digestFilter(DigestAuthenticationEntryPoint digestAuthenticationEntryPoint) {
        DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
        filter.setAuthenticationEntryPoint(digestEntryPoint());
        filter.setUserDetailsService(userDetailsService);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/ressources/**", "/js/**", "/static/fonts/**", "/static/css/**","/register").permitAll()
        .antMatchers("/dashboard").hasAnyAuthority("USER","ADMIN")        
        .antMatchers("/dashboard*","/addcomputer","/editComputer","/deleteComputer","/listUser","/computer*","/find*").hasAuthority("ADMIN")
                
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll().defaultSuccessUrl("/dashboard").failureUrl("/login?error")
                .and()
                    .logout().logoutSuccessUrl("/login?logout")
                    .and()
                    .exceptionHandling().accessDeniedPage("/403");
    }
    
}
