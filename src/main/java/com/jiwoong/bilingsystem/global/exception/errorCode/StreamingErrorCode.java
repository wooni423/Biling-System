package com.jiwoong.bilingsystem.global.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum StreamingErrorCode implements ErrorCode {

    NOT_FOUND_VIDEO(HttpStatus.NOT_FOUND, "비디오를 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
