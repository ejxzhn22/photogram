package com.cos.photogramstart.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentDto {

	@NotBlank // 빈값이거나 null체크 , 빈공백 까지
	private String content;
	//@NotEmpty //빈값이거나 null체크
	@NotNull
	private Integer imageId;
	
	//toEntity가 필요 없다
}
