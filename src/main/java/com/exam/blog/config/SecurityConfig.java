package com.exam.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.blog.config.auth.PrincipalDetailService;

// 빈(Bean) 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

/* 시큐리티의 기본 설정이니 이해가 안 돼도 일단 묶어서 사용하기 */
@Configuration // IoC
@EnableWebSecurity // 시큐리티 필터가 등록 : 스프링 시큐리티의 설정
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 시 권한/인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // Spring이 관리
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인 해줄 때, 가로챈 password가 뭐로 해시되었는지 알아야 함!
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화 (테스트 시 사용하면 좋음)  >>  요청 시 csrf 토큰이 없으면 차단됨. 테스트 후에 삭제
			.authorizeRequests()
				.antMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**") // 해당 경로는
				.permitAll()	// 허용
				.anyRequest()	// 그외의 경로는
				.authenticated()	// 인증 필요
			.and()
				.formLogin()
				.loginPage("/auth/loginForm") // 인증이 필요한 모든 페이지에 대한 요청은 로그인 폼으로.
				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소 요청을 가로챈다 >> 대신 로그인
				.defaultSuccessUrl("/") // 요청이 완료되면
				.failureUrl("/fail") // 실패하면
				;
	}
}

/* csrf, xss

csrf : 사이트 간 요청 위조 > csrf 토큰 세션을 만들고 클라이언트에서 요청 시 같이 보내도록 함.
xss : 사이트 간 스크립팅 > lucy(필터) 라이브러리  사용 

*/