package com.exam.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 빈(Bean) 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

/* 시큐리티의 기본 설정이니 이해가 안 돼도 일단 묶어서 사용하기 */
@Configuration // IoC
@EnableWebSecurity // 시큐리티 필터가 등록 : 스프링 시큐리티의 설정
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 시 권한/인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean // Spring이 관리
	public BCryptPasswordEncoder endodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화 (테스트 시 사용하면 좋음)
			.authorizeRequests()
				.antMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**") // 해당 경로는
				.permitAll()	// 허용
				.anyRequest()	// 그외의 경로는
				.authenticated()	// 인증 필요
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");
	}
}
