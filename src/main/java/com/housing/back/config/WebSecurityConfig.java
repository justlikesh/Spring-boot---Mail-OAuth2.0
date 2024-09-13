package com.housing.back.config;

import com.housing.back.filter.JwtAuthenticationFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;


@Configurable              // 빈을 등록할수 있도록 만들어주는것
@Configuration             //WebSecurityConfig 라는 클래스가 빈 메서드를 추가하고잇다
@EnableWebSecurity         //web security 에 관한 설정
@RequiredArgsConstructor   // 의존성 주입 및 제어의 역전
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .cors(cors -> cors  // 람다식
                    .configurationSource(corsConfigurationSource())
            )
            .csrf(CsrfConfigurer::disable) //사용하지 않겠다
                .httpBasic(HttpBasicConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement  //세션 처리를 어떻게 할꺼냐
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 상태 유지하지 않겠다
            )
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/","/api/v1/auth/**").permitAll()  //이 패턴에 대해서는 모두 허용하겠다
                        .requestMatchers("/api/v1/user/**").hasRole("USER")
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated() // 나머지 모든 request 에대해서 인증을하도록하겠다
                ) //특정 권한이 있는 사람만 작업할것인지 에대한 작업
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new FailedAuthenticationEntryPoint()))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); //이전에 필터는 뭐로 등록할거냐

        return httpSecurity.build();

    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*"); // * 을 에스터리스크 라고 하는데 모든 메서드에 대해서 허용하겠다는 뜻
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/vi/**", corsConfiguration);

        return source;
    }
}

class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException ) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); //권한없음
        // { "code" : "NP", "message" : "No Permission." }
        response.getWriter().write("{ \"code\" : \"NP\", \"message\" : \"No Permission.\" }");
    }
}