package com.springawsbook.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // final이 있는 필드에다가 생성자에서 값 넣어줌
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
