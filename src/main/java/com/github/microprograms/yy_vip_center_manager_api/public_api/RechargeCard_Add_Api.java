package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_entity_definition_runtime.annotation.Comment;
import com.github.microprograms.micro_api_runtime.annotation.MicroApiAnnotation;
import com.github.microprograms.micro_api_runtime.model.Operator;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_api_runtime.exception.MicroApiExecuteException;
import com.github.microprograms.micro_api_runtime.enums.MicroApiReserveResponseCodeEnum;
import com.github.microprograms.yy_vip_center_manager_api.utils.Commons;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Required;

@Comment(value = "充值卡 - 单个添加")
@MicroApiAnnotation(type = "read", version = "v0.0.2")
public class RechargeCard_Add_Api {

    private static Operator<?> getOperator(Req req) throws Exception {
        return Commons.buildOperator(RechargeCard_Add_Api.class, req.getToken());
    }

    private static RechargeCard buildRechargeCard(Req req) {
        RechargeCard rechargeCard = new RechargeCard();
        return rechargeCard;
    }

    private static void core(Req req, Response resp) throws Exception {
        Operator<?> operator = getOperator(req);
        if (operator == null)
            throw new MicroApiExecuteException(MicroApiReserveResponseCodeEnum.unknown_operator_exception);
        if (operator.isPermissionDenied())
            throw new MicroApiExecuteException(MicroApiReserveResponseCodeEnum.permission_denied_exception);
        MicroOss.insertObject(buildRechargeCard(req));
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getRawPasswordSeriesCode(), "rawPasswordSeriesCode");
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

        @Comment(value = "原始密码序列编码串")
        @Required(value = true)
        private String rawPasswordSeriesCode;

        public String getRawPasswordSeriesCode() {
            return rawPasswordSeriesCode;
        }

        public void setRawPasswordSeriesCode(String rawPasswordSeriesCode) {
            this.rawPasswordSeriesCode = rawPasswordSeriesCode;
        }
    }
}
