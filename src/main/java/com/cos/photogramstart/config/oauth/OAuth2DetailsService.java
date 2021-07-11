package com.cos.photogramstart.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		//System.out.println("OAuth2 서비스");
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		Map<String, Object> userinfo = oauth2User.getAttributes();
		
		String username = "facebook_"+(String)userinfo.get("id");
		String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
		String email = (String) userinfo.get("email");
		String name = (String) userinfo.get("name");
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) { // 페이스북 최초 로그인
			User user = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.name(name)
					.role("ROLE_USER")
					.build();
			
			return new PrincipalDetails(userRepository.save(user), oauth2User.getAttributes());
		}else { // 이미 페이스북으로 회원가입 되있음
			return new PrincipalDetails(userEntity,oauth2User.getAttributes());
		}
		
	}
}
