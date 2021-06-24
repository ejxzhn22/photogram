package com.cos.photogramstart.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SubScribeApiController {
	
	private final SubscribeService subscribeService;
	
	@PostMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principal, @PathVariable int toUserId) {
		subscribeService.구독하기(principal.getUser().getId(),toUserId);
		return new ResponseEntity<>(new CMRespDto<>(1,"구독하기 성공",null),HttpStatus.OK);
	}
	
	@DeleteMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?>unSubscribe(@AuthenticationPrincipal PrincipalDetails principal, @PathVariable int toUserId) {
		subscribeService.구독취소하기(principal.getUser().getId(),toUserId);
		return new ResponseEntity<>(new CMRespDto<>(1,"구독하기 성공",null),HttpStatus.OK);
	}
}
