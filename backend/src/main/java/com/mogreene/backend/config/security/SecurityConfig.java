package com.mogreene.backend.config.security;

import com.mogreene.backend.jwt.JwtAccessDeniedHandler;
import com.mogreene.backend.jwt.JwtAuthenticationEntryPoint;
import com.mogreene.backend.jwt.JwtFilter;
import com.mogreene.backend.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @name : SecurityConfig
 * @author : Mo-Greene
 * @date : 2023/07/05
 * Security Configuration
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    /**
     * 비밀번호 복호화
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * web,favicon 예외
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers(
                        "/favicon.ico"
                );
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //토큰 사용하기에 csrf 사용x
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                //h2 console 설정
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                //세션 사용하지 않기 때문에 STATELESS 지정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //토큰이 없는 상태에서 요청하는 api permitAll
                .and()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers(
                        HttpMethod.GET, "/**").permitAll()
                .anyRequest().authenticated()

                //JwtFilter 적용 => Username, Password 인증 전 Jwt filter 실행
                .and()
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
