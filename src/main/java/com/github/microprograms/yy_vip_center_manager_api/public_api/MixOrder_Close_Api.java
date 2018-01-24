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

@MicroApi(comment = "商品订单 - 标记为已处理", type = "read", version = "v0.0.8")
public class MixOrder_Close_Api {

    private static Operator<?> getOperator(Req req) throws MicroOssException {
        return Fn.buildOperator(MixOrder_Close_Api.class, req.getToken());
    }

    private static Condition buildFinalCondition(Req req) {
        return Condition.build("id=", req.getOrderId());
    }

    private static List<Field> buildFieldsToUpdate(Req req) throws MicroOssException {
        AdminUser adminUser = Fn.queryAdminUserByToken(req.getToken());
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("isDispose", 1));
        fields.add(new Field("dtDispose", System.currentTimeMillis()));
        fields.add(new Field("disposerId", adminUser.getId()));
        fields.add(new Field("disposerLoginName", adminUser.getLoginName()));
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
        MicroOss.updateObject(MixOrder.class, fields, finalCondition);
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getOrderId(), "orderId");
        Response resp = new Response();
        core(req, resp);
        return resp;
    }

    public static class Req extends Request {

        @Comment(value = "Token") @Required(value = true) private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Comment(value = "商品订单ID") @Required(value = true) private String orderId;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }
}
