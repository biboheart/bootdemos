package com.biboheart.dwechat.wechat.exception;

public class WxErrorException extends Exception {
    private WxError error;

    public WxErrorException(WxError error) {
        super(error.toString());
        this.error = error;
    }

    public WxErrorException(WxError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public WxError getError() {
        return this.error;
    }

}
