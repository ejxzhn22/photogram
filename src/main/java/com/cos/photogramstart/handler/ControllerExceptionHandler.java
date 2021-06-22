package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice // 모든 예외를 낚아챈다
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
		//CMRespDto, Script 비교
		//1.클라이언트에게 응답할 떄는Script 좋음.
		//2.Ajax통신 - CMRespDto
		//3.Android 통신 - CMRespDto
		return Script.back(e.getErrorMap().toString());
	}
}
