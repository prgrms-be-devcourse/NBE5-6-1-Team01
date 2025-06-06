package com.grepp.nbe561team01.infra.error.exceptions;

import com.grepp.nbe561team01.infra.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonException extends RuntimeException {

    private final ResponseCode code;

    public CommonException(ResponseCode code) {
        this.code = code;
    }

    public CommonException(ResponseCode code, Exception e) {
        this.code = code;
        log.error(e.getMessage(), e);
    }

    public ResponseCode code() {
        return code;
    }
}