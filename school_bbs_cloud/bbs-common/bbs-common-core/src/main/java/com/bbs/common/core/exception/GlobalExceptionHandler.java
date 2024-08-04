package com.bbs.common.core.exception;

import com.bbs.common.core.constant.HttpStatus;
import com.bbs.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BizException.class)
    public R runtimeException(BizException e) {
        return R.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public R runtimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return R.fail(HttpStatus.ERROR, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R bodyValidException(MethodArgumentNotValidException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        return R.fail(HttpStatus.BAD_REQUEST, error.getDefaultMessage());
    }

    @ExceptionHandler(ReqPathException.class)
    public R pathValidException(ReqPathException e) {
        return R.fail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
