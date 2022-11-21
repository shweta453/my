package com.mycrudapi.my.enumeration;

public enum ResponseCode {

    EMPTY(0),
    DAO_DATABASE(1),
    SUCCESS(6),
    FAIL(7), ERROR(8);

    int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
