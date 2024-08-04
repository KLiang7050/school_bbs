package com.bbs.common.core.exception;

public class BizException extends Exception {
    private static final long serialVersionUID = 1L;
    private String msg;
    private int code;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
        this.code = 500;
        this.msg = message;
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
