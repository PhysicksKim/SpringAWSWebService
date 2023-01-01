package com.springawsbook.springboot.config.auth;

import com.springawsbook.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;


/**
 * https://velog.io/@wjddn3711/%EB%82%98%EC%9D%98-%EC%A3%BC%EB%B3%80-%EB%A7%9B%EC%A7%91%EB%93%A4%EC%9D%84-%EA%B0%84%ED%8E%B8%ED%95%98%EA%B2%8C-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%904
 * WebSecurityConfigurerAdapter 는 Deprecated 되었으므로 위 링크 방식으로 대체함
 *
 * # Differences
 * extends WebSecurityConfigurerAdapter -> Bean
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService CustomOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/","/css/**","/images/**",
                        "/js/**","h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(CustomOAuth2UserService);
        return http.build();
    }
}
