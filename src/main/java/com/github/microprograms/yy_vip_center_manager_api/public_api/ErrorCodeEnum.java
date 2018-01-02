package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_api_runtime.model.ResponseCode;

public enum ErrorCodeEnum implements ResponseCode {

    /**Token已失效，请重新登录*/
    invalid_token(1010, "Token已失效，请重新登录"), /**没有字段需要更新，请填写需要更新的字段*/
    no_fields_need_to_be_updated(1011, "没有字段需要更新，请填写需要更新的字段"), /**账号和密码不匹配*/
    invalid_password(1012, "账号和密码不匹配"), /**手机号已被注册*/
    phone_already_registered(1013, "手机号已被注册"), /**验证码错误*/
    invalid_verification_code(1014, "验证码错误");

    private ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    public int getCode() {
        return code;
    }

    private final String message;

    public String getMessage() {
        return message;
    }
}
