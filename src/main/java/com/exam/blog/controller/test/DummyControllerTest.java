package com.exam.blog.controller.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.blog.model.RoleType;
import com.exam.blog.model.User;
import com.exam.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired // DI 의존 주입
	private UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	//public String join(String userName, String password, String email) { // key = value 형태   (form 태그)
	public String join(User user) {
		System.out.println("id: "+user.getId());
		System.out.println("userName: "+user.getUserName());
		System.out.println("password: "+user.getPassword());
		System.out.println("email: "+user.getEmail());
		System.out.println("role: "+user.getRole());
		System.out.println("joinDate: "+user.getJoinDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
	
	//localhost:8088/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4를 요청하면 결과가 null이 되는 문제 >> Optional로 User객체를 감싸서 가져오고 null인지 판단
		
		// 빈 객체 반환
		/*User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
			// Supplier 인터페이스
			@Override
			public User get() {
				// TODO Auto-generated method stub
				return new User();
			}
		});*/
		
		// 잘못된 인수
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() { // 람다식으로 대체 가능
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다.  : " +id);
			}
		});
		
		// User = 자바 오브젝트
		// 웹브라우저가 이해할 수 있는 데이터로 변환 (json)
		// 스프링부트에서는 MessageConverter가 응답 시 자동으로 작동 --> Jackson 라이브러리 호출해서 json 타입으로 변환하여 브라우저에 전달
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	//페이지 당 2개씩 페이징
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> paging = userRepository.findAll(pageable); 
		List<User> users = paging.getContent(); // getContent() 페이징 정보를 생략하고 반환 타입이 Page가 아니라 객체 리스트
		
		// Page의 isLast() isFirst() 등으로 페이징 처리 가능
		return users;
	}
	
	@Transactional // 트랜잭션이 실행되고 메서드가 종료될 때 자동 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : "+ id);
		System.out.println("password : "+ requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow( ()-> { // User 영속화
			return new IllegalArgumentException("수정 실패 : 존재하지 않는 아이디");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// save() id를 전달하지 않으면 insert
		//      ''    를 전달하면 update
		//      '' 를 전달했는데 해당 id가 없으면 insert
		
		// save()는 id가 이미 존재하면 업데이트해주는 기능이 있으나 부분 변경 시 없는 데이터를 null로 만듦 >> 기존 데이터를 갖는 인스턴스에 변경 부분을 반영
		//userRepository.save(user);
		
		//더티 체킹 : 영속화된 객체와 비교해서 변경을 감지하고 update 수행
		return null;
	}
}
