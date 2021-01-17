package com.demo.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
    {
        @Autowired
        private ImaniaUserDetailsService imaniaUserDetailsService;
//        @Autowired
//        private JwtRequestFilter jwtRequestFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    //.antMatchers("/api/**","/swagger-ui.html","/**").permitAll()
                    .antMatchers("/api/94mart/offer/**").hasRole("ADMIN")
                    .antMatchers("/api/94mart/location/**").hasRole("USER")
                    .antMatchers("/v3/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**").permitAll()
                    .antMatchers("/api/employers/sign-up/**","/api/employees/sign-up","/swagger-ui/**","/log-in/","log-out","/swagger-ui.html#/").permitAll()

//                    .antMatchers("/api/imania/location/**").hasRole(UserRole.USER.name())

                    .anyRequest()
                    .authenticated()
            .and().formLogin();
//                    .and()
//                    .sessionManagement();
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
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
