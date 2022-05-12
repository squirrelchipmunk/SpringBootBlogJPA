package com.exam.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.exam.blog.model.User;

import lombok.AllArgsConstructor;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인 진행 >>
// UserDetails 타입 오브젝트를 스프링 시큐리티의 고유한 세션 저장소에 저장
@AllArgsConstructor
public class PrincipalDetail implements UserDetails{

	private User user; // 콤포지션 (객체를 품고 있는 것)

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { // 계정이 만료되지 않았는지
		return true; // 만료 안 됨
	}

	@Override
	public boolean isAccountNonLocked() { // 계정이 잠겨있지 않은지
		return true; // 잠기지 않음
	}

	@Override
	public boolean isCredentialsNonExpired() { // 비밀번호가 만료되지 않았는지
		return true; // 만료 안 됨
	}

	@Override
	public boolean isEnabled() { // 계정 활성화
		return true; // 활성화됨
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // 계정이 갖는 권한 목록을 리턴
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		/*
		collectors.add(new GrantedAuthority() {	
			@Override
			public String getAuthority() {
				return "ROLE_"+user.getRole(); // ROLE_ 꼭 넣어주기(prefix) >> ROLE_USER
			}
		});
		*/
		collectors.add( ()->{return "ROLE_"+user.getRole();} );
		return collectors;
	}
}
