package com.demo.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
    {
        @Autowired
        private ImaniaUserDetailsService imaniaUserDetailsService;
        @Autowired
        private JwtRequestFilter jwtRequestFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
//                    .antMatchers("/**").permitAll()
                    .antMatchers("/94mart/offer/admin**","/94mart/admin/**","/94mart/report-offer/admin**","/94mart/red-list/admin**","94mart/user/admin**").hasRole("ADMIN")
                    .antMatchers("/94mart/location/**").hasRole("USER")
//                    .antMatchers("/v3/api-docs", "/configuration/ui","/swagger-ui.html#/","/swagger-ui/**", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**").permitAll()
                    .antMatchers("/api/employers/sign-up/**","/api/employees/sign-up","/log-in","log-out").permitAll()

//                    .antMatchers("/api/imania/location/**").hasRole(UserRole.USER.name())

                    .anyRequest()
                    .authenticated()
//            .and().formLogin();
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(imaniaUserDetailsService).passwordEncoder(passwordEncoder());
        }
//
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder(12);
        }
//
        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }
