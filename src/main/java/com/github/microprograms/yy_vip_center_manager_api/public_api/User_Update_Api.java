package com.github.microprograms.yy_vip_center_manager_api.public_api;

import java.util.ArrayList;
import java.util.List;
import com.github.microprograms.micro_api_runtime.annotation.MicroApi;
import com.github.microprograms.micro_api_runtime.enums.MicroApiReserveResponseCodeEnum;
import com.github.microprograms.micro_api_runtime.exception.MicroApiPassthroughException;
import com.github.microprograms.micro_api_runtime.model.Operator;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_nested_data_model_runtime.Comment;
import com.github.microprograms.micro_nested_data_model_runtime.Required;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_oss_core.exception.MicroOssException;
import com.github.microprograms.micro_oss_core.model.Field;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

@MicroApi(comment = "用户 - 更新", type = "read", version = "v0.0.20")
public class User_Update_Api {

    private static Operator<?> getOperator(Req req) throws MicroOssException {
        return Fn.buildOperator(User_Update_Api.class, req.getToken());
    }

    private static Condition buildFinalCondition(Req req) {
        return Condition.build("id=", req.getUserId());
    }

    private static List<Field> buildFieldsToUpdate(Req req) {
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("level", req.getLevel()));
        fields.add(new Field("isDisable", req.getIsDisable()));
        return Fn.buildFieldsIgnoreBlank(fields);
    }

    private static void core(Req req, Response resp) throws Exception {
        Operator<?> operator = getOperator(req);
        if (operator == null)
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.unknown_operator_exception);
        if (operator.isPermissionDenied())
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.permission_denied_exception);
        Condition finalCondition = buildFinalCondition(req);
        List<Field> fields = buildFieldsToUpdate(req);
        MicroOss.updateObject(User.class, fields, finalCondition);
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getUserId(), "userId");
        Response resp = new Response();
        core(req, resp);
        return resp;
    }

    public static class Req extends Request {

        @Comment(value = "Token")
        @Required(value = true)
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Comment(value = "用户ID")
        @Required(value = true)
        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Comment(value = "等级(0普通用户,1一级代理,2二级代理,3三级代理)")
        @Required(value = false)
        private Integer level;

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        @Comment(value = "是否禁用(0否1是)")
        @Required(value = false)
        private Integer isDisable;

        public Integer getIsDisable() {
            return isDisable;
        }

        public void setIsDisable(Integer isDisable) {
            this.isDisable = isDisable;
        }
    }
}
