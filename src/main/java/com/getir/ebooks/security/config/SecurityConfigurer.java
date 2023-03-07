package com.getir.ebooks.security.config;

import com.getir.ebooks.security.jwt.JwtRequestFilter;
import com.getir.ebooks.security.service.AuthenticationProviderService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final AuthenticationProviderService authenticationProvider;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfigurer(AuthenticationProviderService authenticationProvider, JwtRequestFilter jwtRequestFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(
                        "/users/authenticate",
                        "/users/createdumpusers",
                        "/customer/register",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        security.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
